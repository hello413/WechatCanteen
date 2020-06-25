package com.itpainter.domain;

public class good {
    private String good_food_id;
    private String good_type;
    private String good_num;
    private String good_order_id;

    public good(String good_food_id, String good_type, String good_num) {
        this.good_food_id = good_food_id;
        this.good_type = good_type;
        this.good_num = good_num;
    }

    public good() {
    }
    public good(String good_food_id, String good_num) {
        this.good_food_id = good_food_id;
        this.good_num = good_num;
    }
    @Override
    public String toString() {
        return "good{" +
                "good_food_id='" + good_food_id + '\'' +
                ", good_type='" + good_type + '\'' +
                ", good_num='" + good_num + '\'' +
                ", good_order_id='" + good_order_id + '\'' +
                '}';
    }

    public String getGood_food_id() {
        return good_food_id;
    }

    public void setGood_food_id(String good_food_id) {
        this.good_food_id = good_food_id;
    }

    public String getGood_type() {
        return good_type;
    }

    public void setGood_type(String good_type) {
        this.good_type = good_type;
    }

    public String getGood_num() {
        return good_num;
    }

    public void setGood_num(String good_num) {
        this.good_num = good_num;
    }

    public String getGood_order_id() {
        return good_order_id;
    }

    public void setGood_order_id(String good_order_id) {
        this.good_order_id = good_order_id;
    }

    public good(String good_food_id, String good_type, String good_num, String good_order_id) {
        this.good_food_id = good_food_id;
        this.good_type = good_type;
        this.good_num = good_num;
        this.good_order_id = good_order_id;
    }
}
