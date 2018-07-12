package com.itheima.bos.domain;

import java.sql.Timestamp;


/**
 * Workbill entity. @author MyEclipse Persistence Tools
 */

public class Workbill  implements java.io.Serializable {


    // Fields    

     private String id;
     private String noticebillId;
     private String type;
     private String pickstate;
     private Timestamp buildtime;
     private Integer attachbilltimes;
     private String remark;
     private String staffId;


     public static final String TYPE_1 = "新单";
     public static final String TYPE_2 = "追单";
     public static final String TYPE_3 = "改单";
     public static final String TYPE_4 = "销单";
     
     public static final String PIKCSTATE_NO = "未取件";
     public static final String PICKSTATE_RUNNING = "取件中";
     public static final String PICKSTATE_YES = "已取件";
      
    // Constructors

    /** default constructor */
    public Workbill() {
    }

	/** minimal constructor */
    public Workbill(String id, Timestamp buildtime) {
        this.id = id;
        this.buildtime = buildtime;
    }
    
    /** full constructor */
    public Workbill(String id, String noticebillId, String type, String pickstate, Timestamp buildtime, Integer attachbilltimes, String remark, String staffId) {
        this.id = id;
        this.noticebillId = noticebillId;
        this.type = type;
        this.pickstate = pickstate;
        this.buildtime = buildtime;
        this.attachbilltimes = attachbilltimes;
        this.remark = remark;
        this.staffId = staffId;
    }

   
    // Property accessors

    public String getId() {
        return this.id;
    }
    
    public void setId(String id) {
        this.id = id;
    }

    public String getNoticebillId() {
        return this.noticebillId;
    }
    
    public void setNoticebillId(String noticebillId) {
        this.noticebillId = noticebillId;
    }

    public String getType() {
        return this.type;
    }
    
    public void setType(String type) {
        this.type = type;
    }

    public String getPickstate() {
        return this.pickstate;
    }
    
    public void setPickstate(String pickstate) {
        this.pickstate = pickstate;
    }

    public Timestamp getBuildtime() {
        return this.buildtime;
    }
    
    public void setBuildtime(Timestamp buildtime) {
        this.buildtime = buildtime;
    }

    public Integer getAttachbilltimes() {
        return this.attachbilltimes;
    }
    
    public void setAttachbilltimes(Integer attachbilltimes) {
        this.attachbilltimes = attachbilltimes;
    }

    public String getRemark() {
        return this.remark;
    }
    
    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getStaffId() {
        return this.staffId;
    }
    
    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }
   








}