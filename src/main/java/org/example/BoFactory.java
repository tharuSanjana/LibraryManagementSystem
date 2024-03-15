package org.example;


import org.example.bo.impl.*;
import org.example.dao.impl.AdminDaoImpl;

public class BoFactory {

   public static BoFactory boFactory;
    private BoFactory(){

    }
    public static BoFactory boFactory(){
        return boFactory == null? boFactory = new BoFactory():boFactory;
    }

    public void getBo(){

    }

    public enum BOTypes{
       ADMIN,BOOK,BRANCH,USER,USERBOOK
    }
    public SuperBO getBoTypes(BOTypes boTypes){
        switch (boTypes){
            case ADMIN:
                return new AdminBoImpl();
            case BOOK:
                return new BookBoImpl();
            case BRANCH:
                return  new BranchBoImpl();
            case USER:
                return  new UserBoImpl();
            case USERBOOK:
                return  new TransactionBoImpl();
            default:
                return null;

        }

    }
}
