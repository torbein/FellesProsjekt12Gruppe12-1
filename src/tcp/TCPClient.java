package tcp;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

public class TCPClient {
	private Socket clientSocket;
	private String sentence;
	private String modifiedSentences;
	private DataOutputStream outToServer;
	private BufferedReader inFromServer;
	private BufferedReader inFromUser;
	private boolean userAlreadyConnected = false;
	
	public TCPClient(String userName) throws IOException{

		inFromUser = new BufferedReader(new InputStreamReader(System.in));
		try {
			clientSocket = new Socket("78.91.1.120",7070);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(clientSocket.isConnected()){
			System.out.println("halla");
			outToServer = new DataOutputStream(clientSocket.getOutputStream());
			inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			connectToServer();
			while(true){
				sentence = "MSG " + inFromUser.readLine();
				outToServer.writeBytes(sentence + '\n');
				modifiedSentences = inFromServer.readLine();
				System.out.println("FROM SERVER: " + modifiedSentences);

			}
		}
		else{
			closeConnection();
		}
	}
	
	public static void main(String[] args) throws UnknownHostException, IOException {
		new TCPClient("LOL");
	}
	public void closeConnection() throws IOException{
		clientSocket.close();

	}
	public void connectToServer() throws IOException{
		do{
			sentence = "NICK " + inFromUser.readLine();
			outToServer.writeBytes(sentence + '\n');
			modifiedSentences = inFromServer.readLine();
			System.out.println("FROM SERVER: " + modifiedSentences);
			setIsUserConnected(true);
		} while(modifiedSentences.equals("SERVER: Bruker er paa"));
		setIsUserConnected(false);
	
	}
	public void setIsUserConnected(boolean b){
		userAlreadyConnected = b;

	}
	public boolean getIsUserConnected(){
		return userAlreadyConnected;
	}
	public void sendMessage(String userName){
		
	}
}
