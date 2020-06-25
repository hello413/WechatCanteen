package com.itpainter.domain;

public class Admini {
    private String admini_id;
    private String admini_name;
    private String admini_password;
    private String admini_telephone;
    private String admini_window;

    public Admini() {
    }

    @Override
    public String toString() {
        return "Admini{" +
                "admini_id='" + admini_id + '\'' +
                ", admini_name='" + admini_name + '\'' +
                ", admini_password='" + admini_password + '\'' +
                ", admini_telephone='" + admini_telephone + '\'' +
                ", admini_window='" + admini_window + '\'' +
                '}';
    }

    public Admini(String admini_id, String admini_name, String admini_password, String admini_telephone, String admini_window) {
        this.admini_id = admini_id;
        this.admini_name = admini_name;
        this.admini_password = admini_password;
        this.admini_telephone = admini_telephone;
        this.admini_window = admini_window;
    }

    public String getAdmini_id() {
        return admini_id;
    }

    public void setAdmini_id(String admini_id) {
        this.admini_id = admini_id;
    }

    public String getAdmini_name() {
        return admini_name;
    }

    public void setAdmini_name(String admini_name) {
        this.admini_name = admini_name;
    }

    public String getAdmini_password() {
        return admini_password;
    }

    public void setAdmini_password(String admini_password) {
        this.admini_password = admini_password;
    }

    public String getAdmini_telephone() {
        return admini_telephone;
    }

    public void setAdmini_telephone(String admini_telephone) {
        this.admini_telephone = admini_telephone;
    }

    public String getAdmini_window() {
        return admini_window;
    }

    public void setAdmini_window(String admini_window) {
        this.admini_window = admini_window;
    }
}
