pipeline	{
	agent any
	triggers { githubPush() }
	stages	{
		stage('Build')	{
			steps	{
				dir('accounts')	{
					mvn clean package
					docker build -t accounts:v1 .
				}
				dir('api-gateway')	{
					mvn clean package
					docker build -t api-gateway:v1 .
				}
				dir('bill-payment')	{
					mvn clean package
					docker build -t bill-payment:v1 .
				}
				dir('config-server')	{
					mvn clean package
					docker build -t config-server:v1 .
				}
				dir('eureka-server')	{
					mvn clean package
					docker build -t eureka-server:v1 .
				}
				dir('loan')	{
					mvn clean package
					docker build -t loan:v1 .
				}
				dir('login')	{
					mvn clean package
					docker build -t login:v1 .
				}
				dir('profile')	{
					mvn clean package
					docker build -t profile:v1 .
				}
				dir('registration')	{
					mvn clean package
					docker build -t registration:v1 .
				}
				dir('statement')	{
					mvn clean package
					docker build -t statement:v1 .
				}
				dir('transfer')	{
					mvn clean package
					docker build -t transfer:v1 .
				}
			}
		}
		
		stage('deploy')	{
			steps	{
				docker run -d -p 8005:8005 acoounts:v1
				docker run -d -p 9000:9000 api-gateway:v1
				docker run -d -p 8006:8006 bill-payment:v1
				docker run -d -p 7000:7000 config-server:v1
				docker run -d -p 8761:8761 eureka-server:v1
				docker run -d -p 8004:8004 loan:v1
				docker run -d -p 8001:8001 login:v1
				docker run -d -p 8007:8007 profile:v1
				docker run -d -p 8000:8000 registration:v1
				docker run -d -p 8003:8003 statement:v1
				docker run -d -p 8002:8002 transfer:v1
			}
		}
	}
}