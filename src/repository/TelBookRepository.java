package repository;

import dto.TelDto;

import java.util.List;

public interface TelBookRepository {
    int insert(TelDto dto);        // 등록 → 처리된 행 수

    int update(TelDto dto);        // 수정 → 처리된 행 수

    int delete(int id);            // 삭제 → 처리된 행 수

    List<TelDto> findAll();        // 전체 조회

    TelDto findById(int id);       // 한 건 조회 (없으면 null)
}