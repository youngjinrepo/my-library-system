package me.my_library_system.adapter.in.web.reservation;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import me.my_library_system.adapter.in.web.reservation.dto.ReserveRequest;
import me.my_library_system.adapter.out.persistence.ReservationPersistenceAdapter;
import me.my_library_system.application.reservation.ReservationService;
import me.my_library_system.domain.reservation.Reservation;
import me.my_library_system.domain.reservation.ReservationRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservations")
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;
    private final ReservationPersistenceAdapter persistenceAdapter;

    @PostMapping
    public ResponseEntity<Void> reserve(@Valid @RequestBody ReserveRequest request) {
        reservationService.reserve(request.bookInfoId(), request.memberId());
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<Reservation>> getAllReservations() {
        return ResponseEntity.ok(persistenceAdapter.findAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<Reservation> getReservationById(@Valid @PathVariable Long id) {
        Reservation reservation = persistenceAdapter.findById(id).orElseThrow();
        return ResponseEntity.ok(reservation);
    }
}
