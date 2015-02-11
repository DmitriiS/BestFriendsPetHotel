package com.mws3000.bestfriendspethotel;


public class Dogs {

    private String name, picture, gender, breed;

    public Dogs(){
    }

    public Dogs(String name, String picture, String gender, String breed){
        this.name = name;
        this.picture = picture;
        this.gender = gender;
        this.breed = breed;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }



}
