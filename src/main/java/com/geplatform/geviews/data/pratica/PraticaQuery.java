package com.geplatform.geviews.data.pratica;

import com.geplatform.geviews.data.questionario.Query;
import lombok.Data;

@Data
public class PraticaQuery {

    Query indicatore;
    String response;
    String comment;

    @Override
    public String toString() {
        return "PraticaQuery{" +
                "indicatore=" + indicatore +
                ", response='" + response + '\'' +
                ", comment='" + comment + '\'' +
                '}';
    }

}
