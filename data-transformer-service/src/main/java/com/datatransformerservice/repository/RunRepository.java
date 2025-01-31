package com.datatransformerservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.datatransformerservice.model.Run;

@Repository
public interface RunRepository extends JpaRepository<Run, String> {
}
