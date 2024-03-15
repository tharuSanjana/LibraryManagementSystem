package org.example;

import jakarta.persistence.Query;
import org.example.config.FactoryConfiguration;
import org.example.dto.TransactionDto;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class QueryDao {
    public List<TransactionDto> getAllUserBorrowBooks() {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

       /* String hql = "SELECT new org.example.dto.TransactionDto(ub.id, ub.user, ub.book, ub.returnDate, ub.reserveDate, ub.borrowDate)" +
                " FROM UserBook ub" +
                " INNER JOIN User u ON ub.user.id = u.name";*/
        String hql = "SELECT new org.example.dto.TransactionDto(ub.id,u,b, ub.returnDate, ub.reserveDate, ub.borrowDate)" +
                " FROM UserBook ub" +
                " INNER JOIN ub.user u" +
                " INNER JOIN ub.book b";

        Query query = session.createQuery(hql);
        List<TransactionDto> transactionList = (List<TransactionDto>) ((org.hibernate.query.Query<?>) query).list();

        transaction.commit();
        session.close();

        return transactionList;

    }
}
