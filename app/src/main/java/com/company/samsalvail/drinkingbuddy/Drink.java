package com.company.samsalvail.drinkingbuddy;

import java.io.Serializable;

/**
 * Created by Kamil on 2015-11-26.
 */
public class Drink implements Serializable{
    private int id;
    private String category;
    private String title;
    private String postedBy;
    private String link;
    private String description;
    private String website;

    Drink(int id, String category, String title, String postedBy, String link, String description, String website) {
        this.id = id;
        this.category = category;
        this.title = title;
        this.postedBy = postedBy;
        this.link = link;
        this.description = description;
        this.website = website;
    }


    public int getID() {
        return id;
    }

    public String getCategory() {
        return category;
    }

    public String getPostedBy() {
        return postedBy;
    }

    public String getTitle() {
        return title;
    }

    public String getLink() {
        return link;
    }

    public String getDescription() {
        return description;
    }

    public String getWebsite() {
        return website;
    }





}
