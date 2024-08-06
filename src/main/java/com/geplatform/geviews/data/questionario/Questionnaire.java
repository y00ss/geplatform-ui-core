package com.geplatform.geviews.data.questionario;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document("ge_questionnaire")
public class Questionnaire {

    @Id
    private String id;
    private Topic topic;
    private List<Query> queries;
}
