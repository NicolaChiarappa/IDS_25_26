package it.unicam.coloni.hackhub.context.workspace.application.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MeetingDto {
    private Long id;
    private Long eventId;
    private Long teamId;
    private Long mentorId;
    private Long ticketId;
    private LocalDateTime date;
    private String link;
    private boolean isUpcoming;
}
