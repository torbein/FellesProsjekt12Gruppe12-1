package tcp;

import java.io.*;
import java.net.*;

public class Clients extends Thread implements Runnable {
    private Socket client;
    private BufferedReader in = null;
    private PrintWriter out = null;
 
    Clients(Socket client) {
        this.client = client;
        try {
            /* obtain an input stream to this client ... */
            in = new BufferedReader(new InputStreamReader(
                        client.getInputStream()));
            /* ... and an output stream to the same client */
            out = new PrintWriter(client.getOutputStream(), true);
        } catch (IOException e) {
            System.err.println(e);
            return;
        }
    }
 
    public void run() {
        String msg, response;
        MessageServerProtocol protocol = new MessageServerProtocol(this);
        try {
            /* loop reading lines from the client which are processed 
             * according to our protocol and the resulting response is 
             * sent back to the client */
            while ((msg = in.readLine()) != null) {
                response = protocol.process(msg);
                out.println("SERVER: " + response);
            }
            protocol.logOff();
            
        } catch (IOException e) {
            System.err.println(e);
        }
    }
 
    public void sendMsg(String msg) {
        out.println(msg);
    }
}
