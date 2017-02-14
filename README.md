#Introduction

This project was implemeneted as part of our Web Services and API Development module. 

In order to solve the online banking web service domain problem we implemented a RESTful API using the JAR-XS and Jersey framework. Our proposed solution to the problem domain was to use the MVC design pattern for separation of concerns.

The banking service is broken down in the following components:


##Resources
- These resources, namely Customer and Account, is the public API for the system. These resources will make the relevant requests to the services classes when they receive a request.
##Services
- This is where our logic resides and all of the management of the banking data.
- It Provides any functions that need to be performed on the data and also sends and receives information from our persistence classes.
##Security
- Manages the security of the system, in particular the API Key.
##Database
- Manages the persistence of data and the CRUD functionality to our database, using the JPA.
##Models
- These are POJOs of objects such as Customer, Account and Transaction.

##Security

The online banking web service will require security across the entire API and we have proposed to solve the security concern by implementing a simple API key filter.

API Key filter 
- Implement API key filter using Jersey’s ContainerFilterRequest object
- You must enter API key in the header to be authorised to make a request. 

Example in CURL - curl -vi -X GET http://localhost:49000/api/customers -H ’API_KEY: 521197c4-bb29-11e6-a4a6-cec0c932ce01

POSTMAN – Set the header properties as following:
Key – API_KEY 
Value – API_KEY: 521197c4-bb29-11e6-a4a6-cec0c932ce01

##Notes on Running Banking System
When the running on NetBeans if the server is running and you run the client application, NetBeans restarts the server. This may cause errors in the client as it will receive 404 response codes from the server. To avoid this issue, wait until the server is finished restarting before sending requests via the client. This restart takes approx. 60 seconds.

##Restful API
All URIs begin with: /api/*

###GET Requests

API Name:  Customers Resource
</br>
Description:  This allows the retrieval all customer details.
</br>
URI:   /customers/{cust_id}
</br>
HTTP verb:  GET
</br>
Parameters: cust_id (Integer, optional, URL param)
</br>
Resource contents: Returns details (customer id, name, address, email, phone) for all or specified customer
</br>
Pre-Conditions: Customer table must be populated, specified customer must exist.
</br>
Post-Conditions: The data is return in JSON format with status code 200

API Name:   Balance Resource
</br>
Description:  This allows the retrieval of the balance in a customer’s account.
</br>
URI:  account /balance/{cust_id}
</br>
HTTP verb:  GET
</br>
Parameters: cust_id (Integer, URL param, required), account (Integer, optional)
</br>
Resource contents: Returns balance for all, or specific, accounts that a customer holds.
</br>
Pre-Conditions: Customer must hold one or more accounts.
</br>
Post-Conditions: The data is return in JSON format with status code 200

API Name: Transaction Resource
</br>
Description:  This allows the retrieval of the transaction on customer accounts.
</br>
URI:   /transactions/{cust_id}
</br>
HTTP verb:  GET
</br>
Parameters: cust_id (Integer, URL param, required), account (Integer, URL param, optional)
</br>
Resource contents: Returns transactions on all, or specific, accounts that a customer holds.
</br>
Pre-Conditions: Transaction must have been made on the account.
</br>
Post-Conditions: The data is return in JSON format with status code 200

###POST Requests

API Name: Customer Resource
</br>
Description:  This allows the addition of a new customer into the system.
</br>
URI:   /customers
</br>
HTTP verb:  POST
</br>
Parameters: name, address, email, phone (JSON, required)	
</br>
Resource contents: The details of the newly added customer.
</br>
Pre-Conditions: The customer must not already exist in the system.
</br>
Post-Conditions: A new customer is added to the system. The details and status 201 is returned.

API Name: Transaction Resource
</br>
Description:  This allows withdrawals or lodgements to be made on an account.
</br>
URI:   accounts/{transactions_type}
</br>
HTTP verb:  POST
</br>
Parameters: cust_id, account_no, amount (JSON, required), account_no_to, cust_id_to, (JSON, only if transfer)	
</br>
Resource contents: The details of the transaction.
</br>
Pre-Conditions: The customer holds an account. The account must hold efficient funds.
</br>
Post-Conditions: A transaction has been made, either withdrawal, lodgement or transfer. The details and status 201 is returned.

API Name: Account Resource
</br>
Description:  This allows an account to be made.
</br>
URI:   /accounts
</br>
HTTP verb:  POST
</br>
Parameters: cust_id (JSON, required)	
</br>
Resource contents: The details of the customer and branch.
</br>
Pre-Conditions: The customer must exist.
</br>
Post-Conditions: An account has been made under a customer name. The details and status 201 is returned.

API Name: Transfer Resource
</br>
Description:  This allows a transfer to be made between accounts.
</br>
URI:   accounts/transfers
</br>
HTTP verb:  POST
</br>
Parameters: cust_id, account_no, account_no_to, cust_id_to, amount (JSON, required)	
</br>
Resource contents: The details of the transaction.
</br>
Pre-Conditions: Both accounts must exist and the account must hold the efficient funds.
</br>
Post-Conditions: A withdrawal has been made from one account and a lodgement to another. The details and status 201 is returned.

###DELETE Request

API Name: Customer Resource
</br>
Description:  This allows a customer to be deleted.
</br>
URI:   /customers/{cust_id}
</br>
HTTP verb:  DELETE
</br>
Parameters: cust_id (URL param, required)	
</br>
Resource contents: The customer details.
</br>
Pre-Conditions: The customer must exist.
</br>
Post-Conditions: The customer is deleted from the system. The status 204 is returned.

API Name: Account Resource
</br>
Description:  This allows a customer’s account to be deleted.
</br>
URI:   /accounts/{account_no}
</br>
HTTP verb:  DELETE
</br>
Parameters: account_no (URL Param, required)	
</br>
Resource contents: The account details.
</br>
Pre-Conditions: The customer’s account must exist.
</br>
Post-Conditions: The customer’s account is deleted from the system. The status 204 is returned.

###PUT Request

API Name: Customer Resource
</br>
Description:  This allows a customer’s details to be updated
</br>
URI:   /customers/{cust_id}
</br>
HTTP verb:  POST
</br>
Parameters: cust_id (URL param), name, address, email, phone (JSON, required)	
</br>
Resource contents: The updated details of the customer.
</br>
Pre-Conditions: The customer must already exist in the system.
</br>
Post-Conditions: A customer’s details are updated in the system. The details and status 201 is returned.

