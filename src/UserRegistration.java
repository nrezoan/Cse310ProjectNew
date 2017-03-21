import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.UIManager;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.awt.event.ActionEvent;

public class UserRegistration extends JFrame {

	private JPanel contentPane;
	private JTextField textField_name;
	private JPasswordField textField_password;
	private JTextField textField_email;
	private JTextField textField_2;
	private JPasswordField passwordField_1;
	private JTextField textField_age;
	private JPasswordField textField_confirmation;
	private ObjectOutputStream oos;
	private ObjectInputStream ois;
	private UserRegistrationGetterSetter registrationGetterSetter;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 * @param ois
	 * @param oos
	 */
	public UserRegistration(ObjectOutputStream oos, ObjectInputStream ois) {
		registrationGetterSetter = new UserRegistrationGetterSetter();
		this.oos = oos;
		this.ois = ois;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(220, 220, 220));
		panel.setBounds(0, 0, 434, 461);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblRegisterHere = new JLabel("Register Here");
		lblRegisterHere.setForeground(new Color(255, 255, 255));
		lblRegisterHere.setHorizontalAlignment(SwingConstants.CENTER);
		lblRegisterHere.setFont(new Font("Bell Gothic Std Black", Font.PLAIN, 24));
		lblRegisterHere.setBounds(101, 60, 234, 44);
		panel.add(lblRegisterHere);

		JLabel lblName = new JLabel("Name");
		lblName.setForeground(new Color(255, 255, 255));
		lblName.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblName.setBounds(43, 144, 71, 20);
		panel.add(lblName);

		textField_name = new JTextField();
		textField_name.setBackground(new Color(245, 245, 245));
		textField_name.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textField_name.setBounds(133, 138, 199, 26);
		panel.add(textField_name);
		textField_name.setColumns(10);
		

		JLabel lblPassword = new JLabel("Password");
		lblPassword.setForeground(new Color(255, 255, 255));
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblPassword.setBounds(43, 309, 86, 20);
		panel.add(lblPassword);

		textField_password = new JPasswordField();
		textField_password.setBackground(new Color(245, 245, 245));
		textField_password.setBounds(133, 303, 199, 26);
		panel.add(textField_password);
		

		JLabel lblEmail = new JLabel("Email");
		lblEmail.setForeground(new Color(255, 255, 255));
		lblEmail.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblEmail.setBounds(43, 254, 71, 20);
		panel.add(lblEmail);

		textField_email = new JTextField();
		textField_email.setBackground(new Color(245, 245, 245));
		textField_email.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textField_email.setColumns(10);
		textField_email.setBounds(133, 248, 199, 26);
		panel.add(textField_email);
		

		JButton btnNewButton = new JButton("Sign Up");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				registrationGetterSetter.setName(textField_name.getText());
				registrationGetterSetter.setEmail(textField_email.getText());
				registrationGetterSetter.setPassword(String.valueOf(textField_password.getPassword()));
				registrationGetterSetter.setPasswordConfirm(String.valueOf(textField_confirmation.getPassword()));
				if(!textField_age.getText().equals("")){
					try{
						
						registrationGetterSetter.setAge(Integer.parseInt(textField_age.getText()));
					}catch(NumberFormatException nf){
						JOptionPane.showMessageDialog(null,"Age is a Number \n Not letter");
						textField_age.setText("");
					}
				}
				
				
				
				try {
					if(registrationGetterSetter.valueConsistency()){
						if(registrationGetterSetter.passwordMatching()){
							oos.writeObject(registrationGetterSetter.toString());	
							 JOptionPane.showMessageDialog(null,"Successfully Registered\nNow Log In");
							 textField_age.setText("");
							 textField_confirmation.setText("");
							 textField_name.setText("");
							 textField_password.setText("");
							 textField_email.setText("");
							 
						}
						else{
							textField_confirmation.setText("");
							JOptionPane.showMessageDialog(null,"Password Does Not Match\nPlease Use Same Password","Alert",JOptionPane.WARNING_MESSAGE);
						}
						  
					}
					else {
						JOptionPane.showMessageDialog(null,"Wrong Input\nInput Correct Values into Correct Field","Alert",JOptionPane.WARNING_MESSAGE);     
					}
					

				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton.setBackground(UIManager.getColor("Button.disabledForeground"));
		btnNewButton.setBounds(213, 375, 120, 33);
		panel.add(btnNewButton);

		textField_age = new JTextField();
		textField_age.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textField_age.setColumns(10);
		textField_age.setBackground(new Color(245, 245, 245));
		textField_age.setBounds(133, 187, 199, 26);
		panel.add(textField_age);
		

		JLabel lblAge = new JLabel("Age");
		lblAge.setForeground(Color.WHITE);
		lblAge.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblAge.setBounds(43, 189, 71, 20);
		panel.add(lblAge);

		textField_confirmation = new JPasswordField();
		textField_confirmation.setBackground(new Color(245, 245, 245));
		textField_confirmation.setBounds(133, 338, 199, 26);
		panel.add(textField_confirmation);
		

		JLabel lblConfirm = new JLabel("Confirm");
		lblConfirm.setForeground(Color.WHITE);
		lblConfirm.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblConfirm.setBounds(43, 344, 86, 20);
		panel.add(lblConfirm);

		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon("img3.jpg"));
		lblNewLabel.setBounds(0, 0, 434, 461);
		panel.add(lblNewLabel);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(230, 230, 250));
		panel_1.setBounds(444, 0, 440, 461);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		JLabel lblAlreadyHaveAn = new JLabel("Already have an account?");
		lblAlreadyHaveAn.setHorizontalAlignment(SwingConstants.CENTER);
		lblAlreadyHaveAn.setFont(new Font("Bell Gothic Std Black", Font.PLAIN, 24));
		lblAlreadyHaveAn.setBounds(59, 60, 318, 44);
		panel_1.add(lblAlreadyHaveAn);

		JLabel label_1 = new JLabel("Name");
		label_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		label_1.setBounds(79, 199, 71, 20);
		panel_1.add(label_1);

		textField_2 = new JTextField();
		textField_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textField_2.setColumns(10);
		textField_2.setBounds(169, 193, 199, 26);
		panel_1.add(textField_2);

		JLabel label_3 = new JLabel("Password");
		label_3.setFont(new Font("Tahoma", Font.BOLD, 15));
		label_3.setBounds(79, 257, 86, 20);
		panel_1.add(label_3);

		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(169, 251, 199, 26);
		panel_1.add(passwordField_1);

		JButton btnSignin = new JButton("Sign In");
		btnSignin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					oos.writeObject("LogIn ");
					String listen = (String) ois.readObject();
					System.out.println(listen);
				} catch (IOException | ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnSignin.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnSignin.setBackground(new Color(204, 204, 255));
		btnSignin.setBounds(247, 307, 120, 33);
		panel_1.add(btnSignin);

		JLabel lblSignInHere = new JLabel("Sign in Here . . .");
		lblSignInHere.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblSignInHere.setHorizontalAlignment(SwingConstants.CENTER);
		lblSignInHere.setBounds(96, 128, 232, 26);
		panel_1.add(lblSignInHere);
	}
}
