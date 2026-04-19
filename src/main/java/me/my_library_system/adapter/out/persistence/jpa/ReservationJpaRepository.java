package me.my_library_system.adapter.out.persistence.jpa;

import me.my_library_system.domain.reservation.Reservation;
import me.my_library_system.domain.reservation.ReservationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ReservationJpaRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findByMemberIdAndStatus(Long memberId, ReservationStatus reservationStatus);

    @Query("""
        SELECT COUNT(r) FROM Reservation r
                WHERE r.memberId = :memberId
                AND r.status = :reservationStatus
                AND r.reservationCanceledDate BETWEEN :start AND :end
        """)
    int countByMemberId(Long memberId, ReservationStatus reservationStatus, LocalDateTime start, LocalDateTime end);
}
