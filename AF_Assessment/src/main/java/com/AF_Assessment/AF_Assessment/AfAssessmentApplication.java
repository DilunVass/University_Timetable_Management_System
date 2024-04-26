package com.AF_Assessment.AF_Assessment;

import com.AF_Assessment.AF_Assessment.JWT.keyProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;


@EnableConfigurationProperties(keyProperties.class)
@SpringBootApplication
public class AfAssessmentApplication {

//	@Autowired
//	private EmailSenderService senderService;
	public static void main(String[] args) {
		SpringApplication.run(AfAssessmentApplication.class, args);
	}
//	@EventListener(ApplicationReadyEvent.class)
//	public void triggerMail() throws MessagingException {
//		senderService.sendSimpleEmail("dilunvass2001@gmail.com",
//				"From Dilun",
//				"Hi this is dilun");
//
//	}

}