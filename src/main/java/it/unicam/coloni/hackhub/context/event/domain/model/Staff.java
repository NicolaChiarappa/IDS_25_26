package it.unicam.coloni.hackhub.context.event.domain.model;


import it.unicam.coloni.hackhub.shared.domain.enums.PlatformRoles;

import java.util.*;


public class Staff {

    private final static Map<PlatformRoles, Integer> maxQuantity = Map.of(
            PlatformRoles.JUDGE,1,
            PlatformRoles.MENTOR, 100,
            PlatformRoles.ORGANIZER, 1
    );

    protected Staff(List<Assignment> assignments) {
        if(assignments==null || assignments.isEmpty()){
            throw new IllegalArgumentException("The list must contain at least an organizer");
        }
        this.assignments = assignments;
        this.event = assignments.getFirst().getEvent();
    }

    private final List<Assignment> assignments;
    private final Event event;


    /**
     * Modifies the assignment list and returns the new assignment added to the list
     * @param mentor the user to add as mentor
     * @return the new assignment
     */
    public Assignment addMentor(StaffMember mentor){
        checkRole(mentor, PlatformRoles.MENTOR);
        checkQuantity(PlatformRoles.MENTOR);

        Assignment assignment = new Assignment(mentor.getId(), null, PlatformRoles.MENTOR, event);
        assignments.add(assignment);
        return assignment;
    }


    /**
     * Modifies the assignment list and returns the new assignment added to the list
     * @param judge the user to add as judge
     * @return the new assignment
     */
    public Assignment addJudge(StaffMember judge){
        checkRole(judge, PlatformRoles.JUDGE);
        checkQuantity(PlatformRoles.JUDGE);

        Assignment assignment = new Assignment(judge.getId(),null, PlatformRoles.JUDGE, event);
        assignments.add(assignment);
        return assignment;
    }

    /**
     * Assign a mentor to a specified {@link Team}
     * @param mentor the mentor to assign to the team
     * @param teamId the team linked to the mentor
     * @return the new assignment
     */
    public Assignment updateMentor(StaffMember mentor, Long teamId){
        if(this.contains(mentor.getId())){
            Assignment assignment = new Assignment(mentor.getId(), teamId, PlatformRoles.MENTOR, event);
            this.assignments.add(assignment);
            return assignment;
        }else {
            throw new IllegalArgumentException("The mentor with id:" + mentor.getId() +"is not assigned to this event");
        }
    }

    public boolean contains(Long userId){
        return assignments.stream().anyMatch(assignment -> assignment.getUserId().equals(userId));
    }


    private void checkQuantity(PlatformRoles role){
        Integer roleCount = (int) assignments.stream()
                .filter(assignment -> assignment.getRole()==role)
                .count();

        if(roleCount.compareTo(maxQuantity.get(role))>=0){
            throw new IllegalStateException("Impossible to add this staff member:" + role + "s quantity limit reached");
        }
    }


    private void checkRole(StaffMember user, PlatformRoles role){
        if(user.getRole()!=role){
            throw new IllegalArgumentException("The user has not the correct role on the platform, expected: " + role + " actual: " + user.getRole());
        }
    }



}
