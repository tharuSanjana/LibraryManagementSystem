package org.example.dao.custom;

import org.example.CrudDao;
import org.example.dto.BranchDto;
import org.example.entity.Branch;

import java.util.ArrayList;

public interface BranchDao extends CrudDao<Branch> {
    boolean saveBranch(Branch branch);
    String getGenerateAdminId();
    ArrayList<Branch> getAllBranches();

    BranchDto setLocation(String id);

    boolean updateBranch(Branch branch);

   // boolean deleteBranch(Branch branch);

    boolean deleteBranchBranch(String id);
}
