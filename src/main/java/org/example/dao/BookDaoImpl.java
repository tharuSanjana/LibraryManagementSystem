package org.example.dao;

import jakarta.persistence.Query;
import org.example.config.FactoryConfiguration;
import org.example.dto.BookDto;
import org.example.dto.UserDto;
import org.example.entity.Book;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BookDaoImpl implements BookDao{
    UserDto userDto = new UserDto();
    @Override
    public String getGenerateBookId() {

        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        String hql = "SELECT b.id FROM Book b ORDER BY b.id DESC";
        Query query = session.createQuery(hql);
        query.setMaxResults(1);

        List<String> result = (List<String>) ((org.hibernate.query.Query<?>) query).list();
        if (!result.isEmpty()) {
            String latestBookId = result.get(0);
            return splitBookId(latestBookId);
        } else {
            return splitBookId(null);
        }
    }

    @Override
    public ArrayList<String> getCmbBranchId() {
        List<String> ids = new ArrayList<>();

        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        try {
            String hql = "SELECT b.id FROM Branch b";
            Query query = session.createQuery(hql);

            List<Object> resultList = (List<Object>) ((org.hibernate.query.Query<?>) query).list();
            for (Object obj : resultList) {
                String id = (String) obj;
                ids.add(id);
            }

            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return (ArrayList<String>) ids;
    }

    @Override
    public boolean save(Book book) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.save(book);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public ArrayList<Book> getAllBooks() {

        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();


            String hql = "FROM Book";
            Query query = session.createQuery(hql);
            List<Book> bookList = (List<Book>) ((org.hibernate.query.Query<?>) query).list();
            transaction.commit();
            session.close();
            return new ArrayList<>(bookList);
        }

    @Override
    public boolean deleteBook(Book book) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        String hql = "DELETE FROM Book WHERE id = :id";
        Query query = session.createQuery(hql);
        query.setParameter("id", book.getId());
        int rowsDeleted = query.executeUpdate();
        transaction.commit();
        session.close();
        return rowsDeleted > 0;
    }



    @Override
    public ArrayList<Book> getAvailableBooks() {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();


        String hql = "SELECT b FROM Book b WHERE b.availability='yes'";
        Query query = session.createQuery(hql);
        List<Book> bookList = (List<Book>) ((org.hibernate.query.Query<?>) query).list();
        transaction.commit();
        session.close();
        return new ArrayList<>(bookList);


    }

    @Override
    public ArrayList<String> getCmbBookId() {
        List<String> ids = new ArrayList<>();

        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();


        try {
            String hql = "SELECT bo.id FROM Book bo WHERE bo.availability = :availability";
            Query query = session.createQuery(hql);
            query.setParameter("availability", "yes");

            List<Object> resultList = (List<Object>) ((org.hibernate.query.Query<?>) query).list();
            for (Object obj : resultList) {
                String id = (String) obj;
                ids.add(id);
            }

            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return (ArrayList<String>) ids;
    }

    @Override
    public BookDto searchBookId(String id) {

        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        String hql = "SELECT b FROM Book b WHERE id = :id";
        Query query = session.createQuery(hql);
        query.setParameter("id", id);

        List<Book> resultList = (List<Book>) ((org.hibernate.query.Query<?>) query).list();

        BookDto bookDto = null;
        if (!resultList.isEmpty()) {
            Book book = resultList.get(0);
            bookDto = new BookDto( book.getTitle(),book.getAuthor(),book.getGenre(),book.getBranches());
        }

        transaction.commit();
        session.close();

        return bookDto;
    }

    @Override
    public boolean saveBorrowBook(String bookId, String bookName, String availability, LocalDate currentDate, LocalDate returnDate) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        String hql = "UPDATE Book b SET b.availability = :availability, b.borrowDate = :currentDate, b.returnDate = :returnDate " +
                "WHERE b.id = :bookId AND b.title = :bookName";

        int updatedEntities = session.createQuery(hql)
                .setParameter("availability", availability)
                .setParameter("currentDate", currentDate)
                .setParameter("returnDate", returnDate)
                .setParameter("bookId", bookId)
                .setParameter("bookName", bookName)
                .executeUpdate();

        transaction.commit();
        session.close();

        return updatedEntities > 0;

    }

  /*  @Override
    public boolean saveBorrowBookUser(String userId, String bookId) {

        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        String hql = "";
    }*/

    @Override
    public boolean updateBook(Book book) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        String hql = "UPDATE Book SET title = :title, author = :author, genre = :genre, branches = :branch WHERE id = :id";
        Query query = session.createQuery(hql);
        query.setParameter("title", book.getTitle());
        query.setParameter("author", book.getAuthor());
        query.setParameter("genre", book.getGenre());
        query.setParameter("branch", book.getBranches());
        query.setParameter("id", book.getId());

        int rowsUpdated = query.executeUpdate();

        transaction.commit();
        session.close();

        return rowsUpdated > 0;
    }

   @Override
    public ArrayList<String> getUserCmbBookId(String userId, String isReturn) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();


       String hql = "SELECT ub.book.id FROM UserBook ub WHERE ub.user.id = :userId AND ub.isReturn = :isReturn";

        Query query = session.createQuery(hql, String.class);
        query.setParameter("userId", userId);
        query.setParameter("isReturn", isReturn);


        List<String> bookIds = (List<String>) ((org.hibernate.query.Query<?>) query).list();


        transaction.commit();
        session.close();


        return new ArrayList<>(bookIds);

    }

    @Override
    public boolean saveReturnBook(String availability, LocalDate borrowDate, LocalDate returnDate, String bookId) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        String hql = "UPDATE Book b SET b.availability = :availability, b.borrowDate = :borrowDate, b.returnDate = :returnDate WHERE b.id = :bookId";
        Query query = session.createQuery(hql);
        query.setParameter("availability", availability);
        query.setParameter("borrowDate", borrowDate);
        query.setParameter("returnDate", returnDate);
        query.setParameter("bookId", bookId);

        int updatedRows = query.executeUpdate();
        transaction.commit();
        session.close();

        return updatedRows > 0;

    }

    @Override
    public boolean deleteBookBranch(String id) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        String hql = "DELETE FROM Book WHERE id = :id";
        Query query = session.createQuery(hql);
        query.setParameter("id", id);
        int rowsDeleted = query.executeUpdate();
        transaction.commit();
        session.close();
        return rowsDeleted > 0;
    }

    @Override
    public String getUsername(String id) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        String hql = "SELECT u.name FROM User u WHERE u.id = :id";
        Query query = session.createQuery(hql);
        query.setParameter("id", id);

        String username = (String) ((org.hibernate.query.Query<?>) query).uniqueResult(); // Assuming the ID is unique
        transaction.commit();
        session.close();

        return username;
    }

    @Override
    public ArrayList<BookDto> searchBookName(String bookName) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

       /* String hql = "FROM Book b WHERE b.title = :bookName";
        Query query = session.createQuery(hql);
        query.setParameter("bookName", bookName);

        ArrayList<BookDto> result = (ArrayList<BookDto>) ((org.hibernate.query.Query<?>) query).list();

        transaction.commit();
        session.close();

        return result;*/

      /*  String hql = "FROM Book b WHERE b.title = :bookName";
        Query query = session.createQuery(hql);
        query.setParameter("bookName", bookName);

        List<Book> books = (List<Book>) ((org.hibernate.query.Query<?>) query).list();
        ArrayList<BookDto> result = new ArrayList<>();

        for (Book book : books) {
            BookDto dto = new BookDto();

            dto.setId(book.getId());
            dto.setTitle(book.getTitle());
            dto.setAuthor(book.getAuthor());
            dto.setGenre(book.getGenre());

            result.add(dto);
        }

        transaction.commit();
        session.close();

        return result;*/

        String hql = "SELECT b.id, b.title, b.author, b.genre FROM Book b WHERE b.title = :bookName";
        Query query = session.createQuery(hql);
        query.setParameter("bookName", bookName);

        List<Object[]> rows = (List<Object[]>) ((org.hibernate.query.Query<?>) query).list();
        ArrayList<BookDto> result = new ArrayList<>();

        for (Object[] row : rows) {
            BookDto dto = new BookDto();
            dto.setId((String) row[0]);
            dto.setTitle((String) row[1]);
            dto.setAuthor((String) row[2]);
            dto.setGenre((String) row[3]);

            result.add(dto);
        }

        transaction.commit();
        session.close();

        return result;
    }


    private String splitBookId(String currentBookId) {

        if (currentBookId != null) {
            String[] split = currentBookId.split("B0");

            if (split.length > 1) {
                try {
                    int id = Integer.parseInt(split[1]);
                    id++;
                    if (id < 10) {
                        return "B00" + id;
                    } else {
                        return "B0" + id;
                    }
                } catch (NumberFormatException e) {

                    e.printStackTrace();
                }
            }
        }
        return "B001";
    }


}
