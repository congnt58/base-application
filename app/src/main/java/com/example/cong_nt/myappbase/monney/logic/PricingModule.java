package com.example.cong_nt.myappbase.monney.logic;

import com.example.cong_nt.myappbase.monney.object.Prices;
import com.example.cong_nt.myappbase.monney.object.Promotion;

import java.util.ArrayList;

public class PricingModule {

    public double priceWithDistance(double distance, Prices price) {
        double totalPrice = 0;
        double distanceTemp = distance;

        if (price == null) return 0;

        ArrayList<Prices.Price_over> priceOvers = price.getListPriceOver();
        if (distanceTemp < price.getOpeningDistance()) {
            return price.getOpeningPrice();
        }

        distanceTemp -= price.getOpeningDistance();
        totalPrice += price.getOpeningPrice();

        if (priceOvers == null || priceOvers.isEmpty()) {
            totalPrice += distanceTemp * price.getPricePerKilomet();
            return totalPrice;
        } else {
            double t = priceOvers.get(0).getDistance() - price.getOpeningDistance();
            if (t < 0) t = 0;
            if (distanceTemp > t) {
                totalPrice += t * price.getPricePerKilomet();
                distanceTemp -= t;
                t = 0;
                for (int i = 0; i < priceOvers.size(); i++) {
                    if (i != priceOvers.size() - 1) {
                        t = priceOvers.get(i + 1).getDistance() - priceOvers.get(i).getDistance();
                        if (t < 0) t = 0;
                        if (distanceTemp > t) {
                            totalPrice += t * priceOvers.get(i).getValue();
                            distanceTemp -= t;
                        } else {
                            totalPrice += distanceTemp * priceOvers.get(i).getValue();
                            return totalPrice;
                        }
                    } else {
                        totalPrice += distance * priceOvers.get(i).getValue();
                    }
                }
            } else {
                totalPrice += distanceTemp * price.getPricePerKilomet();
            }
        }
        return totalPrice;
    }

    public double pricePromo(double totalPrice, Promotion promo) {
        double pricePromo = 0;
        if (promo != null) {
            switch (promo.getTypePromo()) {
                case 1:
                    pricePromo = totalPrice * (promo.getValuePromo() / 100);
                    if (pricePromo > promo.getMax_discount())
                        pricePromo = promo.getMax_discount();
                    return pricePromo;
                case 2:
                    return promo.getValuePromo();
                default:
                    break;
            }
        }
        return pricePromo;
    }
}
