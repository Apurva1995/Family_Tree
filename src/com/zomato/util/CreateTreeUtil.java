package com.zomato.util;

import java.util.ArrayList;
import java.util.List;

import com.zomato.model.Person;

public class CreateTreeUtil {

	private static Person rootOfTree;
	private static List<String> unmarriedPeopleInFamily;
	
	/*
	 * While loading the class only, keeping track of unmarried 
	 * people in family for family tree creation.
	 */
	static {
		
		unmarriedPeopleInFamily = new ArrayList<>();
		unmarriedPeopleInFamily.add("Ish");
		unmarriedPeopleInFamily.add("Vrita");
		unmarriedPeopleInFamily.add("Jata");
		unmarriedPeopleInFamily.add("Kriya");
		unmarriedPeopleInFamily.add("Misa");
	}
	
	/*
	 * This method with help of all the private methods,
	 * creates the tree and returns the root of the Tree.
	 */
	public static Person createTree() {
		
		Person person = new Person();
		person.setName("King Shan");
		person.setGender("Male");
		person.setFather(null);
		person.setMother(null);
		create(person);
		person.getSpouse().setFather(null);
		person.getSpouse().setMother(null);
		rootOfTree = person;
		return rootOfTree;
	}
	
	// A recursive utility function to create the tree
	private static void create(Person person) {
		
		if(unmarriedPeopleInFamily.contains(person.getName()))
			return;
		
		setSpouse(person);
		setChildren(person);
		
		if(person.getChildren() != null) {
			for(int i=0;i<person.getChildren().size();i++)
				create(person.getChildren().get(i));
		}
	}
	
	/*
	 * This method returns List of children of 
	 * a person and sets parents of children as well.
	 */
	private static List<Person> setChildrenUtil(String[] names, String[] genders, Person father, Person mother) {
		
		List<Person> children = new ArrayList<>();
		Person person;
		
		for(int i=0;i<names.length;i++) {
			
			person = new Person();
			person.setName(names[i]);
			person.setGender(genders[i]);
			person.setFather(father);
			person.setMother(mother);
			children.add(person);
		}
		
		return children;
	}
	
	//Sets children of a person using setChildrenUtil() method.
	private static void setChildren(Person person) {
		
		switch(person.getName()) {
		
		case "King Shan":
			String[] kingN = {"Ish", "Chit", "Vich", "Satya"};
			String[] kingG = {"Male", "Male", "Male", "Female"};
			person.setChildren(setChildrenUtil(kingN, kingG, person, person.getSpouse()));
			person.getSpouse().setChildren(person.getChildren());
			break;
		case "Chit":
			String[] chitN = {"Drita", "Vrita"};
			String[] chitG = {"Male", "Male"};
			person.setChildren(setChildrenUtil(chitN, chitG, person, person.getSpouse()));
			person.getSpouse().setChildren(person.getChildren());
			break;
		case "Vich":
			String[] vichN = {"Vila", "Chika"};
			String[] vichG = {"Male", "Female"};
			person.setChildren(setChildrenUtil(vichN, vichG, person, person.getSpouse()));
			person.getSpouse().setChildren(person.getChildren());
			break;
		case "Satya":
			String[] satyaN = {"Satvy", "Savya", "Saayan"};
			String[] satyaG = {"Female", "Male", "Male"};
			person.setChildren(setChildrenUtil(satyaN, satyaG, person, person.getSpouse()));
			person.getSpouse().setChildren(person.getChildren());
			break;
		case "Drita":
			String[] dritaN = {"Jata", "Driya"};
			String[] dritaG = {"Male", "Female"};
			person.setChildren(setChildrenUtil(dritaN, dritaG, person, person.getSpouse()));
			person.getSpouse().setChildren(person.getChildren());
			break;
		case "Driya":
			person.setChildren(new ArrayList<>());
			person.getSpouse().setChildren(person.getChildren());
			break;
		case "Vila":
			String[] vilaN = {"Lavnya"};
			String[] vilaG = {"Female"};
			person.setChildren(setChildrenUtil(vilaN, vilaG, person, person.getSpouse()));
			person.getSpouse().setChildren(person.getChildren());
			break;
		case "Chika":
			person.setChildren(new ArrayList<>());
			person.getSpouse().setChildren(person.getChildren());
			break;
		case "Lavnya":
			person.setChildren(new ArrayList<>());
			person.getSpouse().setChildren(person.getChildren());
			break;
		case "Satvy":
			person.setChildren(new ArrayList<>());
			person.getSpouse().setChildren(person.getChildren());
			break;
		case "Savya":
			String[] savyaN = {"Kriya"};
			String[] savyaG = {"Male"};
			person.setChildren(setChildrenUtil(savyaN, savyaG, person, person.getSpouse()));
			person.getSpouse().setChildren(person.getChildren());
			break;
		case "Saayan":
			String[] saayanN = {"Misa"};
			String[] saayanG = {"Male"};
			person.setChildren(setChildrenUtil(saayanN, saayanG, person, person.getSpouse()));
			person.getSpouse().setChildren(person.getChildren());
			break;
		default:
			System.out.println(person.getName() + " is probably not married");
			break;
		}
	}
	
	//Creates and return Spouse for a person.
	private static Person getSpouse(String name, String gender) {
		
		Person spouse = new Person();;
		spouse.setName(name);
		spouse.setGender(gender);
		return spouse;
	}
	
	//Sets spouse of a person using getSpouse() method.
	private static void setSpouse(Person person) {
		
		switch (person.getName()) {

		case "King Shan":
			person.setSpouse(getSpouse("Queen Anga", "Female"));
			person.getSpouse().setSpouse(person);
			break;
		case "Chit":
			person.setSpouse(getSpouse("Ambi", "Female"));
			person.getSpouse().setSpouse(person);
			break;
		case "Vich":
			person.setSpouse(getSpouse("Lika", "Female"));
			person.getSpouse().setSpouse(person);
			break;
		case "Satya":
			person.setSpouse(getSpouse("Vyan", "Male"));
			person.getSpouse().setSpouse(person);
			break;
		case "Drita":
			person.setSpouse(getSpouse("Jaya", "Female"));
			person.getSpouse().setSpouse(person);
			break;
		case "Driya":
			person.setSpouse(getSpouse("Mnu", "Male"));
			person.getSpouse().setSpouse(person);
			break;
		case "Vila":
			person.setSpouse(getSpouse("Jnki", "Female"));
			person.getSpouse().setSpouse(person);
			break;
		case "Chika":
			person.setSpouse(getSpouse("Kpila", "Male"));
			person.getSpouse().setSpouse(person);
			break;
		case "Lavnya":
			person.setSpouse(getSpouse("Gru", "Male"));
			person.getSpouse().setSpouse(person);
			break;
		case "Satvy":
			person.setSpouse(getSpouse("Asva", "Male"));
			person.getSpouse().setSpouse(person);
			break;
		case "Savya":
			person.setSpouse(getSpouse("Krpi", "Female"));
			person.getSpouse().setSpouse(person);
			break;
		case "Saayan":
			person.setSpouse(getSpouse("Mina", "Female"));
			person.getSpouse().setSpouse(person);
			break;
		default:
			System.out.println("Invalid Person " + person.getName());
			break;
		}
	}
}
