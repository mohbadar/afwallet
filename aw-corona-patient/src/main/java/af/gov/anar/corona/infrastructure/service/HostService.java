package af.gov.anar.corona.infrastructure.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Service
public class HostService {

    public  static String SERVICE_HOST;
    public  static int SERVICE_PORT;

    @Value("${server.port}")
    public void setServicePort(int port)
    {
        SERVICE_PORT =port;
    }

    @Value("${server.address}")
    public void setServiceHost(String host)
    {
        SERVICE_HOST =host;
    }


    public  String getDefaultHostName () throws UnknownHostException {
        return InetAddress.getLocalHost().getHostName();
    }

    public  String getDefaultIP () throws UnknownHostException {
        return InetAddress.getLocalHost().getHostAddress();
    }
}
