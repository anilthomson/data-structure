package interview;

public class MoveZeros {
    public void moveZeroes(int[] arr) {
        int len = arr.length;
       int nonzeroindex= 0;
       for(int i=0;i<len;i++){
           if(arr[i]!=0){
               arr[nonzeroindex++]=arr[i];
           }
       }
       for(int i=nonzeroindex;i<len;i++)
          arr[i]=0;
   } 
}
