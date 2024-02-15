package com.jspsiders.studentinfo;

import java.util.Scanner;

//import com.jspsiders.student.siginlogin.StudentSiginLogin;

public class StudentMain {
	public static void student() {
		Scanner scanner = new Scanner(System.in);
		boolean flag = true;
		while(flag) {
			System.out.println("Enter 1 For Add Student\nEnter 2 for Search Student\nEnter 3 for View All Student\nEnter 4 for Delete 					student\nEnter 5 for Exist");
			int choice = scanner.nextInt();
			switch (choice) {
			case 1:
				StudentOperation.addNewStudent(scanner);
				break;
			case 2:
				StudentOperation.searchStudent(scanner);
				break;
			case 3:
				StudentOperation.fetchAllStudent(scanner);
				break;
			case 4:
				StudentOperation.removeStudent(scanner);
				break;
				
			case 5:
				
					flag=false;
					System.out.println("Thank you Visit Again");			
				break;
				//StudentSiginLogin.logIn(scanner);
				

			default:
				System.out.println("Invalid Choice");
				break;
			}
			
		}
		scanner.close();
	}
}
