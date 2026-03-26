package me.my_library_system.domain;

public record BookItemRegisterRequest(
        String title, String volume, String author, String location, String classify, int bookNo) {


}
