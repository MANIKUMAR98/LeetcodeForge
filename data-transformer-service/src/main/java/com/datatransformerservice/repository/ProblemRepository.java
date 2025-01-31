package com.datatransformerservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.datatransformerservice.model.Problem;

@Repository
public interface ProblemRepository extends JpaRepository<Problem, String> {
}
