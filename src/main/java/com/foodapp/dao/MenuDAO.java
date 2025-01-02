// MenuDAO.java in com.foodapp.dao
package com.foodapp.dao;

import java.util.List;
import com.foodapp.model.Menu;

public interface MenuDAO {
    int insert(Menu menu);
    List<Menu> fetchAll();
    Menu fetchOne(int menuId);
    List<Menu> fetchByRestaurantId(int restaurantId);
    int update(int menuId, String description, int price, boolean isAvailable);
    int delete(int menuId);
}
