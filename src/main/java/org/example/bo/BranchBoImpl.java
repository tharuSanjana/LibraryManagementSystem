package org.example.bo;

import org.example.config.FactoryConfiguration;
import org.example.dao.*;
import org.example.dto.BranchDto;
import org.example.entity.Branch;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;

public class BranchBoImpl implements BranchBo{
    BranchDao branchDao = (BranchDao) new BranchDaoImpl();
    AdminDao adminDao = new AdminDaoImpl();
    UserDao userDao = new UserDaoImpl();
    BookDao bookDao = new BookDaoImpl();
    @Override
    public boolean saveBranch(BranchDto dto) {

        return branchDao.saveBranch(new Branch(dto.getId(),dto.getLocation()));
    }

    @Override
    public String getGenerateBranchId() {
        return branchDao.getGenerateAdminId();
    }

    @Override
    public ArrayList<BranchDto> getAllBranches() {
        ArrayList<BranchDto> branch = new ArrayList<>();
        ArrayList<Branch> allBranches =  branchDao.getAllBranches();

        for (int i=0;i<allBranches.size();i++){
            branch.add(new BranchDto(allBranches.get(i).getId(),allBranches.get(i).getLocation()));
        }
        return branch;
    }

    @Override
    public BranchDto setLocation(String id) {
        return branchDao.setLocation(id);
    }

    @Override
    public boolean updateBranch(BranchDto dto) {
        return branchDao.updateBranch(new Branch(dto.getId(),dto.getLocation()));
    }

    @Override
    public boolean deleteBranch(BranchDto dto) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

       boolean adminDeleted = adminDao.deleteAdmin(dto.getId());
       if (adminDeleted){
           boolean userDeleted = userDao.deleteUserBranch(dto.getId());
           if (userDeleted){
               boolean bookDeleted = bookDao.deleteBookBranch(dto.getId());
               if (bookDeleted){
                   boolean branchDeleted = branchDao.deleteBranchBranch(dto.getId());
                   if (branchDeleted){
                       transaction.commit();
                       session.close();
                   }
               }
           }
       }
       return true;
    }
}
