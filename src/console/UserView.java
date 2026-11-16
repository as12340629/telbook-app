package console;

import dto.TelDto;
import exception.InputValidation;
import exception.MyException;
import service.TelBookService;

import java.util.*;

public class UserView {

    private InputValidation validation = new InputValidation();
    private Scanner         sc         = new Scanner(System.in);
    private TelBookService  service;

    public UserView(TelBookService service) {
        this.service = service;
    }

    public void insert() {
        System.out.println("== 전화번호 등록 ==");
        TelDto dto = new TelDto();

        // 이름
        boolean nameOk = false;
        do {
            try {
                System.out.print("이름: ");
                dto.setName(sc.next());
                validation.nameCheck(dto.getName());
                nameOk = true;
            } catch (MyException e) {
                System.out.println(e.getMessage());
            }
        } while (!nameOk);

        // 나이
        boolean ageOk = false;
        do {
            try {
                System.out.print("나이: ");
                dto.setAge(sc.nextInt());
                validation.ageCheck(dto.getAge());
                ageOk = true;
            } catch (MyException e) {
                System.out.println(e.getMessage());
            }
        } while (!ageOk);

        System.out.print("주소: ");
        dto.setAddress(sc.next());

        // 전화번호
        boolean telOk = false;
        do {
            try {
                System.out.print("전화번호: ");
                dto.setPhone(sc.next());
                validation.phoneCheck(dto.getPhone());
                telOk = true;
            } catch (MyException e) {
                System.out.println(e.getMessage());
            }
        } while (!telOk);

        boolean success = service.register(dto);
        if (success) {
            System.out.println("✅ 등록 완료!");
            System.out.println(dto);
        } else {
            System.out.println("❌ 등록 실패");
        }
    }

    public void update() {
        System.out.print("수정할 ID: ");
        int id = sc.nextInt();

        if (!service.exists(id)) {
            System.out.println("해당 ID 가 없습니다.");
            return;
        }

        TelDto dto = new TelDto();
        dto.setId(id);

        // 이름/나이/주소/전화번호 입력 (insert 와 동일 패턴)
        boolean nameOk = false;
        do {
            try {
                System.out.print("수정할 이름: ");
                dto.setName(sc.next());
                validation.nameCheck(dto.getName());
                nameOk = true;
            } catch (MyException e) {
                System.out.println(e.getMessage());
            }
        } while (!nameOk);

        boolean ageOk = false;
        do {
            try {
                System.out.print("수정할 나이: ");
                dto.setAge(sc.nextInt());
                validation.ageCheck(dto.getAge());
                ageOk = true;
            } catch (MyException e) {
                System.out.println(e.getMessage());
            }
        } while (!ageOk);

        System.out.print("수정할 주소: ");
        dto.setAddress(sc.next());

        boolean telOk = false;
        do {
            try {
                System.out.print("수정할 전화번호: ");
                dto.setPhone(sc.next());
                validation.phoneCheck(dto.getPhone());
                telOk = true;
            } catch (MyException e) {
                System.out.println(e.getMessage());
            }
        } while (!telOk);

        boolean success = service.modify(dto);
        System.out.println(success ? "✅ 수정 완료!" : "❌ 수정 실패");
    }

    public void delete() {
        System.out.print("삭제할 ID: ");
        int id = sc.nextInt();

        boolean success = service.remove(id);
        System.out.println(success
                ? "✅ ID " + id + " 삭제 완료!"
                : "❌ 삭제 실패 (ID 확인)");
    }

    public void searchAll() {
        List<TelDto> list = service.getAll();

        printHeader();
        if (list.isEmpty()) {
            System.out.println("데이터가 없습니다.");
        } else {
            Iterator<TelDto> it = list.iterator();
            while (it.hasNext()) {
                System.out.println(it.next());
            }
        }
        printLine();
    }

    public void searchOne() {
        System.out.print("검색할 ID: ");
        int id = sc.nextInt();

        TelDto dto = service.getOne(id);
        if (dto == null) {
            System.out.println("해당 ID 가 없습니다.");
        } else {
            printHeader();
            System.out.println(dto);
            printLine();
        }
    }

    private void printHeader() {
        printLine();
        System.out.println("ID  이름    나이   주소      전화번호");
        printLine();
    }

    private void printLine() {
        System.out.println("============================================");
    }
}