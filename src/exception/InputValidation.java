package exception;

import java.util.regex.Pattern;

public class InputValidation {
    // 정규 표현식 (Regular Expression)
    // 이름 검증 - 한글만 허용
    public void nameCheck(String name) throws MyException{
        boolean check = Pattern.matches("^[ㄱ-ㅎ가-힣]*$",name);
        if (!check){
            throw new MyException("이름은 한글로 입력해 주세요 .");
        }
    }

    // 나이 검증 : 0 ~ 130
    public void ageCheck(int age)throws MyException{
        if (age<0 || age >130){
            throw new MyException("나이는 0세부터 130세까지 입니다.");
        }
    }
    // 전화번호 검증 - 형식 체크 ( 010-XXXX-XXXX)
    public void phoneCheck (String phone) throws MyException{
        boolean check= Pattern.matches("^01([0|1|6|7|8|9])" +
                "-([0-9]{3,4})-([0-9]{4})$",phone);
        if (! check){
            throw new MyException("전화번호 형식은 [XXX-XXXX-XXXX]입니다.");
        }
    }
}
