public class Palindrome_No{
    public static void main (String[]args){
        int num = 121;
        int original = num;
        int reverse =0;
        while(num>0){
            int digit = num%10;
            reverse = reverse *10+digit;
            num = num/10;
        }
    if(reverse==original){
        System.out.println("number is palindrome");
    }
    else {
        System.out.println("number is not palindrome");
    }
    }
}