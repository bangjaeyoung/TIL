# 스프링에서의 Validation
> 유효성 검증, http request에서 잘못된 부분을 체크

## 데이터에서의 Validation
- 필수 데이터가 존재하는지
- 문자열의 길이, 숫자형 데이터 특정 범위에 해당하는지
- 전화번호, email 등 특정 형식에 부합한 형태인지

## 비즈니스에서의 Validation
- 특정 로직 전에 수행되어야 하는 로직이 제대로 수행되었는지
ex) 상품 배달 요청 전에 결제가 된 상태인지 확인

## 1. Java Bean Validation
> 가장 많이 활용, JavaBean 내에 annotation으로 검증 방법 명시
```java
public class UserInfoRequest {
    
    @NotBlank(message = "이름을 입력해주세요")
    @Size(max = 10, message = "이름의 최대 길이는 10자입니다.")
    private String name;
    
    @Min(0, "나이는 0보다 커야 합니다.")
    private int age;
    
    @Email("올바른 이메일 형식이 아닙니다.")
    private String email;
    
    // getters and setters
}
```

<br>

위의 작업만으로는 검증 처리가 되지 않고, 아래와 같이 **@Valid** 어노테이션을 추가해주어야 함

```java
@PostMapping()
public UserInfoResponse createUser(@Valid @RequestBody final UserInfoRequest request) {
    // user creation logics
} 
```
만약 검증에 실패한다면, **MethodArgumentNotValidException** 발생

## 2. Spring validator 인터페이스 구현을 통한 Validation
> *Ref - https://docs.spring.io/spring-framework/docs/3.0.0.M3/reference/html/ch06s02.html*

## Validation 주의 및 권장 사항
- JavaBean Validation 방식은 검증 방식이 한눈에 들어오지만, Spring validator 인터페이스 구현을 통한 Validation 방식은 해당 코드가 어디에 위치하는지 찾기가 쉽지 않음(즉, 유지보수성이 떨어짐)
- **Validation은 가능한 로직 초기에 수행하도록 하고, 실패 시에는 exception을 던지도록 하는 것이 편함**
- 요청 dto에서 Java Bean Validation 방식으로 데이터 1차 검증을 하고, 비즈니스 로직에서 Custom Exception을 통해 예외를 던져서 ErrorCode, ErrorMessage를 응답하도록 하는 것이 편함
- Spring validator 인터페이스 구현을 통한 Validation 방식이 Java Bean Validation 방식보다는 더 구체적인 검증 로직을 짤 수 있긴 함
- **결국, 팀 내에서 사용하는 검증 패턴을 따르는 것이 좋음**