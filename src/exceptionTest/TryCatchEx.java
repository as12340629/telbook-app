package exceptionTest;

public class TryCatchEx {
    static void main(String[] args) {
        // ArithmeticException
        try {
            int result = 10/0;
            System.out.println(result);
        } catch (ArithmeticException e){
            System.out.println("0 으로 나눌 수 없어요.");
            System.out.println("오류 메시지 : "+e.getMessage());
        }catch (Exception e){
            System.out.println("오류 메시지 : "+e.getMessage());
        }
        finally {
            System.out.println("여긴 무조건 실행됩니다.");
        }
        try {
            int[] arr = {1,2,3};
            System.out.println(arr[10]);
        } catch (ArrayIndexOutOfBoundsException e){
            System.out.println("배열이 없습니다 : " + e.getMessage());
        }finally {
            System.out.println("하하");
        }

    }
}
