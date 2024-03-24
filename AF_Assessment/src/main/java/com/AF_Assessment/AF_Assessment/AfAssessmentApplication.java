package com.AF_Assessment.AF_Assessment;

import com.AF_Assessment.AF_Assessment.JWT.keyProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties(keyProperties.class)
@SpringBootApplication
public class AfAssessmentApplication {

	public static void main(String[] args) {
		SpringApplication.run(AfAssessmentApplication.class, args);
	}

}