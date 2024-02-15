package com.jspsiders.studentinfo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class StudentOperation {
	
	private static Connection connection;
	private static PreparedStatement preparedStatement;
	private static String query;
	private static ResultSet resultSet;
	
	
	public static void addNewStudent(Scanner scanner) {
		
		try {
			openConnection();
			query = "INSERT INTO student2 values(?,?,?,?)";
			preparedStatement = connection.prepareStatement(query);
			
			System.out.println("How many records you want to add?");
			int count = scanner.nextInt();
			for(int i=1;i<=count;i++) {
				System.out.println("Enter Student Id");
				int id=scanner.nextInt();
				scanner.nextLine();
				System.out.println("Enter Student Name");
				String name= scanner.nextLine();
				System.out.println("Enter Student Email");
				String email= scanner.nextLine();
				System.out.println("Enter Student Age");
				int age= scanner.nextInt();
				
				preparedStatement.setInt(1, id);
				preparedStatement.setString(2, name);
				preparedStatement.setString(3, email);
				preparedStatement.setInt(4, age);
				
				preparedStatement.addBatch();
			}
			
		      int[] res = preparedStatement.executeBatch();
		      System.out.println(res.length +" Student(s) Added in table");
			
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
	public static void searchStudent(Scanner scanner) {
		try {
			openConnection();
			System.out.println("Enter 1 to Search Student by ID\nEnter 2 for Search Student by name");
			int choice  = scanner.nextInt();
			switch (choice) {
			case 1:
				System.out.println("Enter Student id");
				int id = scanner.nextInt();
				query = "SELECT * from student2 where id = ?";
				    preparedStatement = connection.prepareStatement(query);
				    preparedStatement.setInt(1, id);
				        resultSet = preparedStatement.executeQuery();
				        if (resultSet.next()) {
							System.out.println(resultSet.getInt(1)+" "+resultSet.getString(2)+" "+resultSet.getString(3)+" "+resultSet.getInt(4));
						}else {
							System.out.println("This Student is no there");
						}
				break;
				
			case 2:
				scanner.nextLine();
				System.out.println("Enter Student Name");
				String name = scanner.nextLine();
				query = "SELECT * from student2 where name = ?";
				    preparedStatement = connection.prepareStatement(query);
				    preparedStatement.setString(1, name);
				        resultSet = preparedStatement.executeQuery();
				        if (resultSet.next()) {
							System.out.println(resultSet.getInt(1)+" "+resultSet.getString(2)+" "+resultSet.getString(3)+" "+resultSet.getInt(4));
						}else {
							System.out.println("This Student is no there");
						}
				break;
				

			default:
				System.out.println("Invalid Choice");
				break;
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
	public static void fetchAllStudent(Scanner scanner) {
		try {
			openConnection();
			query = "SELECT * FROM student2";
			preparedStatement = connection.prepareStatement(query);
			
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				System.out.println(resultSet.getInt(1)+" "+resultSet.getString(2)+" "+resultSet.getString(3)+" "+resultSet.getInt(4));
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
	public static void removeStudent(Scanner scanner) {
		try {
			openConnection();
			System.out.println("Enter 1 to Delete Student by ID\nEnter 2 for Delete Student by name");
			int choice  = scanner.nextInt();
			switch (choice) {
			case 1:
				System.out.println("Enter Student id");
				int id = scanner.nextInt();
				query = "DELETE from student2 where id = ?";
				    preparedStatement = connection.prepareStatement(query);
				    preparedStatement.setInt(1, id);
				        int res = preparedStatement.executeUpdate();
				        if (res == 1) {
							System.out.println("1 Student removed");
						}else {
							System.out.println("Student not Deleted");
						}
				break;
				
			case 2:
				scanner.nextLine();
				System.out.println("Enter Student Name");
				String name = scanner.nextLine();
				query = "DELETE from student2 where name = ?";
				    preparedStatement = connection.prepareStatement(query);
				    preparedStatement.setString(1, name);
				        int res2 = preparedStatement.executeUpdate();
				        if (res2 == 1) {
							System.out.println("1 Student Remove");
						}else {
							System.out.println("Student not Remove");
						}
				break;
				

			default:
				System.out.println("Invalid Choice");
				break;
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
	
	public static void openConnection() throws SQLException {
		connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/weja4", "root", "system");
	}
	public static void closeConnection() throws SQLException {
		
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
