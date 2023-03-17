# Null Safety
> - Null 안정성을 높이는 방법   
> - NPE(Null Pointer Exception)을 방지하는 방법   
> - IDE에서 경고를 표시하게 함으로써 1차적인 문제를 방지, 에러 위치 확인에 도움
> - boilerplate code 중 하나인 null 체크 코드를 줄일 수 있음 - [Reference](https://docs.spring.io/spring-framework/docs/current/reference/html/core.html#null-safety)

## @NonNull Annotation
> 해당 값이나 함수 등이 Null이 아님을 나타내는 어노테이션

<br>

메서드 파라미터에 붙이면, null 데이터가 들어오는 것을 사전에 방지
```java
import java.org.springframework.lang.NonNull;

public void method(*@NonNull* String request) {
    // ...
}
```

<br>

프로퍼티에 붙이면, null을 저장하는 경우 경고
```java
import java.org.springframework.lang.NonNull;

@NonNull
public String request = "REQUEST";

public void method() {
    
    request = null; // IDE에서 경고창 띄워줌
}
```

<br>

메서드에 붙이면, null을 리턴하는 경우 경고
**@NonNull이 붙은 메서드를 이용하는 곳에서도 Null 데이터가 없는 것을 신뢰하고 사용할 수 있음**
```java
import java.org.springframework.lang.NonNull;

@NonNull
public void method() {
    
    return null; // IDE에서 경고창 띄워줌
}
```

## @Nullable Annotation
- @NonNull과는 반대로 null일 수 있음을 명시
- 해당 어노테이션에 붙은 값을 사용할 때, null check를 항상 수행하라고 IDE에서 경고창을 띄워줌

---
## Spring에서 기본적으로 제공하는 것 이외의 Null 관련 어노테이션
- [JetBrains](https://www.jetbrains.com/help/idea/nullable-and-notnull-annotations.html)
- [Lombok](https://projectlombok.org/features/NonNull)