package com.geplatform.geviews.data.questionario;

import com.geplatform.geviews.constants.CompanyCluster;
import com.geplatform.geviews.data.AbstractEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Query extends AbstractEntity {


    private CompanyCluster cluster;
    private String question;
    private int source;
    // getters e setters

}
