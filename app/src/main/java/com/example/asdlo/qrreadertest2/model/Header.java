package com.example.asdlo.qrreadertest2.model;


public class Header extends RecyclerViewItem {

    private String headerText;

    public Header ( String headerText ){
        headerText= headerText ;
    }

    public String getHeaderText() {
        return headerText;
    }

    public void setHeaderText(String headerText) {
        this.headerText = headerText;
    }


}
