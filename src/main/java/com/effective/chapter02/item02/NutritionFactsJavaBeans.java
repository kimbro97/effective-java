package com.effective.chapter02.item02;

public class NutritionFactsJavaBeans {
    private int servingSize = -1;
    private int servings = -1;
    private int calories = 0;
    private int fat = 0;
    private int sodium = 0;
    private int carbohydrate = 0;

    public NutritionFactsJavaBeans() {}

    public void setServingSize(int servingSize) {
        this.servingSize = servingSize;
    }

    public void setServings(int servings) {
        this.servings = servings;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public void setFat(int fat) {
        this.fat = fat;
    }

    public void setSodium(int sodium) {
        this.sodium = sodium;
    }

    public void setCarbohydrate(int carbohydrate) {
        this.carbohydrate = carbohydrate;
    }

    public static void main(String[] args) {
        NutritionFactsJavaBeans nutritionFactsJavaBeans = new NutritionFactsJavaBeans();
        nutritionFactsJavaBeans.setServingSize(10);
        nutritionFactsJavaBeans.setServings(10);
        nutritionFactsJavaBeans.setCalories(10);
        nutritionFactsJavaBeans.setFat(40);
        nutritionFactsJavaBeans.setSodium(40);
        nutritionFactsJavaBeans.setCarbohydrate(40);
    }
}
