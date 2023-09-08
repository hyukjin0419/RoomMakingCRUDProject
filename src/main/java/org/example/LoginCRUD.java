package org.example;

import java.io.*;
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
        String name = setName();
        String birth = setBirth();
        String id = setID();
        String pw = setPW();

        return new Login(name, birth, id, pw);
    }


    public String setName(){
        String name = null;
        System.out.print("=> 이름을 입력하세요: ");
        name = s.nextLine();

        return name;
    }
    public String setBirth(){
        String birth = null;
        System.out.print("=> 생년월일 여섯자리를 입력하세요: ");
        while(true){
            birth = s.nextLine();
            if(isDigitRight(6,birth)){
                return birth;
            }
            System.out.print("=> 입력이 잘못 되었습니다. 생년월일 여섯자리를 입력하세요: ");
        }

    }

    public boolean isDigitRight(int a, String str){
        return str.length() == a;
    }

    public String setID(){
        String id = null;
        while(true) {
            System.out.print("=> 회원가입할 ID를 입력하세요: ");
            id = s.nextLine();
            if(!isDuplicated(id,list))
                return id;
            else
                System.out.println("중복되는 ID입니다. 다른 ID를 입력해주세요.");
        }
    }

    public String setPW(){
        String pw;
        String pwCheck;
        while(true) {
            System.out.print("=> 비밀번호를 입력하세요: ");
            pw = s.nextLine();
            System.out.print("=> 비밀번호를 다시 입력하세요: ");
            pwCheck = s.nextLine();
            if (pwdUnity(pw,pwCheck)) return pw;
            System.out.println("비밀번호가 일치하지 않습니다. 다시 입력하세요. ");
        }
    }

    public boolean pwdUnity(String pw1, String pw2){
        if (pw1.equals(pw2)){
            return true;
        } else{
            return false;
        }
    }

    public boolean isDuplicated(String id, ArrayList<Login> list){
        for (Login one : list) {
            if (one.getId().equals(id)) {
                return true;
            }
        }
        return false;
    }

    public Login findID(String id, ArrayList<Login> list){
        for (Login one : list) {
            if (one.getId().equals(id)) {
                return one;
            }
        }
        return null;
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
                String name = data[0];
                String birth = data[1];
                String id = data[2];
                String pw = data[3];
                list.add(new Login(name, birth, id, pw));
                count++;
            }
            br.close();
            System.out.println("===>" + count + "명 member의 정보를 로드하였습니다.");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveFile(){
        try {
            PrintWriter pr = new PrintWriter(new FileWriter(fname));
            for(Login one : list) {
                pr.write(one.toFileString() + "\n");
            }
            pr.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void changePassword() {
        System.out.print("비밀번호를 변경할 아이디를 입력하세요: ");
        String id = s.nextLine();
        Login one = findID(id,list);
        if (one == null)
            System.out.println("일치하는 아이디가 없습니다. 다시 시도해주세요. ");
        else
            one.setPw(setPW());
    }
}
