public class Remove_Duplicate {
    public static void main(String[] args) {

        int arr[] = {1,2,2,3,4,4,5};

        // Duplicate ko 0 bana do
        for(int i = 0; i < arr.length; i++) {

            for(int j = i + 1; j < arr.length; j++) {

                if(arr[i] == arr[j]) {
                    arr[j] = 0;   // duplicate mila to 0 kar diya
                }
            }
        }

        // Ab 0 ko print nahi karenge
        for(int i = 0; i < arr.length; i++) {
            if(arr[i] != 0) {
                System.out.print(arr[i] + " ");
            }
        }
    }
}