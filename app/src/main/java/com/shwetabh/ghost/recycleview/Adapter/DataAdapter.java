package com.shwetabh.ghost.recycleview.Adapter;

public class DataAdapter
{
    public String ImageURL;
    public String ImageTitle;

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String genre;

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getReleaseyear() {
        return releaseyear;
    }

    public void setReleaseyear(String releaseyear) {
        this.releaseyear = releaseyear;
    }

    public String rating;  public String releaseyear;


        public String getImageUrl() {

            return ImageURL;
        }

        public void setImageUrl(String imageServerUrl) {

            this.ImageURL = imageServerUrl;
        }

        public String getImageTitle() {

            return ImageTitle;
        }

        public void setImageTitle(String Imagetitlename) {

            this.ImageTitle = Imagetitlename;
        }

}