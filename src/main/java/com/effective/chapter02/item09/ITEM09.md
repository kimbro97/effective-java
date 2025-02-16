## Item09 try-fanally 보다 try-with-resources를 사용하라


자바 라이브러리에는 close 메서드를 호출해 직접 닫아줘야 하는 자원이 많다. 자원 닫기는 클라이언트가 놓치기 귀워서 예측할 수 없는 성능 문제로 이어지기도 한다. 이런 자원 중 상당수가 안전망으로 finalizer를 활용하고는 있지만 finalizer는 그리 밎을만하지 못하다.

``` java
public class TryFinally {  
      
    static String firstLineOfFile(String path) throws IOException {  
        BufferedReader bufferedReader = new BufferedReader(new FileReader(path));  
          
        try {  
            return bufferedReader.readLine();  
        } finally {  
            bufferedReader.close();  
        }  
    }  
      
}
```

나쁘지 않지만, 자원을 하나 더 사용한다면 어떨까 ?

``` java
public class TryFinally {  
      
	static void copy(String src, String dst) throws IOException {  
	    FileInputStream in = new FileInputStream(src);  
	    try {  
	        FileOutputStream out = new FileOutputStream(dst);  
	        try {  
	            byte[] buf = new byte[1024];  
	            int n;  
	            while ((n = in.read(buf)) >= 0) {  
	                out.write(buf, 0, n);  
	            }  
	        }finally {  
	            out.close();  
	        }  
	    } finally {  
	        in.close();  
	    }  
	}
}
```

자원이 둘 이상이면 try-finally 방식은 너무 지저분해진다 ....

try-with-resources를 사용한 예제를 보자

``` java
static String firstLineOfFile2(String path) throws IOException {  
    try (BufferedReader br = new BufferedReader(new FileReader(path))) {  
        return br.readLine();  
    }  
}  
  
static void copy2(String src, String dst) throws IOException {  
    try (FileInputStream in = new FileInputStream(src);  
         FileOutputStream out = new FileOutputStream(dst)) {  
            byte[] buf = new byte[1024];  
            int n;  
            while ((n = in.read(buf)) >= 0) {  
                out.write(buf, 0, n);  
            }  
         }  
}

```

try-with-resources 버전이 짧고 읽기 수월할 뿐 아니라 문제를 진단하기도 휠씬 좋다.

보통의 try-finally에서처럼 try-with-resources에서도 catch절을 쓸 수 있다. catch절 덕분에 try 문을 더 중첩하지 않고도 다수의 예외를 처리할 수 있다.

``` java
static String firstLineOfFile3(String path, String defaultValue) {  
    try (BufferedReader br = new BufferedReader(new FileReader(path))) {  
        return br.readLine();  
    } catch (IOException e) {  
        return defaultValue;  
    }  
}
  
static void copy2(String src, String dst) throws IOException {  
    try (FileInputStream in = new FileInputStream(src);  
         FileOutputStream out = new FileOutputStream(dst)) {  
            byte[] buf = new byte[1024];  
            int n;  
            while ((n = in.read(buf)) >= 0) {  
                out.write(buf, 0, n);  
            }  
         }  
}
```

### 핵심 정리
- 꼭 회수해야 하는 자원을 다룰 때는 try-finally 말고, try-with-resources를 사용하자
- 코드는 더 짧고 분명해지고, 만들어지는 예외 정보도 훨씬 유용하다.
- try-finally로 작성하면 실용적이지 못할 만큼 코드가 지저분해지는 경우라도, try-with-resources로는 확하소 쉽게 자원을 회수할 수 있다.