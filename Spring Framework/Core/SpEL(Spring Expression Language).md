# SpEL(Spring Expression Language)
> Expression Language(표현 언어)는 간단한 문법을 통해 필요한 데이터나 설정값을 불러올 수 있게 하는 특별한 형태의 표현식에 가까운 언어(그래프 접근 등) - [Reference](https://docs.spring.io/spring-framework/docs/current/reference/html/core.html#expressions)

**SpEL은 스프링에서 주로 쓰는 언어 형식**

**`@Value("${config.value}")`와 같은 설정값을 주입받는데 많이 사용**

## SpEL의 값 평가(evaluation)
```java
ExpressionParser parser = new SpelExpressionParser();

Expression exp1 = parser.parseExpression("'Hello World'");
String msg1 = (String) exp.getValue();   // "Hello World"

Expression exp2 = parser.parseExpression("`Hello World'.concat('!')");
String msg2 = (String) exp2.getValue(); // "Hello World!"

Expression exp3 = parser.parseExpression("new String(`hello world').toUpperCase()");
String msg3 = exp3.getValue(String.class);  // "HELLO WORLD"
```
- SpelParser는 "" 안의 문자열을 평가해서 결과값을 생성
- 'Hello World'는 문자열 상수가 되며, `concat()` 메서드 등 메서드를 호출 가능
- String 객체를 new로 생성해서 사용 가능

> 굳이 힘들게 표현식을 사용해서 Hello World를 가공할 필요는 없지만, 예시를 들기 위해 작성한 코드

## Bean의 Property를 설정
> 실무에서는 주로 Bean의 Property를 설정하기 위한 방법으로 많이 사용
- 기본적으로 `#{<expression string>}` 방식으로 Property를 설정
- application.properties 또는 application.yml의 값을 불러올 때는 `${<property name>}` 방식으로 사용

```java
@Component
public class SimpleComponent {
    @Value("#{ 1+1 }")
    int two; // 2

    @Value("#{ 2 eq 2 }")
    boolean isTrue; // true

    @Value("${ server.hostname }")
    String hostName; // www.server.com

    @Value("#{ ${ server.hostname } eq 'www.server.com'}")
    boolean isHostSame; // true
}
```
**운영 환경과 테스트 환경을 분리할 때도 위 방식처럼 많이 사용**
