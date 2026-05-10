package me.my_library_system.application.port.in.reservation;

import me.my_library_system.application.port.in.reservation.command.ReserveBookCommand;
import me.my_library_system.application.port.in.reservation.result.ReserveBookResult;

public interface ReserveBookUseCase {

    ReserveBookResult reserveBook(ReserveBookCommand command);
}
