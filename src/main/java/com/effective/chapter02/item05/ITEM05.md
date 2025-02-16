많은 클래스가 하나 이상의 자원에 의존한다.

``` java
public class SpellCheckerStatic {  
    private static final Lexicon dictionary = new Lexicon();  
      
    private SpellCheckerStatic() {}  
      
    public static boolean isValid(String word) {  
        return true;  
    }  
      
    public static List<String> suggestions(String typo) {  
        return List.of();  
    }  
}
```

``` java
public class SpellCheckerSingleton {  
    private static final Lexicon dictionary = new Lexicon();  
  
    private SpellCheckerSingleton() {}  
      
    public static SpellCheckerSingleton INSTANCE = new SpellCheckerSingleton();  
  
    public static boolean isValid(String word) {  
        return true;  
    }  
  
    public static List<String> suggestions(String typo) {  
        return List.of();  
    }  
}
```

두 방식 모두 사전을 단 하나만 사용한다고 가정한다는 점에서 그리 휼륭해 보이지 않는다.

사용하는 자원에 따라 동작이 달라지는 클래스에는 정적 유틸리티 클래스나 싱글턴 방식이 적합하지 않다.

대신 클래스가 여러 자원 인스턴스를 지원해야 하며, 클라인트가 원하는 자원을 사용해야 한다.
인스턴스를 생성할 때 생성자에 필요한 자원을 넘겨주는 방식이다. 이는 의존 객체 주입의 한 형태로, 맞춤법 검사기를 생성할 때 의존 객체인 사전을 주입해주면 된다.

``` java
public class SpellCheckerDi {  
    private final Lexicon dictionary;  
      
    public SpellCheckerDi(Lexicon dictionary) {  
        this.dictionary = Objects.requireNonNull(dictionary);  
    }  
  
    public static boolean isValid(String word) {  
        return true;  
    }  
  
    public static List<String> suggestions(String typo) {  
        return List.of();  
    }  
}
```

클래스가 내부적으로 하나 이상의 자원에 의존하고, 그 자원이 클래스 동작에 영향을 준다면 싱글턴과 정적 유틸리티 클래스는 사용하지 않는 것이 좋다. 이 자원들을 클래스가 직접 만들게 해서도 안된다. 대신 필요한 자원을 생성자에 넘겨주자. 의존 객체 주입이라 하는 이 기법은 클래스의 유연성, 재사용성, 테스트 용이성을 기막히게 개선해준다.