## Item07 다 쓴 객체 참조를 해제하라

C, C++ 처럼 메모리를 관리해야하는 언어를 사용하다가 Java 언어를 사용하면 가비지 컬렉터가 알아서 메모리 관리를 해주지만 메모리 관리에 신경을 소홀히해서는 안된다.

``` java
public class Stack {  
    private Object[] elements;  
    private int size = 0;  
    private static final int DEFAULT_CAPACITY = 16;  
      
    public Stack() {  
        elements = new Object[DEFAULT_CAPACITY];  
    }  
  
    public void push(Object object) {  
        this.ensureCapacity();  
        elements[size++] = object;  
    }  
      
    public Object pop() {  
        if (size == 0) {  
            throw new EmptyStackException();  
        }  
        return elements[--size];  
    }  
  
    private void ensureCapacity() {  
        if (elements.length == size) {  
            elements = Arrays.copyOf(elements, size + 1);  
        }  
    }  
}
```

위 코드에서 메모리 누수가 일어나는 위치는 어디일까 ?
바로 pop을 하는 과정에서 일어난다. 그 이유로는 스택에서 제거된 객체에 대한 참조가 여전히 배열에 남아 있기 때문이다. 이로인해 가비지 칼렉터가 해당 객체를 회수하지 못하고, 메모리가 계속 점유되게 되는거다

이 메모리 누수를 해결하기위한 방법은 아주 간단하다. 제거했던 위치에 `null`값으로 설정해주면된다.

``` java
public class Stack {  
    private Object[] elements;  
    private int size = 0;  
    private static final int DEFAULT_CAPACITY = 16;  
      
    public Stack() {  
        elements = new Object[DEFAULT_CAPACITY];  
    }  
  
    public void push(Object object) {  
        this.ensureCapacity();  
        elements[size++] = object;  
    }  
      
    public Object pop() {  
        if (size == 0) {  
            throw new EmptyStackException();  
        }  
        Object result = elements[--size];
        elements[size] = null;
        return result;  
    }  
  
    private void ensureCapacity() {  
        if (elements.length == size) {  
            elements = Arrays.copyOf(elements, size + 1);  
        }  
    }  
}
```

메모리 누수는 겉으로 잘 드러나지 않아 시스템에 수년간 잠복하는 사례도 있다. 이런 누수는 철저한 코드 리뷰나 프로파일러 같은 디버깅 도구를 동원해야만 발견되기도 한다. 그래서 이런 종류의 문제는 예방법을 읽혀두는것이 매우 중요하다.