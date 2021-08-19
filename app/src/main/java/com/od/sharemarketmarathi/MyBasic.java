package com.od.sharemarketmarathi;

public class MyBasic {
    String ti; //
    String tx;
    String url;

    public MyBasic() {
    }

    public MyBasic(String ti, String tx, String url) {
        this.ti = ti;
        this.tx = tx;
        this.url = url;
    }

    public String getTi() {
        return ti;
    }

    public void setTi(String ti) {
        this.ti = ti;
    }

    public String getTx() {
        return tx;
    }

    public void setTx(String tx) {
        this.tx = tx;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}