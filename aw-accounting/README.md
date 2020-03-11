### Service Template of ePayFrame


It is used to develop services for ePayFrame. 


#### Multitenancy 

You need add the `realm` property in header of your requests.


```curl

curl -X GET \
     -H "Authorization: Bearer ${TOKEN} \
     -H "realm: ORGA1" http://localhost:7001/api/user

```

You also need to add `TenantName-keycloak.json` file in resources directory. 

#### Usage Guide

- Clone the repository 
- Rename the service directory name to `anar-servicename`
- Rename artifactId to your service artifactId
- Change the github remote address to the new service URL. 
- Add the required library as a module in service
- Start implementing awesome features


#### Documentation

Refer to this repository **Wiki** section.
https://github.com/Anar-Framework/anar-servicetemplate/wiki
