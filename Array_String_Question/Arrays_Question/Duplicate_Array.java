public class Duplicate_Array {

    public static void main(String args[]) {

        int[] arr = {1, 2, 3, 4, 5, 3};
        int n = arr.length;
        boolean found = false;

        for(int i = 0; i < n; i++) {
            for(int j = i + 1; j < n; j++) {
                if(arr[i] == arr[j]) {
                    System.out.println("Duplicate element found: " + arr[i]);
                    found = true;
                }
            }
        }

        if(!found) {
            System.out.println("No duplicate elements found");
        }
    }
}