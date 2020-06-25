package com.itpainter.dao;

import com.itpainter.domain.OrderList;
import com.itpainter.domain.User;
import com.itpainter.domain.food;
import com.itpainter.domain.good;

import java.util.List;

public interface VisitorDao {
    /**
     * 判断用户名和手机号是否注册
     * @param user
     * @return
     */
    User findNameOrTel(User user);

    Integer regist(User user);

    User login(User user);

    List<food> menu(String window_id);

    String findWName(String window_id);

    Integer  submit(OrderList orderList, String timeid);

    boolean addgood(good good);

    List<OrderList> findOrder(int user_id, int statu);

    boolean orderfinish(String order_id, String time_id);

    String findTime(String canteen, String tier, String time);

    String findTimeId(String order_id);
}
