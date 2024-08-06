package com.geplatform.geviews.data.questionario;

import com.geplatform.geviews.data.AbstractEntity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@Setter
@Document( "ge_topic")
public class Topic extends AbstractEntity {

    String name;
    int weight;

    private List<Question> questions;


}
