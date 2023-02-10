package org.dan.listviewprac;

public class PersonEntity{

    String name;
    String mobileNum;

    public PersonEntity(String name, String mobileNum) {
        this.name = name;
        this.mobileNum = mobileNum;
    }

    public String getName() {
        return name;
    }

    public String getMobileNum() {
        return mobileNum;
    }

    @Override
    public String toString() {
        return "PersonEntity{" +
                "name='" + name + '\'' +
                ", mobileNum='" + mobileNum + '\'' +
                '}';
    }
}
