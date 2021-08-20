package com.od.sharemarketmarathi;

public class Candle_Model {
    private  String url;
    private String title;  // same in the firebase
   private String info;

    public Candle_Model() {
    }

    public Candle_Model(String url, String title, String info) {
        this.url = url;
        this.title = title;
        this.info = info;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
