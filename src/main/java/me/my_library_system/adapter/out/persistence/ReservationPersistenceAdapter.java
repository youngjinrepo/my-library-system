package me.my_library_system.adapter.out.persistence;

import lombok.RequiredArgsConstructor;
import me.my_library_system.adapter.out.persistence.jpa.ReservationJpaRepository;
import me.my_library_system.domain.reservation.Reservation;
import me.my_library_system.domain.reservation.ReservationRepository;
import me.my_library_system.domain.reservation.ReservationStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservationPersistenceAdapter implements ReservationRepository {
    private final ReservationJpaRepository reservationJpaRepository;

    @Override
    public void save(Reservation reservation) {
        reservationJpaRepository.save(reservation);
    }

    @Override
    public List<Reservation> findByMemberIdAndStatus(Long memberId, ReservationStatus reservationStatus) {
        return reservationJpaRepository.findByMemberIdAndStatus(memberId, reservationStatus);
    }

    @Override
    public int countCancellationSince(Long memberId, int cancellationCooldown, LocalDateTime end) {
        return reservationJpaRepository.countByMemberId(memberId, ReservationStatus.CANCELLED, end.minusDays(cancellationCooldown), end);
    }
}
