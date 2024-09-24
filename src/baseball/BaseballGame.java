package baseball;

import java.util.*;

public class BaseballGame {
    private int[] answer; //정답 숫자를 저장할 배열
    private int attempts; // 시도 횟수
    private int digitLength; //자리수

    //4-1. 자리수 설정을 위한 생성자
    /*@param digitLength 설정된 자리수*/
    //객체 생성 시 정답을 생성합니다.
    public BaseballGame(int digitLenth) {
        this.digitLength = digitLenth; //자리수 설정
        generateAnswer(); //1-1. 정답 숫자 생성하기
        attempts = 0; //시도 횟수 초기화
    }

    public BaseballGame() {
        this(3); //기본 자리수는 3
    }

    /* Set 인터페이스 HashSet 클래스 사용할 경우
    //정답 숫자를 생성하는 메서드
    private void generateAnswer() {
        Random random = new Random(); //랜덤 숫자 생성을 위한 객체
        Set<Integer> numberSet = new HashSet<>(); //중복을 방지하기 위한 Set

        //중복되지 않는 숫자가 3개가 될 때까지 반복
        while (numberSet.size() < 3) {
            int num = random.nextInt(9) + 1; //1부터 9까지의 랜덤 숫자 생성
            numberSet.add(num); //Set은 중복을 허용하지 않으므로 자동으로 중복이 제거됨
        }

        //Set을 배열로 변환하여 정답으로 저장
        answer = new int[3];
        int index = 0;
        for (int num : numberSet) {
            answer[index++] = num;
        }
    }*/

    /*1-1. 정답 숫자 생성하기
         Collections.shuffle을 사용하여 중복되지 않는 숫자 생성*/
    private void generateAnswer() {
        //1부터 9까지의 숫자를 리스트에 추가
        List<Integer> numbers = new ArrayList<>();
        for (int i = 1; i <= 9; i++) {
            numbers.add(i);
        }

        //리스트를 무작위로 섞음
        Collections.shuffle(numbers);

        //섞인 리스트에서 앞의 digitLength개의 숫자를 선택하여 정답으로 설정
        answer = new int[digitLength];
        for (int i = 0; i < digitLength; i++) {
            answer[i] = numbers.get(i);
        }
    }

    //1-2. 정답을 맞추기 위해 숫자를 입력하기
    public int play() { //반환 타입을 void에서 int로 변경
        //자리수에 따라 로직이 동적으로 작동
        Scanner scanner = new Scanner(System.in); //사용자 입력을 받기 위한 Scanner 객체 생성
        System.out.println("게임을 시작합니다.");

        while (true) {
            // 1. 사용자로부터 입력값을 받음
            System.out.println("숫자를 입력하세요.");
            String input = scanner.nextLine();

            //2. 올바른 입력값을 받았는지 검증
            if (!validateInput(input)) {
                System.out.println("올바르지 않은 입력값입니다.");
                continue; //입력이 올바르지 않으면 다시 입력받음
            }

            attempts++; //시도 횟수 증가

            //1-3. 결과값 출력 및 게임 로직 적용하기
            //3. 게임 진행횟수 증가
            //4. 스트라이크 개수 계산
            int strike = countStrike(input);

            //6. 볼 개수 계산
            int ball = countBall(input);

            //7. 힌트 출력
            displayHint(strike, ball);

            //5. 정답여부 확인, 만약 정답이면 break를 이용해 반복문 탈출
            if (strike == digitLength) {
                System.out.println("정답입니다!");
                break; // 반복문을 탈출하여 다음 단계로 넘어갑니다.
            }
        }
        return attempts; //게임 진행 횟수 반환
    }

    //1-3. 스트라이크 개수 계산
    /* @param input 사용자 입력값
    @return 스트라이크 개수*/
    private int countStrike(String input) {
        int strike = 0; //스트라이크 개수 초기화
        for (int i = 0; i < digitLength; i++) {
            int inputDigit = Character.getNumericValue(input.charAt(i)); //입력값의 각 자리 숫자
            if (inputDigit == answer[i]) {
                strike++; //위치와 숫자가 모두 일치하면 스트라이크증가
            }
        }
        return strike;
    }

    //1-3. 볼 개수 계산
    /*param input 사용자 입력값
    @return 볼개수*/
    private int countBall(String input) {
        int ball = 0; //볼 개수 초기화
        for (int i = 0; i < digitLength; i++) {
            int inputDigit = Character.getNumericValue(input.charAt(i));
            //숫자는 일치하지만 위치가 다르면 볼 증가
            if (inputDigit != answer[i] && containsDigit(inputDigit)) {
                ball++;
            }
        }
        return ball;
    }

    //정답에 해당 숫자가 포함되어 있는지 확인하는 메서드
    /*@param digit 확인할 숫자
    @return 포함되어 있으면 true, 아니면 false*/
    private boolean containsDigit(int digit) {
        for (int num : answer) {
            if (num == digit) {
                return true;
            }
        }
        return false;
    }

    //1-3. 힌트를 출력하는 메서드
    /*@param strike 스트라이크 개수
    @param ball 볼 개수*/
    private void displayHint(int strike, int ball) {
        if (strike == 0 && ball == 0) {
            System.out.println("아웃"); //스트라이크와 볼이 모두 0이면 "아웃" 출력
        } else {
            String result = "";
            if (strike > 0) {
                result += strike + "스트라이크 ";
            }
            if (ball > 0) {
                result += ball + "볼";
            }
            System.out.println(result.trim()); //힌트 출력
        }
    }

    //2-1. 입력값이 유효한지 검사하기
   /* 입력값의 유효성을 검사하는 메서드
    @parm input 사용자 입력값
    @return 입력값이 유효하면 true, 그렇지 않으면 false*/
    protected boolean validateInput(String input) {
        //입력값이 null이거나 길이가 digitLength이 아니면 유효하지 않음
        if (input == null || input.length() != digitLength) {
            return false;
        }

        List<Character> charList = new ArrayList<>(); //중복 검사를 위한 리스트
        for (char c : input.toCharArray()) {
            //숫자가 아닌 문자가 포함되어 있으면 유효하지 않음
            if (!Character.isDigit(c)) {
                return false;
            }
            //'0'은 사용 불가
            if (c == '0') {
                return false;
            }
            //중복된 숫자가 있는지 확인
            if (charList.contains(c)) {
                return false; //리스트에 이미 존재하면 중복된 숫자
            }
            charList.add(c); //리스트에 문자 추가
        }
        return true; //모든 조건을 통과하면 유효한 입력
    }

}

