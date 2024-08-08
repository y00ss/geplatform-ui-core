package com.geplatform.geviews.services.questionnaire;

import com.geplatform.geviews.constants.CompanyCluster;
import com.geplatform.geviews.data.questionario.Questionnaire;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface QuestionnaireService {
    List<Questionnaire> getAllQuestionnaires();

    List<Questionnaire> getQuestionnairesByCompanyCluster(CompanyCluster companyCluster);

}
