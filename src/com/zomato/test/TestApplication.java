package com.zomato.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import com.zomato.model.Person;
import com.zomato.service.ProblemSolverService;
import com.zomato.util.CreateTreeUtil;
import com.zomato.util.ProblemSolverUtil;

public class TestApplication {

	public static Person root;
	public String[] arr = new String[] {"Paternal Uncle","Maternal Uncle",
												"Paternal Aunt", "Maternal Aunt",
												"Sister law","Brother law",
												"Cousin","Son","Daughter",
												"Brothers","Sisters",
												"Grand Daughter","Grand Son",
												"Father", "Mother"};
	
	static {
		
		/*
		 * Creating the tree that is provided in the question.
		 */
		root = CreateTreeUtil.createTree();
		
		/*
		 * Since in problem 1 and 4, there aren't any update required 
		 * in the underlying tree, storing all the relations related 
		 * to a specific person in a table. So that for 'n' number of
		 * queries, we won't have to do 'n' traversals.
		 */
		ProblemSolverUtil.storeRelations(root);
	}
	
	public void printTree(Person person) {
		
		if(person != null) {
			
			System.out.print(person.getName() + " ");
			if(person.getSpouse() != null)
				System.out.println(person.getSpouse().getName());
			
			if (person.getChildren() != null) {
				for (int i = 0; i < person.getChildren().size(); i++) {

					printTree(person.getChildren().get(i));
				}
			}
		}
	}
	
	public void printRelations(Person person) {
		
		List<List<Person>> differentRelation = ProblemSolverUtil.relations.get(person.getName());
		System.out.println(person.getName());
		for(int i=0;i<differentRelation.size();i++) {
			
			List<Person> relation = differentRelation.get(i);
			System.out.print(arr[i] + "-> ");
			for(int j=0;j<relation.size();j++) {
				System.out.print(relation.get(j).getName() + " ");
			}
			System.out.println();
		}
		System.out.println();
		
		/*if(person.getSpouse() != null) {
			
			List<List<Person>> differentRelation = ProblemSolverUtil.relations.get(person.getSpouse().getName());
			System.out.println(person.getSpouse().getName());
			for(int i=0;i<differentRelation.size();i++) {
				
				List<Person> relation = differentRelation.get(i);
				System.out.print(arr[i] + "-> ");
				for(int j=0;j<relation.size();j++) {
					System.out.print(relation.get(j).getName() + " ");
				}
				System.out.println();
			}
			System.out.println();
		}*/
		if (person.getChildren() != null) {
			for (int i = 0; i < person.getChildren().size(); i++) {

				printRelations(person.getChildren().get(i));
			}
		}
	}
	
	public static void main(String[] args) {
		
		//TestApplication testApplication = new TestApplication();
		
		//testApplication.printTree(root);
		//testApplication.printRelations(root);
		
		int problemNumber;
		String personName;
		String relation;
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		ProblemSolverService problemSolverService = new ProblemSolverService();
		
		
		do {
			System.out.println("1. Press 1 to get solution for problem 1");
			System.out.println("2. Press 2 to get solution for problem 4");
			System.out.println("3. Press 0 to exit");
			try {
				problemNumber = Integer.parseInt(bufferedReader.readLine());
				switch(problemNumber) {
				
				case 0:
					System.out.println("Good Bye");
					System.exit(0);
				case 1:
					System.out.println("Enter person's name");
					personName = bufferedReader.readLine();
					System.out.println("Enter person's relation");
					relation = bufferedReader.readLine();
					problemSolverService.solutionToProblemOne(personName, relation);
					break;
				default:
					System.out.println("Please enter valid number");
					break;
				}
			}catch(IOException exception) {
				System.out.println("Someting went wrong while reading input. Please try again");
				exception.printStackTrace();
			}
		}while(true);
		
	}

}
