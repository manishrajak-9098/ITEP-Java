public class Intersection_of_Two_Array{
    public static void main (String[] args){

        int a[]={1,2,3,4,5,6};
        int b[]={2,3,4,3,5};

        for(int i = 0; i < a.length; i++){

            for(int j = 0; j < b.length; j++){

                if(a[i] == b[j]){
                    System.out.println("Intersection element: " + a[i]);
                    break;
                }
            }
        }
    }
}