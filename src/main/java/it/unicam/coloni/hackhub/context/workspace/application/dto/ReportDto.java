package it.unicam.coloni.hackhub.context.workspace.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReportDto {
    private Long id;
    private Long eventId;
    private Long teamId;
    private Long authorId;
    private String description;
}
