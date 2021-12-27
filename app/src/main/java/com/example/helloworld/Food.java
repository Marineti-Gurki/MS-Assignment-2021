package com.example.helloworld;

public class Food {

    int _id;
    String _name;
    String _quantity;
    String _brands;
    String _categories;
    String _labels;
    String _stores;

    public Food(String string, String cursorString, String s){}

    public Food(int id, String name, String quantity) {
        this._id = id;
        this._name = name;
        this._quantity = quantity;

    }
    public int getId() {
        return this._id;
    }

    public void setId(int id) {
        this._id = id;
    }

    public String get_name() {
        return this._name;
    }

    public void set_name(String name) {
        this._name = name;
    }

    public String get_quantity() {
        return this._quantity;
    }

    public void set_quantity(String quantity) {
        this._quantity = quantity;
    }

    public String get_brands() {
        return this._brands;
    }

    public void set_brands(String brands) {
        this._brands = brands;
    }

    public String get_categories() {
        return this._categories;
    }

    public void set_categories(String categories) {
        this._categories = categories;
    }

    public String get_labels() {
        return this._labels;
    }

    public void set_labels(String labels) {
        this._labels = labels;
    }

    public String get_stores() {
        return this._stores;
    }

    public void set_stores(String stores) {
        this._stores = stores;
    }
}
