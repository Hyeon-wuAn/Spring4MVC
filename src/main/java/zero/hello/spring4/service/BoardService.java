package zero.hello.spring4.service;

import zero.hello.spring4.model.Board;

import java.util.List;

public interface BoardService{

    List<Board> readBoard(int cpg);
    Board readOneBoard(String bno);
    boolean saveBoard(Board bd);
}
