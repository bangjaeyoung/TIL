# AOP(Aspect Oriented Programming) - 관점 지향 프로그래밍
> 공통적인 처리가 필요할 때 필요한 것이 바로 AOP
> interceptor나 filter보다 조금 더 똑똑한 방법 - [Reference](https://docs.spring.io/spring-framework/docs/current/reference/html/core.html#aop)
- 로깅(Logging)
- 트랜잭선(Transaction)
- 인증(Authentication)
- 락(Lock)

**코드 분석 측면에서 안좋을 수 있기 때문에, 알맞은 케이스에 적절하게 적용해야 함**



## AOP 기본 개념

### Aspect
특정 관심사에 대해 모듈화한 것

### Advice
로깅, 트랜잭션, 인증 등 적용하는 실제 기능

### Join Point
Advice를 적용할 수 있는 포인트(모든 곳에 적용할 순 없음)

### Pointcut
Join Point를 뽑을 조건식

### Target Object
Advice가 적용될 대상

### AOP Proxy
Target Object에 Advice를 적용할 때 하는 작업

주로 **CGLIB(Code Generation Library, 실행 중에 실시간으로 코드를 생성하는 라이브러리) 프록시**를 사용해서 Target Object의 전이나 후에 적용하는 게 아닌, 실시간으로 프록싱 처리하여 Advice를 적용

### Weaving
Advice를 비즈니스 로직 코드에 삽입하는 것



## AspectJ
> AOP를 제대로 사용하기 위한 라이브러리로, **Spring Boot에서 기본적으로 지원하는 라이브러리**

### Aspect 생성
```java
import org.aspectj.lang.annotation.Aspect;

@Aspect
@Component      // 스프링 Bean으로 등록해서 사용
public class CustomAspect {
    // ...
}
```

### Pointcut 생성
```java
import org.aspectj.lang.annotation.Aspect;

@Aspect
@Component
public class CustomAspect {
    
    @Pointcut("execution(* transfer(..))")
    private void doTransfer() {
        // ...
    }
}
```
- @Pointcut 뒤의 조건식을 **포인트 컷 표현식**이라고 말함
- *Ref - <https://www.baeldung.com/spring-aop-pointcut-tutorial>*

### Pointcut 결합
```java
import org.aspectj.lang.annotation.Aspect;

@Aspect
@Component
public class CustomAspect {

    // public 메서드 대상
    @Pointcut("execution(public * *(..))") 
    private void publicOperation() {
        // ...
    }

    // 특정 패키지 대상
    @Pointcut("within(com.xxx.yyy.zzz..*)") 
    private void doTransfer() {
        // ...
    }

    // 위 두 조건을 결합
    @Pointcut("publicOperation() && doTransfer()") 
    private void operationTransfer() {
        // ...
    }   
```

## Advice
> 포인트 컷 표현식을 통해 포인트 컷 전/후/주변에서 실행될 이벤트 정의

### 포인트 컷 전
```java
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
public class BeforeAdvice {

    // 미리 정의된 doTransfer()라는 포인트 컷 전에 doSomething()이 실행됨
    @Before("com.xxx.yyy.PointcutExample.doTransfer()") 
    private void doSomething() {
        // ...
    }
}
```

### 포인트 컷 후
```java
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.AfterReturning;

@Aspect
public class AfterReturningAdvice {

    // 미리 정의된 doTransfer()라는 포인트 컷에서 return이 발생된 후에 doSomething()이 실행됨
    @AfterReturning("com.xxx.yyy.PointcutExample.doTransfer()") 
    private void doSomething() {
        // ...
    }
}
```
ex) 로깅, 알림

### 포인트 컷 주변(전/후)
```java
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.ProceedingJoinPoint;

@Aspect
public class AroundAdvice {
    
    @Around("com.xxx.yyy.PointcutExample.doTransfer()") 
    private Object checkTime(ProceedingJoinPoint pjp) throws Throwable {
        // start stopwatch
        Object retVal = pjp.proceed();
        
        // stop stopwatch
        return retVal;
    }
}
```
ex) 특정 메서드 전/후로 시간을 체크, Lock