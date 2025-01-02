// UserDAO.java in com.foodapp.dao
package com.foodapp.dao;

import java.util.List;
import com.foodapp.model.User;

public interface UserDAO {
    int insert(User user);
    List<User> fetchAll();
    User fetchOne(String emailId);
    User fetchOne(int userId);
    int update(int userId, String address);
    int delete(int userId);
    boolean isAdmin(int userId);
    User validateUser(String username, String password);
}
