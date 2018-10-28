package com.zomato.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zomato.constant.Relation;
import com.zomato.model.Person;

public class ProblemSolverUtil {

	public static Map<String, List<List<Person>>> relations = new HashMap<>();
	private static final int NUMBER_OF_RELATION_MAINTAINED = 15;

	
	private static void storeRelationsExceptPaternalAndMaternalUncleAndAunt(Person person) {
		
		if(person.getSpouse() != null) {
			
			if (relations.get(person.getSpouse().getName()) == null) {
				for (int i = 0; i < person.getChildren().size(); i++) {

					storeRelationsExceptPaternalAndMaternalUncleAndAunt(person.getChildren().get(i));
				}
			}
		}
		
		List<List<Person>> differentRelations = new ArrayList<>(Collections.nCopies(NUMBER_OF_RELATION_MAINTAINED, null));
		relations.put(person.getName(), differentRelations);
		
		List<Person> father = new ArrayList<>();
		List<Person> mother = new ArrayList<>();
		
		//Setting parents
		if (person.getFather() != null) {	
			father.add(person.getFather());
			mother.add(person.getMother());
		}
		differentRelations.set(Relation.FATHER.getValue(), father);
		differentRelations.set(Relation.MOTHER.getValue(), mother);
		
		setRelationsExceptPaternalAndMaternalAuntAndUncle(person);
		if(person.getSpouse() != null && relations.get(person.getSpouse().getName()) == null) {
			storeRelationsExceptPaternalAndMaternalUncleAndAunt(person.getSpouse());	
		}
	}
	
	private static void storeRemainingRelations(Person person) {
		
		setRemainingRelations(person);
		if (person.getSpouse() != null) {

			if (relations.get(person.getSpouse().getName()).get(Relation.PATERNAL_UNCLE.getValue()) == null) {
				for (int i = 0; i < person.getChildren().size(); i++) {

					storeRemainingRelations(person.getChildren().get(i));
				}
				storeRemainingRelations(person.getSpouse());
			}
		}
	}
	
	public static void storeRelations(Person person) {

		storeRelationsExceptPaternalAndMaternalUncleAndAunt(person);
		storeRemainingRelations(person);
		
	}

	private static void setSisterInLaw(Person person) {

		List<List<Person>> differentRelation = relations.get(person.getName());
		List<Person> sisterInLaws = new ArrayList<>();

		if (person.getSpouse() != null) {

			if (person.getSpouse().getFather() != null) {

				for (int i = 0; i < person.getSpouse().getFather().getChildren().size(); i++) {

					if(person.getSpouse().getFather().getChildren().get(i).getName().equals(person.getSpouse().getName()))
						continue;
					
					if (person.getSpouse().getFather().getChildren().get(i).getGender().equals("Female"))
						sisterInLaws.add(person.getSpouse().getFather().getChildren().get(i));
				}
			}
		}

		if (person.getFather() != null) {

			for (int i = 0; i < person.getFather().getChildren().size(); i++) {

				if (person.getFather().getChildren().get(i).getName().equals(person.getName()))
					continue;

				if (person.getFather().getChildren().get(i).getSpouse() != null)
					if(person.getFather().getChildren().get(i).getSpouse().getGender().equals("Female"))
					sisterInLaws.add(person.getFather().getChildren().get(i).getSpouse());
			}
		}

		differentRelation.set(Relation.SISTER_IN_LAW.getValue(), sisterInLaws);
	}

	private static void setBrotherInLaw(Person person) {

		List<List<Person>> differentRelation = relations.get(person.getName());
		List<Person> brotherInLaws = new ArrayList<>();

		if (person.getSpouse() != null) {

			if (person.getSpouse().getFather() != null) {

				for (int i = 0; i < person.getSpouse().getFather().getChildren().size(); i++) {
					
					if(person.getSpouse().getFather().getChildren().get(i).getName().equals(person.getSpouse().getName()))
						continue;
					
					if (person.getSpouse().getFather().getChildren().get(i).getGender().equals("Male"))
						brotherInLaws.add(person.getSpouse().getFather().getChildren().get(i));
				}
			}
		}

		if (person.getFather() != null) {

			for (int i = 0; i < person.getFather().getChildren().size(); i++) {

				if (person.getFather().getChildren().get(i).getName().equals(person.getName()))
					continue;

				if (person.getFather().getChildren().get(i).getSpouse() != null)
					if(person.getFather().getChildren().get(i).getSpouse().getGender().equals("Male"))
					brotherInLaws.add(person.getFather().getChildren().get(i).getSpouse());
			}
		}

		differentRelation.set(Relation.BROTHER_IN_LAW.getValue(), brotherInLaws);
	}

	private static void setSonsAndDaughters(Person person) {
		
		List<List<Person>> differentRelation = relations.get(person.getName());
		List<Person> sons = new ArrayList<>();
		List<Person> daughters = new ArrayList<>();
		
		if(person.getChildren() != null) {
			
			for(int i=0;i<person.getChildren().size();i++) {
				
				if(person.getChildren().get(i).getGender().equals("Male"))
					sons.add(person.getChildren().get(i));
				else
					daughters.add(person.getChildren().get(i));
			}
		}
		
		differentRelation.set(Relation.SON.getValue(), sons);
		differentRelation.set(Relation.DAUGHTER.getValue(), daughters);
	}
	
	private static void setBrothersAndSisters(Person person) {

		List<List<Person>> differentRelation = relations.get(person.getName());
		List<Person> brothers = new ArrayList<>();
		List<Person> sisters = new ArrayList<>();

		if (person.getFather() != null) {

			for (int i = 0; i < person.getFather().getChildren().size(); i++) {

				if(person.getFather().getChildren().get(i).getName().equals(person.getName()))
					continue;
				
				if (person.getFather().getChildren().get(i).getGender().equals("Male"))
					brothers.add(person.getFather().getChildren().get(i));
				else
					sisters.add(person.getFather().getChildren().get(i));
			}
		}
		
		differentRelation.set(Relation.BROTHER.getValue(), brothers);
		differentRelation.set(Relation.SISTER.getValue(), sisters);
	}
	
	private static void setGrandDaughtersAndSons(Person person) {
		
		List<List<Person>> differentRelation = relations.get(person.getName());
		List<Person> grandDaughters = new ArrayList<>();
		List<Person> grandSons = new ArrayList<>();
		
		if(person.getChildren() != null && !person.getChildren().isEmpty()) {
			
			for (int i = 0; i < person.getChildren().size(); i++) {
				
				if(person.getChildren().get(i).getChildren() != null) {
					for (int j = 0; j < person.getChildren().get(i).getChildren().size(); j++) {
						
						if(person.getChildren().get(i).getChildren().get(j).getGender().equals("Male"))
							grandSons.add(person.getChildren().get(i).getChildren().get(j));
						else
							grandDaughters.add(person.getChildren().get(i).getChildren().get(j));
					}
				}
			}
		}
		
		differentRelation.set(Relation.GRAND_DAUGHTER.getValue(), grandDaughters);
		differentRelation.set(Relation.GRAND_SON.getValue(), grandSons);
	}
	
	private static void setPaternalAndMaternalUncle(Person person) {
		
		List<List<Person>> differentRelation = relations.get(person.getName());
		List<Person> paternalUncle = new ArrayList<>();
		List<Person> maternalUncle = new ArrayList<>();
		
		if (person.getFather() != null) {

			paternalUncle.addAll(relations.get(person.getFather().getName()).get(Relation.BROTHER.getValue()));
			paternalUncle.addAll(relations.get(person.getFather().getName()).get(Relation.BROTHER_IN_LAW.getValue()));
			maternalUncle.addAll(relations.get(person.getMother().getName()).get(Relation.BROTHER.getValue()));
			maternalUncle.addAll(relations.get(person.getMother().getName()).get(Relation.BROTHER_IN_LAW.getValue()));
		}
		
		differentRelation.set(Relation.PATERNAL_UNCLE.getValue(), paternalUncle);
		differentRelation.set(Relation.MATERNAL_UNCLE.getValue(), maternalUncle);
	}
	
	private static void setPaternalAndMaternalAunt(Person person) {

		List<List<Person>> differentRelation = relations.get(person.getName());
		List<Person> paternalAunt = new ArrayList<>();
		List<Person> maternalAunt= new ArrayList<>();

		if (person.getFather() != null) {

			paternalAunt.addAll(relations.get(person.getFather().getName()).get(Relation.SISTER.getValue()));
			paternalAunt.addAll(relations.get(person.getFather().getName()).get(Relation.SISTER_IN_LAW.getValue()));
			maternalAunt.addAll(relations.get(person.getMother().getName()).get(Relation.SISTER.getValue()));
			maternalAunt.addAll(relations.get(person.getMother().getName()).get(Relation.SISTER_IN_LAW.getValue()));
		}

		differentRelation.set(Relation.PATERNAL_AUNT.getValue(), paternalAunt);
		differentRelation.set(Relation.MATERNAL_AUNT.getValue(), maternalAunt);
	}
	
	private static void setCousin(Person person) {
		
		List<List<Person>> differentRelation = relations.get(person.getName());
		List<Person> cousin = new ArrayList<>();
		
		if(person.getFather() != null) {
			
			if(person.getFather().getChildren().indexOf(person) > 0) {
				
				cousin.addAll(relations.get(person.getFather().getChildren().get(0).getName()).get(Relation.COUSIN.getValue()));
			}
			else {
				
				List<Person> fatherSiblings = relations.get(person.getFather().getName()).get(Relation.BROTHER.getValue());
				for(int i=0;i<fatherSiblings.size();i++) {
					
					if(fatherSiblings.get(i).getChildren() != null) {
						for(int j=0;j<fatherSiblings.get(i).getChildren().size();j++) {
							cousin.add(fatherSiblings.get(i).getChildren().get(j));
						}
					}
				}
				
				fatherSiblings = relations.get(person.getFather().getName()).get(Relation.SISTER.getValue());
				for (int i = 0; i < fatherSiblings.size(); i++) {

					if (fatherSiblings.get(i).getChildren() != null) {
						for (int j = 0; j < fatherSiblings.get(i).getChildren().size(); j++) {
							cousin.add(fatherSiblings.get(i).getChildren().get(j));
						}
					}
				}
				
				List<Person> motherSiblings = relations.get(person.getMother().getName()).get(Relation.BROTHER.getValue());
				for(int i=0;i<motherSiblings.size();i++) {
					
					if(motherSiblings.get(i).getChildren() != null) {
						for(int j=0;j<motherSiblings.get(i).getChildren().size();j++) {
							cousin.add(motherSiblings.get(i).getChildren().get(j));
						}
					}
				}
				
				motherSiblings = relations.get(person.getMother().getName()).get(Relation.SISTER.getValue());
				for (int i = 0; i < motherSiblings.size(); i++) {

					if (motherSiblings.get(i).getChildren() != null) {
						for (int j = 0; j < motherSiblings.get(i).getChildren().size(); j++) {
							cousin.add(motherSiblings.get(i).getChildren().get(j));
						}
					}
				}
			}
		}
		
		differentRelation.set(Relation.COUSIN.getValue(), cousin);
	}
	
	private static void setRelationsExceptPaternalAndMaternalAuntAndUncle(Person person) {

		setSisterInLaw(person);
		setBrotherInLaw(person);
		setSonsAndDaughters(person);
		setBrothersAndSisters(person);
		setGrandDaughtersAndSons(person);
	}
	
	private static void setRemainingRelations(Person person) {

		setPaternalAndMaternalUncle(person);
		setPaternalAndMaternalAunt(person);
		setCousin(person);
	}
}
