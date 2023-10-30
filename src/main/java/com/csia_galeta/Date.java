package com.csia_galeta;

public class Date {
    public byte day;
    public byte month;
    public byte year;

    public String getDate (){
        String fullDate = this.day + "/" + this.month + "/" + this.year;
        return fullDate;
    }


}
