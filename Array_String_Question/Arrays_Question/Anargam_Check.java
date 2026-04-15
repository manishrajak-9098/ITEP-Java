public class Anargam_Check{
    public static void main (String[] args){

        String s1 = "listen";
        String s2 = "silent";

        if(s1.length() != s2.length()){
            System.out.println("Not an Anagram");
            return;
        }

        char[] a1 = s1.toCharArray();
        char[] a2 = s2.toCharArray();

        java.util.Arrays.sort(a1);
        java.util.Arrays.sort(a2);

        if(java.util.Arrays.equals(a1, a2)){
            System.out.println("Anagram");
        } else {
            System.out.println("Not an Anagram");
        }
    }
}
