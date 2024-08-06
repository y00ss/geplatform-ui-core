package com.geplatform.geviews.data.questionario;

import com.geplatform.geviews.constants.CompanyCluster;
import com.geplatform.geviews.data.AbstractEntity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document("ge_question")
public class Question extends AbstractEntity {


    private CompanyCluster cluster;
    private String question;
    private int source;
    // getters e setters

    private Topic topic;
}
