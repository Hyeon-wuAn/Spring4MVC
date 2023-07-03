package zero.hello.spring4.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zero.hello.spring4.dao.BoardDAO;
import zero.hello.spring4.model.Board;

import java.util.List;

@Service("bsrv")
public class BoardServiceImpl implements BoardService {

    @Autowired BoardDAO bdao;
    @Override
    public List<Board> readBoard(int cpg) {
        int snum = (cpg - 1) * 15;

        return bdao.selectBoard(snum);
    }

    @Override
    public Board readOneBoard(String bno) {
        return bdao.selectOneBoard(bno);
    }

    @Override
    public boolean saveBoard(Board bd) {
        boolean isSaved = false;

        if (bdao.insertBoard(bd) > 0) isSaved = true;

        return isSaved;
    }
}
