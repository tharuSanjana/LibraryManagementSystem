package org.example.dao.impl;

import jakarta.persistence.Query;
import org.example.config.FactoryConfiguration;
import org.example.dao.custom.AdminDao;
import org.example.entity.Admin;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class AdminDaoImpl implements AdminDao {


    @Override
    public boolean saveAdmin(Admin admin) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

       /* Admin ad = new Admin();
        ad.setId(admin.getId());
        ad.setName(admin.getName());
        ad.setEmail(admin.getEmail());
        ad.setBranch(admin.getBranch());
        ad.setUsername(admin.getUsername());
        ad.setPassword(admin.getPassword());*/

        session.save(admin);
        transaction.commit();
        session.close();

        return true;
    }

    @Override
    public String getGenerateAdminId() {

        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        String hql = "SELECT ad.id FROM Admin ad ORDER BY ad.id DESC";
        Query query = session.createQuery(hql);
        query.setMaxResults(1);

        List<String> result = (List<String>) ((org.hibernate.query.Query<?>) query).list();
        if (!result.isEmpty()) {
            String latestAdminId = result.get(0);
            return splitCustomerId(latestAdminId);
        } else {
            return splitCustomerId(null);
        }

    }

    @Override
    public boolean chekUsernamePwd(Admin admin) {

        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();


            Query query = session.createQuery("FROM Admin WHERE username = :username", Admin.class);
            query.setParameter("username", admin.getUsername());
            List<Admin> results = (List<Admin>) ((org.hibernate.query.Query<?>) query).list();

            transaction.commit();


            if (results.isEmpty()) {
                return false;
            }


            Admin retrievedAdmin = results.get(0);


            return retrievedAdmin.getPassword().equals(admin.getPassword());
        } catch (Exception e) {

            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }


    }

    @Override
    public ArrayList<String> getCmbBranchId() {

        List<String> ids = new ArrayList<>();

        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        try {
            String hql = "SELECT b.id FROM Branch b";
            Query query = session.createQuery(hql);

            List<Object> resultList = (List<Object>) ((org.hibernate.query.Query<?>) query).list();
            for (Object obj : resultList) {
                String id = (String) obj;
                ids.add(id);
            }

            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }

        return (ArrayList<String>) ids;
    }

    @Override
    public ArrayList<Admin> getAllAdmins() {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();


        String hql = "FROM Admin";
        Query query = session.createQuery(hql);
        List<Admin> adminList = (List<Admin>) ((org.hibernate.query.Query<?>) query).list();
        transaction.commit();
        session.close();
        return new ArrayList<>(adminList);
    }

    @Override
    public boolean deleteAdmin(String id) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        String hql = "DELETE FROM Admin a WHERE a.id = :adminId";
        Query query = session.createQuery(hql);
        query.setParameter("adminId", id);

        int deletedRows = query.executeUpdate();
        transaction.commit();
        session.close();

        return deletedRows > 0;
    }

    private String splitCustomerId(String currentCustomerId) {

        if (currentCustomerId != null) {
            String[] split = currentCustomerId.split("A0");

            if (split.length > 1) {
                try {
                    int id = Integer.parseInt(split[1]);
                    id++;
                    if (id < 10) {
                        return "A00" + id;
                    } else {
                        return "A0" + id;
                    }
                } catch (NumberFormatException e) {

                    e.printStackTrace();
                }
            }
        }
        return "A001";
    }

    @Override
    public boolean save(Admin dto) {
        return false;
    }

    @Override
    public boolean delete(String id) {
        return false;
    }

    @Override
    public String getGenerateId() {
        return null;
    }

    @Override
    public List<String> getCmbId() {
        return null;
    }

    @Override
    public ArrayList<Admin> getAll() {
        return null;
    }
}
