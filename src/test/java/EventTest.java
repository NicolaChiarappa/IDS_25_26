import it.unicam.coloni.hackhub.model.Assignment;
import it.unicam.coloni.hackhub.model.Staff;
import it.unicam.coloni.hackhub.model.User;
import it.unicam.coloni.hackhub.model.UserRole;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class EventTest {


    @Test
    public void shouldFailWhenAddMoreThanOneJudge(){
        User giudice1 = new User();
        giudice1.setFirstName("giudice1");
        giudice1.setRole(UserRole.JUDGE);

        User giudice2 = new User();
        giudice2.setFirstName("giudice2");
        giudice2.setRole(UserRole.JUDGE);



        Assignment a1 = new Assignment();
        a1.setUser(giudice1);

        Assignment a2 = new Assignment();
        a2.setUser(giudice2);

        List<Assignment> assignments = new ArrayList<>();
        assignments.add(a1);
        assignments.add(a2);

        System.out.println(assignments);

        //Staff staff = new Staff(assignments);

        Assertions.assertThrows(IllegalStateException.class, ()-> new Staff(assignments));







    }

}
