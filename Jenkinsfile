pipeline	{
	agent any
	triggers { githubPush() }
	environment {
		ACCOUNTS_CONTAINER = 'accounts_container'
		API_GATEWAY_CONTAINER = 'api-gateway_container'
		BILL_PAYMENT_CONTAINER = 'bill-payment_container'
		CONFIG_SERVER_CONTAINER = 'config-server_container'
		EUREKA_SERVER_CONTAINER = 'eureka-server_container'
		LOAN_CONTAINER = 'loan_container'
		LOGIN_CONTAINER = 'login_container'
		PROFILE_CONTAINER = 'profile_container'
		REGISTRATION_CONTAINER = 'registration_container'
		STATEMENT_CONTAINER = 'statement_container'
		TRANSFER_CONTAINER = 'transfer_container'
		
		ACCOUNTS_IMAGE = 'accounts'
		API_GATEWAY_IMAGE = 'api-gateway'
		BILL_PAYMENT_IMAGE = 'bill-payment'
		CONFIG_SERVER_IMAGE = 'config-server'
		EUREKA_SERVER_IMAGE = 'eureka-server'
		LOAN_IMAGE = 'loan'
		LOGIN_IMAGE = 'login'
		PROFILE_IMAGE = 'profile'
		REGISTRATION_IMAGE = 'registration'
		STATEMENT_IMAGE = 'statement'
		TRANSFER_IMAGE = 'transfer'
		
		IMAGE_TAG = 'v1'
	}
	stages	{
		stage('Build')	{
			steps	{
				dir('accounts')	{
					powershell """
						mvn clean package -DskipTests
						docker build -t $ACCOUNTS_IMAGE:$IMAGE_TAG .
					"""
				}
				dir('api-gateway')	{
					powershell """
						mvn clean package -DskipTests
						docker build -t $API_GATEWAY_IMAGE:$IMAGE_TAG .
					"""
				}
				dir('bill-payment')	{
					powershell """
						mvn clean package -DskipTests
						docker build -t $BILL_PAYMENT_IMAGE:$IMAGE_TAG .
					"""
				}
				dir('config-server')	{
					powershell """
						mvn clean package -DskipTests
						docker build -t $CONFIG_SERVER_IMAGE:$IMAGE_TAG .
					"""
				}
				dir('eureka-server')	{
					powershell """
						mvn clean package -DskipTests
						docker build -t $EUREKA_SERVER_IMAGE:$IMAGE_TAG .
					"""
				}
				dir('loan')	{
					powershell """
						mvn clean package -DskipTests
						docker build -t $LOAN_IMAGE:$IMAGE_TAG .
					"""
				}
				dir('login')	{
					powershell """
						mvn clean package -DskipTests
						docker build -t $LOGIN_IMAGE:$IMAGE_TAG .
					"""
				}
				dir('profile')	{
					powershell """
						mvn clean package -DskipTests
						docker build -t $PROFILE_IMAGE:$IMAGE_TAG .
					"""
				}
				dir('registration')	{
					powershell """
						mvn clean package -DskipTests
						docker build -t $REGISTRATION_IMAGE:$IMAGE_TAG .
					"""
				}
				dir('statement')	{
					powershell """
						mvn clean package -DskipTests
						docker build -t $STATEMENT_IMAGE:$IMAGE_TAG .
					"""
				}
				dir('transfer')	{
					powershell """
						mvn clean package -DskipTests
						docker build -t $TRANSFER_IMAGE:$IMAGE_TAG .
					"""
				}
			}
		}
		
		stage('Stop Old Containers') {
            steps {
                powershell '''
	                # Stop and remove old container if running
	                if [ "$(docker ps -q -f name=$ACCOUNTS_CONTAINER)" ]; then
	                  docker stop $ACCOUNTS_CONTAINER
	                  docker rm $ACCOUNTS_CONTAINER
	                fi
	                if [ "$(docker ps -q -f name=$API_GATEWAY_CONTAINER)" ]; then
	                  docker stop $API_GATEWAY_CONTAINER
	                  docker rm $API_GATEWAY_CONTAINER
	                fi
	                if [ "$(docker ps -q -f name=$BILL_PAYMENT_CONTAINER)" ]; then
	                  docker stop $BILL_PAYMENT_CONTAINER
	                  docker rm $BILL_PAYMENT_CONTAINER
	                fi
	                if [ "$(docker ps -q -f name=$CONFIG_SERVER_CONTAINER)" ]; then
	                  docker stop $CONFIG_SERVER_CONTAINER
	                  docker rm $CONFIG_SERVER_CONTAINER
	                fi
	                if [ "$(docker ps -q -f name=$EUREKA_SERVER_CONTAINER)" ]; then
	                  docker stop $EUREKA_SERVER_CONTAINER
	                  docker rm $EUREKA_SERVER_CONTAINER
	                fi
	                if [ "$(docker ps -q -f name=$LOAN_CONTAINER)" ]; then
	                  docker stop $LOAN_CONTAINER
	                  docker rm $LOAN_CONTAINER
	                fi
	                if [ "$(docker ps -q -f name=$LOGIN_CONTAINER)" ]; then
	                  docker stop $LOGIN_CONTAINER
	                  docker rm $LOGIN_CONTAINER
	                fi
	                if [ "$(docker ps -q -f name=$PROFILE_CONTAINER)" ]; then
	                  docker stop $PROFILE_CONTAINER
	                  docker rm $PROFILE_CONTAINER
	                fi
	                if [ "$(docker ps -q -f name=$REGISTRATION_CONTAINER)" ]; then
	                  docker stop $REGISTRATION_CONTAINER
	                  docker rm $REGISTRATION_CONTAINER
	                fi
	                if [ "$(docker ps -q -f name=$STATEMENT_CONTAINER)" ]; then
	                  docker stop $STATEMENT_CONTAINER
	                  docker rm $STATEMENT_CONTAINER
	                fi
	                if [ "$(docker ps -q -f name=$TRANSFER_CONTAINER)" ]; then
	                  docker stop $TRANSFER_CONTAINER
	                  docker rm $TRANSFER_CONTAINER
	                fi
                '''
            }
        }
        
		stage('Run New Containers')	{
			steps	{
				powershell """
					docker run -d --name $ACCOUNTS_CONTAINER -p 8005:8005 $ACCOUNTS_IMAGE:$IMAGE_TAG
					docker run -d --name $API_GATEWAY_CONTAINER -p 9000:9000 $API_GATEWAY_IMAGE:$IMAGE_TAG
					docker run -d --name $BILL_PAYMENT_CONTAINER -p 8006:8006 $BILL_PAYMENT_IMAGE:$IMAGE_TAG
					docker run -d --name $CONFIG_SERVER_CONTAINER -p 7000:7000 $CONFIG_SERVER_IMAGE:$IMAGE_TAG
					docker run -d --name $EUREKA_SERVER_CONTAINER -p 8761:8761 $EUREKA_SERVER_IMAGE:$IMAGE_TAG
					docker run -d --name $LOAN_CONTAINER -p 8004:8004 $LOAN_IMAGE:$IMAGE_TAG
					docker run -d --name $LOGIN_CONTAINER -p 8001:8001 $LOGIN_IMAGE:$IMAGE_TAG
					docker run -d --name $PROFILE_CONTAINER -p 8007:8007 $PROFILE_IMAGE:$IMAGE_TAG
					docker run -d --name $REGISTRATION_CONTAINER -p 8000:8000 $REGISTRATION_IMAGE:$IMAGE_TAG
					docker run -d --name $STATEMENT_CONTAINER -p 8003:8003 $STATEMENT_IMAGE:$IMAGE_TAG
					docker run -d --name $TRANSFER_CONTAINER -p 8002:8002 $TRANSFER_IMAGE:$IMAGE_TAG
				"""
			}
		}
	}
	
	post {
        success {
            echo 'Pipeline completed successfully!'
        }
        failure {
            echo 'Pipeline failed. Check logs.'
        }
        always {
            cleanWs()
        }
    }
}