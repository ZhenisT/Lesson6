import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import taskArray.TaskArrayBool;

import java.util.Arrays;
import java.util.Collection;


@RunWith(value = Parameterized.class)
public class ParameterizedTest1 {
    private static TaskArrayBool bool = null;
    private int[] arr;
    private boolean res;

    public ParameterizedTest1(int[] arr, boolean res) {
        this.arr = arr;
        this.res = res;
    }

    @Parameterized.Parameters
    public static Collection boolArraysTest() {
        return Arrays.asList(new Object[][]{
                {new int[]{10, 3, 4, 40, 17, 5, 1}, true},
                {new int[]{5, 1, 4, 8, 12, 7, 5}, true},
                {new int[]{1, 2, 4, 4, 2, 4, 3, 4, 1, 7}, true},
                {new int[]{5, 2, 7, 8, 9, 6, 3, 0, 10, 7}, false}
        });
    }
    @Before
    public void init() {
        System.out.println("init calc");
        bool = new TaskArrayBool();
    }

    @After
    public void tearDown() throws Exception {
        bool = null;
    }

    @Test
    public void testbool(){
        Assert.assertEquals(bool.containNumber(arr),res);
    }
}

