package me.my_library_system.application.reservation;

import lombok.RequiredArgsConstructor;
import me.my_library_system.domain.library.LibraryRepository;
import me.my_library_system.domain.loan.LoanRepository;
import me.my_library_system.domain.reservation.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservationService {
    private final ReservationRepository reservationRepository;
    private final LoanRepository loanRepository;
    private final LibraryRepository libraryRepository;
    private final Clock clock;

    @Transactional
    public void reservationProcess(Long bookInfoId, Long memberId) {
        ReservationPolicy policy = libraryRepository.getLibrary().getReservationPolicy();
        List<Reservation> reservations = reservationRepository.findByMemberIdAndStatus(memberId, ReservationStatus.ACTIVE);

        boolean hasSameReserve = reservations.stream().anyMatch(reservation -> reservation.getBookInfoId().equals(bookInfoId));

        ReservationCreateContext context = new ReservationCreateContext(
                hasSameReserve,
                reservations.size(),
                reservationRepository.countCancellationSince(memberId, policy.cancellationCooldown(), LocalDateTime.now(clock)),
                loanRepository.countOverdueLoans(memberId, LocalDateTime.now(clock))
        );

        Reservation reservation = Reservation.createReservation(memberId, bookInfoId, policy, context);

        reservationRepository.save(reservation);
    }

}
