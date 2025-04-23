// src/main/java/com/project/demo/dto/ProjectDTO.java
package com.project.demo.models;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Data
public class ProjectDTO {

    @NotBlank
    private String name;

    @NotBlank
    private String code;

    private String note;

    @NotNull
    private Long encadrantId;

    private Long rapporteurId;
    private Long presidentId;

    @NotNull @Size(min = 1)
    private List<Long> studentIds;
}
