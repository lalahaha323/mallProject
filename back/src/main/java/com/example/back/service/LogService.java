package com.example.back.service;

import com.example.back.pojo.Result;

import java.util.Map;

/**
 * @author lala
 */
public interface LogService {

//    public List<User> getByMap(Map<String, Object> map);
//
//    public User getById(Integer id);
//
//    public User create(User user)throws DataAccessException;
//
//    public User update(User user);
//
//    public int delete(Integer id);
//
//    public User getByUserName(String userName);
//
//    public List<User_Role> getRoles(Integer id);

    public Result findEmail(String user_email);
    public Result userRegister(Map<Object, Object> map);
}


