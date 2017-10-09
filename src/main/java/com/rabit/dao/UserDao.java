package com.rabit.dao;

import com.rabit.modle.UserModel;

import java.util.List;

public interface UserDao {
    /**
     * 查询所有数据
     * @return
     */
    public List<UserModel> findAll();

    /**
     * 插入数据
     * @param user
     */
    public void insertUser(UserModel user);

    /**
     * 删除数据
     * @param userName
     */
    public void removeUser(String userName);

    /**
     * 修改数据
     * @param user
     */
    public void updateUser(UserModel user);

    /***
     * 按照条件查询
     * @param userName
     * @return
     */
    public List<UserModel> findForRequery(String userName);
}
