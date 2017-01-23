#Introduction

In order to solve the online banking web service problem we will be implementing a RESTful API using the JAR-XS and Jersey framework. Our proposed solution to the problem domain will be to use the MVC design pattern.

The banking service will be broken down in the following components:


•	Resources
o	These resources, namely Customer and Account, is the public API for the system. These resources will make the relevant requests to the services classes when they receive a request.
•	Services
o	This is where our logic resides and all of the management of the banking data.
o	 It Provides any functions that need to be performed on the data and also sends and receives information from our persistence classes.
•	Security
o	Manages the security of the system, in particular the API Key.
•	Database
o	Manages the persistence of data and the CRUD functionality to our database, using the JPA.
•	Models
o	These are POJOs of objects such as Customer, Account and Transaction.

#Security

The online banking web service will require security across the entire API and we have proposed to solve the security concern by implementing the following:

•	API Key filter 
o	Implement API key filter using Jersey’s ContainerFilterRequest object
o	You must enter API key in the header to be authorised to make a request. 

Example in CURL - curl -vi -X GET http://localhost:49000/api/customers -H ’API_KEY: 521197c4-bb29-11e6-a4a6-cec0c932ce01

POSTMAN – Set the header properties as following:
Key – API_KEY 
Value –API_KEY: 521197c4-bb29-11e6-a4a6-cec0c932ce01

#Notes on Running Banking System
When the running on NetBeans if the server is running and you run the client application, NetBeans restarts the server. This may cause errors in the client as it will receive 404 response codes from the server. To avoid this issue, wait until the server is finished restarting before sending requests via the client. This restart takes approx. 60 seconds.

#Restful API
All URIs begin with: /api/*

#GET Requests

API Name:  Customers Resource
Description:  This allows the retrieval all customer details.
URI:   /customers/{cust_id}
HTTP verb:  GET
Parameters: cust_id (Integer, optional, URL param)
Resource contents: Returns details (customer id, name, address, email, phone) for all or specified customer
Pre-Conditions: Customer table must be populated, specified customer must exist.
Post-Conditions: The data is return in JSON format with status code 200

API Name:   Balance Resource
Description:  This allows the retrieval of the balance in a customer’s account.
URI:  account /balance/{cust_id}
HTTP verb:  GET
Parameters: cust_id (Integer, URL param, required), account (Integer, optional)
Resource contents: Returns balance for all, or specific, accounts that a customer holds.
Pre-Conditions: Customer must hold one or more accounts.
Post-Conditions: The data is return in JSON format with status code 200

API Name: Transaction Resource
Description:  This allows the retrieval of the transaction on customer accounts.
URI:   /transactions/{cust_id}
HTTP verb:  GET
Parameters: cust_id (Integer, URL param, required), account (Integer, URL param, optional)
Resource contents: Returns transactions on all, or specific, accounts that a customer holds.
Pre-Conditions: Transaction must have been made on the account.
Post-Conditions: The data is return in JSON format with status code 200

#POST Requests

API Name: Customer Resource
Description:  This allows the addition of a new customer into the system.
URI:   /customers
HTTP verb:  POST
Parameters: name, address, email, phone (JSON, required)	
Resource contents: The details of the newly added customer.
Pre-Conditions: The customer must not already exist in the system.
Post-Conditions: A new customer is added to the system. The details and status 201 is returned.

API Name: Transaction Resource
Description:  This allows withdrawals or lodgements to be made on an account.
URI:   accounts/{transactions_type}
HTTP verb:  POST
Parameters: cust_id, account_no, amount (JSON, required), account_no_to, cust_id_to, (JSON, only if transfer)	
Resource contents: The details of the transaction.
Pre-Conditions: The customer holds an account. The account must hold efficient funds.
Post-Conditions: A transaction has been made, either withdrawal, lodgement or transfer. The details and status 201 is returned.

API Name: Account Resource
Description:  This allows an account to be made.
URI:   /accounts
HTTP verb:  POST
Parameters: cust_id (JSON, required)	
Resource contents: The details of the customer and branch.
Pre-Conditions: The customer must exist.
Post-Conditions: An account has been made under a customer name. The details and status 201 is returned.

API Name: Transfer Resource
Description:  This allows a transfer to be made between accounts.
URI:   accounts/transfers
HTTP verb:  POST
Parameters: cust_id, account_no, account_no_to, cust_id_to, amount (JSON, required)	
Resource contents: The details of the transaction.
Pre-Conditions: Both accounts must exist and the account must hold the efficient funds.
Post-Conditions: A withdrawal has been made from one account and a lodgement to another. The details and status 201 is returned.



#DELETE Request

API Name: Customer Resource
Description:  This allows a customer to be deleted.
URI:   /customers/{cust_id}
HTTP verb:  DELETE
Parameters: cust_id (URL param, required)	
Resource contents: The customer details.
Pre-Conditions: The customer must exist.
Post-Conditions: The customer is deleted from the system. The status 204 is returned.

API Name: Account Resource
Description:  This allows a customer’s account to be deleted.
URI:   /accounts/{account_no}
HTTP verb:  DELETE
Parameters: account_no (URL Param, required)	
Resource contents: The account details.
Pre-Conditions: The customer’s account must exist.
Post-Conditions: The customer’s account is deleted from the system. The status 204 is returned.

#PUT Request

API Name: Customer Resource
Description:  This allows a customer’s details to be updated
URI:   /customers/{cust_id}
HTTP verb:  POST
Parameters: cust_id (URL param), name, address, email, phone (JSON, required)	
Resource contents: The updated details of the customer.
Pre-Conditions: The customer must already exist in the system.
Post-Conditions: A customer’s details are updated in the system. The details and status 201 is returned.

