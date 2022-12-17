package ACQ_Server;

import ACQ_Server.util.Ansi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSocket;
import java.io.*;

public class ExchangeHTTPS implements Runnable {

    private final static Logger LOGGER = LoggerFactory.getLogger(ACQ.class);

    @Override
    public void run() {
        try
        {
            SSLServerSocketFactory sslserversocketfactory = (SSLServerSocketFactory) SSLServerSocketFactory.getDefault();
            SSLServerSocket sslserversocket = (SSLServerSocket) sslserversocketfactory.createServerSocket(29190);
            SSLSocket sslsocket = (SSLSocket) sslserversocket.accept();
            LOGGER.info(Ansi.GREEN + "Connexion au serveur HTTPS réussie");
            InputStream inputstream = sslsocket.getInputStream();
            InputStreamReader inputstreamreader = new InputStreamReader(inputstream);
            BufferedReader bufferedreader = new BufferedReader(inputstreamreader);
            OutputStream outputstream = sslsocket.getOutputStream();
            OutputStreamWriter outputstreamwriter = new OutputStreamWriter(outputstream);
            BufferedWriter bufferedwriter = new BufferedWriter(outputstreamwriter);
            LOGGER.info(Ansi.CYAN  + "Reading ...");
            String message = bufferedreader.readLine();
            LOGGER.info("Message from HTTPS Server:" + message);
            String response = "Message was received :" + message;
            bufferedwriter.write(response + '\n');
            bufferedwriter.flush();
            sslsocket.close();

        } catch (Exception exception) {
            exception.printStackTrace();
            System.out.println(exception);
        }
    }
}
