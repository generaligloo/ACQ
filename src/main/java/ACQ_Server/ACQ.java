package ACQ_Server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ACQ {
    private final static Logger LOGGER = LoggerFactory.getLogger(ACQ.class);

    public static void main(String[] args) {
        //init method
        System.setProperty("javax.net.ssl.keyStore","src\\main\\java\\ACQ_Server\\store\\ServerACQ.jks");
        System.setProperty("javax.net.ssl.keyStorePassword","123456ACQ");
        System.setProperty("javax.net.ssl.trustStore","src\\main\\java\\ACQ_Server\\store\\ServerACQ.jks");
        System.setProperty("javax.net.ssl.trustStorePassword","123456ACQ");

        ExchangeHTTPS ExchangeHTTPS = new ExchangeHTTPS();
        ExchangeACS ExchangeACS = new ExchangeACS();
        Thread ExchangeHTTPSThread = new Thread(ExchangeHTTPS);
        Thread ExchangeACSThread = new Thread(ExchangeACS);
        ExchangeHTTPSThread.start();
        ExchangeACSThread.start();
    }
}
