package com.vikination.bakingapp.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Viki Andrianto on 9/15/17.
 */

public class IngredientsResponse implements Serializable{
    private static final long serialVersionUID = -865940534275958237L;

    int id;
    String name;
    ArrayList<Ingredient> ingredients;
    ArrayList<Step> steps;
    int servings;
    String image;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Ingredient> getIngredients() {
        return ingredients;
    }

    public ArrayList<Step> getSteps() {
        return steps;
    }

    public int getServings() {
        return servings;
    }

    public String getImage() {
        return image;
    }

    public static class Ingredient implements Serializable{
        private static final long serialVersionUID = 6175662307370794515L;

        double quantity;
        String measure;
        String ingredient;

        public double getQuantity() {
            return quantity;
        }

        public String getMeasure() {
            return measure;
        }

        public String getIngredient() {
            return ingredient;
        }
    }

    public static class Step implements Serializable{
        private static final long serialVersionUID = -4333736188755716941L;

        int id;
        String shortDescription;
        String description;
        String videoURL;
        String thumbnailURL;

        public int getId() {
            return id;
        }

        public String getShortDescription() {
            return shortDescription;
        }

        public String getDescription() {
            return description;
        }

        public String getVideoURL() {
            return videoURL;
        }

        public String getThumbnailURL() {
            return thumbnailURL;
        }
    }
}
