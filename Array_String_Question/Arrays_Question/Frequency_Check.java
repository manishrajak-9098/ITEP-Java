public class Frequency_Check {
    public static void main(String[] args) {

        int arr[] = {1, 2, 2, 3, 3, 3, 4};
        int n = arr.length;

        for(int i = 0; i < n; i++) {

            int count = 1;

            if(arr[i] == -1) {
                continue;
            }

            for(int j = i + 1; j < n; j++) {
                if(arr[i] == arr[j]) {
                    count++;
                    arr[j] = -1; 
                }
            }

            System.out.println(arr[i] + " occurs " + count + " times");
        }
    }
}