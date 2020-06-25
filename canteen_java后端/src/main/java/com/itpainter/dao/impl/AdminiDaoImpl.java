package com.itpainter.dao.impl;

import com.itpainter.dao.AdminiDao;
import com.itpainter.domain.Admini;
import com.itpainter.domain.OneString;
import com.itpainter.domain.OrderList;
import com.itpainter.domain.User;
import com.itpainter.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AdminiDaoImpl implements AdminiDao {

    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public User findNameOrTel(User user) {
        User u = null;
        try {
            //1.定义sql
            String sql = "select * from admini where admini_name = ? or admini_telephone = ?";
            //2.执行sql
            u = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), user.getUser_name(), user.getUser_telephone());
        } catch (Exception e) {
        }
        return u;
    }

    @Override
    public Integer regist(User user) {
        String sql = "insert into admini(admini_name, admini_password, admini_telephone, admini_window) values(?,?,?,?)";
        //2.执行sql

        int i = template.update(sql, user.getUser_name(),
                user.getUser_password(),
                user.getUser_telephone()
        );

        return i;
    }

    @Override
    public List<OrderList> findOrder(String admini_window, int statu) {
        List<OrderList> res = null;
        String sql = "select * from orderlist where window_id = ? and style = ?";
        try {
            res = template.query(sql, new BeanPropertyRowMapper<OrderList>(OrderList.class), admini_window, statu + "");
        } catch (Exception e) {
            throw new RuntimeException("查询订单失败");
        }
        return res;
    }

    @Override
    public String findUName(Integer user_id) {
        OneString strs = null;
        String name = null;
        try {
            //1.定义sql
            String sql = "select user_name as str from user where user_id = ?";
            //2.执行sql
            strs = template.queryForObject(sql, new BeanPropertyRowMapper<OneString>(OneString.class), user_id);
            name = strs.getStr();
        } catch (Exception e) {
            throw new RuntimeException("没找到");
        }
        return name;
    }

    @Override
    public Admini login(Admini user) {
        Admini u = null;
        try {
            //1.定义sql
            String sql = "select * from admini where admini_telephone = ? and admini_password = ?";
            //2.执行sql
            u = template.queryForObject(sql, new BeanPropertyRowMapper<Admini>(Admini.class), user.getAdmini_telephone(), user.getAdmini_password());
        } catch (Exception e) {
        }
        return u;
    }
}