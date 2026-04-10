package me.my_library_system.domain;

import me.my_library_system.adapter.out.persistence.sequnce.CustomSequence;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class CustomSequenceTest {

    @Test
    void getNextSequence() {
        CustomSequence customSequence = new CustomSequence("TEST_SEQUENCE", 1);
        int startSeq = customSequence.getNextSequence(10);
        assertThat(startSeq).isEqualTo(2);

        assertThat(customSequence.getCurrentVal()).isEqualTo(11);
    }

}