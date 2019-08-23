package taskArray;



public class TaskArrayBool {
    private static final int NUMBERFOUR = 4;
    private static final int NUMBERONE = 1;
    int[] array;

    public TaskArrayBool() {
    }

    public TaskArrayBool(int[] array) {
        this.array = array;
    }

    public boolean containNumber(int[] array) {
        for (int i = 0; i < array.length; i++) {
            if ((array[i] == NUMBERFOUR) || (array[i] == NUMBERONE)) {
                return true;
            }
        }
        return false;
    }

}
