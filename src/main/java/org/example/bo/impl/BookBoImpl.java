package org.example.bo.impl;

import org.example.DAOFactory;
import org.example.bo.custom.BookBo;
import org.example.config.FactoryConfiguration;
import org.example.dao.custom.BookDao;
import org.example.dao.custom.TransactionDao;
import org.example.dao.custom.UserDao;
import org.example.dao.impl.BookDaoImpl;
import org.example.dao.impl.TransactionDaoImpl;
import org.example.dao.impl.UserDaoImpl;
import org.example.dto.BookDto;
import org.example.dto.BorrowDto;
import org.example.entity.Book;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;

public class BookBoImpl implements BookBo {

    //BookDao bookDao =  new BookDaoImpl();
    BookDao bookDao = (BookDao) DAOFactory.daoFactory().getDAOTypes(DAOFactory.DAOTypes.BOOK);
    UserDao userDao = new UserDaoImpl();

    TransactionDao transactionDao = new TransactionDaoImpl();
    @Override
    public String getGenerateBookId() {
        return bookDao.getGenerateBookId() ;
    }

    @Override
    public ArrayList<String> getCmbBranchId() {
        return bookDao.getCmbBranchId();
    }

    @Override
    public boolean save(BookDto dto) {
        return bookDao.save(new Book(dto.getId(), dto.getTitle(), dto.getAuthor(), dto.getGenre(), dto.getAvailability(),dto.getBranch()));
    }

    @Override
    public ArrayList<BookDto> getAllBooks() {
        ArrayList<BookDto> books = new ArrayList<>();
        ArrayList<Book> allBooks =  bookDao.getAllBooks();

        for (int i=0;i<allBooks.size();i++){
            books.add(new BookDto(allBooks.get(i).getId(),allBooks.get(i).getTitle(),allBooks.get(i).getAuthor(),allBooks.get(i).getGenre(),allBooks.get(i).getAvailability(),allBooks.get(i).getBranches()));
        }
        return books;

    }

    @Override
    public boolean deleteBook(BookDto dto) {
        return  bookDao.deleteBook((new Book(dto.getId(), dto.getTitle(), dto.getAuthor(), dto.getGenre(), dto.getBranch())));
    }



    @Override
    public ArrayList<BookDto> getAvailableBooks() {
        ArrayList<BookDto> books = new ArrayList<>();
        ArrayList<Book> allBooks =  bookDao.getAvailableBooks();

        for (int i=0;i<allBooks.size();i++){
            books.add(new BookDto(allBooks.get(i).getId(),allBooks.get(i).getTitle(),allBooks.get(i).getAuthor(),allBooks.get(i).getGenre(),allBooks.get(i).getBranches()));
        }
        return books;
    }

    @Override
    public ArrayList<String> getCmbBookId() {
        return bookDao.getCmbBookId();
    }

    @Override
    public BookDto searchBookId(String id) {
        return bookDao.searchBookId(id);
    }

    @Override
    public boolean borrowBook(BorrowDto dto) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

         boolean isSavedBorrowBook = bookDao.saveBorrowBook(dto.getBookId(),dto.getBookName(),dto.getAvailability(),dto.getBorrowDate(),dto.getReturnDate());

         if (isSavedBorrowBook){
            /* boolean isSavedUser = userDao.saveBorrowBookUser(dto.getUserId(),dto.getBookId());*/
             boolean isSavedUserBook = transactionDao.saveUserBook(dto.getTransId(),dto.getIsReturn(),dto.getReserveDate(),dto
                     .getBorrowDate(),dto.getReturnDate(),dto.getUserId(),dto.getBookId());
             if (isSavedUserBook) {
                 transaction.commit();
                 session.close();

             }
         }

        return true;
    }

    @Override
    public boolean updateBook(BookDto dto) {
        return bookDao.updateBook(new Book(dto.getId(), dto.getTitle(), dto.getAuthor(), dto.getGenre(), dto.getBranch()));
    }

    @Override
    public ArrayList<String> getUserCmbBookId(String userId, String isReturn) {
        return bookDao.getUserCmbBookId(userId,isReturn);
    }

    @Override
    public String getUsername(String id) {
        return bookDao.getUsername(id);

    }

    @Override
    public ArrayList<BookDto> searchBookName(String bookName) {
        return bookDao.searchBookName(bookName);
    }


}
