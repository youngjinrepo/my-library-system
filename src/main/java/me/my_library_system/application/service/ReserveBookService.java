package me.my_library_system.application.service;

import lombok.RequiredArgsConstructor;
import me.my_library_system.application.port.in.reservation.ReserveBookUseCase;
import me.my_library_system.application.port.in.reservation.command.ReserveBookCommand;
import me.my_library_system.application.port.in.reservation.result.ReserveBookResult;
import me.my_library_system.domain.library.LibraryRepository;
import me.my_library_system.domain.loan.LoanRepository;
import me.my_library_system.domain.reservation.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ReserveBookService implements ReserveBookUseCase {
    private final ReservationRepository reservationRepository;
    private final LoanRepository loanRepository;
    private final LibraryRepository libraryRepository;
    private final Clock clock;


    @Override
    public ReserveBookResult reserveBook(ReserveBookCommand command) {
        LocalDateTime now = LocalDateTime.now(clock);
        LocalDate nowDate = now.toLocalDate();

        ReservationPolicy policy = libraryRepository.getLibrary().getReservationPolicy();
        List<Reservation> reservations = reservationRepository.findByMemberIdAndStatus(command.memberId(), ReservationStatus.ACTIVE);

        boolean hasSameReserve = reservations.stream().anyMatch(reservation -> reservation.getBookInfoId().equals(command.bookInfoId()));


        ReservationCreateContext context = new ReservationCreateContext(
                hasSameReserve,
                reservations.size(),
                reservationRepository.countCancellationSince(command.memberId(), policy.cancellationCooldown(), now),
                loanRepository.countOverdueLoans(command.memberId(), nowDate)
        );

        Reservation reservation = Reservation.createReservation(command.bookInfoId(), command.memberId(), policy, context);

        Reservation saved = reservationRepository.save(reservation);

        return ReserveBookResult.from(saved);
    }
}
