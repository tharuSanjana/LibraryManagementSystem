package org.example.dao;

import jakarta.persistence.Query;
import org.example.config.FactoryConfiguration;
import org.example.dto.BranchDto;
import org.example.entity.Branch;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class BranchDaoImpl implements BranchDao {

    @Override
    public boolean saveBranch(Branch branch) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Branch b = new Branch();
        b.setId(branch.getId());
        b.setLocation(branch.getLocation());

        session.save(b);
        transaction.commit();
        session.close();
        return true;

    }

    @Override
    public String getGenerateAdminId() {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        String hql = "SELECT b.id FROM Branch b ORDER BY b.id DESC";
        Query query = session.createQuery(hql);
        query.setMaxResults(1);

        List<String> result = (List<String>) ((org.hibernate.query.Query<?>) query).list();
        if (!result.isEmpty()) {
            String latestAdminId = result.get(0);
            return splitBranchId(latestAdminId);
        } else {
            return splitBranchId(null);
        }
    }

    @Override
    public ArrayList<Branch> getAllBranches() {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();


        String hql = "FROM Branch";
        Query query = session.createQuery(hql);
        List<Branch> branchList = (List<Branch>) ((org.hibernate.query.Query<?>) query).list();
        transaction.commit();
        session.close();
        return new ArrayList<>(branchList);
    }

    @Override
    public BranchDto setLocation(String id) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        String hql = "SELECT br FROM Branch br WHERE id = :id";
        Query query = session.createQuery(hql);
        query.setParameter("id", id);

        List<Branch> resultList = (List<Branch>) ((org.hibernate.query.Query<?>) query).list();

        BranchDto branchDto = null;
        if (!resultList.isEmpty()) {
            Branch branch = resultList.get(0);
            branchDto = new BranchDto( branch.getLocation());
        }

        transaction.commit();
        session.close();

        return branchDto;
    }

    @Override
    public boolean updateBranch(Branch branch) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        String hql = "UPDATE Branch SET id = :id, location = :location WHERE id = :id";
        Query query = session.createQuery(hql);
        query.setParameter("location",branch.getLocation());
        query.setParameter("id", branch.getId());

        int rowsUpdated = query.executeUpdate();

        transaction.commit();
        session.close();

        return rowsUpdated > 0;
    }

    @Override
    public boolean deleteBranch(Branch branch) {
       /* Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            // Delete associated books first
            Query bookDeleteQuery = session.createQuery("DELETE FROM Book WHERE branchId = :branchId");
            bookDeleteQuery.setParameter("branchId", branch.getId());
            int booksDeleted = bookDeleteQuery.executeUpdate();
            // Commit the transaction for book deletion
            transaction.commit();

            // Now delete the branch
            if (booksDeleted > 0) {
                // If books were deleted, begin a new transaction for branch deletion
                transaction = session.beginTransaction();
            }

            String hql = "DELETE FROM Branch WHERE id = :id";
            Query branchDeleteQuery = session.createQuery(hql);
            branchDeleteQuery.setParameter("id", branch.getId());
            int branchesDeleted = branchDeleteQuery.executeUpdate();

            transaction.commit();
            return branchesDeleted > 0;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace(); // Handle or log the exception properly
            return false;
        } finally {
            session.close();
        }*/
        return  false;
    }

    @Override
    public boolean deleteBranchBranch(String id) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        String hql = "DELETE FROM Branch WHERE id = :id";
        Query query = session.createQuery(hql);
        query.setParameter("id", id);

        int rowsDeleted = query.executeUpdate();
        transaction.commit();
        session.close();

        return rowsDeleted > 0;
    }

    private String splitBranchId(String currentBranchId) {

        if (currentBranchId != null) {
            String[] split = currentBranchId.split("BR0");

            if (split.length > 1) {
                try {
                    int id = Integer.parseInt(split[1]);
                    id++;
                    if (id < 10) {
                        return "BR00" + id;
                    } else {
                        return "BR0" + id;
                    }
                } catch (NumberFormatException e) {

                    e.printStackTrace();
                }
            }
        }
        return "BR001";
    }
}
