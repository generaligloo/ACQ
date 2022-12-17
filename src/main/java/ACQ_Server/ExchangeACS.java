package ACQ_Server;

import ACQ_Server.ACQ;
import ACQ_Server.util.Ansi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.*;

public class ExchangeACS implements Runnable {

    private final static Logger LOGGER = LoggerFactory.getLogger(ACQ.class);

    @Override
    public void run() {
        try
        {
            SSLSocketFactory sslsocketfactory = (SSLSocketFactory) SSLSocketFactory.getDefault();
            SSLSocket sslsocket = (SSLSocket) sslsocketfactory.createSocket("localhost", 29180);
            LOGGER.info(Ansi.GREEN + "Connexion au serveur ACS réussi !");
            InputStream inputstream = sslsocket.getInputStream();
            InputStreamReader inputstreamreader = new InputStreamReader(inputstream);
            BufferedReader bufferedreader = new BufferedReader(inputstreamreader);
            OutputStream outputstream = sslsocket.getOutputStream();
            OutputStreamWriter outputstreamwriter = new OutputStreamWriter(outputstream);
            BufferedWriter bufferedwriter = new BufferedWriter(outputstreamwriter);

            LOGGER.info(Ansi.BLUE + "Envoi des informations au serveur ACS...");
            bufferedwriter.write("Ceci est un message pour l'ACS" + '\n');
            bufferedwriter.flush();
            LOGGER.info(Ansi.BLUE + "En attente de la réponse du serveur ACS...");
            String response = bufferedreader.readLine();
            LOGGER.info(Ansi.GREEN + "Résponse de l'ACS: "+ response);
            sslsocket.close();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
