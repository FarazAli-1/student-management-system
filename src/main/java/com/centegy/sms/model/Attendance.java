package com.centegy.sms.model;

import java.time.LocalDate;

public class Attendance {

    public Attendance(LocalDate date, boolean isPresent){
        this.date = date;
        this.isPresent = isPresent;
    }


    public boolean isPresent() {
        return isPresent;
    }

    public void setPresent(boolean present) {
        isPresent = present;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    private LocalDate date;
    private boolean isPresent;




}
