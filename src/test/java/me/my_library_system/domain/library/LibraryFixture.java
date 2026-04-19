package me.my_library_system.domain.library;

public class LibraryFixture {

    public static Policy creatrPolicy() {
        return new Policy(1,2,3, 4, 5, 3);
    }

    public static Policy creatrBadPolicy() {
        return new Policy(0,0,0, 0,  0, 3);
    }
}
