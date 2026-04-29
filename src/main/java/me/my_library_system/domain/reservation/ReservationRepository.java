package me.my_library_system.domain.reservation;

import java.time.LocalDateTime;
import java.util.List;

public interface ReservationRepository {

    void save(Reservation reservation);

    List<Reservation> findByMemberIdAndStatus(Long memberId, ReservationStatus reservationStatus);

    int countCancellationSince(Long memberId, int cancellationCooldown, LocalDateTime now);

    int countByBookInfoIdAndStatus(Long bookInfoId, ReservationStatus reservationStatus);
}
