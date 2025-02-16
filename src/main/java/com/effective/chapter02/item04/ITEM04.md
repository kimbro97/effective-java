생성자를 막으려면 간단하게 private로 생성자를 만들어라

``` java
public class UtilityClass {  
    // 기본 생성자가 만들어지는 것을 막는다(인스턴스화 방지용)  
    private UtilityClass() {  
        throw new AssertionError();  
    }  
}
```