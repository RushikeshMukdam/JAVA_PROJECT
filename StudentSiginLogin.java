package com.jspsiders.student.siginlogin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.jspsiders.studentinfo.StudentMain;

public class StudentSiginLogin {
	
	private static Connection connection;
	private static PreparedStatement preparedStatement;
	private static String query;
	private static ResultSet resultSet;
	
	public static void sigIn(Scanner scanner) {
		
		System.out.println("Enter Admin Name");
		String name = scanner.next();
		System.out.println("Enter Email");
		String email = scanner.next();
		System.out.println("Enter Password");
		String password = scanner.next();
		
		try {
			openConnection();
			query = "INSERT INTO admin values(?,?,?)";
			 preparedStatement = connection.prepareStatement(query);
			 preparedStatement.setString(1, name);
			 preparedStatement.setString(2, email);
			 preparedStatement.setString(3, password);
			 
			  int res = preparedStatement.executeUpdate();
			  if (res == 1) {
				System.out.println("Sigin Successful");
			}else {
				System.out.println("Error");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				closeConnection();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	public static void logIn(Scanner scanner) {
		
		scanner.nextLine();
		System.out.println("Enter EMAIl");
		String email = scanner.nextLine();
		System.out.println("ENTER PASSWORD");
		String password = scanner.nextLine();
		
		try {
			openConnection();
			query = "SELECT * FROM admin WHERE email = ? AND password = ?"; 
			preparedStatement = connection.prepareStatement(query);
			
			preparedStatement.setString(1, email);
			preparedStatement.setString(2, password);
			
			  resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				System.out.println("Login Successful");
				StudentMain.student();
			}else {
				System.out.println("Invalid gmail or password");
			}
			 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				closeConnection();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	private static void openConnection() throws SQLException {
		connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/weja4", "root", "system");
	}
	private static void closeConnection() throws SQLException {
		
		if (resultSet != null) {
			resultSet.close();
		}
		if (preparedStatement != null) {
			preparedStatement.close();
		}
		if (connection != null) {
			connection.close();
		}
	}

}
