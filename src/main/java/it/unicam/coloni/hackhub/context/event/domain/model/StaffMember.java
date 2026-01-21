package it.unicam.coloni.hackhub.context.event.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import it.unicam.coloni.hackhub.shared.domain.enums.PlatformRoles;
import lombok.*;

import java.util.ArrayList;
import java.util.List;



@Data
@RequiredArgsConstructor
@NoArgsConstructor
public class StaffMember {

    @NonNull
    private Long id;

    @NonNull
    private String username;

    @NonNull
    private PlatformRoles role;

    @ToString.Exclude
    @JsonIgnore
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private List<Assignment> assignments = new ArrayList<>();

    public List<Assignment> getAssignments()
    {
        return List.copyOf(this.assignments);
    }





}
