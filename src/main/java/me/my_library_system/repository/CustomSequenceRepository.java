package me.my_library_system.repository;

import jakarta.persistence.LockModeType;
import me.my_library_system.domain.CustomSequence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomSequenceRepository extends JpaRepository<CustomSequence, String> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT s FROM CustomSequence s WHERE s.sequenceName = :name")
    Optional<CustomSequence> findByNameWithLock(@Param("name") String name);
}
