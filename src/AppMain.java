import console.UserView;
import db.DBConn;
import repository.TelBookJdbcRepository;
import repository.TelBookRepository;
import service.TelBookService;

import java.util.Scanner;

public class AppMain {
    public static void main(String[] args) {

        // ── 객체 조립 ──────────────────────────
        TelBookRepository repository = new TelBookJdbcRepository();  // 구현체 선택
        TelBookService service    = new TelBookService(repository);
        UserView userView   = new UserView(service);
        // ──────────────────────────────────────

        Scanner sc = new Scanner(System.in);
        int input;

        while (true) {
            do {
                System.out.println("1.입력  2.수정  3.삭제  4.전체출력  5.ID검색  6.종료");
                System.out.print("▶ 메뉴 입력: ");
                input = sc.nextInt();
            } while (input < 1 || input > 6);

            switch (input) {
                case 1 -> userView.insert();
                case 2 -> userView.update();
                case 3 -> userView.delete();
                case 4 -> userView.searchAll();
                case 5 -> userView.searchOne();
                case 6 -> {
                    DBConn.close();
                    System.out.println("종료합니다.");
                    System.exit(0);
                }
            }
        }
    }
}