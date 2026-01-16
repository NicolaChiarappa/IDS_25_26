package it.unicam.coloni.hackhub.context.identity.domain.models;

import it.unicam.coloni.hackhub.context.event.domain.model.BaseEntity;
import it.unicam.coloni.hackhub.context.event.domain.model.UserRole;
import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(
        name = "users",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"username"}),
                @UniqueConstraint(columnNames = {"email"})
        }
)
@EqualsAndHashCode(callSuper = true)
@Data
@RequiredArgsConstructor
@NoArgsConstructor
public class User extends BaseEntity {

    @Column
    @NonNull
    private String firstName;

    @Column
    private String lastName;

    @Column(name = "username")
    private String username;

    @Column(name = "email")
    private String email;

    @Column
    private String password;

    @Enumerated(EnumType.STRING)
    @NonNull
    private UserRole role;

//    @ToString.Exclude
//    @OneToMany(mappedBy = "user")
//    @JsonIgnore
//    @Getter(AccessLevel.NONE)
//    @Setter(AccessLevel.NONE)
//    private List<Assignment> assignments = new ArrayList<>();

//    public List<Assignment> getAssignments()
//    {
//        return List.copyOf(this.assignments);
//    }


//    public boolean isAvailable(DateRange dateRange){
//
//        if(!assignments.isEmpty()){
//            return assignments.stream()
//                    .anyMatch(assignment -> !assignment.getEvent().getRunningPeriod().overlap(dateRange));
//        }
//        return true;
//    }


}
