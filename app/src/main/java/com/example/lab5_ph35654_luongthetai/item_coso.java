package com.example.lab5_ph35654_luongthetai;

public class item_coso {
    private  String name;
    private int Icon;

    public item_coso() {
    }

    public item_coso(String name, int icon) {
        this.name = name;
        Icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIcon() {
        return Icon;
    }

    public void setIcon(int icon) {
        Icon = icon;
    }
}
