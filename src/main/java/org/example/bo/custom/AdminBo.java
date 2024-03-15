package org.example.bo.custom;

import org.example.SuperBO;
import org.example.dto.AdminDto;

import java.util.ArrayList;

public interface AdminBo extends SuperBO {
     boolean saveAdmin(AdminDto adminDto);
     String getGenerateAdminId();
     boolean chekUsernamePwd(AdminDto adminDto);
     ArrayList<String> getCmbBranchId();
     ArrayList<AdminDto> getAllAdmins();
}
