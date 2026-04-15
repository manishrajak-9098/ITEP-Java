public class Fibonacci_Series{
    public static void main(String[]args){
        int a = -1;
        int b =  1;
        int c;
        int n = 10;
        for(int i=1; i<=n; i++){
            c = a + b;
            System.out.println("Fiboncacci Series: " + c);
            a=b;
            b=c;

        }
    }
}