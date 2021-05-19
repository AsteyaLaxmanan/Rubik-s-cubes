public class binarySearch {
  
	int binarySearch(int arr[] , int a , int b , int c) {
    
		if (b>=a) {
    
		int mid = a + (b-1)/2;
		if (arr[mid] == c) {
     	return mid;
      }
     
    if (arr[mid] > c) {
       return binarySearch(arr, a, mid - 1, c);
     }
     
     if (arr[mid] <c) {
      return binarySearch(arr, mid + 1, b, c); 
    
     }
		}
     
		return -1;
     
	}
	public static void main (String args[]) {
		binarySearch object1 = new binarySearch();
		int arr[] = {2,3,4,10,40};
		int n = arr.length;
		int x = 10;
		int result = object1.binarySearch(arr, 0, n-1, x);
		if (result == -1) {
			System.out.println("Element is not present.");
		}
		else {
			System.out.println("Element found at index " + result);	
		}
	}
	
}

  


