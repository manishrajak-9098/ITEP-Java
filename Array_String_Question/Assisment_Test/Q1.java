//count Frequency of elements in an array
import java.util.Scanner;
class Q1{
    public static void main(String[] args){
         Scanner sc = new Scanner(System.in);

        System.out.print("Enter size: ");
        int n = sc.nextInt();

        int arr[] = new int[n];

        System.out.println("Enter elements:");
        for(int i = 0; i < n; i++){
            arr[i] = sc.nextInt();
        }


        for(int i=0; i<n; i++){
            int count = 1;
            if(arr[i] == -1){
                continue;
            }
            for (int j = i+1; j<n; j++){
                if(arr[i] == arr[j]){
                    count++;
                    arr[j] = -1;
                }
            }
            System.out.println(arr[i] + " occurs " + count + " times");
        }
    }
}