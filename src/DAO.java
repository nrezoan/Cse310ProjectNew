
import java.util.*;
import java.sql.*;
import java.io.*;

public class DAO {

	private Connection connection;

	public DAO() {
		// connect to database
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/tic_tac_toe_db", "root", "");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("DB connection successful... ");
	}

	public List<String> getAllPlayers() throws Exception {
		List<String> list = new ArrayList<>();

		Statement statement = null;
		ResultSet resultSet = null;

		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery("select * from player");

			while (resultSet.next()) {
				String ss = resultSet.getString("name") + "," + resultSet.getString("email") + ","
						+ resultSet.getString("password");
				list.add(ss);
			}

			return list;
		} finally {
			close(statement, resultSet);
		}
	}

	public String viewMyProfile(String name) throws Exception {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		int count = 0;
		String info = "";

		String selectSQL = "SELECT * FROM player WHERE name = ?";
		try {
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, name);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				info = "Name: " + resultSet.getString("name");
				info += "\nAge: " + resultSet.getString("age");
				info += "\nEmail: " + resultSet.getString("email");
			}

		} finally {
			close(preparedStatement, resultSet);
		}
		return info;
	}

	private static void close(Connection connection, Statement statement, ResultSet resultSet) throws SQLException {

		if (resultSet != null) {
			resultSet.close();
		}

		if (statement != null) {

		}

		if (connection != null) {
			connection.close();
		}
	}

	private void close(Statement statement, ResultSet resultSet) throws SQLException {
		close(null, statement, resultSet);
	}

	public void signUp(String name, int age, String email, String password, String confirm) throws Exception {

		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		String insertTableSQL = "INSERT INTO player" + "(name, age, email, password, confirm) VALUES" + "(?,?,?,?,?)";

		try {
			// set the parameters
			preparedStatement = connection.prepareStatement(insertTableSQL);
			preparedStatement.setString(1, name);
			preparedStatement.setInt(2, age);
			preparedStatement.setString(3, email);
			preparedStatement.setString(4, password);
			preparedStatement.setString(5, confirm);

			preparedStatement.executeUpdate();

			System.out.println("Insert complete");
		}

		finally {
			close(preparedStatement, resultSet);
		}

	}

	public boolean logIn(String name, String password) throws Exception {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		int count = 0;
		String uPassword = "";

		String selectSQL = "SELECT password FROM player WHERE name = ?";
		try {
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, name);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				uPassword = resultSet.getString("password");
				System.out.println("Password " + uPassword);
				count++;

			}

		} finally {
			close(preparedStatement, resultSet);
		}
		if (count < 1) {
			System.out.println("Resut is empty or name is not valid");
			return false;
		} else {
			if (uPassword.equals(password)) {
				System.out.println("Login Successful");
				return true;
			} else {
				System.out.println("Wrong Password");
				return false;
			}
		}
	}

	public static void main(String[] args) throws Exception {

		DAO dao = new DAO();
		// dao.signUp("sabia", 41, "sabia@gmail.com", "abc", "abc");
		// System.out.println(dao.getAllPlayers());
		dao.logIn("nazib", "123456");
	}

}
