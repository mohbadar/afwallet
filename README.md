## eBreshna System

#### How to setup the project
- Download Keycloak from (https://www.keycloak.org/downloads.html)
- Setup Anar Framework
```xml
git clone https://github.com/Anar-Framework/anar-parent.git
cd anar-parent/script/setup/
bash setup.sh
cd ../..
```
- clone eBreshna repository

```xml
git clone https://github.com/nsia-infosys/eBreshna.git
```
- Open the eBreshna with `intellij` IDE
- Add imported libraries in `pom.xml` from `File>New>Module from Existing Sources...`

- Setup eBreshna-web
```xml
git clone https://github.com/nsia-infosys/eBreshna-web
cd eBreshna-web
npm install 
npm start
```

#### How to build the source code
- Run the Keycloak `cd keycloak_path/bin` and `bash standalone.sh`
- Run `main` method from `af.gov.anar.ebreshna.Applicant`



#### Documentation

Refer to this repository **Wiki** section.
https://github.com/nsia-infosys/eBreshna/wiki
