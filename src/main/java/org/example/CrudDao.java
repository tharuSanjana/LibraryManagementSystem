package org.example;

import java.util.ArrayList;
import java.util.List;

public interface CrudDao<T> extends SuperDao {
    boolean save(T dto);
    boolean delete(String id);
    String getGenerateId();
    List<String> getCmbId();
    ArrayList<T> getAll();
}
