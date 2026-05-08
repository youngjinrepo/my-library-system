package me.my_library_system.application.reservation;

import me.my_library_system.domain.library.LibraryFixture;
import me.my_library_system.domain.library.LibraryRepository;
import me.my_library_system.domain.loan.LoanRepository;
import me.my_library_system.domain.reservation.*;
import org.junit.jupiter.api.Test;

import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.BDDMockito.*;

class ReservationServiceTest {
    ReservationRepository reservationRepository = mock(ReservationRepository.class);
    LoanRepository loanRepository = mock(LoanRepository.class);
    LibraryRepository libraryRepository = mock(LibraryRepository.class);
    Clock clock = Clock.systemDefaultZone();
    ReservationService reservationService = new ReservationService(reservationRepository, loanRepository, libraryRepository, clock);

    @Test
    void 예약_성공() {
        ReservationPolicy policy = LibraryFixture.defaultLibrary().getReservationPolicy();

        given(libraryRepository.getLibrary())
                .willReturn(LibraryFixture.defaultLibrary());
        given(reservationRepository.findByMemberIdAndStatus(10L, ReservationStatus.ACTIVE))
                .willReturn(List.of());
        given(reservationRepository.countCancellationSince(10L, 3, LocalDateTime.now(clock)))
                .willReturn(0);
        given(loanRepository.countOverdueLoans(10L, LocalDate.now(clock)))
                .willReturn(0);

        reservationService.reserve(1L, 10L);

        then(reservationRepository).should(times(1)).save(any());
    }

}