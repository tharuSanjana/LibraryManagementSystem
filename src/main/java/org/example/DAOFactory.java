package org.example;


import org.example.dao.impl.*;

public class DAOFactory {
    public static DAOFactory daoFactory;
    private DAOFactory(){

    }
    public static DAOFactory daoFactory(){
        return daoFactory == null? daoFactory = new DAOFactory():daoFactory;
    }

    public void getDAO(){

    }

    public enum DAOTypes{
        ADMIN,BOOK,BRANCH,USER,USERBOOK
    }
    public SuperDao getDAOTypes(DAOTypes daoTypes){
        switch (daoTypes){
            case ADMIN:
                return  new AdminDaoImpl();
            case BOOK:
                return new BookDaoImpl();
            case BRANCH:
                return  new BranchDaoImpl();
            case USER:
                return  new UserDaoImpl();
            case USERBOOK:
                return  new TransactionDaoImpl();
            default:
                return null;

        }

    }
}
