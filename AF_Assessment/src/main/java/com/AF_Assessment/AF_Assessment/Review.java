package com.AF_Assessment.AF_Assessment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collation = "review")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Review {
    private ObjectId id;
    private String body;
}
