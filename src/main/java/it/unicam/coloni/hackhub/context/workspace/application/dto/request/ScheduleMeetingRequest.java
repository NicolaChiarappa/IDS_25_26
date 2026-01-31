package it.unicam.coloni.hackhub.context.workspace.application.dto.request;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleMeetingRequest {
        private Long eventId;
        private Long teamId;
        private LocalDateTime date;
        private String link;
}
