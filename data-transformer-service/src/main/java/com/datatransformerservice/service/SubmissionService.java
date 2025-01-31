package com.datatransformerservice.service;

import com.datatransformerservice.dto.SubmissionRequestDTO;
import com.datatransformerservice.dto.SubmissionResponseDTO;
import com.datatransformerservice.dto.TestSubmissionResponseDTO;
import com.datatransformerservice.model.*;
import com.datatransformerservice.repository.SubmissionRepository;
import com.fasterxml.jackson.databind.json.JsonMapper;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SubmissionService {
    private final RunService runService;
    private final SubmissionRepository submissionRepository;
    private final KafkaService kafkaService;
    private final JsonMapper jsonMapper;

    @Autowired
    public SubmissionService(
            RunService runService,
            KafkaService kafkaService,
            SubmissionRepository submissionRepository
    ) {
        this.runService = runService;
        this.submissionRepository = submissionRepository;
        this.kafkaService = kafkaService;
        jsonMapper = new JsonMapper();
    }

    public Optional<Submission> getSubmissionById(String sid) {
        return submissionRepository.findById(sid);
    }

    public List<Submission> getSubmissionByPid(String pid) {
        return submissionRepository.findByProblemId(pid);
    }

    // TODO handle exception
    @SneakyThrows
    public SubmissionResponseDTO submit(Problem problem, SubmissionRequestDTO submissionReq) {
        Run run = Run.builder()
                .problem(problem)
                .code(submissionReq.getCode())
                .lang(submissionReq.getLang())
                .status("Queued")
                .type("Submission")
                .build();
        runService.saveRun(run);

        Submission submission = Submission.builder()
                .problem(problem)
                .run(run)
                .build();
        submissionRepository.save(submission);

        TestSubmissionResponseDTO testSubmissionRes = TestSubmissionResponseDTO.from(run);
        kafkaService.produceMessage(jsonMapper.writeValueAsString(testSubmissionRes));

        return SubmissionResponseDTO.from(submission);
    }

    // TODO handle exception
    @SneakyThrows
    public TestSubmissionResponseDTO submitTest(Problem problem, SubmissionRequestDTO testSubmissionReq) {
        Run testRun = Run.builder()
                .problem(problem)
                .code(testSubmissionReq.getCode())
                .lang(testSubmissionReq.getLang())
                .status("Queued")
                .type("Test")
                .build();
        runService.saveRun(testRun);

        TestSubmissionResponseDTO testSubmissionRes = TestSubmissionResponseDTO.from(testRun);
        kafkaService.produceMessage(jsonMapper.writeValueAsString(testSubmissionRes));

        return testSubmissionRes;
    }
}
