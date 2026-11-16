package service;

import dto.TelDto;
import repository.TelBookRepository;

import java.util.List;

public class TelBookService {

    private final TelBookRepository repository;   // 구현체가 아닌 인터페이스 타입

    public TelBookService(TelBookRepository repository) {
        this.repository = repository;
    }

    // 등록
    public boolean register(TelDto dto) {
        int result = repository.insert(dto);
        return result > 0;
    }

    // 수정 — 있는 데이터인지 먼저 확인
    public boolean modify(TelDto dto) {
        TelDto found = repository.findById(dto.getId());
        if (found == null) {
            return false;
        }
        int result = repository.update(dto);
        return result > 0;
    }

    // 삭제 — 있는 데이터인지 먼저 확인
    public boolean remove(int id) {
        TelDto found = repository.findById(id);
        if (found == null) {
            return false;
        }
        int result = repository.delete(id);
        return result > 0;
    }

    // 전체 조회
    public List<TelDto> getAll() {
        List<TelDto> list = repository.findAll();
        return list;
    }

    // 한 건 조회
    public TelDto getOne(int id) {
        TelDto dto = repository.findById(id);
        return dto;
    }

    // 존재 여부 확인
    public boolean exists(int id) {
        TelDto found = repository.findById(id);
        return found != null;
    }
}