package it.unicam.coloni.hackhub.model;


import lombok.Data;
import java.util.*;

@Data
public class Staff {

    private static Map<UserRole, Integer> maxQuantity = Map.of(
            UserRole.JUDGE,1,
            UserRole.MENTOR, 100,
            UserRole.ORGANIZER, 1
    );

    public Staff(List<Assignment> assignments) {
        this.assignments = assignments;
        this.event = assignments.getFirst().getEvent();
    }

    private List<Assignment> assignments;
    private Event event;


    /**
     * Modifies the assignment list and returns the new assignment added to the list
     * @param mentor the user to add as mentor
     * @return the new assignment
     */
    public Assignment addMentor(User mentor){
        checkRole(mentor, UserRole.MENTOR);
        checkQuantity(UserRole.MENTOR);

        Assignment assignment = new Assignment(mentor, event, null);
        assignments.add(assignment);
        return assignment;
    }


    /**
     * Modifies the assignment list and returns the new assignment added to the list
     * @param judge the user to add as judge
     * @return the new assignment
     */
    public Assignment addJudge(User judge){
        checkRole(judge, UserRole.JUDGE);
        checkQuantity(UserRole.JUDGE);

        Assignment assignment = new Assignment(judge, event, null);
        assignments.add(assignment);
        return assignment;
    }

    /**
     * Assign a mentor to a specified {@link Team}
     * @param mentor the mentor to assign to the team
     * @param team the team linked to the mentor
     * @return the new assignment
     */
    public Assignment updateMentor(User mentor, Team team){
        if(this.contains(mentor)){
            Assignment assignment = new Assignment(mentor, event, team);
            this.assignments.add(assignment);
            return assignment;
        }else {
            throw new IllegalArgumentException("The mentor with id:" + mentor.getId() +"is not assigned to this event");
        }
    }

    public boolean contains(User user){
        return assignments.stream().anyMatch(assignment -> assignment.getUser().equals(user));
    }


    private void checkQuantity(UserRole role){
        Integer roleCount = (int) assignments.stream()
                .filter(assignment -> assignment.getUser().getRole()==role)
                .count();

        if(roleCount.compareTo(maxQuantity.get(role))>=0){
            throw new IllegalStateException("Impossible to add this staff member:" + role + "s quantity limit reached");
        }
    }


    private void checkRole(User user, UserRole role){
        if(user.getRole()!=role){
            throw new IllegalArgumentException("The user has not the correct role on the platform, expected: " + role + " actual: " + user.getRole());
        }
    }



}
