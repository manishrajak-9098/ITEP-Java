class Second_Largest{
	public static void main(String  args[]){
	
	int arr[] ={10, 5, 8, 20, 15};
	int n = arr.length;
	for(int i=0; i<n; i++){
		for(int j = i+1; j<n; j++){
		if(arr[i] > arr[j]){
			int temp = arr[i];
			arr[i]= arr[j];
			arr[j] = temp;
}
}
}
System.out.println("second largest element in array : "+arr[n-2]);
	