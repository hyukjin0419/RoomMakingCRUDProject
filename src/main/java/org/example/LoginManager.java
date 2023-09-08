package org.example;

import java.util.InputMismatchException;
import java.util.Scanner;

public class LoginManager {
    Scanner s = new Scanner(System.in);
    LoginCRUD loginCRUD;

    LoginManager(){
        loginCRUD = new LoginCRUD(s);
    }

    //로그인 한 후 진행되려면 어떻게 시스템이 구성되어야 하는지?
    public int selectMenu(){
        System.out.print("*** 환영합니다 ***\n"
            + "1. 로그인하기\n"
            + "2. 회원가입하기\n"
            + "3. 아이디 찾기\n"
            + "4. 비밀번호 찾기\n"
            + "0. 나가기\n"
            + "=> 원하는 메뉴를 선택해주세요: ");
        return s.nextInt();
    }

    public void start(){
        loginCRUD.loadMember();
        while(true){
            try {
                int menu = selectMenu();
                if(menu == 0) {
                    System.out.println("프로그램 종료");
                    System.exit(0);
                } else if(menu == 1){
                    loginCRUD.logIn();
                } else if(menu == 2){
                    loginCRUD.addMember();
                } else if(menu == 3){
                    loginCRUD.findID();
                } else if(menu == 4){
                    loginCRUD.changePassword();
                }

                loginCRUD.saveFile();
            } catch (InputMismatchException e){
                //UI로 구현하면 이게 필요 없을 수 있기 때문에 이런식으로만 완성
                System.out.println("잘못된 입력입니다. 프로그램이 종료 됩니다");
                System.exit(0);
            }

        }
    }
}
