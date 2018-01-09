package com.example.ptwitchapon.familyday.Model;

/**
 * Created by ptwitchapon on 8/1/2561.
 */

public class UserModel {

    /**
     * success : 1
     * profile : {"username":"ptwitchapon","first_name":"วิชญ์พล","last_name":"พิมพ์รัตน์"}
     */

    private String success;
    private ProfileBean profile;

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public ProfileBean getProfile() {
        return profile;
    }

    public void setProfile(ProfileBean profile) {
        this.profile = profile;
    }

    public static class ProfileBean {
        /**
         * username : ptwitchapon
         * first_name : วิชญ์พล
         * last_name : พิมพ์รัตน์
         */

        private String username;
        private String first_name;
        private String last_name;

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getFirst_name() {
            return first_name;
        }

        public void setFirst_name(String first_name) {
            this.first_name = first_name;
        }

        public String getLast_name() {
            return last_name;
        }

        public void setLast_name(String last_name) {
            this.last_name = last_name;
        }
    }
}
