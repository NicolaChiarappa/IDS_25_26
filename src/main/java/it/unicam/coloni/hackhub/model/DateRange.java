package it.unicam.coloni.hackhub.model;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.mapstruct.Named;

import java.time.LocalDate;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DateRange {


   @Named(value = "fromDates")
   public static DateRange fromDates(LocalDate startDate, LocalDate endDate){
      return new DateRange(startDate, endDate);
   }

   private LocalDate startDate;
   private LocalDate endDate;


   public boolean overlap(DateRange range){
      //TODO:
      return false;
   }

}
