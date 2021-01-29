package com.imran.api;

public class Getdata
{
    private String uid,name,address;

    public Getdata(String uid,String name,String address)
    {
     this.uid=uid;
     this.name=name;
     this.address=address;
}

    public String getUid() {
        return uid;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }
}
