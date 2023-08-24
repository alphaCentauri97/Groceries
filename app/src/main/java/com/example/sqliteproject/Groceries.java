package com.example.sqliteproject;

public class Groceries {
    private int id;
    private String title;
    private String descTitle;
    private String description;
    private String validFrom;
    private String validTill;

    public Groceries() {
    }

    public Groceries(String title, String descTitle, String description, String validFrom, String validTill) {
        this.title = title;
        this.descTitle = descTitle;
        this.description = description;
        this.validFrom = validFrom;
        this.validTill = validTill;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescTitle() {
        return descTitle;
    }

    public void setDescTitle(String descTitle) {
        this.descTitle = descTitle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getValidFrom() {
        return validFrom;
    }

    public void setValidFrom(String validFrom) {
        this.validFrom = validFrom;
    }

    public String getValidTill() {
        return validTill;
    }

    public void setValidTill(String validTill) {
        this.validTill = validTill;
    }
}
