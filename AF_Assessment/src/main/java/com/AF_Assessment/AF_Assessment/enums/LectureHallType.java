package com.AF_Assessment.AF_Assessment.enums;

public enum LectureHallType {
    COMPUTER_SCIENCE("Computer Science"), SOFTWARE_ENGINEERING("Software Engineering"),BIO_TECH("Bio Tech");

    
    private final String type;

    LectureHallType(String type){
        this.type = type;
    }

    public String getType(){
        return type;
    }
    
}
