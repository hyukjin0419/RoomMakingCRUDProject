package org.example;

public interface ICRUD {
    public Object add();
    public int update(Object object);
    public int delete(Object object);
    public void selectOne(int id);
}
