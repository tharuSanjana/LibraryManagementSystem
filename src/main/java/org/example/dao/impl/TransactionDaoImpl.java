package org.example.dao.impl;

import jakarta.persistence.Query;
import org.example.config.FactoryConfiguration;
import org.example.dao.custom.TransactionDao;
import org.example.entity.UserBook;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TransactionDaoImpl implements TransactionDao {
    @Override
    public String generateTransId() {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        String hql = "SELECT ub.id FROM UserBook ub ORDER BY ub.id DESC";
        Query query = session.createQuery(hql);
        query.setMaxResults(1);

        List<String> result = (List<String>) ((org.hibernate.query.Query<?>) query).list();
        if (!result.isEmpty()) {
            String latestUserId = result.get(0);
            return splitUserId(latestUserId);
        } else {
            return splitUserId(null);
        }
    }

    @Override
    public boolean saveUserBook(String transId, String isReturn, LocalDate reserveDate, LocalDate borrowDate, LocalDate returnDate, String userId, String bookId) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        String hql = "INSERT INTO UserBook (id, isReturn, reserveDate, borrowDate, returnDate, user, book) " +
                "SELECT :transId, :isReturn, :reserveDate, :borrowDate, :returnDate, u, b " +
                "FROM User u, Book b " +
                "WHERE u.id = :userId AND b.id = :bookId";

        int rowsAffected = session.createQuery(hql)
                .setParameter("transId", transId)
                .setParameter("isReturn", isReturn)
                .setParameter("reserveDate", reserveDate)
                .setParameter("borrowDate", borrowDate)
                .setParameter("returnDate", returnDate)
                .setParameter("userId", userId)
                .setParameter("bookId", bookId)
                .executeUpdate();

        transaction.commit();
        session.close();

        return rowsAffected > 0;


    }

    @Override
    public ArrayList<UserBook> getAllUserBorrowBooks() {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        String hql = "FROM UserBook ";
        Query query = session.createQuery(hql);


        ArrayList<UserBook> userBooks = (ArrayList<UserBook>) ((org.hibernate.query.Query<?>) query).list();

        transaction.commit();
        session.close();

        return userBooks;
    }

    @Override
    public boolean saveReturnUserBook(String isReturn, LocalDate reserveDate, String bookId) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        String hql = "UPDATE UserBook ub SET ub.isReturn = :isReturn, ub.reserveDate = :reserveDate WHERE ub.book.id = :bookId";
        Query query = session.createQuery(hql);
        query.setParameter("isReturn", isReturn);
        query.setParameter("reserveDate", reserveDate);
        query.setParameter("bookId", bookId);

        int updatedRows = query.executeUpdate();
        transaction.commit();
        session.close();

        return updatedRows > 0;
    }




    private String splitUserId(String currentUserId) {

        if (currentUserId != null) {
            String[] split = currentUserId.split("T0");

            if (split.length > 1) {
                try {
                    int id = Integer.parseInt(split[1]);
                    id++;
                    if (id < 10) {
                        return "T00" + id;
                    } else {
                        return "T0" + id;
                    }
                } catch (NumberFormatException e) {

                    e.printStackTrace();
                }
            }
        }
        return "T001";
    }

    @Override
    public boolean save(UserBook dto) {
        return false;
    }

    @Override
    public boolean delete(String id) {
        return false;
    }

    @Override
    public String getGenerateId() {
        return null;
    }

    @Override
    public List<String> getCmbId() {
        return null;
    }

    @Override
    public ArrayList<UserBook> getAll() {
        return null;
    }
}
