package org.example.bo.impl;

import org.example.DAOFactory;
import org.example.bo.custom.TransactionBo;
import org.example.dao.custom.TransactionDao;
import org.example.dao.impl.TransactionDaoImpl;
import org.example.dto.TransactionDto;
import org.example.entity.UserBook;

import java.util.ArrayList;
import java.util.List;

public class TransactionBoImpl implements TransactionBo {
   // TransactionDao transactionDao = new TransactionDaoImpl();
    TransactionDao transactionDao = (TransactionDao) DAOFactory.daoFactory().getDAOTypes(DAOFactory.DAOTypes.USERBOOK);
    @Override
    public String generateTransId() {
        return transactionDao.generateTransId();
    }

    @Override
    public List<TransactionDto> getAllUserBorrowBooks() {
        ArrayList<TransactionDto> trans = new ArrayList<>();
        ArrayList<UserBook> allTrans =  transactionDao.getAllUserBorrowBooks();

        for (int i=0;i<allTrans.size();i++){
            trans.add(new TransactionDto(allTrans.get(i).getId(),
                    allTrans.get(i).getReturnDate(),
                    allTrans.get(i).getReserveDate(),
                    allTrans.get(i).getBorrowDate(),
                    allTrans.get(i).getIsReturn(),
                    allTrans.get(i).getUser(),
                    allTrans.get(i).getBook())
            );

        }
        return trans;
    }

    @Override
    public List<TransactionDto> getAllUserBorrowBookDetails() {
        ArrayList<TransactionDto> trans = new ArrayList<>();
        ArrayList<UserBook> allTrans =  transactionDao.getAllUserBorrowBooks();

        for (int i=0;i<allTrans.size();i++){
            trans.add(new TransactionDto(allTrans.get(i).getId(),
                    allTrans.get(i).getBook(),
                    allTrans.get(i).getUser(),
                    allTrans.get(i).getBorrowDate(),
                    allTrans.get(i).getReturnDate(),
                    allTrans.get(i).getReserveDate(),
                    allTrans.get(i).getIsReturn()

                    )
            );

        }
        return trans;
    }
}
