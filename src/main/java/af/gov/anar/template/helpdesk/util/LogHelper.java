package af.gov.anar.template.helpdesk.util;

import org.hibernate.SessionFactory;
import org.hibernate.jpa.HibernateEntityManagerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.sql.Blob;
import java.sql.Clob;

@Component
public class LogHelper {

    @Bean // Need to expose SessionFactory to be able to work with BLOBs
    public SessionFactory sessionFactory(HibernateEntityManagerFactory hemf) {
        return hemf.getSessionFactory();
    }

    @Autowired
    private SessionFactory sessionFactory;

    public Blob createBlob(InputStream content, long size) {
        return sessionFactory.getCurrentSession().getLobHelper().createBlob(content, size);
    }

    public Clob createClob(InputStream content, long size, Charset charset) {
        return sessionFactory.getCurrentSession().getLobHelper().createClob(new InputStreamReader(content, charset), size);
    }
}

