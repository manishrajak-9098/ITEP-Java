//input = 1,2,3,4,5,6
//output = 4,5,6,1,2,3
import java.util.Scanner;

class Q3 {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter size: ");
        int n = sc.nextInt();

        int arr[] = new int[n];

        System.out.println("Enter elements:");
        for(int i = 0; i < n; i++){
            arr[i] = sc.nextInt();
        }

    
        int result[] = new int[n];
        int index = 0;

    
        for(int i = n/2; i < n; i++){
            result[index] = arr[i];
            index++;
        }
  
        for(int i = 0; i < n/2; i++){
            result[index] = arr[i];
            index++;
        }


        for(int i = 0; i < n; i++){
            System.out.print("output is : "+result[i] + " ");
        }
    }
}