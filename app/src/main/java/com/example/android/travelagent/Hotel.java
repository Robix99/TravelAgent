package com.example.android.travelagent;

class Hotel {

    private String title;
    private String info;
    private String varos;
    private final int imageResource;



    public Hotel(String title, String info, int imageResource, String varos) {
        this.title = title;
        this.info = info;
        this.imageResource = imageResource;
        this.varos=varos;
    }

    String getVaros() {
        return varos;
    }

    String getTitle() {
        return title;
    }

    String getInfo() {
        return info;
    }

    public int getImageResource() {
        return imageResource;
    }

}
