package com.example.eldercareapp;
//code written by me
public class MyListData{
    private String description;
    private String cal;
    private int imgId;
    private static int full=0;


    public MyListData(String description, String cal, int imgId) {
        this.description = description;
        this.cal = cal;
        this.imgId = imgId;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getCal() {  return "Calories: "+cal;    }

    public int caladd(){
        int i=Integer.parseInt(cal);

        full=full+i;
        return full;
    }
    public String setmsg(int cal){
        if(full>cal)
        {return "Your recommended calorie limit is over";}
        else
        {return "You can add some more calories to attain recommended calorie intake";}
    }
    public void setCal(String cal) {        this.cal = cal;    }
    public int getImgId() {
        return imgId;
    }
    public void setImgId(int imgId) {
        this.imgId = imgId;
    }
}