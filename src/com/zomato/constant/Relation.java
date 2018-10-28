package com.zomato.constant;

public enum Relation {

	PATERNAL_UNCLE(0),
	MATERNAL_UNCLE(1),
	PATERNAL_AUNT(2),
	MATERNAL_AUNT(3),
	SISTER_IN_LAW(4),
	BROTHER_IN_LAW(5),
	COUSIN(6),
	SON(7),
	DAUGHTER(8),
	BROTHER(9),
	SISTER(10),
	GRAND_DAUGHTER(11),
	GRAND_SON(12),
	FATHER(13),
	MOTHER(14);
	
	private final int value;
	
	private Relation(int value) {
		
		this.value = value;
	}
	
	public int getValue() {
		
		return value;
	}
}
