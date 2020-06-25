package com.itpainter.service;

import com.itpainter.domain.OrderList;
import com.itpainter.domain.User;
import com.itpainter.domain.food;
import com.itpainter.domain.good;

import java.util.List;

public interface VisitorService {

    Integer regist(User user);

    User login(User user);

    List<food> menu(String window_id);

    String findWName(String window_id);


    Integer submit(OrderList orderList, String timeid);

    boolean addgood(good i);

    List<OrderList> findOrder(int user_id, int i);

    boolean orderfinish(String order_id, String time_id);

    String findTime(String canteen, String tier, String time);

    String findTimeId(String order_id);

}
