public class Negative_Element {
    public static void main(String[] args) {

        int arr[] = {5, -2, 7, -8, 3};
        int check = 0;   // 0 se compare karenge

        for(int i = 0; i < arr.length; i++) {

            if(arr[i] < check) {
                System.out.println("Negative number: " + arr[i]);
            }
        }
    }
}