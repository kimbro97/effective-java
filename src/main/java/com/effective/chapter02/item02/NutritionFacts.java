package com.effective.chapter02.item02;

public class NutritionFacts {

    private final int servingSize;
    private final int getServings;
    private final int calories;
    private final int fat;
    private final int sodium;
    private final int carbohydrate;

    public NutritionFacts(int servingSize, int servings) {
        this(servingSize, servings, 0);
    }

    public NutritionFacts(int servingSize, int servings, int calories) {
        this(servingSize, servings, calories, 0);
    }

    public NutritionFacts(int servingSize, int servings, int calories, int fat) {
        this(servingSize, servings, calories, fat, 0);
    }

    public NutritionFacts(int servingSize, int servings, int calories, int fat, int sodium) {
        this(servingSize, servings, calories, fat, sodium, 0);
    }

    public NutritionFacts(int servingSize, int getServings, int calories, int fat, int sodium, int carbohydrate) {
        this.servingSize = servingSize;
        this.getServings = getServings;
        this.calories = calories;
        this.fat = fat;
        this.sodium = sodium;
        this.carbohydrate = carbohydrate;
    }

    public static void main(String[] args) {
        // 이 클래스의 인스턴스를 만들려면 원하는 매개변수를 모두 포함한 생성자 중 가장 짧은 것을 골라 호출
        // 점충적 생성자 패턴도 쓸 수는 있지만, 매개변수 개수가 많아지면 클라이언트 코드를 작성하거나 읽기 어려워진다.
        NutritionFacts nutritionFacts1 = new NutritionFacts(240, 8, 100, 0, 35, 27);
        NutritionFacts nutritionFacts2 = new NutritionFacts(240, 8, 100, 0, 35);
        NutritionFacts nutritionFacts3 = new NutritionFacts(240, 8, 100, 0);
        NutritionFacts nutritionFacts4 = new NutritionFacts(240, 8, 100);
        NutritionFacts nutritionFacts5 = new NutritionFacts(240, 8);
    }
}
