package it.unicam.coloni.hackhub.context.workspace.application.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetTicketsRequest {
    private Long eventId;

}
