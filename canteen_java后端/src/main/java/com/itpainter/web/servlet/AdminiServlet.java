package com.itpainter.web.servlet;

import com.itpainter.domain.*;
import com.itpainter.service.AdminiService;
import com.itpainter.service.impl.AdminiServiceImpl;
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

@WebServlet("/admini/*")
public class AdminiServlet extends BaseServlet {
    private AdminiService service = new AdminiServiceImpl();

    /**
     * 注册
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
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.获取用户名和密码数据
        String admini_telephone = request.getParameter("admini_telephone");
        String admini_password = request.getParameter("admini_password");
        System.out.println(admini_telephone+"-+-"+admini_password);
        if (admini_telephone==null||admini_password==null){
            Info info = new Info();
            info.setErrorMsg("请输入完整的信息");
            writeValue(info, response);
        }
        //2.封装User对象
        Admini admini = new Admini();
        admini.setAdmini_telephone(admini_telephone);
        admini.setAdmini_password(admini_password);
        //3.调用Service查询
        Admini u  = service.login(admini);

        Info info = new Info();

        //4.判断用户对象是否为null
        if (u == null) {
            //用户名密码或错误
            info.setFlag(false);
            info.setErrorMsg("用户名或密码错误");
        }
        //5.判断登录成功
        if (u != null) {
            request.getSession().setAttribute("adminiuser", u);//登录成功标记
            //登录成功
            info.setFlag(true);
            info.setErrorMsg("登录成功");
        }
        System.out.println(u);
        writeValue(info, response);
    }

    //查看完成订单
    public void finishOrder(HttpServletRequest request, HttpServletResponse response) throws IOException{
        HttpSession session = request.getSession(false);
//        if(session != null){
//            Admini adminiuser = (Admini) session.getAttribute("adminiuser");
//            String admini_window = adminiuser.getAdmini_window();
        String admini_window = "1";
            List<OrderList> orderLists = service.findOrder(admini_window,1);
        List<order> orders =new ArrayList<>();
        Info info = new Info();
        info.setFlag(true);
        for (OrderList i:orderLists){
            order order = getOrder(i);
            System.out.println(order);
            orders.add(order);
        }
        info.setObjects(orders.toArray());
        writeValue(info,response);
//        }else {
//            throw new RuntimeException("未登录");
//        }
    }
    private order getOrder(OrderList i) {
        order order = new order();
        order.setOrder_id(i.getOrder_id());
        order.setWindow_name(service.findUName(i.getUser_id()));
        Timestamp time = i.getCreate_time();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        order.setCreate_time(sdf.format(time));
        order.setStyle(i.getStyle().equals("0")?"待收货":"已收货");
        order.setOrder_price(i.getOrder_price()+"");
        return order;
    }
    //查看未完成订单
    public void nofinishOrder(HttpServletRequest request, HttpServletResponse response) throws IOException{
//        HttpSession session = request.getSession(false);
//        if(session != null){
//            Admini adminiuser = (Admini) session.getAttribute("adminiuser");
//            String admini_window = adminiuser.getAdmini_window();
        String admini_window ="1";
            List<OrderList> orderLists = service.findOrder(admini_window,0);
        List<order> orders =new ArrayList<>();
        Info info = new Info();
        info.setFlag(true);
        for (OrderList i:orderLists){
            order order = getOrder(i);
            System.out.println(order);
            orders.add(order);
        }
        info.setObjects(orders.toArray());
        writeValue(info,response);
//        }else {
//            throw new RuntimeException("未登录");
//        }
    }
}
