

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import taskArray.Task1;


import java.util.Arrays;
import java.util.Collection;

@RunWith(value = Parameterized.class)
public class ParameterizedTest {
    private static Task1 taskArray = null;
    private int[] arr;
    private int[] res;

    public ParameterizedTest(int[] arr, int[] res) {
        this.arr = arr;
        this.res = res;
    }

    @Parameterized.Parameters
    public static Collection arraysTest() {
        return Arrays.asList(new Object[][]{
                {new int[]{10, 3, 4, 40, 17, 5, 1}, new int[]{40, 17, 5, 1}},
                {new int[]{5, 1, 4, 8, 12, 7, 5}, new int[]{8, 12, 7, 5}},
                {new int[]{1, 2, 4, 4, 2, 4, 3, 4, 1, 7}, new int[]{1, 7}}
        });
    }

    @Before
    public void init() {
        System.out.println("init calc");
        taskArray = new Task1();
    }

    @Test
    public void testchangeArray() {
        Assert.assertArrayEquals(res, taskArray.changeArray(arr));
    }

    @Test(expected = RuntimeException.class)
    public void testchangeArray1() {
        taskArray.changeArray(new int[]{10, 3, 14, 40, 17, 5, 10});
    }


    @After
    public void tearDown() throws Exception {
        taskArray = null;
    }

}


