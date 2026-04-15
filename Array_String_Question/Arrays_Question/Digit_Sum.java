public class Digit_Sum {
    public static void main(String[] args) {

        int num = 12345;
        int sum = 0;

        while(num > 0) {
            int digit = num % 10;   // last digit nikal lo
            sum += digit;           // sum me add kar do
            num /= 10;             // last digit hata do
        }

        System.out.println("Sum of digits: " + sum);
    }
}