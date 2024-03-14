package org.example.bo;

import org.example.dto.ReturnDto;
import org.example.dto.UserDto;

import java.util.ArrayList;

public interface UserBo {
    ArrayList<String> getCmbBranchId();

    String generateUserId();
    boolean save(UserDto dto);
    boolean checkUsernamePassword(UserDto userDto);
    ArrayList<UserDto> getAllUsers();

    String getLogUserId(String username);

    UserDto getUserDetails(String id);

    boolean updateUser(UserDto dto);

    ArrayList<String> getCmbUserId();

    UserDto searchUserId(String id);

    boolean deleteUser(UserDto dto);

    boolean returnBook(ReturnDto dto);
}
