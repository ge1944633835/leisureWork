package com.gcg.leisureWork.commons.beans;

import java.util.List;

/**
 * Created by 戈传光 on 2016/8/17.
 * 邮箱 1944633835@qq.com
 */
public class City {


    /**
     * status : 0
     * msg : ok
     * result : [{"id":"499","name":"东城区","parentid":"1","parentname":"北京","areacode":"010","zipcode":"100000","depth":"2"},{"id":"500","name":"西城区","parentid":"1","parentname":"北京","areacode":"010","zipcode":"100000","depth":"2"},{"id":"501","name":"海淀区","parentid":"1","parentname":"北京","areacode":"010","zipcode":"100000","depth":"2"},{"id":"502","name":"朝阳区","parentid":"1","parentname":"北京","areacode":"010","zipcode":"100000","depth":"2"},{"id":"503","name":"崇文区","parentid":"1","parentname":"北京","areacode":"010","zipcode":"100000","depth":"2"}]
     */

    private String status;
    private String msg;
    /**
     * id : 499
     * name : 东城区
     * parentid : 1
     * parentname : 北京
     * areacode : 010
     * zipcode : 100000
     * depth : 2
     */

    private List<ResultBean> result;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        private String id;
        private String name;
        private String parentid;
        private String parentname;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getParentid() {
            return parentid;
        }

        public void setParentid(String parentid) {
            this.parentid = parentid;
        }

        public String getParentname() {
            return parentname;
        }

        public void setParentname(String parentname) {
            this.parentname = parentname;
        }
    }
}
