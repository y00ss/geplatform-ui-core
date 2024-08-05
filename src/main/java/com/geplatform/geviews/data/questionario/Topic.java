package com.geplatform.geviews.data.questionario;

import com.geplatform.geviews.data.AbstractEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "ge_topic")
public class Topic extends AbstractEntity {

    String name;
    int weight;

    @OneToMany(mappedBy = "topic", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Question> questions;


}
