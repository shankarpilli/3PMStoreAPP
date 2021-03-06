package com.three.pmstore.models;

/**
 * Created by shankar on 10/1/2016.
 */

public class CartItemModel {
    private boolean IsEnabled;
    private String Product_Type;
    private String P_Cost;
    private String P_CustomValidate;
    private String P_Date;
    private String P_Description;
    private String P_hfeatures;
    private String p_id;
    private String P_Information;
    private String P_Name;
    private String P_Qty;
    private String P_shortdesc;
    private String P_Video;
    private String Stock;
    private String StrikeMrp;
    private String image;
    private int selectedQuantity;

    public boolean isEnabled() {
        return IsEnabled;
    }

    public void setEnabled(boolean enabled) {
        IsEnabled = enabled;
    }

    public String getProduct_Type() {
        return Product_Type;
    }

    public void setProduct_Type(String product_Type) {
        Product_Type = product_Type;
    }

    public String getP_Cost() {
        return P_Cost;
    }

    public void setP_Cost(String p_Cost) {
        P_Cost = p_Cost;
    }

    public String getP_CustomValidate() {
        return P_CustomValidate;
    }

    public void setP_CustomValidate(String p_CustomValidate) {
        P_CustomValidate = p_CustomValidate;
    }

    public String getP_Date() {
        return P_Date;
    }

    public void setP_Date(String p_Date) {
        P_Date = p_Date;
    }

    public String getP_Description() {
        return P_Description;
    }

    public void setP_Description(String p_Description) {
        P_Description = p_Description;
    }

    public String getP_hfeatures() {
        return P_hfeatures;
    }

    public void setP_hfeatures(String p_hfeatures) {
        P_hfeatures = p_hfeatures;
    }

    public String getP_id() {
        return p_id;
    }

    public void setP_id(String p_id) {
        this.p_id = p_id;
    }

    public String getP_Information() {
        return P_Information;
    }

    public void setP_Information(String p_Information) {
        P_Information = p_Information;
    }

    public String getP_Name() {
        return P_Name;
    }

    public void setP_Name(String p_Name) {
        P_Name = p_Name;
    }

    public String getP_Qty() {
        return P_Qty;
    }

    public void setP_Qty(String p_Qty) {
        P_Qty = p_Qty;
    }

    public String getP_shortdesc() {
        return P_shortdesc;
    }

    public void setP_shortdesc(String p_shortdesc) {
        P_shortdesc = p_shortdesc;
    }

    public String getP_Video() {
        return P_Video;
    }

    public void setP_Video(String p_Video) {
        P_Video = p_Video;
    }

    public String getStock() {
        return Stock;
    }

    public void setStock(String stock) {
        Stock = stock;
    }

    public String getStrikeMrp() {
        return StrikeMrp;
    }

    public void setStrikeMrp(String strikeMrp) {
        StrikeMrp = strikeMrp;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getSelectedQuantity() {
        return selectedQuantity;
    }

    public void setSelectedQuantity(int selectedQuantity) {
        this.selectedQuantity = selectedQuantity;
    }
}
