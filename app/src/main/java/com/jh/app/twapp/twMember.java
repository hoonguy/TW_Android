package com.jh.app.twapp;

import java.io.Serializable;

public class twMember implements Serializable{
    private String mem_code, pspt_name, country, sex, picture, mem_id, mem_pw;

    public void setMem_code(String mem_code) { this.mem_code = mem_code; }
    public void setPspt_name(String pspt_name) {this.pspt_name = pspt_name; }
    public void setCountry(String country) { this.country = country; }
    public void setSex(String sex) { this.sex = sex; }
    public void setPicture(String picture) { this.picture = picture; }
    public void setMem_id(String mem_id) { this.mem_id = mem_id; }
    public void setMem_pw(String mem_pw) { this.mem_pw = mem_pw; }

    public String getMem_code() { return mem_code; }
    public String getPspt_name() { return pspt_name; }
    public String getCountry() { return country; }
    public String getSex() { return sex; }
    public String getPicture() { return picture; }
    public String getMem_id() { return mem_id; }
    public String getMem_pw() { return mem_pw; }
}
