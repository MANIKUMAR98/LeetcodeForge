package com.datatransformerservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

import com.datatransformerservice.model.Run;
import com.datatransformerservice.model.Submission;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SubmissionResponseDTO {
    private String id;
    private String pid;
    private RunResponseDTO run;
    private ZonedDateTime createdAt;

    public static SubmissionResponseDTO from(Submission submission) {
        return SubmissionResponseDTO.builder()
                .id(submission.getId())
                .pid(submission.getProblem().getId())
                .run(RunResponseDTO.from(submission.getRun()))
                .createdAt(submission.getCreatedAt())
                .build();
    }
}
