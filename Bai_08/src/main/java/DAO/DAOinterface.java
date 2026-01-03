package DAO;

import java.util.ArrayList;

public interface DAOinterface<T> {
    public int insert (T t);

    public int update (T t);

    public int delete (T t);

    public ArrayList<T> SelectAll();

    public T SelectByID (T t);

    public abstract ArrayList<T> SelectByCondition(String condition);

}
