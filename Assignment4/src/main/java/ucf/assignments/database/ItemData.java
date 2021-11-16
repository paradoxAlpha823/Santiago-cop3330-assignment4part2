/*
 *  UCF COP3330 Fall 2021 Assignment 4 Part 2 Solution
 *  Copyright 2021 Diego Santiago
 */

package ucf.assignments.database;


import java.io.Serializable;
import java.time.format.DateTimeFormatter;

public class ItemData {

    public static class Task implements Serializable {

        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        private String title;
        private String date;
        private String desc;
        private boolean complete;

        public Task(){
            this.title = "";
            this.date = "2000-08-23";
            this.desc = "";
            this.complete = false;
        }


        public Task (String title1, String date1, String desc1, boolean complete1) {

            this.title = title1;
            this.date = date1;
            this.desc = desc1;
            this.complete = complete1;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title1) {
            this.title=title1;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date1) {
            this.date= date1;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc1) {
            this.desc=desc1;
        }

        public boolean getComplete() {
            return complete;
        }

        public void setComplete(boolean complete1) {
            this.complete=complete1;
        }


    }

}
