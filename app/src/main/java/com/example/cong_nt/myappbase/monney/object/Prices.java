package com.example.cong_nt.myappbase.monney.object;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Prices {
    @SerializedName("id")
    private int idPrice;
    @SerializedName("price")
    private double pricePerKilomet;
    @SerializedName("opening_distance")
    private double openingDistance;
    @SerializedName("opening_price")
    private double openingPrice;
    @SerializedName("price_per_minute")
    private double pricePerMinute;
    @SerializedName("currency")
    private String currency;
    @SerializedName("price_over")
    private ArrayList<String> listPriceOver;

    //id bang gia
    public int getIdPrice() {
        return idPrice;
    }

    //giá theo mỗi km
    public double getPricePerKilomet() {
        return pricePerKilomet;
    }

    // quãng đường mở cửa
    public double getOpeningDistance() {
        return openingDistance;
    }

    //giá mở cửa
    public double getOpeningPrice() {
        return openingPrice;
    }

    //giá trên mỗi phút
    public double getPricePerMinute() {
        return pricePerMinute;
    }

    //đơn vị tiền
    public String getCurrency() {
        return currency;
    }

    //bảng giá theo khoang
    public ArrayList<Price_over> getListPriceOver() {
        ArrayList<Price_over> listOver = null;
        if (listPriceOver != null && !listPriceOver.isEmpty()) {
            for (int i = 0; i < listPriceOver.size(); i++) {
                String[] price = listPriceOver.get(i).split(" ");
                try {
                    if (listOver == null) {
                        listOver = new ArrayList<>();
                    }
                    listOver.add(new Price_over(price[0], price[1]));
                }catch (IndexOutOfBoundsException e){
//                    listOver.add(new Price_over("0", "0"));
                }
            }
        }
        return listOver;
    }

    public void setListPriceOver(ArrayList<String> price_over) {
        this.listPriceOver = price_over;
    }

    public static class Price_over {
        private double distance;
        private double value;

        public Price_over(String distance, String value) {
            this.distance = Double.parseDouble(distance);
            this.value = Double.parseDouble(value);
        }

        public double getDistance() {
            return distance;
        }

        public void setDistance(double distance) {
            this.distance = distance;
        }

        public double getValue() {
            return value;
        }

        public void setValue(double value) {
            this.value = value;
        }

        @Override
        public String toString() {
            final StringBuffer sb = new StringBuffer("PriceOver{");
            sb.append("distance=").append(distance);
            sb.append(", value=").append(value);
            sb.append('}');
            return sb.toString();
        }
    }
}


