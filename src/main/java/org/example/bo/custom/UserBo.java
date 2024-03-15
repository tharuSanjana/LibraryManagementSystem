package org.example.bo.custom;

import org.example.SuperBO;
import org.example.dto.ReturnDto;
import org.example.dto.UserDto;

import java.util.ArrayList;

public interface UserBo extends SuperBO {
    public abstract ArrayList<String> getCmbBranchId();

    public abstract String generateUserId();
    public abstract boolean save(UserDto dto);
    public abstract boolean checkUsernamePassword(UserDto userDto);
    public abstract ArrayList<UserDto> getAllUsers();

    public abstract String getLogUserId(String username);

    public abstract UserDto getUserDetails(String id);

    public abstract boolean updateUser(UserDto dto);

    public abstract ArrayList<String> getCmbUserId();

    public abstract UserDto searchUserId(String id);

    public abstract boolean deleteUser(UserDto dto);

    public abstract boolean returnBook(ReturnDto dto);
}
