package me.my_library_system.domain.library;

import jakarta.persistence.*;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Embeddable
public class OperatingSchedule {
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "library_regular_closed_days")
    @Enumerated(EnumType.STRING)
    private Set<DayOfWeek> regularClosedDays = new HashSet<>();

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "library_temporary_closed_days")
    private Set<LocalDate> temporaryClosedDays = new HashSet<>();

    public LocalDate calculateDueDate(LocalDate baseDate, int loanDays) {
        LocalDate dueDate = baseDate.plusDays(loanDays);
        while (isClosed(dueDate)) {
            dueDate = dueDate.plusDays(1);
        }
        return dueDate;
    }

    private boolean isClosed(LocalDate dueDate) {
        return regularClosedDays.contains(dueDate.getDayOfWeek())
                || temporaryClosedDays.contains(dueDate);
    }
}
