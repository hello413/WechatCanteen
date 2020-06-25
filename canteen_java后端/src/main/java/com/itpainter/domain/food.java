package com.itpainter.domain;

public class food {
    private String food_id;
    private String food_name;
    private String food_picture;
    private String food_price;
    private String food_style;
    private String food_num;

    @Override
    public String toString() {
        return "food{" +
                "food_name='" + food_name + '\'' +
                ", food_picture='" + food_picture + '\'' +
                ", food_price='" + food_price + '\'' +
                ", food_num='" + food_num + '\'' +
                '}';
    }

    public String getFood_num() {
        return food_num;
    }

    public void setFood_num(String food_num) {
        this.food_num = food_num;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public food(String food_id, String food_name, String food_picture, String food_price, String food_style, String food_num, String style) {
        this.food_id = food_id;
        this.food_name = food_name;
        this.food_picture = food_picture;
        this.food_price = food_price;
        this.food_style = food_style;
        this.food_num = food_num;
        this.style = style;
    }

    private String style;

    public String getFood_name() {
        return food_name;
    }

    public void setFood_name(String food_name) {
        this.food_name = food_name;
    }
    public String getFood_id() {
        return food_id;
    }

    public void setFood_id(String food_id) {
        this.food_id = food_id;
    }

    public String getFood_picture() {
        return food_picture;
    }

    public void setFood_picture(String food_picture) {
        this.food_picture = food_picture;
    }

    public String getFood_price() {
        return food_price;
    }

    public void setFood_price(String food_price) {
        this.food_price = food_price;
    }

    public String getFood_style() {
        return food_style;
    }

    public void setFood_style(String food_style) {
        this.food_style = food_style;
    }

    public food() {
    }

    public food(String food_id, String food_name, String food_picture, String food_price, String food_style) {
        this.food_id = food_id;
        this.food_name = food_name;
        this.food_picture = food_picture;
        this.food_price = food_price;
        this.food_style = food_style;
    }
}
