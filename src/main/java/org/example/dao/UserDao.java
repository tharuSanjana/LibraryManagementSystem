package org.example.dao;

import org.example.dto.UserDto;
import org.example.entity.User;

import java.util.ArrayList;

public interface UserDao {
    ArrayList<String> getCmbBranchId();
    boolean save(User user);
    String getGenerateUserId();
    boolean checkUsernamePassword(User user);
    ArrayList<User> getAllUsers();


    //boolean saveBorrowBookUser(String userId, String bookId);

    String getLogUserId(String username);

    UserDto getUserDetails(String id);

    boolean updateUser(User user);

    ArrayList<String> getCmbUserId();

    UserDto searchUserId(String id);

    boolean deleteUser(User user);

    boolean deleteUserBranch(String id);
}
