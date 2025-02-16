``` java
public class String1 {  
    public static void main(String1[] args) {  
        String str = new String("bikini"); // 따라 하지 말 것!  
        }  
}
```

이 문장은 실행될 때마다 String 인스턴스를 새로 만든다. 완전히 쓸데없는 행위다. 생성자에 넘겨진
"bikini" 자체가 이 생성자로 만들어내려는 String과 기능적으로 완전히 똑같다.

개선된 버전을 보자

``` java
public class String1 {  
    public static void main(String1[] args) {  
        String str = "bikini"; // 개선된 버전
    }  
}
```

이 코드는 새로운 인스턴스를 매번 만드는 대신 하나의 String 인스턴스를 사용한다. 나아가 이 방식을 사용한다면 같은 가상 머신 안에서 이와 똑같은 문자열 리터럴을 사용하는 모든 코드가 같은 객체를 재사용함이 보장된다.

``` java
public class RomanNumerals1 {  
    public static boolean isRomanNumeral(String str) {  
        return str.matches("^(?=.)M*(C[MD] |D?C{0,3})(X[CL]|L?X{0,3}(I[XV]|V?I{0,3})$)");  
    }  
}
```

이 방식의 문제는 String.matches 메서드를 사용한다는 데 있다.
**String.matches는 정규표현식으로 문자열 형태를 확인하는 가장 쉬운 방법이지만, 성능이 중요한 상황에서 반복해 사용하기엔 적합하지 않다. **

``` java
public class RomanNumerals2 {  
  
    private static final Pattern ROMAN = Pattern.compile("^(?=.)M*(C[MD] |D?C{0,3})(X[CL]|L?X{0,3}(I[XV]|V?I{0,3})$)");  
      
    public static boolean isRomanNumeral(String str) {  
        return ROMAN.matcher(str).matches();  
    }  
}
```

성능을 개선하려면 필요한 정규표현식을 표현하는 Pattern 인스턴스를 클래스 초기화 과정에서 직접 생성해 캐시해두고, 나중에 isRomanNumeral 메서드가 호출될 때마다 이 인스턴스를 재사용한다.