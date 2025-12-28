package DAO;

public interface DAOinterface<T> {
    public int insert (T t);

    public int update (T t);

    public int delete (T t);

}
