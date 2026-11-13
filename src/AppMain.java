<<<<<<< HEAD
import db.DBConn;
=======
>>>>>>> ac9e5c6e3c686c0949570119df38c4531d1410cc
import exception.InputValidation;
import exception.MyException;

import java.util.Scanner;

public class AppMain {
    static void main(String[] args) {
<<<<<<< HEAD
        // DB 연결 확인
        DBConn.getConnection();
//        InputValidation validation = new InputValidation();
//        Scanner sc = new Scanner(System.in);
//        String name = sc.next();
//        try {
//            validation.phoneCheck(name);
//
//        } catch (MyException e) {
//            System.out.println(e.getMessage());
//        }
//
//
=======
        InputValidation validation = new InputValidation();
        Scanner sc = new Scanner(System.in);
        String name = sc.next();
        try {
            validation.phoneCheck(name);

        } catch (MyException e) {
            System.out.println(e.getMessage());
        }


>>>>>>> ac9e5c6e3c686c0949570119df38c4531d1410cc

    }
}
