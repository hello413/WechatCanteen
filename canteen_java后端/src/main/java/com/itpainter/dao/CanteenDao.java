package com.itpainter.dao;


import com.itpainter.domain.OneString;
import com.itpainter.domain.Windows;
import com.itpainter.domain.good;
import com.itpainter.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

public class CanteenDao {
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
    //查询指定楼层的窗口
    public List<Windows> findAll(String canteen, String tier){

        List<Windows> res = new ArrayList<>();
        String sql = "select window_id,window_name,window_canteen,window_tier,window_num,window_picture from windows where window_canteen = ? and window_tier = ?";
        List<String> params = new ArrayList<>();
        params.add(canteen);params.add(tier);
        try {
            res = template.query(sql,new BeanPropertyRowMapper<Windows>(Windows.class),params.toArray());
        } catch (Exception throwables) {
            throwables.printStackTrace();
        }finally {

        }
        return res;
    }

    //查询指定时间段指定楼层剩余位置
    public Integer findEmpty(String canteen,String tier,String time) {
        String sql = "select upperlimit-nownum from time where canteen = ? and tier = ? and style = ?";
        Integer res =null;
        try{
            res = template.queryForObject(sql,Integer.class,canteen,tier,time);
        }catch (Exception e){

        }
        return res;
    }

    public List<good> foods(String order_id) {
        List<good> res= null;
        String sql = "select * from goods where good_order_id = ?";
        try {
            res = template.query(sql, new BeanPropertyRowMapper<good>(good.class), order_id);
        }catch (Exception e){
            throw new RuntimeException("查询订单失败");
        }
        return res;
    }

    public String findFName(String good_food_id) {
        OneString strs =null;
        String name =null;
        try {
            //1.定义sql
            String sql = "select food_name as str from foods where food_id = ?";
            //2.执行sql
            strs = template.queryForObject(sql,new BeanPropertyRowMapper<OneString>(OneString.class),good_food_id);
            name = strs.getStr();
        }catch (Exception e){
            throw new RuntimeException("没找到");
        }
        return name;
    }

    public String findWId(String order_id) {
        OneString strs =null;
        String name =null;
        try {
            //1.定义sql
            String sql = "select window_id as str from orderlist where order_id = ?";
            //2.执行sql
            strs = template.queryForObject(sql,new BeanPropertyRowMapper<OneString>(OneString.class),order_id);
            name = strs.getStr();
        }catch (Exception e){
            throw new RuntimeException("没找到");
        }
        return name;
    }

    public String findFPrice(String window_id, String good_food_id) {
        OneString strs =null;
        String name =null;
        try {
            //1.定义sql
            String sql = "select food_price as str from menu where window_id = ? and food_id = ?";
            //2.执行sql
            strs = template.queryForObject(sql,new BeanPropertyRowMapper<OneString>(OneString.class),window_id,good_food_id);
            name = strs.getStr();
        }catch (Exception e){
            throw new RuntimeException("没找到");
        }
        return name;
    }

    public String findFPicture(String window_id, String good_food_id) {
        OneString strs =null;
        String name =null;
        try {
            //1.定义sql
            String sql = "select food_picture as str from menu where window_id = ? and food_id = ?";
            //2.执行sql
            strs = template.queryForObject(sql,new BeanPropertyRowMapper<OneString>(OneString.class),window_id,good_food_id);
            name = strs.getStr();
        }catch (Exception e){
            throw new RuntimeException("没找到");
        }
        return name;
    }
}
