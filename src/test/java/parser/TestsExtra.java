package parser;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class TestsExtra {
    private int[][] table = new int[5][5];
    private int[] tableSimple = new int[5];


    @Test
    public void test() {
        table = new int[][] {
                {1, 0, 0, 0, 0},
                {1, 0, 0, 0, 0},
                {1, 0, 0, 0, 0},
                {1, 0, 0, 0, 0},
                {1, 0, 0, 0, 0},
        };
        boolean result = isWinTest();
//        assertTrue(result);

//        int[] items = new int[100];
//        int position = 100;
//        for(int i = 0; i < position; i++) {
//            if (items[i].getName().equals(key)) {
//
//            }
//        }

//        String one = "111";
//        String two = "222";
//
//        assertThat(one, is(two));


    }

    @Test
    public void simpleTest() {
        tableSimple = new int[] {1, 1, 1, 1, 1};

        boolean result = isWinSimple();
        assertTrue(result);
    }

    public boolean isWinTest() {
        boolean result = false;
        int winCount = 0;
        for (int i = 0; i < table.length; i++) {
            if(table[i][1] == 1) {
                winCount++;
            }
        }
        if (winCount == 5) {
            result = true;
        }
    return result;
    }

    public boolean isWinSimple() {
        boolean result = false;
        int winCount = 0;
        for (int i = 0; i < tableSimple.length; i++) {
            if(tableSimple[i] == 1) {
                winCount++;
            }
        }
        if (winCount == 5) {
            result = true;
        }
        return result;
    }


}
