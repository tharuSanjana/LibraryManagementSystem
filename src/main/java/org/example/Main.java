package org.example;

import org.example.config.FactoryConfiguration;
import org.example.dto.GetUserIdDto;
import org.example.entity.Branch;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class Main {
    public static void main(String[] args) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

      /*  Branch branch = new Branch();
        branch.setId("BR001");
        branch.setLocation("Mathugama");
        session.save(branch);*/
        //System.out.println(branch);

        transaction.commit();
        session.close();
    }
}