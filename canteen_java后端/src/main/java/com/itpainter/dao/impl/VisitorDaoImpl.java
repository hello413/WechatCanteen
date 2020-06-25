package com.itpainter.dao.impl;

import com.itpainter.dao.VisitorDao;
import com.itpainter.domain.*;
import com.itpainter.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

public class VisitorDaoImpl implements VisitorDao {

    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    /**
     * 判断是否存在用户
     *
     * @param user
     * @return
     */
    @Override
    public User findNameOrTel(User user) {
        User u = null;
        try {
            //1.定义sql
            String sql = "select * from user where user_name = ? or user_telephone = ?";
            //2.执行sql
            u = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), user.getUser_name(), user.getUser_telephone());
        } catch (Exception e) {
        }
        return u;
    }

    @Override
    public Integer regist(User user) {
        String sql = "insert into user(user_name,user_password,user_telephone) values(?,?,?)";
        //2.执行sql

        int i = template.update(sql, user.getUser_name(),
                user.getUser_password(),
                user.getUser_telephone()
        );

        return i;
    }

    @Override
    public User login(User user) {
        User u = null;
        try {
            //1.定义sql
            String sql = "select * from user where user_password = ? and user_telephone = ?";
            //2.执行sql
            u = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), user.getUser_password(), user.getUser_telephone());
        } catch (Exception e) {
            System.out.println("登录失败");
        }
        System.out.println(u);
        return u;
    }

    @Override
    public List<food> menu(String window_id) {
        List<food> res = new ArrayList<>();
        String sql = "select menu.food_id,foods.food_name,menu.food_picture,menu.food_price,menu.food_style from menu,foods where menu.window_id = ? and menu.food_id = foods.food_id order by foods.food_type";
        try {
            res = template.query(sql, new BeanPropertyRowMapper<food>(food.class), window_id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }
        return res;
    }

    @Override
    public String findWName(String window_id) {
        OneString strs = null;
        String name = null;
        try {
            //1.定义sql
            String sql = "select window_name as str from windows where window_id = ?";
            //2.执行sql
            strs = template.queryForObject(sql, new BeanPropertyRowMapper<OneString>(OneString.class), window_id);
            name = strs.getStr();
        } catch (Exception e) {
            throw new RuntimeException("没找到");
        }
        return name;
    }

    @Override
    public Integer submit(OrderList orderList, String timeid) {
        String sql0 = "UPDATE time SET nownum = nownum+1 WHERE (time_id = ? and nownum < upperlimit)";
        int j = template.update(sql0, timeid);
        if (j == 0) {
            return -1;
        } else {
            String sql = "insert into orderlist(user_id,window_id,order_price,about,time_id) values(?,?,?,?,?)";
            //2.执行sql

            int i = template.update(sql, orderList.getUser_id(),
                    orderList.getWindow_id(),
                    orderList.getOrder_price(),
                    orderList.getAbout(),
                    timeid
            );

            if (i == 0) {
                return null;
            } else {
                String sql1 = "select max(order_id) as str from orderlist";
                Integer order_id = null;
                OneString one = null;
                try {
                    one = template.queryForObject(sql1, new BeanPropertyRowMapper<OneString>(OneString.class));
                    order_id = Integer.parseInt(one.getStr());
                } catch (Exception e) {
                    throw new RuntimeException("返回当前订单编号失败");
                }
                System.out.println("order_id: " + order_id);
                return order_id;
            }
        }
    }

    @Override
    public boolean addgood(good good) {
        String sql = "insert into goods(good_food_id,good_type,good_num,good_order_id) values(?,?,?,?)";
        //2.执行sql

        int i = template.update(sql, good.getGood_food_id(),
                good.getGood_type(), good.getGood_num(), good.getGood_order_id()
        );

        return i != 0;
    }

    @Override
    public List<OrderList> findOrder(int user_id, int statu) {
        List<OrderList> res = null;
        String sql = "select * from orderlist where user_id = ? and style = ?";
        try {
            res = template.query(sql, new BeanPropertyRowMapper<OrderList>(OrderList.class), user_id + "", statu + "");
        } catch (Exception e) {
            throw new RuntimeException("查询订单失败");
        }
        return res;
    }

    @Override
    public boolean orderfinish(String order_id, String time_id) {
        String sql0 = "UPDATE time SET nownum = nownum-1 WHERE (time_id = ? and nownum >0)";
        int j = template.update(sql0, time_id);
        String sql = "UPDATE orderlist SET style = '1' WHERE (order_id = ?);";
        //2.执行sql
        int i = template.update(sql, order_id);
        return i != 0 && j != 0;
    }

    @Override
    public String findTime(String canteen, String tier, String time) {
        String sql = "select time_id from time where canteen = ? and tier = ? and style = ?";
        Integer res = null;
        try {
            res = template.queryForObject(sql, Integer.class, canteen, tier, time);
        } catch (Exception e) {

        }
        return res + "";
    }

    @Override
    public String findTimeId(String order_id) {
        String sql = "select time_id from orderlist where order_id = ?";
        Integer res = null;
        try {
            res = template.queryForObject(sql, Integer.class, order_id);
        } catch (Exception e) {

        }
        return res + "";
    }

    public void tosay(String word) {
        String sql = "insert into words(word) values(?)";
        //2.执行sql
        int i =template.update(sql, word);
        System.out.println(i!=0);
    }
}
