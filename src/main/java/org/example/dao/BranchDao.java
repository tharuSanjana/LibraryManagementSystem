package org.example.dao;

import org.example.dto.BranchDto;
import org.example.entity.Branch;

import java.util.ArrayList;

public interface BranchDao {
    boolean saveBranch(Branch branch);
    String getGenerateAdminId();
    ArrayList<Branch> getAllBranches();

    BranchDto setLocation(String id);

    boolean updateBranch(Branch branch);

    boolean deleteBranch(Branch branch);

    boolean deleteBranchBranch(String id);
}
