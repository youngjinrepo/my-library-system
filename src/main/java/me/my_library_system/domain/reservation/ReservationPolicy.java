package me.my_library_system.domain.reservation;

import jakarta.persistence.Embeddable;

@Embeddable
public record ReservationPolicy(int maxReservationCnt, int cancellationCooldown) {

}
