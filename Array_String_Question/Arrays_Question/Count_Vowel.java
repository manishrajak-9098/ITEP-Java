public class Count_Vowel {
    public static void main(String[] args) {

        String str = "Manish";
        int count = 0;

        str = str.toLowerCase();   // capital ko small bana diya

        for(int i = 0; i < str.length(); i++) {

            char ch = str.charAt(i);

            if(ch == 'a' || ch == 'e' || 
               ch == 'i' || ch == 'o' || 
               ch == 'u') {

                count++;
            }
        }

        System.out.println("Total vowels: " + count);
    }
}