
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.UnknownHostException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Server {

	private ServerSocket ServSock;
	static ArrayList<ServerThread> serverThreadList;
	static DAO dao;

	Server() {
		try {

			ServSock = new ServerSocket(33333);
			serverThreadList = new ArrayList<ServerThread>();
			dao = new DAO();
			System.out.println("Server running at port 33333");
			while (true) {

				ServerThread m = new ServerThread(ServSock.accept(), serverThreadList, dao);

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
	private DAO dao;
	private ServerThread opponent = null;

	ServerThread(Socket client, ArrayList<ServerThread> serverThreadList, DAO dao) {
		try {
			this.ClientSock = client;
			// adding the thread to the list

			oos = new ObjectOutputStream(ClientSock.getOutputStream());
			ois = new ObjectInputStream(ClientSock.getInputStream());
			this.serverThreadList = serverThreadList;
			this.thr = new Thread(this);
			this.dao = dao;
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
						String allClientName = getAllClientName();
						// sending updated string to all clients
						sendingFromServerToAllClient("name ", allClientName);
					} else if (str.startsWith("SignUp ")) {
						String queryValues = str.substring(7);
						System.out.println(queryValues);
						ArrayList<String> valList = new ArrayList<String>(Arrays.asList(queryValues.split(" ")));
						int age = Integer.parseInt(valList.get(1));
						dao.signUp(valList.get(0), age, valList.get(2), valList.get(3), valList.get(4));

					} else if (str.startsWith("LogIn ")) {
						String queryValues = str.substring(6);
						ArrayList<String> valList = new ArrayList<String>(Arrays.asList(queryValues.split(" ")));
						boolean state = dao.logIn(valList.get(0), valList.get(1));
						if (state) {
							oos.writeObject("Success");
							serverThreadList.add(this);// this line will happen
														// after login
							client_count++;
						} else {
							oos.writeObject("Failed");
						}

					} else if (str.equals("Logout")) {
						serverThreadList.remove(this);
						String allClientName = getAllClientName();
						// sending updated string to all clients
						sendingFromServerToAllClient("name ", allClientName);
						System.out.println("All current clients are after logout " + getAllClientName());
						return;
					} else if (str.startsWith("pairRequest ")) {
						String pairClient = str.substring(12);
						System.out.println("The selected client " + pairClient);
						ServerThread serverThread = matchingPair(pairClient);
						serverThread.oos.writeObject("pairRequest " + this.getClientName());
					}

					else if (str.startsWith("responseRequest ")) {
						String response = str.substring(16);
						ArrayList<String> respondedNameConfirmation = new ArrayList<String>(
								Arrays.asList(response.split(" ")));
						String name = respondedNameConfirmation.get(0);
						String confirmation = respondedNameConfirmation.get(1);
						ServerThread serverThread = matchingPair(name);
						serverThread.oos.writeObject("responseInfo " + this.thr.getName() + " " + confirmation);
						if (confirmation.equals("Accepted")) {
							System.out.println("Pairing done");
							opponent = serverThread;
							System.out.println("After Accept is pressed");
							System.out.println(
									"Opponent= " + opponent.getClientName() + " &&&& " + "Me= " + this.thr.getName());
						}
						System.out.println("Request from " + name + " Request to " + this.thr.getName()); //// ********///////
					}

					else if (str.startsWith("opponent ")) {
						String opponentName = str.substring(9);
						ServerThread serverThread = matchingPair(opponentName);
						opponent = serverThread;
						System.out.println("After OK is pressed");
						System.out.println(
								"Opponent= " + opponent.getClientName() + " &&&& " + "Me= " + this.thr.getName());
					} else if (str.startsWith("Message ")) {
						if (opponent != null) {
							opponent.oos.writeObject(str);
						}

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

	public String getAllClientName() {
		String allClientName = "";
		for (int i = 0; i < serverThreadList.size(); i++) {
			if (allClientName.equals("")) {
				allClientName = serverThreadList.get(i).getClientName();
			} else {
				allClientName += " " + serverThreadList.get(i).getClientName();
			}

		}
		return allClientName;
	}

	public String getClientName() {
		return this.thr.getName();
	}

	public ServerThread matchingPair(String name) throws NullPointerException {

		ServerThread serverThread = null;
		for (int i = 0; i < serverThreadList.size(); i++) {
			if (serverThreadList.get(i).getClientName().equals(name)) {
				serverThread = serverThreadList.get(i);
			}
		}
		return serverThread;
	}

	public boolean pairChecking() {
		if (opponent != null) {
			if (this.equals(opponent.opponent)) {
				return true;
			} else {
				return false;
			}

		} else {
			return false;
		}
	}
}
