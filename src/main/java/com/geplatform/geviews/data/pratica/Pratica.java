package com.geplatform.geviews.data.pratica;

import com.geplatform.geviews.constants.CompanyCluster;
import com.geplatform.geviews.dto.Company;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Map;

@Data
@Document(collection = "ge_pratiche")
public class Pratica {

    @Id
    String id;

    String companyId;
    String companyName;
    CompanyCluster companyCluster;
    Map<String, List<PraticaQuery>> questionnarie;
    String status;
    boolean completed;
}
