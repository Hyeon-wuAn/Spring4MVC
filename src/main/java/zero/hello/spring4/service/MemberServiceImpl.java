package zero.hello.spring4.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zero.hello.spring4.dao.MemberDAO;
import zero.hello.spring4.model.Member;

@Service("msrv")
public class MemberServiceImpl implements MemberService{

    @Autowired
    private MemberDAO mdao;
    @Override
    public boolean saveMember(Member m) {
        boolean isSaved = false;

        if (mdao.insertMember(m) > 0) isSaved = true;

        return false;
    }

}
