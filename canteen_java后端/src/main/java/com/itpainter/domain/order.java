package com.itpainter.domain;

import java.util.Date;

public class order {
    private int order_id;
    private String window_name;
    private String create_time;
    private String style;
    private String order_price;



    public String getWindow_name() {
        return window_name;
    }

    @Override
    public String toString() {
        return "order{" +
                "order_id=" + order_id +
                ", window_name='" + window_name + '\'' +
                ", create_time='" + create_time + '\'' +
                ", style='" + style + '\'' +
                ", order_price='" + order_price + '\'' +
                '}';
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public order(int order_id, String window_name, String create_time, String style, String order_price) {
        this.order_id = order_id;
        this.window_name = window_name;
        this.create_time = create_time;
        this.style = style;
        this.order_price = order_price;
    }

    public void setWindow_name(String window_name) {
        this.window_name = window_name;
    }



    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public String getOrder_price() {
        return order_price;
    }

    public void setOrder_price(String order_price) {
        this.order_price = order_price;
    }

    public order() {
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public order(String window_name, String create_time, String style, String order_price) {
        this.window_name = window_name;
        this.create_time = create_time;
        this.style = style;
        this.order_price = order_price;
    }
}
