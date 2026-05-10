package me.my_library_system.adapter.in.web.reservation;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import me.my_library_system.adapter.in.web.reservation.request.ReserveRequest;
import me.my_library_system.adapter.in.web.reservation.response.ReserveBookResponse;
import me.my_library_system.application.port.in.reservation.ReserveBookUseCase;
import me.my_library_system.application.port.in.reservation.result.ReserveBookResult;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/reservations")
@RequiredArgsConstructor
public class ReservationController {

    private final ReserveBookUseCase reserveBookUseCase;

    @PostMapping
    public ResponseEntity<ReserveBookResponse> reserve(@Valid @RequestBody ReserveRequest request) {
        ReserveBookResult reserveBookResult = reserveBookUseCase.reserveBook(request.toCommand());

        return ResponseEntity.created(URI.create("/api/reservations/" + reserveBookResult.reservationId()))
                .body(ReserveBookResponse.from(reserveBookResult));
    }

}
