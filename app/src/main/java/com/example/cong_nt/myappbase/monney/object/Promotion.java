package com.example.cong_nt.myappbase.monney.object;

import com.google.gson.annotations.SerializedName;

public class Promotion {
    @SerializedName("type")
    private int typePromo; // loại khuyến mại
    @SerializedName("value")
    private float valuePromo; // giá trị khuyến mại
    @SerializedName("max_discount")
    private float max_discount; // mức khuyến mại tối đa

    /**
     * Loại khuyến mại
     *
     * @return = 1 tính theo %, = 2 số tiền khuyến mại
     */
    public int getTypePromo() {
        return typePromo;
    }

    public void setTypePromo(int type) {
        this.typePromo = type;
    }

    /**
     * Loại 1 tính theo % , loại 2 trừ thẳng
     *
     * @return
     */
    public float getValuePromo() {
        return valuePromo;
    }

    public void setValuePromo(float value) {
        this.valuePromo = value;
    }

    /**
     * Số tiền trừ tối đa
     *
     * @return
     */
    public float getMax_discount() {
        return max_discount;
    }

    public void setMax_discount(float max_discount) {
        this.max_discount = max_discount;
    }
}
