
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import javax.swing.JOptionPane;

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
			String userName = name.substring(5);
			frame.dispose();
			MainWindow mainWindow = new MainWindow(oos, ois, userName);
			mainWindow.setVisible(true);

			System.out.println("from client user logged in");

			// sending the name object to the server
			oos.writeObject(name);
			new ReadThread(oos,ois, name, userNameList, mainWindow);
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
	MainWindow mainWindow;
	ObjectOutputStream oos;

	public ReadThread(ObjectOutputStream oos, ObjectInputStream ois, String name, ArrayList<String> userNameList, MainWindow mainWindow) {
		this.ois = ois;
		this.name = name;
		this.oos=oos;
		this.userNameList = userNameList;
		this.mainWindow = mainWindow;
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
						userNameList = new ArrayList<String>(Arrays.asList(name.split(" ")));
						printingArrayList(userNameList);// online user list
						mainWindow.setUserNameList(userNameList);
					} else if (t.startsWith("pairRequest ")) {
						String requester = t.substring(12);
						System.out.println("Request from " + requester);
						int response=responseWindow(requester);
						if(response==-1 || response==1){
							oos.writeObject("responseRequest Failed");
						}
						else{
							oos.writeObject("responseRequest Accepted");
						}
						
						
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

public	int responseWindow(String name) {
		String[] buttons = { "Accept", "Decline" };

		int rc = JOptionPane.showOptionDialog(mainWindow, "Request From " + name, "Confirmation Box",
				JOptionPane.DEFAULT_OPTION, 0, null, buttons, buttons[1]);
		System.out.println(rc);

		return rc;
	}

}
