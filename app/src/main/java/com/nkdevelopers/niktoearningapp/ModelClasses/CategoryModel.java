package com.nkdevelopers.niktoearningapp.ModelClasses;
public class CategoryModel {

    String CategoryName,CategoryImage, CategoryID;

    public CategoryModel() {
    }



    public CategoryModel(String categoryName, String categoryImage, String categoryID) {
        CategoryName = categoryName;
        CategoryImage = categoryImage;
        CategoryID = categoryID;
    }

    public String getCategoryName() {
        return CategoryName;
    }

    public void setCategoryName(String categoryName) {
        CategoryName = categoryName;
    }

    public String getCategoryImage() {
        return CategoryImage;
    }

    public void setCategoryImage(String categoryImage) {
        CategoryImage = categoryImage;
    }

    public String getCategoryID() {
        return CategoryID;
    }

    public void setCategoryID(String categoryID) {
        CategoryID = categoryID;
    }
}
