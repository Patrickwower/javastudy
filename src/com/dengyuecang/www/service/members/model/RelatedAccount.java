package com.dengyuecang.www.service.members.model;

/**
 * Created by acang on 16/8/31.
 */
public class RelatedAccount {

    //关联平台
    private String platform;

    //关联账户在本地的id
    private String accountId;

    //关联平台昵称
    private String accountNickname;

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getAccountNickname() {
        return accountNickname;
    }

    public void setAccountNickname(String accountNickname) {
        this.accountNickname = accountNickname;
    }
}
