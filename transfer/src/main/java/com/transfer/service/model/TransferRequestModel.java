package com.transfer.service.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransferRequestModel {
	
	private String recipientAccountNo;
	private long amount;
	private String description;

}
