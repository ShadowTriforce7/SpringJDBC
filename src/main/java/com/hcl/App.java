package com.hcl;

import java.util.Scanner;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.hcl.config.AppConfig;
import com.hcl.dao.EmpDao;
import com.hcl.model.Emp;

public class App {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
		EmpDao empDao = ctx.getBean(EmpDao.class);
		try (Scanner input = new Scanner(System.in);) {
			// main menu
			boolean menu = true;
			// while loop here
			while (menu) {
				System.out.println("What would you like to do: Insert, Update, Delete, Read, ReadAll, Quit?");
				String option = input.nextLine();
				switch (option.toLowerCase()) {
				case "insert":
					insertEmp(empDao, input);
					break;
				case "update":
					updateEmp(empDao, input);
					break;
				case "delete":
					//TODO
					break;
				case "read":
					readEmp(empDao, input);
					break;
				case "readall":
					readAllEmp(empDao);
					break;
				case "quit":
					break;
				default:
					System.out.println("Invalid option");
					break;
				}
				menu = menuOperator(input, menu, "Would you like to do anything else? ");
			}
		}
	}

	private static void updateEmp(EmpDao empDao, Scanner input) {
		System.out.println("Enter id: ");
		int id = input.nextInt();
		input.nextLine();
		
		System.out.println("Enter name: ");
		String name = input.nextLine();
		
		System.out.println("Enter Salary: ");
		int salary = input.nextInt();
		input.nextLine();

		boolean b = empDao.updateEmp(new Emp(id,name,salary));
		System.out.println("Employee updated: " + b);
	}

	private static void insertEmp(EmpDao empDao, Scanner input) {
		System.out.println("Enter id: ");
		int id = input.nextInt();
		input.nextLine();
		
		System.out.println("Enter name: ");
		String name = input.nextLine();
		
		System.out.println("Enter Salary: ");
		int salary = input.nextInt();
		input.nextLine();
	
		boolean b = empDao.createEmp(new Emp(id,name,salary));
		System.out.println("Employee created: " + b);
	}

	private static void readAllEmp(EmpDao empDao) {
		System.out.println("List of Employees: ");
		for (Emp e : empDao.getAllEmps()) {
			System.out.println(e);
		}
	}

	private static void readEmp(EmpDao empDao, Scanner input) {
		System.out.println("Enter id: ");
		int id = input.nextInt();
		input.nextLine();
		System.out.println("Employee: ");
		Emp e = empDao.getEmpId(id);
		System.out.println(e);
	}

	private static boolean menuOperator(Scanner input, boolean go, String message) {
		// loops operation menu
		System.out.println(message);
		boolean gogo = true;
		while (gogo) {
			String repeat = input.nextLine();
			if (repeat.toUpperCase().equals("Y")) {
				gogo = false;
			} else if (repeat.toUpperCase().equals("N")) {
				go = false;
				gogo = false;
			} else {
				System.out.println("invalid input, try again. input Y or N: ");
			}
		}
		return go;
	}
}
