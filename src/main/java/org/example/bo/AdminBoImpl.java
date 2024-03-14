package org.example.bo;

import org.example.dao.AdminDao;
import org.example.dao.AdminDaoImpl;
import org.example.dto.AdminDto;
import org.example.entity.Admin;

import java.util.ArrayList;

public class AdminBoImpl implements AdminBo{

AdminDao adminDao =  new AdminDaoImpl();
    @Override
    public boolean saveAdmin(AdminDto adminDto) {
        return adminDao.saveAdmin(new Admin(adminDto.getId(),adminDto.getName(),adminDto.getEmail(),adminDto.getBranch(),adminDto.getUsername(),adminDto.getPassword()));
    }

    @Override
    public String getGenerateAdminId() {
        return adminDao.getGenerateAdminId();
    }

    @Override
    public boolean chekUsernamePwd(AdminDto adminDto) {
        return adminDao.chekUsernamePwd(new Admin(adminDto.getUsername(),adminDto.getPassword()));
    }

    @Override
    public ArrayList<String> getCmbBranchId() {

        return adminDao.getCmbBranchId();
    }

    @Override
    public ArrayList<AdminDto> getAllAdmins() {
        ArrayList<AdminDto> admins = new ArrayList<>();
        ArrayList<Admin> allAdmins =  adminDao.getAllAdmins();

        for (int i=0;i<allAdmins.size();i++){
            admins.add(new AdminDto(allAdmins.get(i).getId(),allAdmins.get(i).getName(),allAdmins.get(i).getEmail(),allAdmins.get(i).getBranches(),allAdmins.get(i).getUsername(),allAdmins.get(i).getPassword()));
        }
        return admins;
    }
}
