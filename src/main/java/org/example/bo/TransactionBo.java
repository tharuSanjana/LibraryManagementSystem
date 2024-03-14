package org.example.bo;

import org.example.dto.TransactionDto;

import java.util.List;

public interface TransactionBo {
    String generateTransId();

    List<TransactionDto> getAllUserBorrowBooks();

    List<TransactionDto> getAllUserBorrowBookDetails();

}
