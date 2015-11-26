package com.company.samsalvail.drinkingbuddy;

/**
 * Created by Kamil on 2015-11-26.
 */
public class Drink {
    private int id;
    private String category;
    private String title;
    private String postedBy;
    private String link;
    private String description;
    private int likes;
    private int dislikes;

    Drink(int id, String category, String title, String postedBy, String link, String description, int likes, int dislikes) {
        this.id = id;
        this.category = category;
        this.title = title;
        this.postedBy = postedBy;
        this.link = link;
        this.description = description;
        this.likes = likes;
        this.dislikes = dislikes;
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

    public int getLikes() {
        return likes;
    }

    public int getDislikes() {
        return dislikes;
    }

    protected void liked() {
        likes++;
    }

    protected void disliked() {
        dislikes++;
    }



}
