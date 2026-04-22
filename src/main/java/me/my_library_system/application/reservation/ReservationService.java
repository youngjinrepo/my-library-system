package me.my_library_system.application.reservation;

import lombok.RequiredArgsConstructor;
import me.my_library_system.common.exception.DuplicatedException;
import me.my_library_system.domain.library.LibraryRepository;
import me.my_library_system.domain.library.Policy;
import me.my_library_system.domain.loan.LoanRepository;
import me.my_library_system.domain.reservation.Reservation;
import me.my_library_system.domain.reservation.ReservationRepository;
import me.my_library_system.domain.reservation.ReservationStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservationService {
    private final ReservationRepository reservationRepository;
    private final LoanRepository loanRepository;
    private final LibraryRepository libraryRepository;

    @Transactional
    public void reservationProcess(Long bookInfoId, Long memberId) {
        Policy policy = libraryRepository.getLibrary().getPolicy();
        List<Reservation> reservations = reservationRepository.findByMemberIdAndStatus(memberId, ReservationStatus.ACTIVE);

        boolean hasSameReserve = reservations.stream()
                .anyMatch(reservation -> reservation.getBookInfoId().equals(bookInfoId));
        if (hasSameReserve) {
            throw new DuplicatedException("동일한 예약 내역이 존재합니다.");
        }

        policy.validateMaxReservationCnt(reservations.size());

        int cancellationCooldown = policy.cancellationCooldown();
        int cancelCnt = reservationRepository.countCancellationSince(memberId, cancellationCooldown, LocalDateTime.now());
        if (cancelCnt > 0) {
            throw new IllegalStateException("동일한 도서는 예약 취소 후 "+cancellationCooldown+"간 예약이 불가능 합니다.");
        }
        int overdueLoanCnt = loanRepository.countOverdueLoans(memberId, LocalDateTime.now());
        if (overdueLoanCnt > 0) {
            throw new IllegalStateException("연체중인 도서가 있어 대출 할 수 없습니다.");
        }

        Reservation reservation = Reservation.createReservation(memberId, bookInfoId);

        reservationRepository.save(reservation);
    }

}
