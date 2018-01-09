package com.example.ptwitchapon.familyday.Model;

import java.util.List;

/**
 * Created by ptwitchapon on 9/1/2561.
 */

public class RegisModel {

    /**
     * STATUS : ลงทะเบียนเรียบร้อย
     * STATUS_ID : 1
     * PROFILE : [{"RG_RUNNO":"1801014113","RG_FNAME":"สุดใจ","RG_LNAME":"กิจมณีแก้วสกุล","RG_UNIT":"A-1220"}]
     * PARLRNT : [{"RG_RUNNO":"1801014114","RG_FNAME":"สรวิช","RG_LNAME":"รัตนลือชากุล","RG_UNIT":"A-1220"}]
     * ACTIVITIES : [{"ET_TNAME":"ธรรมะในสวน"},{"ET_TNAME":"ดนตรีในสวน"},{"ET_TNAME":"เกมส์-คอนเสิร์ต"}]
     * LOCATION : ดนตรีในสวน
     */

    private String STATUS;
    private String STATUS_ID;
    private String LOCATION;
    private List<PROFILEBean> PROFILE;
    private List<PARLRNTBean> PARLRNT;
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

    public List<PARLRNTBean> getPARLRNT() {
        return PARLRNT;
    }

    public void setPARLRNT(List<PARLRNTBean> PARLRNT) {
        this.PARLRNT = PARLRNT;
    }

    public List<ACTIVITIESBean> getACTIVITIES() {
        return ACTIVITIES;
    }

    public void setACTIVITIES(List<ACTIVITIESBean> ACTIVITIES) {
        this.ACTIVITIES = ACTIVITIES;
    }

    public static class PROFILEBean {
        /**
         * RG_RUNNO : 1801014113
         * RG_FNAME : สุดใจ
         * RG_LNAME : กิจมณีแก้วสกุล
         * RG_UNIT : A-1220
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

    public static class PARLRNTBean {
        /**
         * RG_RUNNO : 1801014114
         * RG_FNAME : สรวิช
         * RG_LNAME : รัตนลือชากุล
         * RG_UNIT : A-1220
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
