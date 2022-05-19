package com.example.swifttour;

public class UploadTrip {
    String imgurl;
    String caption;

    public UploadTrip(String imgurl, String caption) {
        this.imgurl = imgurl;
        this.caption = caption;
    }

    public String getImgurl() {
        return imgurl;
    }

    public String getCaption() {
        return caption;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public UploadTrip() {
    }
}
