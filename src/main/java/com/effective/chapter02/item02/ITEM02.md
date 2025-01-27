## 점층적 생성자 패턴

```java
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
        NutritionFacts nutritionFacts1 = new NutritionFacts(240, 8, 100, 0, 35, 27);  
        NutritionFacts nutritionFacts2 = new NutritionFacts(240, 8, 100, 0, 35);  
        NutritionFacts nutritionFacts3 = new NutritionFacts(240, 8, 100, 0);  
        NutritionFacts nutritionFacts4 = new NutritionFacts(240, 8, 100);  
        NutritionFacts nutritionFacts5 = new NutritionFacts(240, 8);  
    }  
}

```
이 클래스의 인스턴를 만들려면 원하는 매개변수를 모두 포함한 생성자 중 가장 짧은 것을 골라 호출하면된다.
점층적 생성자 패턴도 쓸 수는 있지만, 매개변수 개수가 많아지면 클라이언트 코드를 작성하거나 읽기 어려워진다.

## 자바빈즈 패턴

자바빈즈 패턴은 매개변수가 없는 생성자로 객체를 만든 후, 세터 메서드들을 호출해 원하는 매개변수의 값을 설정하는 패턴이다.

```java
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
```

점층적 생성자 패턴의 단점들이 자바빈즈 패턴에서는 더 이상 보이지 않는다.
인스턴스를 만들기 쉽고, 그 결과 더 읽기 쉬운 코드가 되었다.

### 단점

- 객체 하나를 만들려면 메서드를 여러 개 호출해야한다.
- 객체가 완전히 생성되기 전까지는 일관성이 무너진 상태에 놓이게 된다.
- 클래스를 불변으로 만들 수 없다.

## 빌더 패턴

필수 매개변수만으로 생성자를 호출해 빌더 객체를 만든다. 그런 다음 빌더 객체가 제공하는 일종의 세터 메서드들로 원하는선택 매개변수들을 설정한다. 마지막으로 매개변수가 없는 build 메서드를 호출해 객체를 만든다.

```java
public class NutritionFactsBuilder {  
    private final int servingSize;  
    private final int servings;  
    private final int calories;  
    private final int fat;  
    private final int sodium;  
    private final int carbohydrate;  
  
    public static class Builder {  
        // 필수 매개변수  
        private final int servingSize;  
        private final int servings;  
  
        // 선택 매개변수 - 기본값으로 초기화한다.  
        private int calories = 0;  
        private int fat = 0;  
        private int sodium = 0;  
        private int carbohydrate = 0;  
  
        public Builder(int servingSize, int servings) {  
            this.servingSize = servingSize;  
            this.servings = servings;  
        }  
  
        public Builder calories(int calories) {  
            this.calories = calories;  
            return this;  
        }  
  
        public Builder fat(int fat) {  
            this.fat = fat;  
            return this;  
        }  
  
        public Builder sodium(int sodium) {  
            this.sodium = sodium;  
            return this;  
        }  
  
        public Builder carbohydrate(int carbohydrate) {  
            this.carbohydrate = carbohydrate;  
            return this;  
        }  
  
        public NutritionFactsBuilder build() {  
            return new NutritionFactsBuilder(this);  
        }  
    }  
  
    private NutritionFactsBuilder(Builder builder) {  
        servingSize = builder.servingSize;  
        servings = builder.servings;  
        calories = builder.calories;  
        fat = builder.fat;  
        sodium = builder.sodium;  
        carbohydrate = builder.carbohydrate;  
    }  
  
    public static void main(String[] args) {  
        NutritionFactsBuilder nutritionFactsBuilder = new NutritionFactsBuilder.Builder(240, 8)  
                .calories(100).sodium(35).carbohydrate(27).build();  
    }  
}

```

빌더의 세터 메서드들은 빌더 자신을 반환하기 때문에 연쇄적으로 호출할 수 있다. 이런 방식을 메서드 호출이 흐르듯 연결된다는 뜻으로 메서드 연쇄라고 한다.
빌더 패턴은 명명된 선택적 매개변수를 흉내 낸 것이다.

```java
import java.util.EnumSet;  
import java.util.Objects;  
import java.util.Set;  
  
public abstract class Pizza {  
    public enum Topping { HAM, MUSHROOM, ONION, PEPPER, SAUSAGE }  
  
    final Set<Topping> toppings;  
  
    abstract static class Builder<T extends Builder<T>> {  
  
        EnumSet<Topping> toppings = EnumSet.noneOf(Topping.class);  
  
        public T addTopping(Topping topping) {  
            toppings.add(Objects.requireNonNull(topping));  
            return self();  
        }  
  
        abstract Pizza build();  
  
        protected abstract T self();  
    }  
  
    Pizza(Builder<?> builder) {  
        toppings = builder.toppings.clone();  
    }  
  
}
```

```java
import java.util.Objects;  
  
public class NyPizza extends Pizza {  
  
    public enum Size { SMALL, MEDIUM, LARGE }  
  
    private final Size size;  
  
    public static class Builder extends Pizza.Builder<Builder> {  
        private final Size size;  
  
        public Builder(Size size) {  
            this.size = Objects.requireNonNull(size);  
        }  
  
        @Override  
        public NyPizza build() {  
            return new NyPizza(this);  
        }  
  
        @Override  
        protected Builder self() {  
            return this;  
        }  
    }  
  
    NyPizza(Builder builder) {  
        super(builder);  
        size = builder.size;  
    }  
}
```

```java
public class Calzone extends Pizza {  
    private final boolean sauceInside;  
  
    public static class Builder extends Pizza.Builder<Builder> {  
        private boolean sauceInside = false;  
  
        public Builder sauceInside() {  
            sauceInside = true;  
            return this;  
        }  
  
        @Override  
        public Calzone build() {  
            return new Calzone(this);  
        }  
  
        @Override  
        protected Builder self() {  
            return this;  
        }  
    }  
    private Calzone(Builder builder) {  
        super(builder);  
        sauceInside = builder.sauceInside;  
    }  
}
```

생성자나 정적 팩터리가 처리해야 할 매개변수가 많다면 빌더 패턴을 선택하는게 더 낫다. 매개변수 중 다수가 필수가 아니거나 같은 타입이면 더 그렇다. 빌더는 점층적 생성자보다 클라이언트 코드를 읽고 쓰기가 휠씬 간결하고, 자바빈즈보다 훨씬 안전하다.