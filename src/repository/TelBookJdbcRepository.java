package repository;

import db.DBConn;
import dto.TelDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TelBookJdbcRepository implements TelBookRepository{
    @Override
    public int insert(TelDto dto) {
        Connection conn = DBConn.getConnection();
        PreparedStatement psmt = null;
        int result = 0;
        try {
            String sql = "INSERT INTO telbooK (name, age, address, phone) VALUES(?,?,?,?)";
            psmt = conn.prepareStatement(sql);
            psmt.setString(1, dto.getName());
            psmt.setInt(2, dto.getAge());
            psmt.setString(3, dto.getAddress());
            psmt.setString(4, dto.getPhone());
            result = psmt.executeUpdate();
        } catch (SQLException e){
            System.out.println("입력 오류 : " + e.getMessage());
        } finally {
            try {
                psmt.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return result;
    }

    @Override
    public int update(TelDto dto) {
        Connection conn = DBConn.getConnection();
        PreparedStatement psmt = null;
        int result = 0;

        try {
            String sql = "UPDATE telbook SET name=?, age=?, address=?, phone=? WHERE id=?";
            psmt = conn.prepareStatement(sql);
            psmt.setString(1, dto.getName());
            psmt.setInt(2, dto.getAge());
            psmt.setString(3, dto.getAddress());
            psmt.setString(4, dto.getPhone());
            psmt.setInt(5, dto.getId());
            result = psmt.executeUpdate();
        }catch (SQLException e) {
            System.out.println("입력오류 : " + e.getMessage());
        } finally {
            try {
                psmt.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return result;
    }

    @Override
    public int delete(int id) {
        // 결과 담을 변수 선엉ㄴ
        int result = 0;
        Connection conn = DBConn.getConnection();
        PreparedStatement psmt = null;
        try {
            String sql = "DELETE FROM telbook WHERE id = ?";
            psmt = conn.prepareStatement(sql);
            psmt.setInt(1, id);
            // 삭제 질의 실행
            // executeQuery : SELECT
            // executeUpdate : INSERT, DELETE, UPDATE
            result = psmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("삭제오류 : " + e.getMessage());
        } finally {
            try {
                psmt.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return result;
    }

    @Override
    public List<TelDto> findAll() {
        // 전화번호부를 받아서 저장할 Dto  리스트를 생성
        List<TelDto> dtoList = new ArrayList<>();
        // DB 연결 얻기
        Connection conn = DBConn.getConnection();
        // SQL 실행하는 기계 생성
        PreparedStatement psmt = null;
        // 결과를 받을 레코드셋 생성
        ResultSet rs = null;
        try {
            // 쿼리 생성
            String sql = "SELECT * FROM telbook ORDER BY name";
            psmt = conn.prepareStatement(sql);
            // 정상실행 된 경우 결과셋을 리스트에 담는다.
            rs = psmt.executeQuery();
            while (rs.next()){
                TelDto dto = new TelDto();
                // 테이블의 필드명을 갖고 찾아서 dto 의 각 필드에 넣어준다.
                dto.setId(rs.getInt("id"));
                dto.setName(rs.getString("name"));
                dto.setAge(rs.getInt("age"));
                dto.setAddress(rs.getString("address"));
                dto.setPhone(rs.getString("phone"));
                dtoList.add(dto);
            }
        } catch (SQLException e) {
            System.out.println("전체 검색 오류 : " + e.getMessage());
        } finally {
            try {
                psmt.close();
                rs.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return dtoList;
    }

    @Override
    public TelDto findById(int id) {
        Connection conn = DBConn.getConnection();
        PreparedStatement psmt = null;
        ResultSet rs = null;
        TelDto dto = null;

        try {
            String sql = "SELECT * FROM telbook WHERE id=?";
            psmt = conn.prepareStatement(sql);
            psmt.setInt(1, id);

            rs = psmt.executeQuery();
            if(rs.next()){
                dto = new TelDto();
                // 테이블의 필드명을 갖고 찾아서 dto 의 각 필드에 넣어준다.
                dto.setId(rs.getInt("id"));
                dto.setName(rs.getString("name"));
                dto.setAge(rs.getInt("age"));
                dto.setAddress(rs.getString("address"));
                dto.setPhone(rs.getString("phone"));
            }
        } catch (SQLException e){
            System.out.println("해당 ID는 존재하지 않음.");
        } finally {
            try {
                psmt.close();
                rs.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return dto;
    }
}
