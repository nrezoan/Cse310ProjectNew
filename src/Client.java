
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Scanner;

public class Client {

	private static ObjectOutputStream oos;
	private static ObjectInputStream ois;
	private static ArrayList<String> userNameList;

	public static void main(String args[]) {
		try {
			String serverAddress = "127.0.0.1";
			int serverPort = 33333;
			Socket client = new Socket(serverAddress, serverPort);
			Scanner input = new Scanner(System.in);
			userNameList = new ArrayList<String>();
			oos = new ObjectOutputStream(client.getOutputStream());
			ois = new ObjectInputStream(client.getInputStream());

			// here user-registration and
			// user log-in will take place
			//
			UserRegistration frame = new UserRegistration(oos, ois);
			frame.setVisible(true);
			while (!frame.isLoggedIn()) {
				System.out.println(frame.isLoggedIn());
			}
			String name = "name " + frame.getUserName();
			frame.dispose();
			new MainWindow(oos,ois).setVisible(true);

			System.out.println("from client user logged in");

			
			// sending the name object to the server
			oos.writeObject(name);
			new ReadThread(ois, name, userNameList);
			while (true) {
				// String s=br.readLine();
				String s = input.nextLine();
				oos.writeObject(s);
			}

		} catch (Exception e) {
			System.out.println(e);
		}
	}

}

class ReadThread implements Runnable {
	private Thread thr;
	ObjectInputStream ois;
	String name;
	ArrayList<String> userNameList;

	public ReadThread(ObjectInputStream ois, String name, ArrayList<String> userNameList) {
		this.ois = ois;
		this.name = name;
		this.userNameList = userNameList;
		this.thr = new Thread(this);
		thr.start();
	}

	public void run() {
		try {
			while (true) {
				String t = (String) ois.readObject();
				if (t != null) {
					if (t.startsWith("name ")) {
						String name = t.substring(5);
						ArrayList<String> nameList = new ArrayList<String>(Arrays.asList(name.split(" ")));
						printingArrayList(nameList);//online user list
					}
				}

			}
		} catch (Exception e) {
			System.out.println(e);
		}

	}

	void updateUserNameList() {

	}

	void printingArrayList(ArrayList<String> nameList) {
		for (int i = 0; i < nameList.size(); i++) {
			System.out.println(nameList.get(i));
		}
	}

}
