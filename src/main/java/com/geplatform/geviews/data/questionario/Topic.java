package com.geplatform.geviews.data.questionario;

import com.geplatform.geviews.data.AbstractEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Topic extends AbstractEntity {

    String name;
    String section;
    String description;
    int weight;

}