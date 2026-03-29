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
        bookItem.setCode(generateCode(Library.getCode(), request.bookNo()));
        //generateCallNo();
        bookItem.setLocation(request.location());

        return bookItem;
    }

    private static String generateCode(String code, int sequenceNo) {
        return String.format("%s%06d",code, sequenceNo);
    }
}
/*
* CODE를 할당하기 위해서는 도서관의 고유코드가 필요함
*
* */