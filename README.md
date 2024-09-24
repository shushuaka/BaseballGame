Main 클래스
프로그램의 시작점으로, 사용자 입력을 받아 메뉴를 관리하고 게임을 실행합니다.

주요 메서드
main: 프로그램 실행 및 메인 루프 관리

변수
Scanner scanner: 사용자 입력을 받기 위한 Scanner 객체
List<Integer> gameRecord: 각 게임의 시도 횟수를 저장하는 리스트
int digitLength: 현재 설정된 자리수 (기본값: 3)

BaseballGame 클래스
게임의 핵심 로직을 담당하는 클래스입니다.

주요 메서드
BaseballGame(int digitLength): 자리수를 설정하는 생성자
generateAnswer: 컴퓨터의 정답 숫자를 랜덤으로 생성
play: 게임을 진행하고 시도 횟수를 반환
validateInput: 사용자 입력의 유효성을 검사
countStrike: 스트라이크 개수를 계산
countBall: 볼 개수를 계산
containsDigit: 정답 숫자에 특정 숫자가 포함되어 있는지 확인
displayHint: 스트라이크와 볼 개수를 출력

변수
int[] answer: 컴퓨터가 생성한 정답 숫자 배열
int attempts: 현재 게임의 시도 횟수
int digitLength: 자리수
