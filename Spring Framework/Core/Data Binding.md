# Data Binding 
사용자의 요청 데이터를 특정 객체에 저장해서 프로그램 Request에 담아주는 것을 의미

## 1. Converter<S, T> interface
> S(Source)라는 타입을 받아서 T<Target>이라는 타입으로 변환해주는 interface - [Reference](https://docs.spring.io/spring-framework/docs/current/reference/html/core.html#core-convert)
```java
package org.springframework.core.convert.converter;

public interface Converter<S, T> {
    
    T convert(S source);
}
```
<br>

### 사용 예시
```java
@Component
public class UserConverter implements Converter<String, User> {
    @Override
    public User convert(String source) {
        return objectmapper.readValue(source, User.class);
    }
}
```
ObjectMapper는 Jackson 라이브러리를 사용해서 Json타입의 데이터를 특정 객체에 담아줌

<br>

```java
// 요청
// GET /user-info
// x-auth-user : {"id": 1, "name": "james"}

// User 클래스
public class User {
    private int id;
    private String name;
}

// 컨트롤러
@GetMapping("/user-info")
public UserInfoResponse getUserInfo(@RequestHeader("x-auth-user") User user) {
    // ...
}
```

### 동작 원리
1. Converter 인터페이스를 구현하고 Bean으로 등록(@Component)
2. 스프링 안의 ConversionService라는 서비스에서 Converter 구현체 Bean들을 Convereter 리스트에 등록
3. Source Class Type -> Target Class Type이 Converter 리스트에 등록된 형식과 일치하는 요청일 경우, 해당하는 Converter가 동작

**요청

## 2. Formatter
> 특정 객체와 String 간의 변환을 담당 - [Reference](https://docs.spring.io/spring-framework/docs/current/reference/html/core.html#format)


### 사용 예시
```java
// Date 객체와 String 간의 변환 예시
package org.springframework.format.datetime;

public final class DateFormatter implements Formatter<Date> {
    public String print(Date date, Locale locale) {
        return getDateFormat(locale, format(date));
    }
    
    public Date parse(String formatted, Locale locale) throws ParseException {
        return getDateFormat(locale).parse(formatted);
    }
    
    // getDateFormat() ...
}
```
- `print()` : Date 형식의 데이터를 특정 locale에 맞춘 String으로 변환하여 API 요청에 대한 응답
- `parse()` : API 요청 시 받아온 String으로 된 데이터를 Date 형식으로 변환
- Converter와 마찬가지로 Spring Bean 등록하면 같은 동작 원리를 가짐

<br>

- **Converter는 S 타입에서 T 타입으로 변환 (주로 API 요청 시에 사용)**
- **Formatter는 특정 객체 타입과 String 타입 간의 변환 (주로 API 요청, 응답 둘 다 사용)**