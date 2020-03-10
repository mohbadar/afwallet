package af.gov.anar.spm.infrastructure.service;

import org.keycloak.KeycloakSecurityContext;
import org.keycloak.representations.AccessToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private KeycloakSecurityContext keycloakSecurityContext;
    @Autowired
    private AccessToken accessToken;

    public String getId()
    {
        return accessToken.getId();
    }

    public String getPreferredUsername()
    {
        return accessToken.getPreferredUsername();
    }

    public String getName()
    {
        return accessToken.getName();
    }


    public String getFamilyName()
    {
        return accessToken.getFamilyName();
    }

    public String getGivenName()
    {
        return accessToken.getGivenName();
    }

    public String getDoB()
    {
        return accessToken.getBirthdate();
    }


    public String getEmail()
    {
        return accessToken.getEmail();
    }


    public boolean getPhoneNumberVerified()
    {
        return accessToken.getPhoneNumberVerified();
    }


    public boolean getEmailVerified()
    {
        return accessToken.getEmailVerified();
    }


    public String getAcr()
    {
        return accessToken.getAcr();
    }



    public String getProfile()
    {
        return accessToken.getProfile();
    }

    public String getIssuer()
    {
        return accessToken.getIssuer();
    }


    public String getType()
    {
        return accessToken.getType();
    }


    public String getLocale()
    {
        return accessToken.getLocale();
    }

    public String getScope()
    {
        return accessToken.getScope();
    }

    public String getPhoneNumber()
    {
        return accessToken.getPhoneNumber();
    }
}
