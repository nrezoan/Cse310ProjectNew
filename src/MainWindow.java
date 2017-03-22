import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
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
	private JButton btn00;
	private JButton btn01;
	private JButton btn02;
	private JButton btn10;
	private JButton btn11;
	private JButton btn12;
	private JButton btn20;
	private JButton btn21;
	private JButton btn22;
	private String whoseTurn = "X";
	private ObjectOutputStream oos;
	private ObjectInputStream ois;
	private String name="";
	private DefaultListModel<String> model;
	JList onlineList; 
	ArrayList<String> userNameList=null;
	   
	String[][] board = new String [3][3];
	



	/**
	 * Create the frame.
	 * @param ois 
	 * @param oos 
	 * @param name 
	 * @param userNameList 
	 */
	public MainWindow(ObjectOutputStream oos, ObjectInputStream ois, String name) {
		this.oos=oos;
		this.ois=ois;
		this.name=name;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		//start of tic tac toe panel
		ticTacToePanel = new JPanel();
		ticTacToePanel.setBounds(571, 150, 300, 300);
		ticTacToePanel.setBackground(new Color(219, 112, 147));
		contentPane.add(ticTacToePanel);
		ticTacToePanel.setLayout(new GridLayout(3, 3,1,1));
		
		btn00=new JButton();
		btn01=new JButton();
		btn02=new JButton();
		btn10=new JButton();
		btn11=new JButton();
		btn12=new JButton();
		btn20=new JButton();
		btn21=new JButton();
		btn22=new JButton();
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
				btn00.setText(whoseTurn);
		        if(whoseTurn.equalsIgnoreCase("X")){
		        	btn00.setForeground(Color.red);
		        	board[0][0]="x";
		    }
		    else{
		    	btn00.setForeground(Color.blue);
		    	board[0][0]="o";
		    }
		        determineWhoseTurn();
				
			}
		});
		btn01.setFont(new java.awt.Font("Verdana", 1, 48));
		btn01.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				btn01.setText(whoseTurn);
		        if(whoseTurn.equalsIgnoreCase("X")){
		        	btn01.setForeground(Color.red);
		        	board[0][1]="x";
		    }
		    else{
		    	btn01.setForeground(Color.blue);
		    	board[0][1]="o";
		    }
		        determineWhoseTurn();
				
			}
		});
		btn02.setFont(new java.awt.Font("Verdana", 1, 48));
		btn02.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				btn02.setText(whoseTurn);
		        if(whoseTurn.equalsIgnoreCase("X")){
		        	btn02.setForeground(Color.red);
		        	board[0][2]="x";
		    }
		    else{
		    	btn02.setForeground(Color.blue);
		    	board[0][2]="o";
		    }
		        determineWhoseTurn();
				
			}
		});
		btn10.setFont(new java.awt.Font("Verdana", 1, 48));
		btn10.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				btn10.setText(whoseTurn);
		        if(whoseTurn.equalsIgnoreCase("X")){
		        	btn10.setForeground(Color.red);
		        	board[1][0]="x";
		    }
		    else{
		    	btn10.setForeground(Color.blue);
		    	board[1][0]="o";
		    }
		        determineWhoseTurn();
				
			}
		});
		btn11.setFont(new java.awt.Font("Verdana", 1, 48));
		btn11.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				btn11.setText(whoseTurn);
		        if(whoseTurn.equalsIgnoreCase("X")){
		        	btn11.setForeground(Color.red);
		        	board[1][1]="x";
		    }
		    else{
		    	btn11.setForeground(Color.blue);
		    	board[1][1]="o";
		    }
		        determineWhoseTurn();
				
			}
		});
		btn12.setFont(new java.awt.Font("Verdana", 1, 48));
		btn12.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				btn12.setText(whoseTurn);
		        if(whoseTurn.equalsIgnoreCase("X")){
		        	btn12.setForeground(Color.red);
		        	board[1][2]="x";
		    }
		    else{
		    	btn12.setForeground(Color.blue);
		    	board[1][2]="o";
		    }
		        determineWhoseTurn();
				
			}
		});
		btn20.setFont(new java.awt.Font("Verdana", 1, 48));
		btn20.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				btn20.setText(whoseTurn);
		        if(whoseTurn.equalsIgnoreCase("X")){
		        	btn20.setForeground(Color.red);
		        	board[2][0]="x";
		    }
		    else{
		    	btn20.setForeground(Color.blue);
		    	board[2][0]="o";
		    }
		        determineWhoseTurn();
				
			}
		});
		btn21.setFont(new java.awt.Font("Verdana", 1, 48));
		btn21.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				btn21.setText(whoseTurn);
		        if(whoseTurn.equalsIgnoreCase("X")){
		        	btn21.setForeground(Color.red);
		        	board[2][1]="x";
		    }
		    else{
		    	btn21.setForeground(Color.blue);
		    	board[2][1]="o";
		    }
		        determineWhoseTurn();
				
			}
		});
		btn22.setFont(new java.awt.Font("Verdana", 1, 48));
		btn22.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				btn22.setText(whoseTurn);
		        if(whoseTurn.equalsIgnoreCase("X")){
		        	btn22.setForeground(Color.red);
		        	board[2][2]="x";
		    }
		    else{
		    	btn22.setForeground(Color.blue);
		    	board[2][2]="o";
		    }
		        determineWhoseTurn();
				
			}
		});


		
		
		
		
		///end of tic tac toe
		JPanel chatPanel = new JPanel();
		chatPanel.setBounds(10, 75, 540, 375);
		contentPane.add(chatPanel);
		chatPanel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 54, 148, 274);
		chatPanel.add(scrollPane);
		
		model = new DefaultListModel<String>();
		onlineList =  new JList<String>(model);
		scrollPane.setViewportView(onlineList);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(158, 54, 382, 274);
		chatPanel.add(scrollPane_1);
		
		JTextArea msgArea = new JTextArea();
		scrollPane_1.setViewportView(msgArea);
		
		JButton btnSend = new JButton("SEND");
		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
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
	
	public void setUserNameList(ArrayList<String> userNameList){
		this.userNameList=userNameList;
	}
	
	   private void determineWhoseTurn(){
		    if(whoseTurn.equalsIgnoreCase("X")){
		    whoseTurn = "O";
		    }
		    else{
		    whoseTurn = "X";
		    }
		    }
}
