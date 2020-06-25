package com.itpainter.service.impl;

import com.itpainter.dao.AdminiDao;
import com.itpainter.dao.impl.AdminiDaoImpl;
import com.itpainter.domain.Admini;
import com.itpainter.domain.OrderList;
import com.itpainter.domain.User;
import com.itpainter.service.AdminiService;

import java.util.List;

public class AdminiServiceImpl implements AdminiService {
    private AdminiDao dao = new AdminiDaoImpl();

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
    public List<OrderList> findOrder(String admini_window, int statu) {
        return dao.findOrder(admini_window,statu);
    }

    @Override
    public String findUName(Integer user_id) {
        return dao.findUName(user_id);
    }

    @Override
    public Admini login(Admini user) {
        return dao.login(user);
    }
}
