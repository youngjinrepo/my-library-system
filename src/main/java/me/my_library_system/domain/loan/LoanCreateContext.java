package me.my_library_system.domain.loan;

public record LoanCreateContext(int currentLoanCnt, java.time.LocalDate dueDate) {
}
