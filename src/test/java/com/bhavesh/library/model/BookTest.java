package com.bhavesh.library.model;

import com.bhavesh.library.exception.InvalidBookException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BookTest {
    @Test
    void constructor_throwsInvalidBookException_whenTitleIsBlank() {
        assertThrows(InvalidBookException.class, () -> {
            new Book("", "Robert C. Martin", "111", 5);
        });
    }

    @Test
    void constructor_returnsTitle_whenBookIsValid() {
        Book book = new Book("Clean Code", "Robert C. Martin", "111", 5);

        assertEquals("Clean Code",book.getTitle());
    }

    @Test
    void constructor_throwsInvalidBookException_whenAuthorIsBlank() {
        assertThrows(InvalidBookException.class, () -> {
            new Book("Clean Code", "", "111", 5);
        });
    }

    @Test
    void constructor_throwsInvalidBookException_whenIsbnIsBlank() {
        assertThrows(InvalidBookException.class, () -> {
            new Book("Clean Code", "Robert C. Martin", "", 5);
        });
    }

    @Test
    void constructor_throwsInvalidBookException_whenQuantityIsZero() {
        assertThrows(InvalidBookException.class, () -> {
            new Book("Clean Code", "Robert C. Martin", "111", 0);
        });
    }

    @Test
    void constructor_throwsInvalidBookException_whenQuantityIsNegative() {
        assertThrows(InvalidBookException.class, () -> {
            new Book("Clean Code", "Robert C. Martin", "111", -1);
        });
    }
}
