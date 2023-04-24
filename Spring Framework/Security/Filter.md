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
