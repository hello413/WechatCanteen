package com.itpainter.service.impl;

import com.itpainter.dao.VisitorDao;
import com.itpainter.dao.impl.VisitorDaoImpl;
import com.itpainter.domain.OrderList;
import com.itpainter.domain.User;
import com.itpainter.domain.food;
import com.itpainter.domain.good;
import com.itpainter.service.VisitorService;

import java.util.List;

public class VisitorServiceImpl implements VisitorService {

    VisitorDao dao = new VisitorDaoImpl();

    @Override
    public Integer regist(User user) {
        User u = dao.findNameOrTel(user);

        if (u!=null){
            //存在该用户了
            return null;
        }
        return dao.regist(user);
    }

    @Override
    public User login(User user) {
        return dao.login(user);
    }

    @Override
    public List<food> menu(String window_id) {
        return dao.menu(window_id);
    }

    @Override
    public String findWName(String window_id) {
        return dao.findWName(window_id);
    }

    @Override
    public Integer submit(OrderList orderList, String timeid) {
        return dao.submit(orderList,timeid);
    }

    @Override
    public boolean addgood(good good) {
        return dao.addgood(good);
    }

    @Override
    public List<OrderList> findOrder(int user_id, int i) {
        return dao.findOrder(user_id,i);
    }

    @Override
    public boolean orderfinish(String order_id, String time_id) {
        return dao.orderfinish(order_id,time_id);
    }

    @Override
    public String findTime(String canteen, String tier, String time) {
        return dao.findTime(canteen,tier,time);
    }

    @Override
    public String findTimeId(String order_id) {
        return dao.findTimeId(order_id);
    }

}
