자동 빈 등록(@component): 업무 로직에 적용하면 좋다. 컨트롤러, 핵심 비즈니스 로직, 리포지토리 등   
수동 빈 등록(@configuration + @bean): 기술 지원 로직에 적용하면 좋다. 주로 DB 연결이나 공통 로그 처리 등의 AOP 로직   
-> 애플리케이션 전체에 광범위하게 영향을 미치는 객체는 수동으로 빈 등록해서 설정 정보에 바로 드러나게 하는 것이 유지 보수에 좋다!   

</br>

 그러나, 비즈니스 로직 중에서도 다형성을 적극 활용할 상황이라면 수동 빈 등록이 적절할 수 있다.   
 ```java
@Configuraiton
public class DiscountPolicyConfig {

  @Bean
  public DiscountPolicy rateDiscountPolicy() {
    return new RateDiscountPolicy();
  }

  @Bean
  public DiscountPolicy fixDiscountPolicy() {
    return new FixDiscountPolicy();
  }
}
```
위처럼 수동 빈 등록하면, 한눈에 파악하기 쉽다. (자동 빈 등록 방식은 함께 협업하는 개발 동료가 코드를 파악하기 힘들 수 있음)   
자동 빈 등록 방식을 사용하려면, 특정 패키지에 같이 묶어 두는 게 좋다.   
결국, 코드를 딱 보고 이해가 단번에 되어야 한다는 것이 중요하다!   

</br>

spring, spring boot가 자동으로 등록하는 빈들은 그대로 사용하는 게 좋다. (데이터베이스 연결에 사용하는 DataSource 로직)   
이에 더 커스텀한 설정이 필요하다면, 수동으로 빈 등록하면 된다.   


