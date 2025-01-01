/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public interface DaoInterface<T> {
    public int insert(T t);
    public int update(T t);
    public int delete(T t);
    public ArrayList<T> selectAll();
    public T slectById(T t);
    public ArrayList<T> selectBycondition(String condition);
    public ArrayList<String> getAllID();
}
