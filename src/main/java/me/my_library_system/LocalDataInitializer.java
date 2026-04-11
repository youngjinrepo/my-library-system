package me.my_library_system;

import lombok.RequiredArgsConstructor;
import me.my_library_system.domain.library.Library;
import me.my_library_system.domain.library.Policy;
import me.my_library_system.domain.library.LibraryRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LocalDataInitializer implements ApplicationRunner {

    private final LibraryRepository libraryRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if ( libraryRepository.count() == 0 ) {
            Policy policy = new Policy(3, 3, 14, 7);
            Library library = new Library(1L, "빛나는 꿈누리 도서관", "LDL", policy);
            libraryRepository.save(library);
        }
    }
}
