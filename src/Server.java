
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.UnknownHostException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Server {

	private ServerSocket ServSock;
	static ArrayList<ServerThread> serverThreadList;

	Server() {
		try {

			ServSock = new ServerSocket(33333);
			serverThreadList = new ArrayList<ServerThread>();
			System.out.println("Server running at port 33333");
			while (true) {

				ServerThread m = new ServerThread(ServSock.accept(), serverThreadList);

			}
		} catch (Exception e) {
			System.out.println("Server starts:" + e);
		}
	}

	public static void main(String args[]) throws UnknownHostException, IOException {
		Server objServer = new Server();
	}
}

class ServerThread implements Runnable {

	private Socket ClientSock;
	private Thread thr;
	private ObjectOutputStream oos;
	private ObjectInputStream ois;
	static int client_count = 0;
	private ArrayList<ServerThread> serverThreadList;

	ServerThread(Socket client, ArrayList<ServerThread> serverThreadList) {
		try {
			this.ClientSock = client;
			// adding the thread to the list
			serverThreadList.add(this);//this line will happen after login
			client_count++;
			oos = new ObjectOutputStream(ClientSock.getOutputStream());
			ois = new ObjectInputStream(ClientSock.getInputStream());
			this.serverThreadList = serverThreadList;
			this.thr = new Thread(this);
			thr.start();
		} catch (Exception ex) {

		}
	}

	public void run() {

		while (true) {
			try {
				Object t = ois.readObject();
				if (t != null) {
					System.out.println("List Size " + serverThreadList.size());
					String str = (String) t;
					if (str.startsWith("name ")) {
						String name = str.substring(5);
						setServerThreadName(name);
						System.out.println("new Client added " + name);
						String allClientName=getAllClientName();
						//sending updated string to all clients
						sendingFromServerToAllClient("name ", allClientName);						
					}
					else if(str.startsWith("SignUp ")){
						System.out.println("Sign up button pressed in client side");
						
					}
					else if(str.startsWith("LogIn ")){
						System.out.println("Log In button pressed in client side");
						oos.writeObject("message from server");
					}

				}

			} catch (Exception ex) {

			}

		}

	}

	void setServerThreadName(String name) {
		this.thr.setName(name);
	}

	void sendingFromServerToAllClient(String tag, String message) {
		for (int i = 0; i < serverThreadList.size(); i++) {
			try {
				serverThreadList.get(i).oos.writeObject(tag + "" + message);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.err.println("error at method sendingFromServer");
			}

		}
	}

	String getAllClientName() {
		String allClientName = "";
		for (int i = 0; i < serverThreadList.size(); i++) {
			if (allClientName.equals("")) {
				allClientName = serverThreadList.get(i).getClientName();
			}
			else{
				allClientName += " " + serverThreadList.get(i).getClientName();
			}
			
		}
		return allClientName;
	}

	String getClientName() {
		return this.thr.getName();
	}
}
