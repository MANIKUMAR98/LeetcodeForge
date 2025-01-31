package com.datatransformerservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.datatransformerservice.model.TemplateCode;
import com.datatransformerservice.model.TemplateCodeKey;
import com.datatransformerservice.model.TesterCode;

import java.util.List;

public interface TemplateCodeRepository extends JpaRepository<TemplateCode, TemplateCodeKey> {
    List<TesterCode> findAllByIdPid(String pid);
}
