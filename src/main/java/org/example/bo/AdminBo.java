package org.example.bo;

import org.example.dto.AdminDto;

import java.util.ArrayList;

public interface AdminBo {
    boolean saveAdmin(AdminDto adminDto);
    String getGenerateAdminId();
    boolean chekUsernamePwd(AdminDto adminDto);
    ArrayList<String> getCmbBranchId();
    ArrayList<AdminDto> getAllAdmins();
}
