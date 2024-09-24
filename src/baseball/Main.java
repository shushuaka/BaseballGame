package baseball;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); //사용자 입력을 받기 위한 Scanner 객체 생성
        List<Integer> gameRecord = new ArrayList<>(); //게임 기록을 저장할 리스트
        int digitLength = 3; //기본 자리수는 3자리로 설정

        //2-2. 출력 개선: 프로그램을 시작할 때 안내문구 표시
        while (true) {
            //3-2. 출력 개선: 실행 및 정답을 맞힌 경우, 표시되는 안내문구 선택지 개선
            System.out.println("환영합니다! 원하시는 번호를 입력해주세요.");
            System.out.println("0. 자리수 설정 1. 게임 시작하기 2. 게임 기록 보기 3. 종료하기");
            String choice = scanner.nextLine();

            //4-1. 게임 난이도 조절
            if (choice.equals("0")) {
                // 자리수 설정을 위한 내부 반복문
                while (true) {
                    System.out.println("설정하고자 하는 자리수를 입력하세요. (3, 4, 5)");
                    String lengthInput = scanner.nextLine();

                    if (lengthInput.equals("3") || lengthInput.equals("4") || lengthInput.equals("5")) {
                        digitLength = Integer.parseInt(lengthInput);
                        System.out.println(digitLength + "자리수 난이도로 설정되었습니다.");

                        //자리수를 설정하면 자동으로 게임 시작
                        BaseballGame game = new BaseballGame(digitLength); // 설정된 자리수로 게임 객체 생성
                        int attempts = game.play();
                        gameRecord.add(attempts);
                        break; //올바른 입력을 받으면 반복문 탈출
                    } else {
                        System.out.println("올바른 자리수를 입력해주세요.");
                    }
                }
            }
                else if (choice.equals("1")) {
                //BaseballGame 객체 생성 & 게임 시작
                BaseballGame game = new BaseballGame(); //게임 객체 생성
                int attempts = game.play(); //1-4. 게임 이어서하기 //게임 시작 및 시도 횟수 반환
                gameRecord.add(attempts); //게임 기록에 시도 횟수 추가
            } else if (choice.equals("2")) {
                //3-1. 게임 기록 통계 출력
                if (gameRecord.isEmpty()) {
                    System.out.println("게임 기록이 없습니다.");
                } else {
                    System.out.println("게임 기록 보기");
                    for (int i = 0; i < gameRecord.size(); i++) {
                        System.out.println((i + 1) + "번째 게임: 시도 횟수 - " + gameRecord.get(i));
                    }
                }
            } else if (choice.equals("3")) {
                //게임 종료
                System.out.println("숫자 야구 게임을 종료합니다.");
                break;
            } else {
                //올바르지 않은 입력값
                System.out.println("올바른 숫자를 입력해주세요.");
            }
        }
    }
}