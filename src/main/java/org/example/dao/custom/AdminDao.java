package org.example.dao.custom;

import org.example.CrudDao;
import org.example.entity.Admin;

import java.util.ArrayList;

public interface AdminDao extends CrudDao<Admin> {


    boolean saveAdmin(Admin admin);

    String getGenerateAdminId();
    boolean chekUsernamePwd(Admin admin);
    ArrayList<String> getCmbBranchId();
    ArrayList<Admin> getAllAdmins();

    boolean deleteAdmin(String id);
}
