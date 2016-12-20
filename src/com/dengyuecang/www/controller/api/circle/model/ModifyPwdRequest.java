package com.dengyuecang.www.controller.api.circle.model;

/**
 * Created by acang on 2016/12/20.
 */
public class ModifyPwdRequest {

    private String oldPwd;

    private String newPwd;

    private String comfirmPwd;

    public String getOldPwd() {
        return oldPwd;
    }

    public void setOldPwd(String oldPwd) {
        this.oldPwd = oldPwd;
    }

    public String getNewPwd() {
        return newPwd;
    }

    public void setNewPwd(String newPwd) {
        this.newPwd = newPwd;
    }

    public String getComfirmPwd() {
        return comfirmPwd;
    }

    public void setComfirmPwd(String comfirmPwd) {
        this.comfirmPwd = comfirmPwd;
    }
}
