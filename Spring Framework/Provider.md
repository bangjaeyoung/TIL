**Dependency Lookup(DL): 의존관계 조회**

특정 빈을 조회하려고 할 때, ApplicationContext를 주입받아서 빈을 조회하기에는 복잡하다.   
이때 사용(DL)할 수 있는 것이 Provider이다.

</br>

## ObjectFactory, ObjectProvider

```java
@Autowired
private ObjectProvider<HelloBean> helloBeanProvider;

@Autowired
private ObjectFactory<HelloBean> provider;

public void lookupBean() {
  HelloBean helloBean1 = helloBeanProvider.getObject();
  HelloBean helloBean2 = provider.getObject();
}
```

두 인터페이스 모두 별도의 라이브러리가 필요없지만 스프링에 의존적이라는 특징이 있다.   
ObjectFactory는 기능이 단순하고, ObjectProvider는 ObjectFactory를 상속하면서 옵션, 스트림 처리 등 편의 기능이 추가적으로 있다.

</br>

## JSR-330 Provider

```java
@Autowired
private Provider<HelloBean> helloBeanProvider;

public void lookupBean() {
  HelloBean helloBean1 = helloBeanProvider.get();
}
```

get() 메서드가 딱 하나 존재하고, 별도의 라이브러리가 필요하다.   
`jakarta.inject:jakarta.inject-api:2.0.1`   

</br>

자바 표준이므로 스프링이 아닌 다른 컨테이너에서도 사용할 수 있다.


</br>

스프링이 제공하는 DL 방법 중 하나인 `@Lookup`이 있지만, 위의 방법들로 충분하다.   
ObjectProvider가 DL을 위한 편의 기능을 많이 제공하기도 하고, 별도 라이브러리가 필요 없기 때문에 이걸 사용하면 된다.   
하지만, 스프링이 아닌 다른 컨테이너에서 DL이 필요한 경우, 라이브러리를 추가하고 JSR-330 Provider를 사용하자.

자바 표준과 스프링이 제공하는 기능이 겹칠 때가 많기에, 어느 것을 선택해 사용할지 고민이 된다면   
**스프링이 제공하는 기능을 우선 사용하고, 다른 컨테이너에서 사용해야 한다면 자바 표준을 이용하자!**   
(대부분 스프링이 더 다양하고 편리한 기능들을 제공)
