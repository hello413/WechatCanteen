package com.itpainter.service;

import com.itpainter.domain.Admini;
import com.itpainter.domain.OrderList;
import com.itpainter.domain.User;

import java.util.List;

public interface AdminiService {
    Admini login(Admini user);

    Integer regist(User user);

    List<OrderList> findOrder(String admini_window, int i);

    String findUName(Integer user_id);
}
