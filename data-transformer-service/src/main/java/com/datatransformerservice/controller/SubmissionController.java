package com.datatransformerservice.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.datatransformerservice.dto.SubmissionRequestDTO;
import com.datatransformerservice.dto.SubmissionResponseDTO;
import com.datatransformerservice.dto.TestSubmissionResponseDTO;
import com.datatransformerservice.model.Problem;
import com.datatransformerservice.model.Submission;
import com.datatransformerservice.service.ProblemService;
import com.datatransformerservice.service.SubmissionService;

import java.util.Optional;

@RestController
@RequestMapping("/submission")
public class SubmissionController {
    private final SubmissionService submissionService;
    private final ProblemService problemService;

    @Autowired
    public SubmissionController(SubmissionService submissionService, ProblemService problemService) {
        this.submissionService = submissionService;
        this.problemService = problemService;
    }

    @GetMapping("/{sid}")
    public ResponseEntity<SubmissionResponseDTO> getSubmissionById(@PathVariable String sid) {
        Optional<Submission> submission = submissionService.getSubmissionById(sid);
        if (submission.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(SubmissionResponseDTO.from(submission.get()));
    }

    @PostMapping
    public ResponseEntity<SubmissionResponseDTO> submitSolution(
            @Valid @RequestBody SubmissionRequestDTO submissionReq
    ) {
        Optional<Problem> question = problemService.getProblemById(submissionReq.getPid());
        if (question.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        SubmissionResponseDTO submissionRes = submissionService.submit(question.get(), submissionReq);
        return ResponseEntity.status(HttpStatus.CREATED).body(submissionRes);
    }

    @PostMapping("/test")
    public ResponseEntity<TestSubmissionResponseDTO> testSolution(
            @Valid @RequestBody SubmissionRequestDTO testSubmissionReq
    ) {
        Optional<Problem> question = problemService.getProblemById(testSubmissionReq.getPid());
        if (question.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        TestSubmissionResponseDTO testSubmissionRes = submissionService.submitTest(question.get(), testSubmissionReq);
        return ResponseEntity.status(HttpStatus.CREATED).body(testSubmissionRes);
    }
}
