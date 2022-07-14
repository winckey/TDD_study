package com.example.tdd_new.study;

import com.example.tdd_new.domain.Study;
import org.springframework.data.jpa.repository.JpaRepository;


public interface StudyRepository extends JpaRepository<Study, Long> {

}
