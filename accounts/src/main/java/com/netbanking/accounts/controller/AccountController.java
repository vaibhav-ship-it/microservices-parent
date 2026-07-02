/**
 * 
 */
package com.netbanking.accounts.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.TreeSet;
import java.util.stream.Collectors;

import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.netbanking.accounts.entity.Account;
import com.netbanking.accounts.entity.Transaction;
import com.netbanking.accounts.entity.TransactionType;
import com.netbanking.accounts.exception.AccountNotFoundException;
import com.netbanking.accounts.model.DepositWithdrawRequestModel;
import com.netbanking.accounts.service.AccountService;
import com.netbanking.accounts.service.TransactionService;
import com.netbanking.accounts.utils.Utils;

/**
 * 
 */
@RestController
@RequestMapping("/account")
//@CrossOrigin(origins = "http://localhost:4200")
public class AccountController {

	private static final String TRANSACTION_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss:SSS";
	private static final String ZERO_TIME = "00:00:00:000";
	private static final String MIDNIGHT_TIME = "23:59:59:999";
	
	private AccountService accountService;
	private TransactionService transactionService;
	//private Account account;
	
	public AccountController(AccountService accountService,
			TransactionService transactionService) {
		this.accountService = accountService;
		this.transactionService = transactionService;
	}

	@PostMapping("/deposit")
	public String deposit(@RequestBody DepositWithdrawRequestModel model)	{
		String accountNo = model.getAccountNo();
		Optional<Account> accountOptional = accountService.findByAccountNumber(accountNo);
		if(accountOptional.isEmpty())	{
			throw new AccountNotFoundException(accountNo + "account not found");
		}
		Account account = accountOptional.get();
		long currentBalance = account.getBalance();
		long amountToDeposit = model.getAmount();
		long updatedBalance = currentBalance+amountToDeposit;
		account.setBalance(updatedBalance);
		Transaction transaction = new Transaction();
		transaction.setAmount(amountToDeposit);
		transaction.setDescription(model.getDescription());
		transaction.setTransactionTime(
				LocalDateTime.parse(LocalDateTime.now().format(DateTimeFormatter.ofPattern(TRANSACTION_TIME_FORMAT)),
						DateTimeFormatter.ofPattern(TRANSACTION_TIME_FORMAT)));
		transaction.setRefNo(new Utils().generateRandomNumbers(12));
		transaction.setTransactionType(TransactionType.CREDIT);
		transaction.setBalance(updatedBalance);
		transaction.setAccount(account);
		Transaction savedTransaction = transactionService.save(transaction);
		account.getTransactions().add(savedTransaction);
		accountService.save(account);
		return amountToDeposit + " deposited successfully, updated balance is " + updatedBalance;
	}
	
	@PostMapping("/withdraw")
	public String withdraw(@RequestBody DepositWithdrawRequestModel model)	{
		String accountNo = model.getAccountNo();
		Optional<Account> accountOptional = accountService.findByAccountNumber(accountNo);
		if(accountOptional.isEmpty())	{
			throw new AccountNotFoundException(accountNo + "account not found");
		}
		Account account = accountOptional.get();
		long currentBalance = account.getBalance();
		long amountToWithdraw = model.getAmount();
		if(currentBalance >= amountToWithdraw)	{
			long updatedBalance = currentBalance - amountToWithdraw;
			account.setBalance(updatedBalance);
			Transaction transaction = new Transaction();
			transaction.setAmount(amountToWithdraw);
			transaction.setDescription(model.getDescription());
			transaction.setTransactionTime(
					LocalDateTime.parse(LocalDateTime.now().format(DateTimeFormatter.ofPattern(TRANSACTION_TIME_FORMAT)),
							DateTimeFormatter.ofPattern(TRANSACTION_TIME_FORMAT)));
			transaction.setRefNo(new Utils().generateRandomNumbers(12));
			transaction.setTransactionType(TransactionType.DEBIT);
			transaction.setBalance(updatedBalance);
			transaction.setAccount(account);
			Transaction savedTransaction = transactionService.save(transaction);
			account.getTransactions().add(savedTransaction);
			accountService.save(account);
			return amountToWithdraw + " withdrawn successfully, updated balance is " + updatedBalance;
		} else	{
			return "Insufficient funds";
		}
	}
	
	@GetMapping("/currentBalance/{accountNo}")
	public long currentBalance(@PathVariable String accountNo)	{
		Optional<Account> accountOptional = accountService.findByAccountNumber(accountNo);
		if(accountOptional.isEmpty())	{
			throw new AccountNotFoundException(accountNo + "account not found");
		}
		Account account = accountOptional.get();
		return account.getBalance();
	}
	
	@GetMapping("/transactionHistory/{accountNo}/{startDate}/{endDate}")
	public List<Transaction> transactionHistory(@PathVariable String accountNo, @PathVariable String startDate, @PathVariable String endDate)	{
		Optional<Account> accountOptional = accountService.findByAccountNumber(accountNo);
		if(accountOptional.isEmpty())	{
			throw new AccountNotFoundException(accountNo + " account not found");
		}
		Account account = accountOptional.get();
		List<Transaction> transactions = account.getTransactions();
		LocalDateTime startTime = LocalDateTime.parse(startDate+" "+ZERO_TIME, DateTimeFormatter.ofPattern(TRANSACTION_TIME_FORMAT));
		LocalDateTime endTime = LocalDateTime.parse(endDate+" "+MIDNIGHT_TIME, DateTimeFormatter.ofPattern(TRANSACTION_TIME_FORMAT));
		List<Transaction> transactionList = transactions.stream()
		.filter(t->(t.getTransactionTime().isEqual(startTime) || t.getTransactionTime().isAfter(startTime)) 
				&& (t.getTransactionTime().isBefore(endTime) || t.getTransactionTime().isEqual(endTime)))
		.sorted((t1,t2)->t2.getTransactionTime().compareTo(t1.getTransactionTime()))
		.collect(Collectors.toList());
		return transactionList;
	}
	
}
