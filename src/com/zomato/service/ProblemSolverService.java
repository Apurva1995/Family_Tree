package com.zomato.service;

import java.util.List;

import com.zomato.constant.Relation;
import com.zomato.model.Person;
import com.zomato.util.ProblemSolverUtil;

public class ProblemSolverService {

	
	private void printRelatives(List<Person> relatives, String name, String relation) {
		
		if(relatives.size() == 0) {
			System.out.println("There isn't any " + relation + " of " + name);
			return;
		}
		
		for(int i=0;i<relatives.size();i++) {
			System.out.print(relatives.get(i).getName());
			if(i != relatives.size()-1)
				System.out.print(", ");
		}
		System.out.println();
	}
	
	public void solutionToProblemOne(String name, String relation) {
		
		if (ProblemSolverUtil.relations.get(name) == null) {
			System.out.println("Sorry no person with provided name exists in family");
			return;
		}
		
		String input = relation.toLowerCase();
		switch (input) {

		case "paternal uncle":
		case "paternal uncles":
			printRelatives(ProblemSolverUtil.relations.get(name).get(Relation.PATERNAL_UNCLE.getValue()), name, relation);
			break;
		case "maternal uncle":
		case "maternal uncles":
			printRelatives(ProblemSolverUtil.relations.get(name).get(Relation.MATERNAL_UNCLE.getValue()), name, relation);
			break;
		case "paternal aunt":
		case "paternal aunts":
			printRelatives(ProblemSolverUtil.relations.get(name).get(Relation.PATERNAL_AUNT.getValue()), name, relation);
			break;
		case "maternal aunt":
		case "maternal aunts":
			printRelatives(ProblemSolverUtil.relations.get(name).get(Relation.MATERNAL_AUNT.getValue()), name, relation);
			break;	
		case "sister-in-law":
			printRelatives(ProblemSolverUtil.relations.get(name).get(Relation.SISTER_IN_LAW.getValue()), name,  relation);
			break;
		case "brother-in-law":
			printRelatives(ProblemSolverUtil.relations.get(name).get(Relation.BROTHER_IN_LAW.getValue()), name, relation);
			break;
		case "cousin":
		case "cousins":
			printRelatives(ProblemSolverUtil.relations.get(name).get(Relation.COUSIN.getValue()), name, relation);
			break;
		case "father":
			printRelatives(ProblemSolverUtil.relations.get(name).get(Relation.FATHER.getValue()), name, relation);
			break;
		case "mother":
			printRelatives(ProblemSolverUtil.relations.get(name).get(Relation.MOTHER.getValue()), name, relation);
			break;
		case "children":
			printRelatives(ProblemSolverUtil.relations.get(name).get(Relation.SON.getValue()), name, relation);
			printRelatives(ProblemSolverUtil.relations.get(name).get(Relation.DAUGHTER.getValue()), name, relation);
			break;
		case "son":
		case "sons":
			printRelatives(ProblemSolverUtil.relations.get(name).get(Relation.SON.getValue()), name, relation);
			break;
		case "daughter":
		case "daughters":
			printRelatives(ProblemSolverUtil.relations.get(name).get(Relation.DAUGHTER.getValue()), name, relation);
			break;
		case "brothers":
		case "brother":
			printRelatives(ProblemSolverUtil.relations.get(name).get(Relation.BROTHER.getValue()), name, relation);
			break;
		case "sister":
		case "sisters":
			printRelatives(ProblemSolverUtil.relations.get(name).get(Relation.SISTER.getValue()), name, relation);
			break;
		case "grand-son":
		case "grand-sons":
			printRelatives(ProblemSolverUtil.relations.get(name).get(Relation.GRAND_SON.getValue()), name, relation);
			break;
		case "grand-daughter":
		case "grand-daughters":
			printRelatives(ProblemSolverUtil.relations.get(name).get(Relation.GRAND_DAUGHTER.getValue()), name, relation);
			break;
		default:
			System.out.println("The relation " + relation + " is not maintained.");
			break;
		}
		return;
	}
}
