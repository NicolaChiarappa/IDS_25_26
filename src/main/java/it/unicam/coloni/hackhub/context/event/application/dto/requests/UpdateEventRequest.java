package it.unicam.coloni.hackhub.context.event.application.dto.requests;

import it.unicam.coloni.hackhub.context.event.domain.model.EventStatus;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class UpdateEventRequest extends EventRequest{

    private Long id;

    private EventStatus status;


}
