package org.example.dao.impl;

import jakarta.persistence.Query;
import org.example.config.FactoryConfiguration;
import org.example.dao.custom.UserDao;
import org.example.dto.UserDto;
import org.example.entity.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {
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
    public boolean save(User user) {
      /*  Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        boolean flag = true;
        System.out.println("ttttt");
        if(flag){
            System.out.println("aaaaaa");
            User u = new User();
            u.setId(user.getId());
            u.setName(user.getName());
            u.setEmail(user.getEmail());
            u.setBranches(user.getBranches());
            u.setUsername(user.getUsername());
            u.setPassword(user.getPassword());

            transaction.commit();
            session.close();
            return true;
        }else {
            return false;
        }*/
       /* Session session = null;
        Transaction transaction = null;*/
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        boolean flag = false;

        try {

            session.save(user);
            transaction.commit();
            flag = true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return flag;


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
    public ArrayList<User> getAll() {
        return null;
    }

    @Override
    public String getGenerateUserId() {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        String hql = "SELECT u.id FROM User u ORDER BY u.id DESC";
        Query query = session.createQuery(hql);
        query.setMaxResults(1);

        List<String> result = (List<String>) ((org.hibernate.query.Query<?>) query).list();
        if (!result.isEmpty()) {
            String latestUserId = result.get(0);
            return splitUserId(latestUserId);
        } else {
            return splitUserId(null);
        }
    }

    @Override
    public boolean checkUsernamePassword(User user) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();


            Query query = session.createQuery("FROM User WHERE username = :username", User.class);
            query.setParameter("username", user.getUsername());
            List<User> results = (List<User>) ((org.hibernate.query.Query<?>) query).list();

            transaction.commit();


            if (results.isEmpty()) {
                return false;
            }


            User retrievedUser = results.get(0);


            return retrievedUser.getPassword().equals(user.getPassword());
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
    public ArrayList<User> getAllUsers() {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();


        String hql = "FROM User";
        Query query = session.createQuery(hql);
        List<User> userList = (List<User>) ((org.hibernate.query.Query<?>) query).list();
        transaction.commit();
        session.close();
        return new ArrayList<>(userList);
    }

   /* @Override
    public boolean saveBorrowBookUser(String userId, String bookId) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            User user = session.get(User.class, userId);
            Book book = session.get(Book.class, bookId);

            if (user != null && book != null) {
                user.getBooks().add(book);
                session.update(user);
                transaction.commit();
                return true;
            } else {
                transaction.rollback();
                return false;
            }
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        } finally {
            session.close();
        }
    }*/

    @Override
    public String getLogUserId(String username) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        String hql = "SELECT u.id FROM User u WHERE u.username = :username";

        Query query = session.createQuery(hql);

        query.setParameter("username", username);

        List<?> result = ((org.hibernate.query.Query<?>) query).list();
        String userId = null;
        if (!result.isEmpty()) {
            userId = result.get(0).toString();
        }

        transaction.commit();
        session.close();

        return userId;

    }

    @Override
    public UserDto getUserDetails(String id) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        String hql = "SELECT u FROM User u WHERE id = :id";
        Query query = session.createQuery(hql);
        query.setParameter("id", id);

        List<User> resultList = (List<User>) ((org.hibernate.query.Query<?>) query).list();

        UserDto userDto = null;
        if (!resultList.isEmpty()) {
            User user = resultList.get(0);
            userDto = new UserDto(user.getName(),user.getEmail(),user.getUsername(),user.getPassword(),user.getBranches());
        }

        transaction.commit();
        session.close();

        return userDto;
    }

    @Override
    public boolean updateUser(User user) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        String hql = "UPDATE User u SET u.name = :name, u.email = :email, u.username = :username, u.password = :password WHERE u.id = :id";
        Query query = session.createQuery(hql);
        query.setParameter("name", user.getName());
        query.setParameter("email", user.getEmail());
        query.setParameter("username", user.getUsername());
        query.setParameter("password", user.getPassword());
        query.setParameter("id", user.getId());
        int rowsAffected = query.executeUpdate();

        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public ArrayList<String> getCmbUserId() {
        List<String> ids = new ArrayList<>();

        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        try {
            String hql = "SELECT u.id FROM User u";
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
    public UserDto searchUserId(String id) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        String hql = "SELECT u FROM User u WHERE u.id = :id";
        Query query = session.createQuery(hql);
        query.setParameter("id", id);

        List<User> resultList = query.getResultList();

        UserDto userDto = null;
        if (!resultList.isEmpty()) {
            User user = resultList.get(0);
            userDto = new UserDto( user.getName(),user.getEmail(),user.getBranches());
        }

        transaction.commit();
        session.close();

        return userDto;

    }

    @Override
    public boolean deleteUser(User user) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        String hql = "DELETE FROM User WHERE id = :id";
        Query query = session.createQuery(hql);
        query.setParameter("id", user.getId());
        int rowsDeleted = query.executeUpdate();
        transaction.commit();
        session.close();
        return rowsDeleted > 0;
    }

    @Override
    public boolean deleteUserBranch(String id) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        String hql = "DELETE FROM User WHERE id = :id";
        Query query = session.createQuery(hql);
        query.setParameter("id",id);
        int rowsDeleted = query.executeUpdate();
        transaction.commit();
        session.close();
        return rowsDeleted > 0;
    }


    private String splitUserId(String currentUserId) {

        if (currentUserId != null) {
            String[] split = currentUserId.split("U0");

            if (split.length > 1) {
                try {
                    int id = Integer.parseInt(split[1]);
                    id++;
                    if (id < 10) {
                        return "U00" + id;
                    } else {
                        return "U0" + id;
                    }
                } catch (NumberFormatException e) {

                    e.printStackTrace();
                }
            }
        }
        return "U001";
    }
}
