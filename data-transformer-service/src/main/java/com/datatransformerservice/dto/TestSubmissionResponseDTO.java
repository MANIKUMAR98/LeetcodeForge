package com.datatransformerservice.dto;

import com.datatransformerservice.model.Run;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TestSubmissionResponseDTO {
    private String rid;

    public static TestSubmissionResponseDTO from(Run run) {
        return TestSubmissionResponseDTO.builder()
            .rid(run.getId())
            .build();
    }
}
