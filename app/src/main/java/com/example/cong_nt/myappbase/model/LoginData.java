package com.example.cong_nt.myappbase.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by CONG on 10/4/2017.
 */

public class LoginData {

    @SerializedName("token")
    private String token;
    @SerializedName("driver_id")
    private String driver_id;
    @SerializedName("info")
    private Info info;
    @SerializedName("activated")
    private int activated;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getDriver_id() {
        return driver_id;
    }

    public void setDriver_id(String driver_id) {
        this.driver_id = driver_id;
    }

    public Info getInfo() {
        return info;
    }

    public void setInfo(Info info) {
        this.info = info;
    }

    public int getActivated() {
        return activated;
    }

    public void setActivated(int activated) {
        this.activated = activated;
    }

    public LoginData(String token, String driver_id, String email, String full_name, String phone_number, String address, String avatar, String identification, String facebook_id, String google_id) {
        this.token = token;
        this.driver_id = driver_id;
        this.info = new Info(email, full_name, phone_number, address, avatar, identification, facebook_id, google_id);
    }

    public static class Info {
        @SerializedName("email")
        private String email;
        @SerializedName("email_verified")
        private String email_verified;
        @SerializedName("full_name")
        private String full_name;
        @SerializedName("phone_number")
        private String phone_number;
        @SerializedName("address")
        private String address;
        @SerializedName("avatar")
        private String avatar;
        @SerializedName("rating")
        private String rating;
        @SerializedName("identification")
        private String identification;
        @SerializedName("invite_code")
        private String invite_code;
        @SerializedName("office_id")
        private String office_id;
        @SerializedName("facebook_id")
        private String facebook_id;
        @SerializedName("google_id")
        private String google_id;

        public Info(String email, String full_name, String phone_number, String address, String avatar, String identification, String facebook_id, String google_id) {
            this.email = email;
            this.full_name = full_name;
            this.phone_number = phone_number;
            this.address = address;
            this.avatar = avatar;
            this.identification = identification;
            this.facebook_id = facebook_id;
            this.google_id = google_id;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getEmail_verified() {
            return email_verified;
        }

        public void setEmail_verified(String email_verified) {
            this.email_verified = email_verified;
        }

        public String getFull_name() {
            return full_name;
        }

        public void setFull_name(String full_name) {
            this.full_name = full_name;
        }

        public String getPhone_number() {
            return phone_number;
        }

        public void setPhone_number(String phone_number) {
            this.phone_number = phone_number;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getRating() {
            return rating;
        }

        public void setRating(String rating) {
            this.rating = rating;
        }

        public String getIdentification() {
            return identification;
        }

        public void setIdentification(String identification) {
            this.identification = identification;
        }

        public String getInvite_code() {
            return invite_code;
        }

        public void setInvite_code(String invite_code) {
            this.invite_code = invite_code;
        }

        public String getOffice_id() {
            return office_id;
        }

        public void setOffice_id(String office_id) {
            this.office_id = office_id;
        }

        public String getFacebook_id() {
            return facebook_id;
        }

        public void setFacebook_id(String facebook_id) {
            this.facebook_id = facebook_id;
        }

        public String getGoogle_id() {
            return google_id;
        }

        public void setGoogle_id(String google_id) {
            this.google_id = google_id;
        }

        @Override
        public String toString() {
            final StringBuffer sb = new StringBuffer("Info{");
            sb.append("email='").append(email).append('\'');
            sb.append(", email_verified='").append(email_verified).append('\'');
            sb.append(", full_name='").append(full_name).append('\'');
            sb.append(", phone_number='").append(phone_number).append('\'');
            sb.append(", address='").append(address).append('\'');
            sb.append(", avatar='").append(avatar).append('\'');
            sb.append(", rating='").append(rating).append('\'');
            sb.append(", identification='").append(identification).append('\'');
            sb.append(", invite_code='").append(invite_code).append('\'');
            sb.append(", office_id='").append(office_id).append('\'');
            sb.append(", facebook_id='").append(facebook_id).append('\'');
            sb.append(", google_id='").append(google_id).append('\'');
            sb.append('}');
            return sb.toString();
        }
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("LoginData{");
        sb.append("token='").append(token).append('\'');
        sb.append(", driver_id='").append(driver_id).append('\'');
        sb.append(", info=").append(info);
        sb.append('}');
        return sb.toString();
    }
}
