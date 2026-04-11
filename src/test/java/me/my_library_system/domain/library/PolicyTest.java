package me.my_library_system.domain.library;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class PolicyTest {

    @Test
    void 최대_대출_건수_테스트() {
        assertThatCode(()->LibraryFixture.creatrPolicy().validateLoanCount(0))
                .doesNotThrowAnyException();
    }

    @Test
    void 최대_대출_건수_실패_테스트(){
        assertThatThrownBy(()->LibraryFixture.creatrPolicy().validateLoanCount(10))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("최대 대출 건수 (");
    }

    @Test
    void 반납연기_검증_성공() {
        Assertions.assertThatCode(()->LibraryFixture.creatrPolicy().validateMaxRenewalCount(0))
                .doesNotThrowAnyException();
    }

    @Test
    void 반납연기_검증_실패() {
        Assertions.assertThatThrownBy(()->LibraryFixture.creatrPolicy().validateMaxRenewalCount(999))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("최대 반납연기 횟수를 초과 하였습니다.");
    }

}