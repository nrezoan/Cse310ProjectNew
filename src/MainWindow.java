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
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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
	private JButton[][] btn = new JButton[3][3];

	private String whoseTurn = null;
	private ObjectOutputStream oos;
	private ObjectInputStream ois;
	private String name = "";
	private DefaultListModel<String> model;
	JList onlineList;
	ArrayList<String> userNameList = null;
	private String wholeText = "";
	private String currentText = "";
	JTextArea msgArea = null;

	String[][] board = new String[3][3];
	boolean[][] checkBoard = new boolean[3][3];
	String winner = "";
	// boolean pressable = true;
	int countPressed = 0;
	private boolean myTurn = true;
	String selectedName = "";

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
		setTitle("Tic Tac Toe");

		// start of tic tac toe panel
		ticTacToePanel = new JPanel();
		ticTacToePanel.setBounds(571, 150, 300, 300);
		ticTacToePanel.setBackground(new Color(219, 112, 147));
		contentPane.add(ticTacToePanel);
		ticTacToePanel.setLayout(new GridLayout(3, 3, 1, 1));
		ticTacToePanel.setVisible(false);

		btn[0][0] = new JButton();
		btn[0][1] = new JButton();
		btn[0][2] = new JButton();
		btn[1][0] = new JButton();
		btn[1][1] = new JButton();
		btn[1][2] = new JButton();
		btn[2][0] = new JButton();
		btn[2][1] = new JButton();
		btn[2][2] = new JButton();
		ticTacToePanel.add(btn[0][0]);
		ticTacToePanel.add(btn[0][1]);
		ticTacToePanel.add(btn[0][2]);
		ticTacToePanel.add(btn[1][0]);
		ticTacToePanel.add(btn[1][1]);
		ticTacToePanel.add(btn[1][2]);
		ticTacToePanel.add(btn[2][0]);
		ticTacToePanel.add(btn[2][1]);
		ticTacToePanel.add(btn[2][2]);

		resetBoard();

		btn[0][0].setFont(new java.awt.Font("Verdana", 1, 48));
		btn[0][0].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (checkBoard[0][0] == false && myTurn == true) {
					btn[0][0].setText(whoseTurn);
					if (whoseTurn.equalsIgnoreCase("X")) {
						btn[0][0].setForeground(Color.red);
						board[0][0] = "x";
						sendingMyTurn("x", "0", "0");

					} else {
						btn[0][0].setForeground(Color.blue);
						board[0][0] = "o";
						sendingMyTurn("o", "0", "0");
					}
					countPressed++;
					checkBoard[0][0] = true;
					myTurn = false;
					determineIfWin();
					determineIfDraw();
				}
			}
		});
		btn[0][1].setFont(new java.awt.Font("Verdana", 1, 48));
		btn[0][1].addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (checkBoard[0][1] == false && myTurn == true) {
					btn[0][1].setText(whoseTurn);
					if (whoseTurn.equalsIgnoreCase("X")) {
						btn[0][1].setForeground(Color.red);
						board[0][1] = "x";
						sendingMyTurn("x", "0", "1");
					} else {
						btn[0][1].setForeground(Color.blue);
						board[0][1] = "o";
						sendingMyTurn("o", "0", "1");
					}
					countPressed++;
					checkBoard[0][1] = true;
					determineIfWin();
					determineIfDraw();
					myTurn = false;
				}
			}
		});
		btn[0][2].setFont(new java.awt.Font("Verdana", 1, 48));
		btn[0][2].addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (checkBoard[0][2] == false && myTurn == true) {
					btn[0][2].setText(whoseTurn);
					if (whoseTurn.equalsIgnoreCase("X")) {
						btn[0][2].setForeground(Color.red);
						board[0][2] = "x";
						sendingMyTurn("x", "0", "2");
					} else {
						btn[0][2].setForeground(Color.blue);
						board[0][2] = "o";
						sendingMyTurn("o", "0", "2");
					}
					countPressed++;
					checkBoard[0][2] = true;
					determineIfWin();
					determineIfDraw();
					myTurn = false;
				}
			}
		});
		btn[1][0].setFont(new java.awt.Font("Verdana", 1, 48));
		btn[1][0].addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (checkBoard[1][0] == false && myTurn == true) {
					btn[1][0].setText(whoseTurn);
					if (whoseTurn.equalsIgnoreCase("X")) {
						btn[1][0].setForeground(Color.red);
						board[1][0] = "x";
						sendingMyTurn("x", "1", "0");
					} else {
						btn[1][0].setForeground(Color.blue);
						board[1][0] = "o";
						sendingMyTurn("o", "1", "0");
					}
					countPressed++;
					checkBoard[1][0] = true;
					determineIfWin();
					determineIfDraw();
					myTurn = false;
				}
			}
		});
		btn[1][1].setFont(new java.awt.Font("Verdana", 1, 48));
		btn[1][1].addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (checkBoard[1][1] == false && myTurn == true) {
					btn[1][1].setText(whoseTurn);
					if (whoseTurn.equalsIgnoreCase("X")) {
						btn[1][1].setForeground(Color.red);
						board[1][1] = "x";
						sendingMyTurn("x", "1", "1");
					} else {
						btn[1][1].setForeground(Color.blue);
						board[1][1] = "o";
						sendingMyTurn("o", "1", "1");
					}
					countPressed++;
					checkBoard[1][1] = true;
					determineIfWin();
					determineIfDraw();
					myTurn = false;
				}
			}
		});
		btn[1][2].setFont(new java.awt.Font("Verdana", 1, 48));
		btn[1][2].addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (checkBoard[1][2] == false && myTurn == true) {
					btn[1][2].setText(whoseTurn);
					if (whoseTurn.equalsIgnoreCase("X")) {
						btn[1][2].setForeground(Color.red);
						board[1][2] = "x";
						sendingMyTurn("x", "1", "2");
					} else {
						btn[1][2].setForeground(Color.blue);
						board[1][2] = "o";
						sendingMyTurn("o", "1", "2");
					}
					countPressed++;
					checkBoard[1][2] = true;
					determineIfWin();
					determineIfDraw();
					myTurn = false;
				}
			}
		});
		btn[2][0].setFont(new java.awt.Font("Verdana", 1, 48));
		btn[2][0].addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (checkBoard[2][0] == false && myTurn == true) {
					btn[2][0].setText(whoseTurn);
					if (whoseTurn.equalsIgnoreCase("X")) {
						btn[2][0].setForeground(Color.red);
						board[2][0] = "x";
						sendingMyTurn("x", "2", "0");
					} else {
						btn[2][0].setForeground(Color.blue);
						board[2][0] = "o";
						sendingMyTurn("o", "2", "0");
					}
					countPressed++;
					checkBoard[2][0] = true;
					determineIfWin();
					determineIfDraw();
					myTurn = false;

				}
			}
		});
		btn[2][1].setFont(new java.awt.Font("Verdana", 1, 48));
		btn[2][1].addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (checkBoard[2][1] == false && myTurn == true) {
					btn[2][1].setText(whoseTurn);
					if (whoseTurn.equalsIgnoreCase("X")) {
						btn[2][1].setForeground(Color.red);
						board[2][1] = "x";
						sendingMyTurn("x", "2", "1");
					} else {
						btn[2][1].setForeground(Color.blue);
						board[2][1] = "o";
						sendingMyTurn("o", "2", "1");
					}
					countPressed++;
					checkBoard[2][1] = true;
					determineIfWin();
					determineIfDraw();
					myTurn = false;
				}
			}
		});
		btn[2][2].setFont(new java.awt.Font("Verdana", 1, 48));
		btn[2][2].addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (checkBoard[2][2] == false && myTurn == true) {
					btn[2][2].setText(whoseTurn);
					if (whoseTurn.equalsIgnoreCase("X")) {
						btn[2][2].setForeground(Color.red);
						board[2][2] = "x";
						sendingMyTurn("x", "2", "2");
					} else {
						btn[2][2].setForeground(Color.blue);
						board[2][2] = "o";
						sendingMyTurn("o", "2", "2");
					}
					countPressed++;
					checkBoard[2][2] = true;
					determineIfWin();
					determineIfDraw();
					myTurn = false;
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
					selectedName = onlineList.getSelectedValue().toString();
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
					oos.writeObject("Message " + currentText);
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

		JLabel userName = new JLabel(name.toUpperCase());
		userName.setBounds(328, 8, 106, 36);
		chatPanel.add(userName);
		userName.setFont(new Font("Arial", Font.BOLD, 14));
		userName.setHorizontalAlignment(SwingConstants.TRAILING);

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
		btnLogout.setBounds(240, 11, 120, 36);
		headerPanel.add(btnLogout);

		JButton btnNewGame = new JButton("New Game");
		btnNewGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ticTacToePanel.setVisible(false);
				scrollPaneOnline.setVisible(true);
				try {
					oos.writeObject("Quit ");
					resetBoard();
				} catch (IOException e1) {
					System.out.println("Main window 460");
					e1.printStackTrace();
				}
			}
		});
		btnNewGame.setBounds(0, 11, 120, 36);
		headerPanel.add(btnNewGame);

		JButton btnSendRequest = new JButton("Send Request");
		btnSendRequest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					oos.writeObject("pairRequest " + selectedName);
					onlineList.clearSelection();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					System.err.println("main window 461");
					e1.printStackTrace();
				}
			}
		});
		btnSendRequest.setBounds(120, 11, 120, 36);
		headerPanel.add(btnSendRequest);

		JButton btnViewMyProfile = new JButton("View My Profile");
		btnViewMyProfile.setBounds(691, 11, 160, 36);
		headerPanel.add(btnViewMyProfile);

		JButton btnViewOpponetProfile = new JButton("View Opponent Profile");
		btnViewOpponetProfile.setBounds(531, 11, 160, 36);
		headerPanel.add(btnViewOpponetProfile);

		JLabel lblXxxHasWon = new JLabel("Tic Tac Toe");
		lblXxxHasWon.setBounds(250, 15, 374, 27);
		headerPanel.add(lblXxxHasWon);
		lblXxxHasWon.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblXxxHasWon.setHorizontalAlignment(SwingConstants.CENTER);
		btnViewOpponetProfile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (!selectedName.equals("")) {
					try {
						oos.writeObject("RequestScore " + selectedName);
						selectedName = "";
					} catch (IOException e1) {
						System.err.println("error at chatWindow line 294");
						e1.printStackTrace();
					}
				}
			}
		});
		btnViewMyProfile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					oos.writeObject("ViewProfile " + name);
					System.out.println("Clicked view " + name);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

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
		this.whoseTurn = coin;
	}

	public void setStringToMessageArea(String msg) {
		if (wholeText.equals("")) {
			wholeText = msg;
		} else {
			wholeText += "\n" + msg;
		}

		msgArea.setText(wholeText);
	}

	public void sendingMyTurn(String val, String posX, String posY) {
		try {
			oos.writeObject("Turn " + val + " " + posX + " " + posY);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void setOpponentToken(String val, int row, int colum) {

		if (checkBoard[row][colum] == false) {

			if (val.equalsIgnoreCase("X")) {
				btn[row][colum].setText(val);
				btn[row][colum].setForeground(Color.red);
				board[row][colum] = "x";

			} else {
				btn[row][colum].setText(val);
				btn[row][colum].setForeground(Color.blue);
				board[row][colum] = "o";

			}
			countPressed++;
			checkBoard[row][colum] = true;
			determineIfWin();
			determineIfDraw();
			myTurn = true;
		}
	}

	private void determineIfDraw() {
		if (countPressed == 9) {
			JOptionPane.showMessageDialog(null, "The Game is draw");
			try {
				oos.writeObject("Draw " + name);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			resetBoard();
		}
	}

	private void determineIfWin() {

		// for x
		if (board[0][0].equals("x") && board[0][1].equals("x") && board[0][2].equals("x")) {
			winner = "x";
		} else if (board[1][0].equals("x") && board[1][1].equals("x") && board[1][2].equals("x")) {
			winner = "x";
		} else if (board[2][0].equals("x") && board[2][1].equals("x") && board[2][2].equals("x")) {
			winner = "x";
		} else if (board[0][0].equals("x") && board[1][0].equals("x") && board[2][0].equals("x")) {
			winner = "x";
		} else if (board[0][1].equals("x") && board[1][1].equals("x") && board[2][1].equals("x")) {
			winner = "x";
		} else if (board[0][2].equals("x") && board[1][2].equals("x") && board[2][2].equals("x")) {
			winner = "x";
		} else if (board[0][0].equals("x") && board[1][1].equals("x") && board[2][2].equals("x")) {
			winner = "x";
		} else if (board[0][2].equals("x") && board[1][1].equals("x") && board[2][0].equals("x")) {
			winner = "x";
		}
		// for o
		else if (board[0][0].equals("o") && board[0][1].equals("o") && board[0][2].equals("o")) {
			winner = "o";
		} else if (board[1][0].equals("o") && board[1][1].equals("o") && board[1][2].equals("o")) {
			winner = "o";
		} else if (board[2][0].equals("o") && board[2][1].equals("o") && board[2][2].equals("o")) {
			winner = "o";
		} else if (board[0][0].equals("o") && board[1][0].equals("o") && board[2][0].equals("o")) {
			winner = "o";
		} else if (board[0][1].equals("o") && board[1][1].equals("o") && board[2][1].equals("o")) {
			winner = "o";
		} else if (board[0][2].equals("o") && board[1][2].equals("o") && board[2][2].equals("o")) {
			winner = "o";
		} else if (board[0][0].equals("o") && board[1][1].equals("o") && board[2][2].equals("o")) {
			winner = "o";
		} else if (board[0][2].equals("o") && board[1][1].equals("o") && board[2][0].equals("o")) {
			winner = "o";
		}

		if (!winner.equals("")) {
			if (winner.equals(whoseTurn)) {
				JOptionPane.showMessageDialog(null, winner + "- You have won");
				try {
					oos.writeObject("Winner " + name);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				JOptionPane.showMessageDialog(null, "You lose");
				try {
					oos.writeObject("Lost " + name);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			resetBoard();
		}
	}

	public void resetBoard() {
		winner = "";
		countPressed = 0;
		myTurn = true;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				board[i][j] = "";
				checkBoard[i][j] = false;
				btn[i][j].setText("");
			}
		}
	}

	public void showProfileInfo(String val) {

		JOptionPane.showMessageDialog(null, val);
	}

	public void flipMyTurn() {
		if (!myTurn) {
			myTurn = true;
		}
	}

}
