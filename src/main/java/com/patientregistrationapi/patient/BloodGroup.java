package com.patientregistrationapi.patient;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum BloodGroup {
	A_POSITIVE("A+"),
    A_NEGATIVE("A-"),
    B_POSITIVE("B+"),
    B_NEGATIVE("B-"),
    AB_POSITIVE("AB+"),
    AB_NEGATIVE("AB-"),
    O_POSITIVE("O+"),
    O_NEGATIVE("O-");
	
	private final String value;
    BloodGroup(String value) {
        this.value = value;
    }
    public String getValue() {
        return value;
    }
    @Override
    public String toString() {
        return value;
    }
    
    @JsonCreator
    public static BloodGroup fromValue(String value) {
        for (BloodGroup bg : BloodGroup.values()) {
            // Verifica tanto o valor quanto o nome do enum
            if (bg.getValue().equalsIgnoreCase(value.trim()) || 
                bg.name().equalsIgnoreCase(value.trim())) {
                return bg;
            }
        }
        throw new IllegalArgumentException("No enum constant for value: " + value);
    }
}
