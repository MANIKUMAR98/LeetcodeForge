package com.datatransformerservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.datatransformerservice.model.Run;
import com.datatransformerservice.repository.RunRepository;

import java.util.Optional;

@Service
public class RunService {
    private final RunRepository runRepository;

    @Autowired
    public RunService(RunRepository runRepository) {
        this.runRepository = runRepository;
    }

    public Optional<Run> getRunById(String rid) {
        return runRepository.findById(rid);
    }

    public Run saveRun(Run run) {
        return runRepository.save(run);
    }
}
