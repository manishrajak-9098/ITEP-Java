//10. Largest Element in array
public class Largest_Element {
    public static void main(String[] args) {
        int[] arr = {10, 25, 3, 45, 5};
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        System.out.println("Largest element in array: " + max);
    }
}