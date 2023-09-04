package org.example;

import java.util.ArrayList;
import java.util.Scanner;

public class RoomCRUD implements ICRUD{
    ArrayList<Room> list;
    Scanner s;

    RoomCRUD(Scanner s){
        list = new ArrayList<>();
        this.s = new Scanner(System.in);
    }
    @Override
    public Object add() {
        return null;
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
}
