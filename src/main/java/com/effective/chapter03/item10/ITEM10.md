# Item10 equals는 일반 규약을 지켜 재정의하라

equals 메서드는 재정의하기 쉬워 보이지만 곳곳에 함정이 도사리고 있어서 자칫하면 끔직한 결과를 초래한다. 문제는 회피하는 가장 쉬운 길은 아예 재정의하지 않는 것이다.

## equals를 재정의하지 않아도되는 상황

- 각 인스턴스가본질적으로 고유할 때
- 인스턴스의 논리적 동치성을 검사할 일이 없을 때
- 상위 클래스에서 재정의한 equals가 하위 클래스에도 딱 들어 맞을 때
- 클래스가 private이거나 pakage-private이고 equals 메서드를 호출할 일이 없을 때

## equals를 재정의하는 상황

- 객체를 논리적 동치성을 확인해야하는데 상위 클래스의 equals가 논리적 동치성을 비교하도록 재정의되지 않았을 때

## equals 메서드 재정의 규약

- 반사성: null이 아닌 모든 참조 값 x에 대해, x.eqals(x)는 true다.
- 대칭성: null이 아닌 모든 참조 값 x, y에 대해, x.equals(y)rk ture면 y.equals(x)도 true다.
- 추이성: null이 아닌 모든 참조 값 x, y, z에 대해, x.equals(y)가 true이고 y.equals(z)도 true면 x.equals(z)도 true다.
- 일관성: null이 아닌 모든 참조 값 x, y에 대해, x.equals(y)를 반복해서 호출하면 항상 true를 반환하거나 항상 false를 반환한다.

## 동치관계란 무엇인가?

쉽게 말해, 집합을 서로 같은 원소들로 이뤄진 부분집합으로 나누는 연산을 말한다.

반사성은 단순히 말하면 객체는 자기 자신과 같아야한다는 뜻이다. 이 요건은 일부러 어기는 경우가 아니라면 만족시키지 못하기가 더 어려워 보인다.

### 대칭성

대칭성은 두 객체는 서로에 대한 동치 여부에 똑같이 답해야한다는 뜻이다. 반사성 요건과 달리 대칭성 요건은 자짗하면 어길 수 있어 보인다.

``` java
package com.effective.chapter03.item10;  
  
import java.util.Objects;  
  
public final class CaseInsensitiveString {  
  
    public static void main(String[] args) {  
        String s = "polish";  
  
        CaseInsensitiveString cis = new CaseInsensitiveString("Polish");  
  
        System.out.println(cis.equals(s)); // true  
        System.out.println(s.equals(cis)); // false  
    }  
  
    private final String s;  
  
    public CaseInsensitiveString(String s) {  
        this.s = Objects.requireNonNull(s);  
    }  
  
    @Override  
    public boolean equals(Object object) {  
  
        if (object instanceof CaseInsensitiveString) {  
            return s.equalsIgnoreCase(  
                    ((CaseInsensitiveString) object).s  
            );  
        }  
        if (object instanceof String) {  
            return s.equalsIgnoreCase((String) object);  
        }  
        return false;  
    }  
}
```

예상 할 수 있듯 cis.equals(s)는 true를 반환한다. 문제는 CaseInsensitiveString의 equals는 일반 String을 알고 있지만 String의 equals는 CaseInsensitiveString의 존재를 모른다는 데 있다. 따라서 s.equals(cis)는 false를 반환하여 대칭성을 명백히 위반한다.

``` java
package com.effective.chapter03.item10;  
  
import java.util.Objects;  
  
public final class CaseInsensitiveString {  
  
    public static void main(String[] args) {  
        String s = "polish";  
  
        CaseInsensitiveString cis = new CaseInsensitiveString("Polish");  
  
        System.out.println(cis.equals(s)); // false  
        System.out.println(s.equals(cis)); // false  
    }  
  
    private final String s;  
  
    public CaseInsensitiveString(String s) {  
        this.s = Objects.requireNonNull(s);  
    }  
  
    @Override  
    public boolean equals(Object object) {  
        return object instanceof CaseInsensitiveString &&  
                ((CaseInsensitiveString) object).s.equalsIgnoreCase(this.s);  
    }  
}
```

이 문제를 해결하려면 CaseInsensitiveString의 equals를 String과도 연동하겠다는 허황한 꿈은 버리고 위에 코드처럼 작성해야한다.

### 추이성

추이성은 첫 번째 객체와 두 번쨰 객체가 같고, 두 번쨰 객체와 세 번쨰 객체가 같다면, 첫 번쨰 객체와 세 번쨰 객체도 같아야한다는 뜻이다. 이 요건도 간단하지만 자칫하면 어기기 쉽다

``` java
package com.effective.chapter03.item10;  
  
import java.util.Objects;  
  
public class Point {  
    private final int x;  
    private final int y;  
  
    public Point(int x, int y) {  
        this.x = x;  
        this.y = y;  
    }  
  
    @Override  
    public boolean equals(Object object) {  
        if (this == object) return true;  
        if (object == null || getClass() != object.getClass()) return false;  
        Point point = (Point) object;  
        return x == point.x && y == point.y;  
    }  
}

package com.effective.chapter03.item10;  
  
public class ColorPoint extends Point {  
  
    private final Color color;  
  
    public ColorPoint(int x, int y, Color color) {  
        super(x, y);  
        this.color = color;  
    }  
}
```

간단한 x, y 좌표를 가지고있는 Point 클래스와 Point 클래스를 확장한 ColorPoint 클래스가 있다.
equals 메서드는 어떻게 해야 할까? 그대로 둔다면 Point의 구현이 상속되어 색상 정보는 무시된 채 비교된다.