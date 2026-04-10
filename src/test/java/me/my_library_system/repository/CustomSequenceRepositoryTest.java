package me.my_library_system.repository;

import me.my_library_system.adapter.out.persistence.sequnce.CustomSequence;
import me.my_library_system.adapter.out.persistence.sequnce.CustomSequenceRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

@DataJpaTest
class CustomSequenceRepositoryTest {

    @Autowired
    CustomSequenceRepository customSequenceRepository;

    @Test
    void findBySequenceNameTest() {
        CustomSequence customSequence = new CustomSequence("TEST_SEQ", 1);
        customSequenceRepository.save(customSequence);

        Assertions.assertThat(customSequenceRepository.findByNameWithLock("TEST_SEQ").get().getCurrentVal()).isEqualTo(1);
    }
}