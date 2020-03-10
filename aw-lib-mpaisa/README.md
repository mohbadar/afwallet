### MPAISA and Upay Library

#### Definitions and Acronyms

- USSD : Unstructured Supplementary Service Data. Unstructured Supplementary Service Data is associated with real-time or instant
                                                  messaging type phone services
- MSISDN : Mobile Subscriber ISDN (i.e. Mobile Number)
- PIN : Personal Identification Number
- Txn ID : Transaction Identifier
- Customer Active customer registered for M-Paisa services


#### Communication Protocol 

The communication between External System and M-Paisa is XML over HTTP(s). The External System
makes the HTTP(s) connection with M-Paisa and sends the request (Post) content as XML. Response
of each request is also sent as XML.

#### Gateway Authenication 
To authenticate the interfaces, M-Paisa will define a Gateway for interfacing System. Interfacing
System will send this gateway information while making HTTP(s) connection with M-Paisa for each
request. Authentication information will be send by URL. Also IP based authentication would also be
done. Details of authentication information are as follows
- REQUEST_GATEWAY_CODE
- REQUEST_GATEWAY_TYPE
- Login ID
- Password

#### URL Pattern
`
TXN_URL=http(s)://<IP>:<PORT>/<Receiver>?LOGIN=<Login>&PASSWORD=<Pwd>&REQUEST_G
ATEWAY_CODE=<Code>&REQUEST_GATEWAY_TYPE=<Type>&requestText=
`
<br/>Example: <br/>

`
http(s)://localhost:8080/NewTxn/ServiceSelector?LOGIN=Web_Bearer2&PASSWORD=cMLUHe2
osdK6KlPLhUyaGa04FOs=&REQUEST_GATEWAY_CODE=WEB&REQUEST_GATEWAY_TYPE=WEB&
requestText=
`
