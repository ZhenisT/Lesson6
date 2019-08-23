package taskArray;

public class Task1 {
    private static final int NUMBERFOUR = 4;
    int[] array;

    public Task1(int[] array) {
        this.array = array;
    }
    public Task1() {

    }

    public int[] changeArray(int[] array) throws RuntimeException {
        int count = 0;
        for (int i = 0; i < array.length - 1; i++) {
            if (array[i] == NUMBERFOUR) {
                count = i;
            }
        }
        if (count==0) {
            throw new RuntimeException("Массив не содержит элемента = 4");
        }
        int[] newarr = new int[array.length - count - 1];
        System.arraycopy(array, count + 1, newarr, 0, newarr.length);
        array = newarr;
        return array;
    }
}
