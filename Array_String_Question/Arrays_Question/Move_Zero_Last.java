//4. Move All Zeros to End: Input:[0,1,0,2,12] | Output:[1,2,12,0,0]

public class Move_Zero_Last {

    public static void main(String args[]) {
        int[] arr = {0, 1, 0, 2, 12};

        int n = arr.length;
        int pos = 0;

        // move non zero
        for (int i = 0; i < n; i++) {
            if (arr[i] != 0) {
                arr[pos] = arr[i];
                pos++;
            }
        }

        // fill zero
        while (pos < n) {
            arr[pos] = 0;
            pos++;
        }

        // print using for-each
        for (int num : arr) {
            System.out.print(num + " ");
        }
    }
}