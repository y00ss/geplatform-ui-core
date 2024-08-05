package com.geplatform.geviews.data.questionario;

import com.geplatform.geviews.constants.CompanyCluster;
import com.geplatform.geviews.data.AbstractEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "ge_question")
public class Question extends AbstractEntity {


    private CompanyCluster cluster;
    private String question;
    private int source;
    // getters e setters

    @ManyToOne
    @JoinColumn(name = "topic_id")
    private Topic topic;
}
