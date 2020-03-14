import java.io.*; 
  
class GFG { 
  
    // Function to search an element in 
    // minimum number of comparisons 
    static String search(int arr[], int n, int x) 
    { 
        
        // no termination condition and thus 
        // no comparison 
        for (int i = 0;i<n; i++) { 
            // this would be executed at-most n times 
            // and therefore at-most n comparisons 
            if (arr[i] == x) { 
                
                    return "Found"; 
  
                
            } 
        } 
        // else not present in the array 
        return "Not Found"; 
    } 
  
    // driver program 
    public static void main(String[] args) 
    { 
        int arr[] = { 4, 6, 1, 5, 8 }; 
        int n = arr.length; 
        int x = 8; 
        System.out.println(search(arr, n, x)); 
    } 
} 
  