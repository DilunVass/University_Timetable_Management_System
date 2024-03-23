package com.AF_Assessment.AF_Assessment.enums;

public enum LabType {
    COMPUTER_SCIENCE("Computer Science"), SOFTWARE_ENGINEERING("Software Engineering"), BUSINESS_MANAGEMENT("Business Management"), BIO_TECH("Bio Tech");

    private final String type;

    LabType(String type){
        this.type = type;
    }

    public String getType(){
        return type;
    }
}
