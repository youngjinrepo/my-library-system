package me.my_library_system.domain;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;


@Entity
public class Library {
    @Getter
    private static String name = "빛나는 꿈누리 도서관";
    @Getter
    private static String code = "LDL";

    Policy policy;
}
