package me.my_library_system.domain;

public record BookItemRegisterRequest(
        String title, String volume, String author, String classify, int bookNo, int bookCnt) {


}
