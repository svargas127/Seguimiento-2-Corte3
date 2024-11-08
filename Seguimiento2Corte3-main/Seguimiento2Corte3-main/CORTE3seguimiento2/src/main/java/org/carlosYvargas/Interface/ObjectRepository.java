package org.carlosYvargas.Interface;

import java.util.List;

public interface ObjectRepository <T>{

    void safeObject(T object);
    void deleteObject(int id);
    void updateObject(T object);

    T findObject(int id);

    List<T> findAll();


}
