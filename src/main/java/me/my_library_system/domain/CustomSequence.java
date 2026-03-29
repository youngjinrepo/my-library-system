package me.my_library_system.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CustomSequence {
    @Id
    private String sequenceName;
    private int currentVal;

    public CustomSequence(String sequenceName, int currentVal) {
        this.sequenceName = sequenceName;
        this.currentVal = currentVal;
    }

    public int getNextSequence(int count) {
        int startSequence = currentVal + 1;
        this.currentVal += count;
        return startSequence;
    }

}
