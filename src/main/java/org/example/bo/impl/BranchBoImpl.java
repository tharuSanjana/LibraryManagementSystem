package org.example.bo.impl;

import org.example.DAOFactory;
import org.example.bo.custom.BranchBo;
import org.example.config.FactoryConfiguration;
import org.example.dao.custom.AdminDao;
import org.example.dao.custom.BookDao;
import org.example.dao.custom.BranchDao;
import org.example.dao.custom.UserDao;
import org.example.dao.impl.AdminDaoImpl;
import org.example.dao.impl.BookDaoImpl;
import org.example.dao.impl.BranchDaoImpl;
import org.example.dao.impl.UserDaoImpl;
import org.example.dto.BranchDto;
import org.example.entity.Branch;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;

public class BranchBoImpl implements BranchBo {
    //BranchDao branchDao = (BranchDao) new BranchDaoImpl();
    BranchDao branchDao = (BranchDao) DAOFactory.daoFactory().getDAOTypes(DAOFactory.DAOTypes.BRANCH);
    //AdminDao adminDao = new AdminDaoImpl();
    AdminDao adminDao = (AdminDao) DAOFactory.daoFactory().getDAOTypes(DAOFactory.DAOTypes.ADMIN);
   // UserDao userDao = new UserDaoImpl();
    UserDao userDao = (UserDao) DAOFactory.daoFactory().getDAOTypes(DAOFactory.DAOTypes.USER);
    //BookDao bookDao = new BookDaoImpl();
    BookDao bookDao = (BookDao) DAOFactory.daoFactory().getDAOTypes(DAOFactory.DAOTypes.BOOK);
    @Override
    public boolean saveBranch(BranchDto dto) {
        System.out.println(dto.getLocation());
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



  /*  @Override
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
    }*/
}
