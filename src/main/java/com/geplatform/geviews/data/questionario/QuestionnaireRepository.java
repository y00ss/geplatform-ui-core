package com.geplatform.geviews.data.questionario;

import com.geplatform.geviews.constants.CompanyCluster;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.List;

@EnableMongoRepositories
public interface QuestionnaireRepository extends MongoRepository<Questionnaire, String>{


    @Aggregation(pipeline = {
            "{ $unwind: '$queries' }",
            "{ $match: { 'queries.cluster': '?0' } }",
            "{ $group: { _id: '$_id', name: { $first: '$name' }, section: { $first: '$section' }, description: { $first: '$description' }, queries: { $push: '$queries' } } }"
    })
    List<Questionnaire> findByQueryCluster(CompanyCluster companyCluster);

}
