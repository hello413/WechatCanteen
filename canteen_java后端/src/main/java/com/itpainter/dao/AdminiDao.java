package com.itpainter.dao;

import com.itpainter.domain.Admini;
import com.itpainter.domain.OrderList;
import com.itpainter.domain.User;

import java.util.List;

public interface AdminiDao {
    Admini login(Admini user);

    User findNameOrTel(User user);

    Integer regist(User user);

    List<OrderList> findOrder(String admini_window, int statu);

    String findUName(Integer user_id);
}
