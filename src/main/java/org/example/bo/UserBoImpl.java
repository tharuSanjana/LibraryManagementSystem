package org.example.bo;

import org.example.config.FactoryConfiguration;
import org.example.dao.*;
import org.example.dto.ReturnDto;
import org.example.dto.UserDto;
import org.example.entity.User;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;

public class UserBoImpl implements UserBo{
    UserDao userDao = new UserDaoImpl();
    BookDao bookDao = new BookDaoImpl();

    TransactionDao transactionDao = new TransactionDaoImpl();
    @Override
    public ArrayList<String> getCmbBranchId() {
        return userDao.getCmbBranchId();

    }

    @Override
    public String generateUserId() {
        return userDao.getGenerateUserId();
    }

    @Override
    public boolean save(UserDto dto) {

        return userDao.save(new User(dto.getId(), dto.getName(), dto.getEmail(), dto.getBranch(),dto.getUsername(), dto.getPassword()));
    }

    @Override
    public boolean checkUsernamePassword(UserDto userDto) {
        return userDao.checkUsernamePassword(new User(userDto.getUsername(),userDto.getPassword()));
    }

    @Override
    public ArrayList<UserDto> getAllUsers() {
        ArrayList<UserDto> users = new ArrayList<>();
        ArrayList<User> allUsers =  userDao.getAllUsers();

        for (int i=0;i<allUsers.size();i++){
            users.add(new UserDto(allUsers.get(i).getId(),allUsers.get(i).getName(),allUsers.get(i).getEmail(),allUsers.get(i).getBranches(),allUsers.get(i).getUsername(),allUsers.get(i).getPassword()));
        }
        return users;

    }

    @Override
    public String getLogUserId(String username) {
        return userDao.getLogUserId(username);
    }

    @Override
    public UserDto getUserDetails(String id) {
        return userDao.getUserDetails(id);
    }

    @Override
    public boolean updateUser(UserDto dto) {
        return userDao.updateUser(new User(dto.getId(),dto.getName(), dto.getEmail(), dto.getUsername(), dto.getPassword()));
    }

    @Override
    public ArrayList<String> getCmbUserId() {
        return userDao.getCmbUserId();

    }

    @Override
    public UserDto searchUserId(String id) {
        return userDao.searchUserId(id);

    }

    @Override
    public boolean deleteUser(UserDto dto) {
        return userDao.deleteUser(new User(dto.getId(), dto.getName(), dto.getEmail(), dto.getBranch()));
    }

    @Override
    public boolean returnBook(ReturnDto dto) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        boolean isBook = bookDao.saveReturnBook(dto.getAvailability(), dto.getBorrowDate(), dto.getReturnDate(), dto.getBookId());

        if (isBook) {
            boolean isUserBook = transactionDao.saveReturnUserBook(dto.getIsReturn(), dto.getReserveDate(), dto.getBookId());

            if (isUserBook) {
                transaction.commit();
                session.close();
            }
        }
        return true;
    }
}
