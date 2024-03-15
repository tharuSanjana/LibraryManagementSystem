package org.example.dto;

public class SearchBookDto {
    @Override
    public String toString() {
        return "SearchBook{}";
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        SearchBookDto.bookName = bookName;
    }

    public SearchBookDto() {
    }

    private static String bookName;
}
