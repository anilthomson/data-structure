package interview;

/* Dynamic Programming Java implementation of Coin 
   Change problem */
   import java.util.Arrays;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.*;
   class CoinChange 
   { 
       static long countWays(int S[], int m, int n) 
       { 
           //Time complexity of this function: O(mn) 
           //Space Complexity of this function: O(n) 
     
           // table[i] will be storing the number of solutions 
           // for value i. We need n+1 rows as the table is 
           // constructed in bottom up manner using the base 
           // case (n = 0) 
           long[] table = new long[n+1]; 
     
           // Initialize all table values as 0 
           Arrays.fill(table, 0);   //O(n) 
     
           // Base case (If given value is 0) 
           table[0] = 1; 
     
           // Pick all coins one by one and update the table[] 
           // values after the index greater than or equal to 
           // the value of the picked coin 
           for (int i=0; i<m; i++) 
               for (int j=S[i]; j<=n; j++) 
                   table[j] += table[j-S[i]]; 
     
           return table[n]; 
       } 
     
       // Driver Function to test above function 
       public static void main(String args[]) 
       { 
        String str = ")d))";
        int n = str.length();
        Map<Integer,Map<Integer,List<Integer>>> map ;
        //map.values().stream().collect(Collectors.toMap(Function.identity(), Function.identity()));
        permute(str, 0, n-1);
        } 
       private static void permute(String str, int l, int r)
       {
           if (l == r)
               System.out.println(str);
           else
           {
               for (int i = l; i <= r; i++)
               {
                   str = swap(str,l,i);
                   permute(str, l+1, r);
                   str = swap(str,l,i);
               }
           }
       }
    
       /**
       * Swap Characters at position
       * @param a string value
       * @param i position 1
       * @param j position 2
       * @return swapped string
       */
       public static  String swap(String a, int i, int j)
       {
           char temp;
           char[] charArray = a.toCharArray();
           temp = charArray[i] ;
           charArray[i] = charArray[j];
           charArray[j] = temp;
           return String.valueOf(charArray);
       }
    
   } 