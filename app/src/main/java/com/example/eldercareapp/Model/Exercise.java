//code written by me
package com.example.eldercareapp.Model;

public class Exercise {
    int image_id;
    int video_id;
    String name;
    String description;


    public Exercise(int image_id, int video_id, String name, String description) {

        this.image_id = image_id;
        this.video_id = video_id;
        this.name = name;
        this.description = description;
    }

    public int getImage_id() {
        return image_id;
    }

    public void setImage_id(int image_id) {
        this.image_id = image_id;
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

    public int getVideo_id() {
        return video_id;
    }

    public void setVideo_id(int video_id) {
        this.video_id = video_id;
    }
}
