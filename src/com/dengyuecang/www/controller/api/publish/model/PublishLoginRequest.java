package com.dengyuecang.www.controller.api.publish.model;

/**
 * Created by acang on 16/7/25.
 */
public class PublishLoginRequest {

    private String openId;

    private String nickname;

    private String sex;

    private String province;

    private String city;

    private String conuntry;

    private String headimgurl;

    private String unionid;

    private Object[] privilege;

    public PublishLoginRequest() {
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getConuntry() {
        return conuntry;
    }

    public void setConuntry(String conuntry) {
        this.conuntry = conuntry;
    }

    public String getHeadimgurl() {
        return headimgurl;
    }

    public void setHeadimgurl(String headimgurl) {
        this.headimgurl = headimgurl;
    }

    public String getUnionid() {
        return unionid;
    }

    public void setUnionid(String unionid) {
        this.unionid = unionid;
    }

    public Object[] getPrivilege() {
        return privilege;
    }

    public void setPrivilege(Object[] privilege) {
        this.privilege = privilege;
    }
}
