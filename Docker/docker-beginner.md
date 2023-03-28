## 컨테이너 기술이란?
애플리케이션이 하나의 컴퓨팅 환경에서 다른 컴퓨팅 환경으로 빠르고 안정적으로 실행되도록 코드와 모든 종속성을 패키징하는 소프트웨어 패키지   
[- Ref](https://www.docker.com/resources/what-container/)

## 도커란?
개발자가 컨테이너에 소프트웨어를 제공할 수 있는 PaaS 도구로, 가상 컨테이너를 제공하여 원하는 형태로 환경을 세팅할 수 있도록 도와주는 플랫폼   
[- Ref](https://www.baeldung.com/ops/docker-guide)

## 도커 파일, 도커 이미지, 도커 컨테이너의 개념은 무엇이고, 서로 어떤 관계?
- 도커 파일 : 명령 또는 절차 모음을 포함하는 간단한 텍스트 파일
- 도커 이미지 : 도커 프레임워크에서 작동하도록 생성된 컨테이너
- 도커 컨테이너 : 애플리케이션을 실행하는 데 사용되는 소형 가상화 런타임 환경

도커 이미지를 생성하기 위해 도커 파일을 만들고, 도커 컨테이너는 도커 이미지를 실행한 상태이다.   
[- Ref](https://cto.ai/blog/docker-image-vs-container-vs-dockerfile/)

## 도커 설치 및 "Hello, World" 출력
1. 해당 [웹사이트](https://www.docker.com/get-started/)에서 설치 파일 다운
2. 터미널(본인 우분투) 실행 후, 도커 파일을 생성하는 명령어 입력   
```shell
nano Dockerfile
```
3. "Hello, World"를 출력하기 위한 도커 파일 내용 작성   
```Dockerfile
FROM alpine:latest
CMD ["echo", "Hello, World"]
```
4. 도커 파일로 도커 이미지 빌드
```shell
docker build -t hello-world .
```
(위 명령어의 의미는 "현재 디렉토리에서 `Dockerfile`을 읽어 도커 이미지를 만들고, 해당 이미지에 `hello-world`라는 `tag` 를 붙혀라" 입니다.)

5. 빌드한 도커 이미지 실행
```shell
docker run hello-world
```
이 명령어는 hello-world라는 이름의 도커 이미지를 실행시켜 "Hello, World"를 출력합니다.