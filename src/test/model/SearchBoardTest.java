package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SearchBoardTest {

    private SearchBoard board;

    @BeforeEach
    void runBefore() {
        board = new SearchBoard(10,10);
    }

    @Test
    void testBoard() {
        board.setLocation('d',9,9);
        assertEquals('d', board.getValue(9,9));
    }


}
