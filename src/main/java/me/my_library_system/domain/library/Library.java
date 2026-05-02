package me.my_library_system.domain.library;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.my_library_system.domain.loan.LoanPolicy;
import me.my_library_system.domain.reservation.ReservationPolicy;

import java.time.LocalDate;

@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Library {
    @Id
    private Long id;

    private String name = "빛나는 꿈누리 도서관";
    private String code = "LDL";

    @Embedded
    private ReservationPolicy reservationPolicy;
    @Embedded
    private LoanPolicy loanPolicy;

    @Embedded
    private OperatingSchedule operatingSchedule;

    public LocalDate calculateDueDate(LocalDate baseDate, int loanDays) {
        return operatingSchedule.calculateDueDate(baseDate, loanDays);
    }
}
