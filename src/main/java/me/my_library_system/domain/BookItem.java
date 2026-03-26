package me.my_library_system.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class BookItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String code;
    private String callNo;
    private String status;
    private String location;


    public static BookItem createBookItem(BookItemRegisterRequest request) {
        BookItem bookItem = new BookItem();
        //generateCode();
        //generateCallNo();
        bookItem.setLocation(request.location());

        return bookItem;
    }
}
