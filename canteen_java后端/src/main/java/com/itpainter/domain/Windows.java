package com.itpainter.domain;

//窗口实体类
public class Windows {
    //窗口id
    private Integer window_id;
    //窗口名
    private String window_name;
    //位置
    private String window_canteen;
    //楼层
    private String window_tier;
    //
    private Integer window_num;
    //图片信息
    private String window_picture;

    public Windows() {
    }

    public Integer getWindow_id() {
        return window_id;
    }

    public void setWindow_id(Integer window_id) {
        this.window_id = window_id;
    }

    public String getWindow_name() {
        return window_name;
    }

    public void setWindow_name(String window_name) {
        this.window_name = window_name;
    }

    public String getWindow_canteen() {
        return window_canteen;
    }

    public void setWindow_canteen(String window_canteen) {
        this.window_canteen = window_canteen;
    }

    public String getWindow_tier() {
        return window_tier;
    }

    public void setWindow_tier(String window_tier) {
        this.window_tier = window_tier;
    }



    public String getWindow_picture() {
        return window_picture;
    }

    public void setWindow_picture(String window_picture) {
        this.window_picture = window_picture;
    }


    @Override
    public String toString() {
        return "Windows{" +
                "window_id=" + window_id +
                ", window_name='" + window_name + '\'' +
                ", window_canteen='" + window_canteen + '\'' +
                ", window_tier=" + window_tier +
                ", window_num=" + window_num +
                ", window_picture='" + window_picture + '\'' +
                '}';
    }

    public Integer getWindow_num() {
        return window_num;
    }

    public void setWindow_num(Integer window_num) {
        this.window_num = window_num;
    }

    public Windows(Integer window_id, String window_name, String window_canteen, String window_tier, Integer window_num, String window_picture) {
        this.window_id = window_id;
        this.window_name = window_name;
        this.window_canteen = window_canteen;
        this.window_tier = window_tier;
        this.window_num = window_num;
        this.window_picture = window_picture;
    }
}
