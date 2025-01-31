package com.datatransformerservice.model;

import jakarta.persistence.Embeddable;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
@Embeddable
public class TemplateCodeKey {
    private String pid;
    private String lang;
}
