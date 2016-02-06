import java.io.*;
import java.net.*;
import java.util.StringTokenizer;
import java.util.Vector;

public class ChatServer {

	static Vector<Socket> ClientSockets;
	static Vector<String> LoginNames;
	ChatServer() throws IOException{
		ServerSocket server = new ServerSocket(5217);
		ClientSockets = new Vector<Socket>();
		LoginNames = new Vector<String>();
		while(true){
			Socket Client = server.accept();
			AcceptClient acceptclient = new AcceptClient(Client);	
		}
	}
	public static void main(String args[] ) throws IOException {
		ChatServer server = new ChatServer();
		
	}
	class AcceptClient extends Thread{
		Socket ClientSocket;
		DataInputStream din;
		DataOutputStream dout;
		AcceptClient(Socket Client) throws IOException{
			ClientSocket = Client;
			din = new DataInputStream(ClientSocket.getInputStream());
			dout = new DataOutputStream(ClientSocket.getOutputStream());
			
			
			String  LoginName = din.readUTF();
			
			LoginNames.add(LoginName);
			ClientSockets.add(ClientSocket);
			
			start();
		}
		
	public void run(){
		while (true){
			try {
				String msgFromClient = din.readUTF();
				StringTokenizer st = new StringTokenizer(msgFromClient);
				String LoginName = st.nextToken();
				String MsgType = st.nextToken();
				String msg = "";
				int lo = -1;
				
				while (st.hasMoreTokens()){
					msg=msg+ " " +st.nextToken();
				}
				
				if (MsgType.equals("LOGIN")){
					for (int i = 0; i < LoginNames.size(); i++){
						Socket pSocket = (Socket) ClientSockets.elementAt(i);
						DataOutputStream pOut = new DataOutputStream(pSocket.getOutputStream());
						pOut.writeUTF(LoginName + " has Logged in.");
						
					}
				}
				else if (MsgType.equals("LOGOUT")){
					for (int i = 0; i < LoginNames.size(); i++){
						if (LoginName.equals(LoginNames.elementAt(i)))
							lo = i;
						Socket pSocket = (Socket) ClientSockets.elementAt(i);
						DataOutputStream pOut = new DataOutputStream(pSocket.getOutputStream());
						pOut.writeUTF(LoginName + " has Logged out.");
					}
					if (lo >= 0){
						LoginNames.removeElementAt(lo);
						ClientSockets.removeElementAt(lo);
					}
				}
				else {
					for (int i = 0; i < LoginNames.size(); i++){
						Socket pSocket = (Socket) ClientSockets.elementAt(i);
						DataOutputStream pOut = new DataOutputStream(pSocket.getOutputStream());
						pOut.writeUTF(LoginName + ": " + msg );
					}
				}
				if (MsgType.equals("LOGOUT")){
					break;
				}
				}
			 catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	}
}
