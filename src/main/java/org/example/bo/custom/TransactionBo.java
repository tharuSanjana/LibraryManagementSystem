package org.example.bo.custom;

import org.example.SuperBO;
import org.example.dto.TransactionDto;

import java.util.List;

public interface TransactionBo extends SuperBO {
    public abstract String generateTransId();

    public abstract List<TransactionDto> getAllUserBorrowBooks();

    public abstract List<TransactionDto> getAllUserBorrowBookDetails();

}
