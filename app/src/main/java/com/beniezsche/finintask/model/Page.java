package com.beniezsche.finintask.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Page {


    @SerializedName("page")
    private String page;
    @SerializedName("per_page")
    private String per_page;
    @SerializedName("total")
    private String total;
    @SerializedName("total_pages")
    private String total_pages;
    @SerializedName("data")
    private ArrayList<Repo> data;


    public Page(String page, String per_page, String total, String total_pages, ArrayList<Repo> data) {
        this.page = page;
        this.per_page = per_page;
        this.total = total;
        this.total_pages = total_pages;
        this.data = data;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getPer_page() {
        return per_page;
    }

    public void setPer_page(String per_page) {
        this.per_page = per_page;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getTotal_pages() {
        return total_pages;
    }

    public void setTotal_pages(String total_pages) {
        this.total_pages = total_pages;
    }

    public ArrayList<Repo> getData() {
        return data;
    }

    public void setData(ArrayList<Repo> data) {
        this.data = data;
    }
}
