싱글턴이란 인스턴스를 오직 하나만 생성할 수 있는 클래스를 말한다.

클래스를 싱글턴으로 만들면 이를 사용하는 클라이언트를 테스트하기가 어려워질 수 있다.

``` java
public class Elvis {  
    public static final Elvis INSTANCE = new Elvis();  
  
    private Elvis() {}  
  
    public void leaveTheBuilding() {}  
}
```

private 생성자는 public static final 필드인 Elvis.INSTANCE를 초기화할 때 딱 한 번만 호출되므로 만들어진 인스턴스가 전체 시스템에서 하나뿐임이 보장된다.

``` java
import java.lang.reflect.Constructor;

public class ReflectionExample {
    public static void main(String[] args) {
        try {
            // Elvis 클래스의 INSTANCE 확인
            Elvis elvis1 = Elvis.INSTANCE;
            System.out.println("elvis1: " + elvis1);

            // 리플렉션을 사용해 Elvis 클래스의 private 생성자에 접근
            Constructor<Elvis> constructor = Elvis.class.getDeclaredConstructor();
            constructor.setAccessible(true); // private 접근 가능하도록 설정

            // 새로운 인스턴스 생성
            Elvis elvis2 = constructor.newInstance();
            System.out.println("elvis2: " + elvis2);

            // 두 인스턴스 비교
            System.out.println("Are they the same instance? " + (elvis1 == elvis2));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
```

하지만 권한이 있는 클라이언트는 리플렉션 API인 AccessibleObject.setAccessible을 사용해 private 생성자를 호출 할 수 있다.
이러한 공격을 방어하려면 생성자를 수정하여 두번째 객체가 생성되려 할 때 예외를 던지게 하면 된다.

``` java
public class Elvis2 {  
    public static final Elvis2 INSTANCE = new Elvis2();  
  
    private Elvis2() {}  
      
    public static Elvis2 getInstance() {  
        return INSTANCE;  
    }  
  
    public void leaveTheBuilding() {}  
}
```

정적팩터리 메서드 방식의 싱글턴을 만드는 코드이다.
Elvis2.getInstance는 항상 같은 객체의 참조를 반환하므로 제2의 Elvis인스턴스란 결코 만들어지지 않는다(리플렉션을 통한 예외는 동일하다)


``` java
public enum Elvis3 {  
    INSTANCE;  
      
    public void leaveTheBuilding() {}  
}
```

public 필드 방식과 비슷하지만, 더 간결하고, 추가 노력 없이 직렬화할 수 있고 , 심지어 아주 복잡하한 직렬화 상황이나 리플렉션 공격에서도 제2의 인스턴가 생기는 일을 완벽히 막아준다.

대부분 상황에서는 원소가 하나뿐인 열거 타입이 싱글턴을 만드는 가장 좋은 방법이다.