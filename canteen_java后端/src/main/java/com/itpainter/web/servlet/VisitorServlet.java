package com.itpainter.web.servlet;

import com.alibaba.fastjson.JSONArray;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.itpainter.dao.CanteenDao;
import com.itpainter.dao.VisitorDao;
import com.itpainter.dao.impl.VisitorDaoImpl;
import com.itpainter.domain.*;
import com.itpainter.service.VisitorService;
import com.itpainter.service.impl.VisitorServiceImpl;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@WebServlet("/visitor/*")
public class VisitorServlet extends BaseServlet {

    //声明Service业务对象
    private VisitorService service = new VisitorServiceImpl();

    /**
     * 注册
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void regist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, String[]> map = request.getParameterMap();
        User user = new User();
        try {
            BeanUtils.populate(user, map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        //UserService userService = new UserServiceImpl();
        Integer flag = service.regist(user);
        Info info = new Info();
        System.out.println(flag);
        //4.响应结果
        if (flag == null) {
            //注册失败
            info.setFlag(false);
            info.setErrorMsg("注册失败!已经有人用这个用户名或手机号了");
        } else if (flag == 0) {
            //注册失败
            info.setFlag(false);
            info.setErrorMsg("服务器拥挤，稍后再来");
        } else {
            //注册成功
            info.setFlag(true);
            info.setErrorMsg("注册成功");
        }
        writeValue(info, response);
    }

    /**
     * 登录功能
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.获取用户名和密码数据
        String user_telephone = request.getParameter("user_telephone");
        String user_password = request.getParameter("user_password");
        if (user_password == null || user_telephone == null) {
            Info info = new Info();
            info.setErrorMsg("请输入完整的信息");
            writeValue(info, response);
        }
        //2.封装User对象
        User user = new User();
        user.setUser_telephone(user_telephone);
        user.setUser_password(user_password);
        //3.调用Service查询
        User u = service.login(user);

        Info info = new Info();
        //System.out.println(u);

        //4.判断用户对象是否为null
        if (u == null) {
            //用户名密码或错误
            info.setFlag(false);
            info.setErrorMsg("用户名或密码错误");
        }
        //5.判断登录成功
        if (u != null) {
            request.getSession().setAttribute("visitoruser", u);//登录成功标记
            //登录成功
            info.setFlag(true);
            info.setErrorMsg("登录成功");
            //response.sendRedirect("http://localhost:8083/canteen/pages/order/order.wxml");
        }
        writeValue(info, response);
    }

    /**
     * 窗口目录
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void windows(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取食堂位置
        String canteen = request.getParameter("canteen");
        //获取楼层信息
        String tier = request.getParameter("tier");
        //获取时间段
        String time = request.getParameter("time");

        if (canteen == null) {
            canteen = "新食堂";
        }
        if (tier == null) {
            tier = "一楼";
        }
        if (time == null) {
            time = "1";
        }
        //查询
        String time_id = service.findTime(canteen, tier, time);

        List<Windows> list = new CanteenDao().findAll(canteen, tier);
        Info info = new Info();
        info.setObjects(list.toArray());
        info.setFlag(true);
        Integer balance = new CanteenDao().findEmpty(canteen, tier, time);
        info.setData(balance + "");
        info.setErrorMsg(time_id);
        System.out.println(info.toString());
        writeValue(info, response);
    }

    /**
     * 菜品目录
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void menu(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String window_id = request.getParameter("window_id");
        List<food> list = service.menu(window_id);
        Info info = new Info();
        info.setObjects(list.toArray());
        info.setFlag(true);
        String window_name = service.findWName(window_id);
        info.setData(window_name);
        System.out.println(info.toString());
        writeValue(info, response);
    }

    /**
     * 提交订单
     *
     * @param request
     * @param response
     * @throws IOException
     */
    public void submit(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(false);
        if (session != null) {
            User user = (User) session.getAttribute("visitoruser");
            String timeid = request.getParameter("time_id");
            System.out.println("time_id:" + timeid);
            //订单信息
            OrderList orderList = new OrderList();
            orderList.setUser_id(user.getUser_id()); //用户id
//            orderList.setUser_id(1);
            //参数：窗口id：window_id,价格price，备注about，菜品数组??
            String window_id = request.getParameter("window_id");
            orderList.setWindow_id(Integer.parseInt(window_id));
            orderList.setOrder_price(Double.valueOf(request.getParameter("price")));
            orderList.setAbout(request.getParameter("about"));
            //调用service
            Integer o_id = service.submit(orderList, timeid);
            Info info = new Info();
            if (o_id == -1) {
                info.setFlag(false);
                info.setData("该楼层订餐人数过多，去别的楼层吧");
                writeValue(info, response);
                return;
            } else if (o_id == null) {
                info.setFlag(false);
                info.setData("服务器繁忙，待会再试试");
                writeValue(info, response);
                return;
            }
            //菜品数组
            String object = request.getParameter("object");
            List<good> list = new ArrayList<good>(JSONArray.parseArray(object, good.class));

            info.setFlag(true);
            for (good i : list) {
                i.setGood_order_id(o_id + "");
                if (!service.addgood(i)) {
                    info.setFlag(false);
                    throw new RuntimeException("未添上");
                }
            }
            writeValue(info, response);
        } else {
            throw new RuntimeException("未登录");
        }
    }

    //查看未完成订单
    public void nofinishOrder(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(false);
        if (session != null) {
            User visitoruser = (User) session.getAttribute("visitoruser");
            int user_id = visitoruser.getUser_id();
            List<OrderList> orderLists = service.findOrder(user_id, 0);
            List<order> orders = new ArrayList<>();
            Info info = new Info();
            info.setFlag(true);
            for (OrderList i : orderLists) {
                order order = getOrder(i);
                System.out.println(order);
                orders.add(order);
            }
            info.setObjects(orders.toArray());
            writeValue(info, response);
        } else {
            throw new RuntimeException("未登录");
        }
    }

    //查看完成订单
    public void finishOrder(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(false);
        if (session != null) {
            User visitoruser = (User) session.getAttribute("visitoruser");
            int user_id = visitoruser.getUser_id();
            List<OrderList> orderLists = service.findOrder(user_id, 1);
            List<order> orders = new ArrayList<>();
            Info info = new Info();
            info.setFlag(true);
            for (OrderList i : orderLists) {
                order order = getOrder(i);
                System.out.println(order);
                orders.add(order);
            }
            info.setObjects(orders.toArray());
            writeValue(info, response);
        } else {
            throw new RuntimeException("未登录");
        }
    }

    private order getOrder(OrderList i) {
        order order = new order();
        order.setOrder_id(i.getOrder_id());
        order.setWindow_name(service.findWName(i.getWindow_id() + ""));
        Timestamp time = i.getCreate_time();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        order.setCreate_time(sdf.format(time));
        order.setStyle(i.getStyle().equals("0") ? "待收货" : "已收货");
        order.setOrder_price(i.getOrder_price() + "");
        return order;
    }

    /**
     * 点击订单完成
     */
    public void orderfinish(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String order_id = request.getParameter("order_id");
        String time = service.findTimeId(order_id);
        boolean boo = service.orderfinish(order_id, time);
        Info info = new Info();
        info.setFlag(boo);
        writeValue(info, response);
    }

    /**
     * 订单查看
     *
     * @param request
     * @param response
     * @throws IOException
     */
    public void orderfood(HttpServletRequest request, HttpServletResponse response) throws IOException {
        CanteenDao service1 = new CanteenDao();
        String order_id = request.getParameter("order_id");
        List<good> list = service1.foods(order_id);
        List<food> list1 = new ArrayList<>();
        String window_id = service1.findWId(order_id);
        for (good i : list) {
            food food = new food();
            food.setFood_name(service1.findFName(i.getGood_food_id()));
            food.setFood_num(i.getGood_num());
            food.setFood_picture(service1.findFPicture(window_id, i.getGood_food_id()));
            food.setFood_price(service1.findFPrice(window_id, i.getGood_food_id()));
            System.out.println(food);
            list1.add(food);
        }
        Info info = new Info();
        info.setObjects(list1.toArray());
        info.setFlag(true);
        info.setData(service.findWName(window_id));
        writeValue(info, response);
    }

    /**
     * 提交反恢
     */
    public void tosay(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String word = request.getParameter("word");
        System.out.println(word);
        new VisitorDaoImpl().tosay(word);
    }
}