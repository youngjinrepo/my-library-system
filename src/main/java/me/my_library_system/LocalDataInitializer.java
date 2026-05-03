package me.my_library_system;

import lombok.RequiredArgsConstructor;
import me.my_library_system.domain.library.Library;
import me.my_library_system.domain.library.OperatingSchedule;
import me.my_library_system.domain.library.LibraryRepository;
import me.my_library_system.domain.loan.LoanPolicy;
import me.my_library_system.domain.reservation.ReservationPolicy;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LocalDataInitializer implements ApplicationRunner {

    private final LibraryRepository libraryRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if ( libraryRepository.count() == 0 ) {

            ReservationPolicy reservationPolicy = new ReservationPolicy(5,3);
            LoanPolicy loanPolicy = new LoanPolicy(5,7, 3);
            OperatingSchedule operatingSchedule = new OperatingSchedule();
            Library library = new Library(1L, "빛나는 꿈누리 도서관", "LDL", reservationPolicy, loanPolicy, operatingSchedule);
            libraryRepository.save(library);
        }
    }
}
