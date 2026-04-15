public class Armstrong_number{
public static void main (String args[]){

int num = 153;
int original = num;
int sum=0;

while(num>0){
	int digit = num%10;
	sum = sum+(digit*digit*digit);
	num = num/10;
	
}
if(original==sum){
System.out.println("its a armstrong no");
}else{
System.out.println("not a armstrong no");
}

}

}
