package com.geplatform.geviews.services.questionnaire;

import com.geplatform.geviews.data.questionario.Questionnaire;
import com.geplatform.geviews.data.questionario.QuestionnaireRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionnaireServiceImpl implements QuestionnaireService{


    private final  QuestionnaireRepository repository;

    public QuestionnaireServiceImpl(QuestionnaireRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Questionnaire> getAllQuestionnaires(){
        return repository.findAll();
    }

    public Questionnaire getQuestionnaireById(String id) {
        return repository.findById(id).orElse(null);
    }

    public Questionnaire createQuestionnaire(Questionnaire questionnaire) {
        return repository.save(questionnaire);
    }

    public Questionnaire updateQuestionnaire(Questionnaire questionnaire) {
        return repository.save(questionnaire);
    }

    public void deleteQuestionnaire(String id) {
        repository.deleteById(id);
    }



}
