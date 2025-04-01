[Docker Compose]

- 여러 컨테이너를 하나의 묶음으로 관리할 수 있는 툴
- 여러 컨테이너를 하나의 환경에서 실행하고 관리 가능
- 복잡한 명령어를 간소화할 수 있음 `docker compose up`


```shell
# compose에 정의된 컨테이너들 실행(포그라운드)
docker compose up


# compose에 정의된 컨테이너들 실행(백그라운드)
docker compose up -d


# compose에 정의된 컨테이너 실행 목록 조회
docker compose ps


# compose에 정의된 컨테이너 실행, 미실행 목록 조회
docker compose ps -a


# compose에 정의된 컨테이너 종료, 모두 삭제
docker compose down


# compose에 정의된 컨테이너에서 발생한 로그들 확인
docker compose logs


# 이미지를 다시 빌드해서 실행해야될 때
docker compose up --build


# 이미지를 최신 버전으로 다운받거나 업데이트하는 용도
docker compose pull
```

<br/>

2025.04.01.