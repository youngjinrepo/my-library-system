package me.my_library_system.domain.library;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LibraryRepository extends CrudRepository<Library, Long> {

    //@Cacheable(value = "libraryCache")
    @Query("SELECT l FROM Library l WHERE l.id = 1")
    Library getLibrary();
}
