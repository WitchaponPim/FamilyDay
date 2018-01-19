package com.example.ptwitchapon.familyday.Model;

import java.util.List;

/**
 * Created by ptwitchapon on 9/1/2561.
 */

public class RegisModel {


    /**
     * STATUS : แสดงข้อมูล
     * STATUS_ID : 1
     * PROFILE : [{"RG_RUNNO":"1801014259","RG_FNAME":"พรเทพ555","RG_LNAME":"อ่ำเพชร","RG_UNIT":"C-0815"}]
     * PARENT : [{"RG_RUNNO":"1801014260","RG_FNAME":"ชื่อจั๊ม","RG_LNAME":"นามสกุลทดสอบ","RG_UNIT":"C-0815"},{"RG_RUNNO":"1801014261","RG_FNAME":"นิฤมล","RG_LNAME":"เนมียะ","RG_UNIT":"A-1619"},{"RG_RUNNO":"1801014262","RG_FNAME":"นิฤมล","RG_LNAME":"เนมียะ","RG_UNIT":"A-1619"},{"RG_RUNNO":"1801014263","RG_FNAME":"นิฤมล2","RG_LNAME":"เนมียะ","RG_UNIT":"A-1619"},{"RG_RUNNO":"1801014264","RG_FNAME":"นินิ","RG_LNAME":"มิยะ","RG_UNIT":"A-1619"},{"RG_RUNNO":"1801014265","RG_FNAME":"ดดด","RG_LNAME":"กก","RG_UNIT":"A-1619"},{"RG_RUNNO":"1801014266","RG_FNAME":"นินิ","RG_LNAME":"มียะ","RG_UNIT":"C-0815"},{"RG_RUNNO":"1801014267","RG_FNAME":"ASD","RG_LNAME":"GWP","RG_UNIT":"C-0815"},{"RG_RUNNO":"1801014268","RG_FNAME":"อัครพล","RG_LNAME":"พิมพ์แจ่ม","RG_UNIT":"C-0815"},{"RG_RUNNO":"1801014270","RG_FNAME":"นิฤมล","RG_LNAME":"นมียะ","RG_UNIT":"C-0815"}]
     * ACTIVITIES : [{"ET_TNAME":"ธรรมะในสวน"},{"ET_TNAME":"ดนตรีในสวน"},{"ET_TNAME":"เกมส์-คอนเสิร์ต"}]
     * LOCATION : 0
     */

    private String STATUS;
    private String STATUS_ID;
    private String LOCATION;
    private List<PROFILEBean> PROFILE;
    private List<PARENTBean> PARENT;
    private List<ACTIVITIESBean> ACTIVITIES;

    public String getSTATUS() {
        return STATUS;
    }

    public void setSTATUS(String STATUS) {
        this.STATUS = STATUS;
    }

    public String getSTATUS_ID() {
        return STATUS_ID;
    }

    public void setSTATUS_ID(String STATUS_ID) {
        this.STATUS_ID = STATUS_ID;
    }

    public String getLOCATION() {
        return LOCATION;
    }

    public void setLOCATION(String LOCATION) {
        this.LOCATION = LOCATION;
    }

    public List<PROFILEBean> getPROFILE() {
        return PROFILE;
    }

    public void setPROFILE(List<PROFILEBean> PROFILE) {
        this.PROFILE = PROFILE;
    }

    public List<PARENTBean> getPARENT() {
        return PARENT;
    }

    public void setPARENT(List<PARENTBean> PARENT) {
        this.PARENT = PARENT;
    }

    public List<ACTIVITIESBean> getACTIVITIES() {
        return ACTIVITIES;
    }

    public void setACTIVITIES(List<ACTIVITIESBean> ACTIVITIES) {
        this.ACTIVITIES = ACTIVITIES;
    }

    public static class PROFILEBean {
        /**
         * RG_RUNNO : 1801014259
         * RG_FNAME : พรเทพ555
         * RG_LNAME : อ่ำเพชร
         * RG_UNIT : C-0815
         */

        private String RG_RUNNO;
        private String RG_FNAME;
        private String RG_LNAME;
        private String RG_UNIT;
        private String RG_SMS;
        private String RG_MOBILE;
        private String NT_TNAME;

        public String getRG_MOBILE() {
            return RG_MOBILE;
        }

        public void setRG_MOBILE(String RG_MOBILE) {
            this.RG_MOBILE = RG_MOBILE;
        }

        public String getNT_TNAME() {
            return NT_TNAME;
        }

        public void setNT_TNAME(String NT_TNAME) {
            this.NT_TNAME = NT_TNAME;
        }

        public String getRG_SMS() {
            return RG_SMS;
        }

        public void setRG_SMS(String RG_SMS) {
            this.RG_SMS = RG_SMS;
        }

        public String getRG_RUNNO() {
            return RG_RUNNO;
        }

        public void setRG_RUNNO(String RG_RUNNO) {
            this.RG_RUNNO = RG_RUNNO;
        }

        public String getRG_FNAME() {
            return RG_FNAME;
        }

        public void setRG_FNAME(String RG_FNAME) {
            this.RG_FNAME = RG_FNAME;
        }

        public String getRG_LNAME() {
            return RG_LNAME;
        }

        public void setRG_LNAME(String RG_LNAME) {
            this.RG_LNAME = RG_LNAME;
        }

        public String getRG_UNIT() {
            return RG_UNIT;
        }

        public void setRG_UNIT(String RG_UNIT) {
            this.RG_UNIT = RG_UNIT;
        }
    }

    public static class PARENTBean {
        /**
         * RG_RUNNO : 1801014260
         * RG_FNAME : ชื่อจั๊ม
         * RG_LNAME : นามสกุลทดสอบ
         * RG_UNIT : C-0815
         */

        private String RG_RUNNO;
        private String RG_FNAME;
        private String RG_LNAME;
        private String RG_UNIT;

        public String getRG_RUNNO() {
            return RG_RUNNO;
        }

        public void setRG_RUNNO(String RG_RUNNO) {
            this.RG_RUNNO = RG_RUNNO;
        }

        public String getRG_FNAME() {
            return RG_FNAME;
        }

        public void setRG_FNAME(String RG_FNAME) {
            this.RG_FNAME = RG_FNAME;
        }

        public String getRG_LNAME() {
            return RG_LNAME;
        }

        public void setRG_LNAME(String RG_LNAME) {
            this.RG_LNAME = RG_LNAME;
        }

        public String getRG_UNIT() {
            return RG_UNIT;
        }

        public void setRG_UNIT(String RG_UNIT) {
            this.RG_UNIT = RG_UNIT;
        }
    }

    public static class ACTIVITIESBean {
        /**
         * ET_TNAME : ธรรมะในสวน
         */

        private String ET_TNAME;

        public String getET_TNAME() {
            return ET_TNAME;
        }

        public void setET_TNAME(String ET_TNAME) {
            this.ET_TNAME = ET_TNAME;
        }
    }
}
