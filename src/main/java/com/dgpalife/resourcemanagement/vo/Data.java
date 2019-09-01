package com.dgpalife.resourcemanagement.vo;

import com.dgpalife.resourcemanagement.model.User;

import java.util.ArrayList;
import java.util.List;

public class Data {

    private List<User> userList = new ArrayList<User>();
    private List<User> datas = new ArrayList<User>();

    private List<Long> ids ;

    //private List<MemberCert> certimgs = new ArrayList<MemberCert>();

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    public List<User> getDatas() {
        return datas;
    }

    public void setDatas(List<User> datas) {
        this.datas = datas;
    }

    public List<Long> getIds() {
        return ids;
    }

    public void setIds(List<Long> ids) {
        this.ids = ids;
    }

//    public List<MemberCert> getCertimgs() {
//        return certimgs;
//    }
//
//    public void setCertimgs(List<MemberCert> certimgs) {
//        this.certimgs = certimgs;
//    }
}
