package it.unicam.coloni.hackhub.context.event.application.strategies;

import it.unicam.coloni.hackhub.context.event.application.dto.requests.AddJudgeRequest;
import it.unicam.coloni.hackhub.context.event.application.dto.requests.AddMentorRequest;
import it.unicam.coloni.hackhub.context.event.application.dto.requests.CreateEventRequest;
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
    public boolean canHandle(CreateEventRequest request) {
        return request.getJudgeId()!=null && request.getMentorsId()!=null && !request.getMentorsId().isEmpty();
    }

    @Transactional
    @Override
    public Event create(CreateEventRequest request, StaffMember organizer) {
        Event event = Event.fromOrganizer(organizer);
        Event settedUpEvent = eventMapper.toEvent(request, event);
        Event savedEvent = eventRepository.save(settedUpEvent);
        AddJudgeRequest judgeRequest = new AddJudgeRequest(savedEvent.getId(), request.getJudgeId());
        staffService.addJudge(judgeRequest);

        for(Long id : request.getMentorsId()){
            AddMentorRequest mentorRequest = new AddMentorRequest(savedEvent.getId(),id);
            staffService.addMentor(mentorRequest);
        }

        return savedEvent;
    }
}
