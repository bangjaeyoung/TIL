# CsrfFilter
**Csrf 토큰을 이용해 Csrf 공격을 방어하는 Filter**
  > Csrf 공격 : 위조된 사이트를 통해 사용자가 의도하지 않은 작업을 수행하도록 유도하는 공격

정상적인 페이지는 Csrf 토큰이 존재하고, 위조된 페이지는 Csrf 토큰이 없거나 잘못된 토큰이 존재함
  > Tymeleaf에서는 페이지를 만들 때, `<input type="hidden">`으로 자동으로 Csrf 토큰을 넣어줌

Csrf Filter는 자동으로 활성화되어 있는 Filter이지만, 명시적으로 **`http.csrf();`** 코드를 추가해주는 게 좋음
  > 비활성화 하려면 **`http.csrf().disable();`**

<br>

# RememberMeAuthenticationFilter
**일반적인 세션보다 더 오랫동안 로그인 사실을 기억할 수 있도록 해주는 Filter**

세션의 만료 시간은 기본 설정이 30분이지만, RememberMeAuthenticationFilter는 기본 설정이 **2주**

이 Filter가 적용된 상태로 애플리케이션을 실행시키고 로그인을 하면, 다음과 같이 2개의 쿠키가 생긴 것을 볼 수 있음

<br>

<img src = https://github.com/bangjaeyoung/TIL/blob/main/img/Spring%20Framework/Security/RememberMeAuthenticationFilter.png>
 
> 기본적으로는 JESSIONID 쿠키만 생김

<br>

웹 서비스 페이지를 껐다 켜도 remember-me 쿠키가 장시간 남아 있고, 이 쿠키로 로그인이 유지됨
> JESSIONID 쿠키는 페이지를 껐다 키면 사라지고, 단시간만 유지됨

만약 애플리케이션 자체가 종료될 경우에는 페이지(클라이언트) 쪽에는 remember-me 쿠키가 남아 있지만, 서버 쪽에는 없으므로 로그아웃됨

이 Filter를 활성화 하려면 **`http.rememberMe();`**
> 비활성화 하려면 **`http.rememberMe().disable();`**

<br>

# AnonymousAuthenticationFilter
**인증이 안된 유저가 요청을 하면, Anonymous(익명) 유저를 만들어서 Authentication에 넣어주는 Filter**
> 인증이 되지 않았을 때, null 값을 넣어주는 게 아닌 기본적인 Authentication을 생성해주는 거라 보면 됨

Anonymous 유저인지 정상적으로 인증된 유저인지는 다른 Filter에서 분기 처리 가능

주로, 회원가입 페이지에는 인증되지 않은 유저도 접근할 수 있기 때문에, Anonymous인 Authentication이 담겨있는 걸 볼 수 있음
> 회원가입하는 controller단에 `SecurityContext securityContext = SecurityContextHolder.getContext();`를 통해 SecurityContext를 불러오고 디버그 포인트를 찍어 디버깅 모드로 실행해보면 확인 가능
<img src=https://github.com/bangjaeyoung/TIL/blob/main/img/Spring%20Framework/Security/AnonymousAuthenticationFilter.png>

기본적으로 만들어지는 익명 객체는 다음과 같은 값을 가짐
- principal : anonymousUser
- authorities : ROLE_ANONYMOUS

***항상 인증 객체가 있는 것이기 때문에, Role 검사를 통해 익명 객체인지는 확인해야 함***

기본적으로 지원하고, 명시적으로 기재 시에 `http.anonymous();`
> 커스텀한 principal 지정을 원할 때, `http.anonymous().principal("custom");`

<br>

# FilterSecurityInterceptor
> 이름이 Interceptor로 끝나지만, Filter 종류 중 하나

이 Filter에서는 앞서 설명한 Filter(SecurityContextPersistenceFilter, UsernamePasswordAuthenticationFilter, AnonymousAuthenticationFilter)들에서 넘어온 SecurityContext의 authentication 내용을 기반으로 **최종 인가 판단을 내림**

그렇기 때문에, 대부분의 경우 Filter들 중 뒤쪽에 위치함

### 동작 방식
- Authentication(인증)을 가져옴
- 인증에 문제가 있는지 판단
  - 인증에 문제가 있다면, **AuthenticationException** 발생
  - 인증에 문제가 없다면, 해당 인증으로 인가를 판단
- 인가 승인 여부 판단
  - 인가가 거절된다면, **AccessDeniedException** 발생
  - 인가가 승인된다면, 정상적으로 Filter 종료

### 코드
1. ``FilterSecurityInterceptor.doFilter()`` ->
2. ``AbstractSecurityInterceptor.beforeInvocation()`` ->
3. ``AbstractSecurityInterceptor.authenticateIfRequired()`` ->
  > 인증에 문제가 발생하면 **AuthenticationException**
4. ``AbstractSecurityInterceptor.attemptAuthorization()``
  > 인가에 문제가 발생하면 **AccessDeniedException**
