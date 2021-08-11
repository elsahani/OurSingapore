package sg.edu.rp.c346.id20023841.oursingapore;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Island implements Serializable {
    private int _id;
    private String name;
    private String description;
    private int sqkm;
    private int stars;

    public Island(int _id, String name, String decription, int sqkm, int stars) {
        this._id = _id;
        this.name = name;
        this.description = decription;
        this.sqkm = sqkm;
        this.stars = stars;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getSqkm() {
        return sqkm;
    }

    public void setSqkm(int sqkm) {
        this.sqkm = sqkm;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    @NonNull
    @Override
    public String toString() {
        String starsString = "";
        for (int i = 0; i < stars; i++) {
            starsString += "* ";
        }
        return starsString;

    }
}