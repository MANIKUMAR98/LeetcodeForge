package com.datatransformerservice.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.datatransformerservice.dto.ProblemRequestDTO;
import com.datatransformerservice.dto.ProblemResponseDTO;
import com.datatransformerservice.dto.SubmissionResponseDTO;
import com.datatransformerservice.model.Problem;
import com.datatransformerservice.service.ProblemService;
import com.datatransformerservice.service.SubmissionService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/problem")
public class ProblemController {
    private final ProblemService problemService;
    private final SubmissionService submissionService;

    @Autowired
    public ProblemController(ProblemService problemService, SubmissionService submissionService) {
        this.problemService = problemService;
        this.submissionService = submissionService;
    }

    @GetMapping
    public List<ProblemResponseDTO> getAllProblems() {
        return problemService.getAllProblems().stream().map(ProblemResponseDTO::from).toList();
    }

    @GetMapping("/{pid}")
    public ResponseEntity<ProblemResponseDTO> getProblemById(@PathVariable("pid") String pid) {
        Optional<Problem> problem = problemService.getProblemById(pid);
        if (problem.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(ProblemResponseDTO.from(problem.get()));
    }

    @GetMapping("/{pid}/submission")
    public ResponseEntity<List<SubmissionResponseDTO>> getQuestionSubmission(@PathVariable("pid") String pid) {
        Optional<Problem> problem = problemService.getProblemById(pid);
        if (problem.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(
                submissionService.getSubmissionByPid(pid).stream()
                        .map(SubmissionResponseDTO::from)
                        .toList()
        );
    }

    @PostMapping
    public ResponseEntity<ProblemResponseDTO> saveQuestion(
            @Valid @RequestBody ProblemRequestDTO problemReq
    ) {
        Problem problem = problemService.saveProblem(problemReq);
        return ResponseEntity.status(HttpStatus.CREATED).body(ProblemResponseDTO.from(problem));
    }

    @PutMapping("/{pid}")
    public ResponseEntity<ProblemResponseDTO> updateQuestion(
            @PathVariable("pid") String pid,
            @Valid @RequestBody ProblemRequestDTO problemReq
    ) {
        Optional<Problem> problem = problemService.getProblemById(pid);
        if (problem.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(
                ProblemResponseDTO.from(
                        problemService.updateProblem(problem.get(), problemReq)
                )
        );
    }

    @DeleteMapping("/{pid}")
    public ResponseEntity<Void> deleteQuestion(@PathVariable("pid") String qid) {
        if (problemService.getProblemById(qid).isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        problemService.deleteQuestionById(qid);
        return ResponseEntity.noContent().build();
    }
}

