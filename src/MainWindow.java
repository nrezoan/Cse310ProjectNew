import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.JScrollBar;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;

public class MainWindow extends JFrame {

	private JPanel contentPane;
	private JTextField textArea;
	private JPanel ticTacToePanel;
	private JScrollPane scrollPaneOnline;
	private JButton btn00;
	private JButton btn01;
	private JButton btn02;
	private JButton btn10;
	private JButton btn11;
	private JButton btn12;
	private JButton btn20;
	private JButton btn21;
	private JButton btn22;
	private String whoseTurn = null;
	private ObjectOutputStream oos;
	private ObjectInputStream ois;
	private String name = "";
	private DefaultListModel<String> model;
	JList onlineList;
	ArrayList<String> userNameList = null;
	private String wholeText = "";
	private String currentText = "";
	JTextArea msgArea=null;

	String[][] board = new String[3][3];
	boolean[][] checkBoard = new boolean[3][3];

	/**
	 * Create the frame.
	 * 
	 * @param ois
	 * @param oos
	 * @param name
	 * @param userNameList
	 */
	public MainWindow(ObjectOutputStream oos, ObjectInputStream ois, String name) {
		this.oos = oos;
		this.ois = ois;
		this.name = name;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// start of tic tac toe panel
		ticTacToePanel = new JPanel();
		ticTacToePanel.setBounds(571, 150, 300, 300);
		ticTacToePanel.setBackground(new Color(219, 112, 147));
		contentPane.add(ticTacToePanel);
		ticTacToePanel.setLayout(new GridLayout(3, 3, 1, 1));
		ticTacToePanel.setVisible(false);

		btn00 = new JButton();
		btn01 = new JButton();
		btn02 = new JButton();
		btn10 = new JButton();
		btn11 = new JButton();
		btn12 = new JButton();
		btn20 = new JButton();
		btn21 = new JButton();
		btn22 = new JButton();
		ticTacToePanel.add(btn00);
		ticTacToePanel.add(btn01);
		ticTacToePanel.add(btn02);
		ticTacToePanel.add(btn10);
		ticTacToePanel.add(btn11);
		ticTacToePanel.add(btn12);
		ticTacToePanel.add(btn20);
		ticTacToePanel.add(btn21);
		ticTacToePanel.add(btn22);
		btn00.setFont(new java.awt.Font("Verdana", 1, 48));
		btn00.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(checkBoard[0][0]==false){
				btn00.setText(whoseTurn);
				if (whoseTurn.equalsIgnoreCase("X")) {
					btn00.setForeground(Color.red);
					board[0][0] = "x";
					sendingMyTurn("x", "0", "0");
					
				} else {
					btn00.setForeground(Color.blue);
					board[0][0] = "o";
					sendingMyTurn("o", "0", "0");
				}
				checkBoard[0][0]=true;
				
				}
			}
		});
		btn01.setFont(new java.awt.Font("Verdana", 1, 48));
		btn01.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(checkBoard[0][1]==false){
				btn01.setText(whoseTurn);
				if (whoseTurn.equalsIgnoreCase("X")) {
					btn01.setForeground(Color.red);
					board[0][1] = "x";
					sendingMyTurn("x", "0", "1");
				} else {
					btn01.setForeground(Color.blue);
					board[0][1] = "o";
					sendingMyTurn("o", "0", "1");
				}
				checkBoard[0][1]=true;
			
				}
			}
		});
		btn02.setFont(new java.awt.Font("Verdana", 1, 48));
		btn02.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(checkBoard[0][2]==false){
				btn02.setText(whoseTurn);
				if (whoseTurn.equalsIgnoreCase("X")) {
					btn02.setForeground(Color.red);
					board[0][2] = "x";
					sendingMyTurn("x", "0", "2");
				} else {
					btn02.setForeground(Color.blue);
					board[0][2] = "o";
					sendingMyTurn("o", "0", "2");
				}
				
				checkBoard[0][2]=true;
				}
			}
		});
		btn10.setFont(new java.awt.Font("Verdana", 1, 48));
		btn10.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(checkBoard[1][0]==false){
				btn10.setText(whoseTurn);
				if (whoseTurn.equalsIgnoreCase("X")) {
					btn10.setForeground(Color.red);
					board[1][0] = "x";
					sendingMyTurn("x", "1", "0");
				} else {
					btn10.setForeground(Color.blue);
					board[1][0] = "o";
					sendingMyTurn("o", "1", "0");
				}
				checkBoard[1][0]=true;
		
				}
			}
		});
		btn11.setFont(new java.awt.Font("Verdana", 1, 48));
		btn11.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(checkBoard[1][1]==false){
				btn11.setText(whoseTurn);
				if (whoseTurn.equalsIgnoreCase("X")) {
					btn11.setForeground(Color.red);
					board[1][1] = "x";
					sendingMyTurn("x", "1", "1");
				} else {
					btn11.setForeground(Color.blue);
					board[1][1] = "o";
					sendingMyTurn("o", "1", "1");
				}
				checkBoard[1][1]=true;
			
				}
			}
		});
		btn12.setFont(new java.awt.Font("Verdana", 1, 48));
		btn12.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(checkBoard[1][2]==false){
				btn12.setText(whoseTurn);
				if (whoseTurn.equalsIgnoreCase("X")) {
					btn12.setForeground(Color.red);
					board[1][2] = "x";
					sendingMyTurn("x", "1", "2");
				} else {
					btn12.setForeground(Color.blue);
					board[1][2] = "o";
					sendingMyTurn("o", "1", "2");
				}
				checkBoard[1][2]=true;
				
				}
			}
		});
		btn20.setFont(new java.awt.Font("Verdana", 1, 48));
		btn20.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(checkBoard[2][0]==false){
				btn20.setText(whoseTurn);
				if (whoseTurn.equalsIgnoreCase("X")) {
					btn20.setForeground(Color.red);
					board[2][0] = "x";
					sendingMyTurn("x", "2", "0");
				} else {
					btn20.setForeground(Color.blue);
					board[2][0] = "o";
					sendingMyTurn("o", "2", "0");
				}
				checkBoard[2][0]=true;
			
				}
			}
		});
		btn21.setFont(new java.awt.Font("Verdana", 1, 48));
		btn21.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(checkBoard[2][1]==false){
				btn21.setText(whoseTurn);
				if (whoseTurn.equalsIgnoreCase("X")) {
					btn21.setForeground(Color.red);
					board[2][1] = "x";
					sendingMyTurn("x", "2", "1");
				} else {
					btn21.setForeground(Color.blue);
					board[2][1] = "o";
					sendingMyTurn("o", "2", "1");
				}
				checkBoard[2][1]=true;
				
				}
			}
		});
		btn22.setFont(new java.awt.Font("Verdana", 1, 48));
		btn22.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(checkBoard[2][2]==false){
				btn22.setText(whoseTurn);
				if (whoseTurn.equalsIgnoreCase("X")) {
					btn22.setForeground(Color.red);
					board[2][2] = "x";
					sendingMyTurn("x", "2", "2");
				} else {
					btn22.setForeground(Color.blue);
					board[2][2] = "o";
					sendingMyTurn("o", "2", "2");
				}
				checkBoard[2][2]=true;
				
				}
			}
		});

		/// end of tic tac toe
		JPanel chatPanel = new JPanel();
		chatPanel.setBounds(10, 75, 540, 375);
		contentPane.add(chatPanel);
		chatPanel.setLayout(null);

		scrollPaneOnline = new JScrollPane();
		scrollPaneOnline.setBounds(0, 54, 148, 274);
		chatPanel.add(scrollPaneOnline);

		model = new DefaultListModel<String>();
		onlineList = new JList<String>(model);
		scrollPaneOnline.setViewportView(onlineList);

		onlineList.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (!e.getValueIsAdjusting()) {
					String temp = onlineList.getSelectedValue().toString();
					try {
						oos.writeObject("pairRequest " + temp);

					} catch (IOException e1) {
						System.err.println("error at chatWindow line 294");
						e1.printStackTrace();
					}
				}

			}
		});

		JScrollPane scrollPaneMsgArea = new JScrollPane();
		scrollPaneMsgArea.setBounds(158, 54, 382, 274);
		chatPanel.add(scrollPaneMsgArea);

		 msgArea = new JTextArea();
		scrollPaneMsgArea.setViewportView(msgArea);

		JButton btnSend = new JButton("SEND");
		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				currentText = name + ": " + textArea.getText();
				try {
					oos.writeObject("Message "+currentText);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (wholeText.equals("")) {
					wholeText = currentText;
				} else {
					wholeText += "\n" + currentText;
				}

				msgArea.setText(wholeText);
				textArea.setText("");
			}
			
		});
		btnSend.setBounds(434, 339, 106, 36);
		chatPanel.add(btnSend);

		JButton btnRefresh = new JButton("Refresh");
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				model.clear();
				for (int i = 0; i < userNameList.size(); i++) {
					System.out.println("line 120");
					if (!name.equals(userNameList.get(i))) {
						model.addElement(userNameList.get(i));
					}
				}

			}
		});
		btnRefresh.setBounds(0, 339, 97, 36);
		chatPanel.add(btnRefresh);

		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(157, 339, 277, 36);
		chatPanel.add(scrollPane_2);

		textArea = new JTextField();
		scrollPane_2.setViewportView(textArea);
		textArea.setColumns(10);

		JLabel olinePlayers = new JLabel("Oline Players");
		olinePlayers.setHorizontalAlignment(SwingConstants.CENTER);
		olinePlayers.setBounds(0, 11, 148, 32);
		chatPanel.add(olinePlayers);

		JLabel requestToPlay = new JLabel("## Wants to play ");
		requestToPlay.setHorizontalAlignment(SwingConstants.CENTER);
		requestToPlay.setBounds(158, 9, 156, 36);
		chatPanel.add(requestToPlay);

		JButton btnAccept = new JButton("Accept");
		btnAccept.setBounds(313, 9, 106, 36);
		chatPanel.add(btnAccept);

		JButton btnDecline = new JButton("Decline");
		btnDecline.setBounds(424, 9, 106, 36);
		chatPanel.add(btnDecline);

		JPanel headerPanel = new JPanel();
		headerPanel.setBounds(10, 11, 861, 53);
		contentPane.add(headerPanel);
		headerPanel.setLayout(null);

		JButton btnLogout = new JButton("Logout");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					oos.writeObject("Logout");
					dispose();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnLogout.setBounds(762, 11, 89, 23);
		headerPanel.add(btnLogout);

		JLabel userName = new JLabel(name);
		userName.setBounds(698, 15, 54, 14);
		headerPanel.add(userName);

		JButton btnNewGame = new JButton("New Game");
		btnNewGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewGame.setBounds(10, 11, 106, 36);
		headerPanel.add(btnNewGame);

		JButton btnViewProfile = new JButton("View Profile");
		btnViewProfile.setBounds(124, 11, 106, 36);
		headerPanel.add(btnViewProfile);

		JLabel lblXxxHasWon = new JLabel("xxx Has Won ");
		lblXxxHasWon.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblXxxHasWon.setHorizontalAlignment(SwingConstants.CENTER);
		lblXxxHasWon.setBounds(279, 15, 374, 27);
		headerPanel.add(lblXxxHasWon);

		JLabel myName = new JLabel("XXX");
		myName.setHorizontalAlignment(SwingConstants.CENTER);
		myName.setFont(new Font("Tahoma", Font.BOLD, 15));
		myName.setBounds(572, 93, 99, 37);
		contentPane.add(myName);

		JLabel opponentName = new JLabel("YYY");
		opponentName.setHorizontalAlignment(SwingConstants.CENTER);
		opponentName.setFont(new Font("Tahoma", Font.BOLD, 15));
		opponentName.setBounds(772, 93, 99, 37);
		contentPane.add(opponentName);

		JLabel lblVs = new JLabel("VS");
		lblVs.setHorizontalAlignment(SwingConstants.CENTER);
		lblVs.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblVs.setBounds(678, 75, 95, 64);
		contentPane.add(lblVs);

	}

	public void setVisibilityGame(boolean val) {
		ticTacToePanel.setVisible(val);
	}

	public void setVisibilityOnlineWindow(boolean val) {
		scrollPaneOnline.setVisible(val);
	}

	public void setUserNameList(ArrayList<String> userNameList) {
		this.userNameList = userNameList;
	}

	public void determineWhoseTurn(String coin) {
		this.whoseTurn=coin;
	}
	
	public void setStringToMessageArea(String msg){
		if(wholeText.equals("")){
			wholeText=msg;
		}
		else{
			wholeText+="\n"+msg;
		}
		
		msgArea.setText(wholeText);
	}
	
	public void sendingMyTurn(String val,String posX,String posY){
		try {
			oos.writeObject("Turn "+val+" "+posX+" "+posY);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void setOpponentToken(String val,int row,int colum){
		
		if(checkBoard[row][colum]==false){
			
			
			if (val.equalsIgnoreCase("X")) {
				btn00.setForeground(Color.red);
				board[row][colum] = "x";
				
				
			} else {
				btn00.setForeground(Color.blue);
				board[row][colum] = "o";
				
			}
			checkBoard[row][colum]=true;
		}
		}
		
	}

