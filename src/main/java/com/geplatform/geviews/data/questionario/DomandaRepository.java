package com.geplatform.geviews.data.questionario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface DomandaRepository extends JpaRepository<Question, Long>, JpaSpecificationExecutor<Question> {
}
