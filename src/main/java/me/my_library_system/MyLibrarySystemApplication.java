package me.my_library_system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

//@EnableCaching
@SpringBootApplication
public class MyLibrarySystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyLibrarySystemApplication.class, args);
	}

}
