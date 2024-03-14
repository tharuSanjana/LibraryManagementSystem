package org.example.dao;

import org.example.entity.Admin;

import java.util.ArrayList;

public interface AdminDao {


    boolean saveAdmin(Admin admin);

    String getGenerateAdminId();
    boolean chekUsernamePwd(Admin admin);
    ArrayList<String> getCmbBranchId();
    ArrayList<Admin> getAllAdmins();

    boolean deleteAdmin(String id);
}
