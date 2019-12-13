package com.example.asdlo.qrreadertest2.model;

public class Footer extends RecyclerViewItem {

    private String footerText;

    public Footer (String footerText ){
        footerText = footerText;

    }

    public String getFooterText() {
        return footerText;
    }

    public void setFooterText(String footerText) {
        this.footerText = footerText;
    }

}
