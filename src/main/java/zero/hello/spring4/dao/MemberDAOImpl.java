package zero.hello.spring4.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import zero.hello.spring4.model.Member;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository("mdao")
public class MemberDAOImpl implements MemberDAO {

    // sql.properties 에 작성한 sql 불러오기
    @Value("#{sql['insertMember']}") private String insertSQL;
    @Value("#{sql['loginMember']}") private String loginSQL;
    @Value("#{sql['selectOneMember']}") private String selectOneSQL;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public int insertMember(Member m) {
        // 매개변수 정의
        Object[] params = new Object[]{
                m.getUserid(), m.getPasswd(),
                m.getName(), m.getEmail()
        };
        return jdbcTemplate.update(insertSQL, params);
    }

    public Member loginMember(Member m) {
        // 매개변수 정의
        Object[] params = new Object[]{
                m.getUserid(), m.getPasswd(),
        };

        // 로그인 매퍼 선언 - 콜백 함수
        RowMapper<Member> mapper = new LoginMapper();

        // 쿼리 실행 : queryForObject(sql문, 매개변수, 매퍼) - 단일값 반환
        m = jdbcTemplate.queryForObject(loginSQL, params, mapper);
        return m;
    }


    private class LoginMapper implements RowMapper<Member> {
        @Override
        public Member mapRow(ResultSet rs, int num) throws SQLException {
            Member m = new Member();

            m.setUserid(rs.getString(1));
            m.setName(rs.getString(2));

            return m;
        }
    }


    public Member selectOneMember(String userid) {
        Object[] params = new Object[]{
                userid
        };

        RowMapper<Member> mapper = new MemberMapper();

        return jdbcTemplate.queryForObject(
                selectOneSQL, params, mapper);
    }

    private class MemberMapper implements RowMapper<Member> {
        @Override
        public Member mapRow(ResultSet rs, int num) throws SQLException {
            Member m = new Member(
                    rs.getString(1), rs.getString(2),
                    null, rs.getString(4),
                    rs.getString(5), rs.getString(6)
            );

            return m;
        }
    }
}