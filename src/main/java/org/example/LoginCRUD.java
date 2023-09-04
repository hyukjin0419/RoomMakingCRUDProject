package org.example;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class LoginCRUD implements ICRUD{
    ArrayList<Login> list;
    Scanner s;
    final String fname = "login_data.txt";

    LoginCRUD(Scanner s){
        list = new ArrayList<>();
        this.s = new Scanner(System.in);
    }

    //회원가입 (add)
    //회원틸퇴 (delete)
    //비밀번호 수정 (update)
    //회원정보 불러오기 (read)
    @Override
    public Object add() {
        String id = null;
        String pw = null;
        while(true) {
            System.out.print("=> 회원가입할 ID를 입력하세요: ");
            id = s.nextLine();
            for (Login one : list) {
                if (one.getId().equals(id)) {
                    System.out.println("중복되는 ID입니다. 다른 ID를 입력해주세요.");
                    continue;
                }
            }
            System.out.print("=> 비밀번호를 입력하세요: ");
            pw = s.nextLine();
            if (id != null && pw != null)
                break;
        }
        return new Login(id, pw);
    }

    public void addMember(){
        Login one = (Login) add();
        list.add(one);
        System.out.println("회원가입이 완료되었습니다.");
    }

    @Override
    public int update(Object object) {
        return 0;
    }

    @Override
    public int delete(Object object) {
        return 0;
    }

    @Override
    public void selectOne(int id) {

    }

    public void loadMember(){
        try {
            BufferedReader br = new BufferedReader(new FileReader(fname));
            String line;
            int count = 0;

            while(true){
                line = br.readLine();
                if(line == null) break;

                String data[] = line.split("\\|");
                String id = data[0];
                String pw = data[1];
                list.add(new Login(id, pw));
            }
            br.close();
            System.out.println("===>" + count + "명 member의 정보를 로드하였습니다.");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
