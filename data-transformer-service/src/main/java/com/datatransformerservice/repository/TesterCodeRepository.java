package com.datatransformerservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.datatransformerservice.model.TesterCode;
import com.datatransformerservice.model.TesterCodeKey;

import java.util.List;

@Repository
public interface TesterCodeRepository extends JpaRepository<TesterCode, TesterCodeKey> {
    List<TesterCode> findAllByIdPid(String pid);
}
