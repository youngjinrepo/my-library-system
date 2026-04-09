package me.my_library_system.domain.book;

public record BookItemRegisterRequest(
        String title, String volume, String author, String classify, int bookNo, int bookCnt, String code) {


}
