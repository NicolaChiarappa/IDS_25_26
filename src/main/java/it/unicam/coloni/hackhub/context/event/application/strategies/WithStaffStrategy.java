package it.unicam.coloni.hackhub.context.event.application.strategies;

import it.unicam.coloni.hackhub.context.event.application.dto.requests.AddJudgeRequest;
import it.unicam.coloni.hackhub.context.event.application.dto.requests.AddMentorRequest;
import it.unicam.coloni.hackhub.context.event.application.dto.requests.WithStaffCreationRequest;
import it.unicam.coloni.hackhub.context.event.application.dto.requests.EventCreationRequest;
import it.unicam.coloni.hackhub.context.event.application.mapper.EventMapper;
import it.unicam.coloni.hackhub.context.event.application.service.StaffService;
import it.unicam.coloni.hackhub.context.event.domain.model.Event;
import it.unicam.coloni.hackhub.context.event.domain.model.StaffMember;
import it.unicam.coloni.hackhub.context.event.domain.repository.EventRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class WithStaffStrategy implements EventCreationStrategy{

    @Autowired
    StaffService staffService;

    @Autowired
    EventRepository eventRepository;

    @Autowired
    EventMapper eventMapper;

    @Override
    public boolean canHandle(EventCreationRequest request) {
        return request instanceof WithStaffCreationRequest;
    }

    @Transactional
    @Override
    public Event create(EventCreationRequest request, StaffMember organizer) {
        WithStaffCreationRequest castRequest = (WithStaffCreationRequest) request;

        Event event = Event.fromOrganizer(organizer);
        Event settedUpEvent = eventMapper.toEvent(castRequest, event);
        settedUpEvent.openSubscription();
        Event savedEvent = eventRepository.save(settedUpEvent);

        AddJudgeRequest judgeRequest = new AddJudgeRequest(savedEvent.getId(), castRequest.getJudgeId());
        staffService.addJudge(judgeRequest);

        for(Long id : castRequest.getMentorsId()){
            AddMentorRequest mentorRequest = new AddMentorRequest(savedEvent.getId(),id);
            staffService.addMentor(mentorRequest);
        }

        return savedEvent;
    }
}
