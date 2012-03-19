package tcp;

import java.io.IOException;
import java.net.*;
import java.util.Hashtable;

public class TCPServer {
	
	public static void main(String[] args) throws IOException {
		ServerSocket tcpWelcomeSocket = new ServerSocket(7070);
		System.out.println("Connected to TCPSERVER!");
		while (true) {
			Socket socketConnection = tcpWelcomeSocket.accept();
			System.out.println("test");

			Clients client = new Clients(socketConnection);
			client.start();
			
		}
	}
	

}
