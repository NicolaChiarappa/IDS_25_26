package it.unicam.coloni.hackhub.context.event.domain.model;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.mapstruct.Named;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DateRange {

   @Named(value = "fromDates")
   public static DateRange fromDates(LocalDate startDate, LocalDate endDate) {
      return new DateRange(startDate, endDate);
   }

   private LocalDate startDate;
   private LocalDate endDate;

   public boolean overlap(DateRange range) {
      if (range == null || this.startDate == null || this.endDate == null ||
            range.startDate == null || range.endDate == null) {
         return false;
      }

      // l'inizio di A deve essere prima (o uguale) alla fine di B
      // E l'inizio di B deve essere prima (o uguale) alla fine di A.
      return !this.startDate.isAfter(range.getEndDate()) &&
            !range.getStartDate().isAfter(this.endDate);
   }

   public boolean contains(LocalDate date) {
      if (date == null || startDate == null || endDate == null) {
         return false;
      }
      return !date.isBefore(startDate) && !date.isAfter(endDate);
   }

   public boolean contains(LocalDateTime dateTime) {
      return contains(dateTime.toLocalDate());
   }

}
