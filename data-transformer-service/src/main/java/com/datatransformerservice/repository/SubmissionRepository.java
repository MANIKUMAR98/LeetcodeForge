package com.datatransformerservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.datatransformerservice.model.Submission;

import java.util.List;

public interface SubmissionRepository extends JpaRepository<Submission, String> {
    List<Submission> findByProblemId(String pid);
}
