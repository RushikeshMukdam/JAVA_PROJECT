package com.jspsiders.student.siginlogin;

import java.util.Scanner;



public class SiginLoginMain {
	
	public static void main(String[] args) {
		boolean flag = true;
		Scanner scanner = new Scanner(System.in);
		while(flag) {
		System.out.println("Enter 1 for Sigin\nEnter 2 for Login\nEnter 3 for Exit");
		
		int choice = scanner.nextInt();
		switch (choice) {
		case 1:
			StudentSiginLogin.sigIn(scanner);
			break;
		case 2:
			StudentSiginLogin.logIn(scanner);
			
			break;
		case 3:
			flag = false;
			System.out.println("Thank you Visit Again");
			break;
			
		default:
			System.out.println("Invalid Input");
			break;
		}
		}
		scanner.close();
	}
	
}
