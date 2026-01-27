package it.unicam.coloni.hackhub.context.event.application.dto.requests;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class WithStaffCreationRequest extends EventCreationRequest {

    private Long judgeId;
    private List<Long> mentorsId;

}
