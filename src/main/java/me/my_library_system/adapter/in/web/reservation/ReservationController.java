package me.my_library_system.adapter.in.web.reservation;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import me.my_library_system.adapter.in.web.reservation.dto.ReserveRequest;
import me.my_library_system.application.reservation.ReservationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/reservations")
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;

    @PostMapping
    public ResponseEntity<Void> reserve(@Valid @RequestBody ReserveRequest request) {
        reservationService.reserve(request.bookInfoId(), request.memberId());
        return ResponseEntity.ok().build();
    }
}
