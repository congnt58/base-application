package com.example.cong_nt.myappbase.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by CONG on 7/5/2017.
 */

public class RedirectResponse {
    @SerializedName("region_name")
    private String regionName;
    @SerializedName("address")
    private String address;
    @SerializedName("maintenance_info")
    private Maintenance_Info maintenance_info;

    public Maintenance_Info getMaintenance_info() {
        return maintenance_info;
    }

    public void setMaintenance_info(Maintenance_Info maintenance_info) {
        this.maintenance_info = maintenance_info;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public static class Maintenance_Info {
        @SerializedName("content")
        private String content;
        @SerializedName("from")
        private String from;
        @SerializedName("to")
        private String to;

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getFrom() {
            return from;
        }

        public void setFrom(String from) {
            this.from = from;
        }

        public String getTo() {
            return to;
        }

        public void setTo(String to) {
            this.to = to;
        }

        @Override
        public String toString() {
            final StringBuffer sb = new StringBuffer("Maintenance_Info{");
            sb.append("content='").append(content).append('\'');
            sb.append(", from='").append(from).append('\'');
            sb.append(", to='").append(to).append('\'');
            sb.append('}');
            return sb.toString();
        }
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("RedirectResponse{");
        sb.append("regionName='").append(regionName).append('\'');
        sb.append(", address='").append(address).append('\'');
        sb.append(", maintenance_info=").append(maintenance_info);
        sb.append('}');
        return sb.toString();
    }
}
