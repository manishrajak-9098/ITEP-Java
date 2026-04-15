//move all zero's at the end 

import java.util.Scanner;
public class Q2 {
    public static void main(String args[]) {

         Scanner sc = new Scanner(System.in);

        System.out.print("Enter size: ");
        int n = sc.nextInt();

        int arr[] = new int[n];

        System.out.println("Enter elements:");
        for(int i = 0; i < n; i++){
            arr[i] = sc.nextInt();
        }

        int position = 0;

        for (int i = 0; i < n; i++) {
            if (arr[i] != 0) {
                arr[position] = arr[i];
                position++;
            }
        }

        // fill zero
        while (position < n) {
            arr[position] = 0;
            position++;
        }

        for (int num : arr) {
            System.out.print(num + " ");
        }
    }
}