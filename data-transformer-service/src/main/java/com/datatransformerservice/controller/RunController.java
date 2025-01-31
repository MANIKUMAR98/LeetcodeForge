package com.datatransformerservice.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.datatransformerservice.dto.RunRequestDTO;
import com.datatransformerservice.dto.RunResponseDTO;
import com.datatransformerservice.model.Run;
import com.datatransformerservice.service.RunService;

import java.util.Optional;

@RestController
@RequestMapping("/run")
public class RunController {
    private final RunService runService;

    @Autowired
    public RunController(RunService runService) {
        this.runService = runService;
    }

    @GetMapping("/{rid}")
    public ResponseEntity<RunResponseDTO> getRunById(@PathVariable String rid) {
        Optional<Run> run = runService.getRunById(rid);
        if (run.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(RunResponseDTO.from(run.get()));
    }

    @PatchMapping("/{rid}")
    public ResponseEntity<RunResponseDTO> updateRun(@PathVariable String rid, @Valid @RequestBody RunRequestDTO runReq) {
        Run run = runService.getRunById(rid).orElse(null);
        if (run == null) {
            return ResponseEntity.notFound().build();
        }

        run.setStatus(runReq.getStatus());
        run.setExitCode(runReq.getExitCode());
        run.setStdOut(runReq.getStdOut());
        run.setStdErr(runReq.getStdErr());
        run.setVerdict(runReq.getVerdict());
        runService.saveRun(run);

        return ResponseEntity.ok(RunResponseDTO.from(run));
    }
}
