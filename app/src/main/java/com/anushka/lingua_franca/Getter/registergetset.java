package com.anushka.lingua_franca.Getter;

import android.net.Uri;

import java.util.Date;

public class registergetset {
        private Uri photourl;
        public String name;
        public String branch;
        public int year ;
        public long contact ;
        public String email;
        public String password;
        public String data;
        public String type;
        public String rollnumber;
        public String designation;
        public String birth;
        public String userid;
        public int score;

    // Default constructor required for calls to
        // DataSnapshot.getValue(User.class)
        public registergetset() {
        }

        public registergetset(String userid,String name,String branch,int year,long contact, String email,String password) {
            this.userid=userid;
            this.name = name;
            this.branch=branch;
            this.year=year;
            this.contact=contact;
            this.email = email;
            this.password=password;
        }
        public registergetset(String data,String type)
        {
            this.data=data;
            this.type=type;
        }

    public Uri getPhotourl(){
        return photourl;
    }
    public void setPhotourl(Uri photourl)
    {
        this.photourl=photourl;
    }
        public String getUserid(){
            return userid;
        }
        public void setUserid(String userid)
        {
            this.userid=userid;
        }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public long getContact() {
        return contact;
    }

    public void setContact(long contact) {
        this.contact = contact;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public String getRollnumber() {
        return rollnumber;
    }

    public void setRollnumber(String rollnumber) {
        this.rollnumber = rollnumber;
    }
}

