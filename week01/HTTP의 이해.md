# 학습키워드
-------------
- HTTP(Hypertext Transfer Protocol)
- HTTP와 HTTPS의 차이(TLS)
- 클라이언트-서버 모델
- stateless와 stateful
- HTTP Cookie와 HTTP Session
- HTTP 메시지 구조
    - HTTP 요청(Request)와 응답(Response)
        - multipart/form-data
    - HTTP 요청 메서드(HTTP request methods)
        - 멱등성
    - HTTP 응답 상태 코드(HTTP response status code)
        - 리다이렉션

# 주요내용 정리
-------------
## HTTP 기초
-------------
- HTTP 통신은 OSI 7계층 중 7계층에서 동작한다.
    - 2계층 - 데이터 링크 계층 ⇒ MAC address
    - 3계층 - 네트워크 계층 ⇒ IP address
    - 4계층 - 전송 계층 → TCP, UDP ⇒ Port number
    - 7계층 - 응용 계층 → HTTP 등 
        - (HTTPS를 위한 TLS 같은 보안 계층이 먼저 들어갈 수도 있다.)
    
   
   
## TCP/IP 통신
-------------
### TCP와 UDP   
전송 계층의 대표적인 프로토콜   
- TCP: 연결이 필요함. 전달 및 순서 보장. (전화)
- UDP: 연결하지 않고 데이터를 보냄. 전달 및 순서를 보장하지 않음. (편지)

### Socket
소켓은 하나의 엔드포인트이다. (소켓이라는 작은 구멍을 통해서 통신을 한다고 생각된다.)   

### TCP 통신순서
1. 서버는 접속 요청을 받기 위한 소켓을 연다. → Listen
2. 클라이언트는 소켓을 만들고, 서버에 접속을 요청한다. → Connect
3. 서버는 접속 요청을 받아서 클라이언트와 통신할 소켓을 따로 만든다. → Accept
4. 소켓을 통해 서로 데이터를 주고 받는다. → Send & Receive ⇒ 반복!
5. 통신을 마치면 소켓을 닫는다. → Close ⇒ 상대방은 Receive로 인지할 수 있다.   
- 즉, 서버는 접속요청을 받기위한 소켓을 열고, 그 소켓을 통해 클라이언트가 접속요청을 하면, 해당 클라이언트 전용 소켓을 따로 만들어서 그 소켓으로 4번과정을 하는것임.