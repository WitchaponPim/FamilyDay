package com.example.ptwitchapon.familyday.Model;

import java.util.List;

/**
 * Created by ptwitchapon on 23/1/2561.
 */

public class SaveModel {

    /**
     * STATUS : มีคนลงทะเบียนแล้ว
     * STATUS_ID : 2
     * PROFILE : [{"RG_RUNNO":"1801014113","RG_FNAME":"สุดใจ","RG_LNAME":"กิจมณีแก้วสกุล","RG_UNIT":"A-1220","RG_SMS":"27W14H","RG_MOBILE":"0850753855","NT_TNAME":"ลุมพินี พาร์ค ปิ่นเกล้า"}]
     */

    private String STATUS;
    private String STATUS_ID;
    private List<PROFILEBean> PROFILE;
    private String TIMESTAMP;
    private String RG_FNAME_REGISTER;

    public String getTIMESTAMP() {
        return TIMESTAMP;
    }

    public void setTIMESTAMP(String TIMESTAMP) {
        this.TIMESTAMP = TIMESTAMP;
    }

    public String getRG_FNAME_REGISTER() {
        return RG_FNAME_REGISTER;
    }

    public void setRG_FNAME_REGISTER(String RG_FNAME_REGISTER) {
        this.RG_FNAME_REGISTER = RG_FNAME_REGISTER;
    }

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

    public List<PROFILEBean> getPROFILE() {
        return PROFILE;
    }

    public void setPROFILE(List<PROFILEBean> PROFILE) {
        this.PROFILE = PROFILE;
    }

    public static class PROFILEBean {
        /**
         * RG_RUNNO : 1801014113
         * RG_FNAME : สุดใจ
         * RG_LNAME : กิจมณีแก้วสกุล
         * RG_UNIT : A-1220
         * RG_SMS : 27W14H
         * RG_MOBILE : 0850753855
         * NT_TNAME : ลุมพินี พาร์ค ปิ่นเกล้า
         */

        private String RG_RUNNO;
        private String RG_FNAME;
        private String RG_LNAME;
        private String RG_UNIT;
        private String RG_SMS;
        private String RG_MOBILE;
        private String NT_TNAME;

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

        public String getRG_SMS() {
            return RG_SMS;
        }

        public void setRG_SMS(String RG_SMS) {
            this.RG_SMS = RG_SMS;
        }

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
    }
}
