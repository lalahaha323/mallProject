package com.example.back.mapper;

import com.example.back.pojo.Permission;
import com.example.back.pojo.Role_Permission;
import com.example.back.pojo.User;
import com.example.back.pojo.User_Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
@Mapper
public interface LogMapper {
    public int userLogin(@Param("user_name") String user_name,
                         @Param("user_password") String user_password);
    public int findEmail(@Param("user_email") String user_email);
    public int findName(@Param("user_name") String user_name);
    public User getByUserName(@Param("user_name") String user_name);
    public ArrayList<User_Role> getRoles(@Param("user_id") Integer user_id);
    public ArrayList<Role_Permission> getPermissions(@Param("role_id") Integer role_id);
    public Permission getById(@Param("per_id") Integer per_id);
    public int insertRegister(@Param("user_name") String user_name,
                              @Param("user_password") String user_password,
                              @Param("user_email") String user_email);
}

