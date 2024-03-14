package org.example.bo;

import org.example.dto.BranchDto;

import java.util.ArrayList;

public interface BranchBo {
    boolean saveBranch(BranchDto dto);
    String getGenerateBranchId();
    ArrayList<BranchDto> getAllBranches();

    BranchDto setLocation(String id);

    boolean updateBranch(BranchDto dto);

    boolean deleteBranch(BranchDto dto);
}
