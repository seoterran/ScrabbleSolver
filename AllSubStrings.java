

import java.util.ArrayList;
import java.util.HashSet;
//import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AllSubStrings {

	List<String> com_str;
	//HashSet<String> com_str;
	
	public AllSubStrings(String str){
		
		com_str = new ArrayList<>();
		//com_str = new HashSet<>();
		allSubStrings(str);

	}
	private void showMemoryUsage(){
		 long memory=Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
		 System.out.println("memory : "+memory);
	}
	/**
	 * Method finding all possible substrings of a given input string
	 * @return void
	 */
	private void allSubStrings(String str){
		char[] arr=str.toCharArray();
		int n = arr.length;
	 
		for(int r=1;r<=n;r++) { 
			char[]data=new char[r];
			StringCombination(arr, data, 0, n-1, 0, r);
		}
		//showMemoryUsage();
	}
	
	/**
	 * Method returning all the possible substrings
	 * @Return ArrayList of all the possible substrings
	 */
	public List<String> get_AllSubStrings(){
		return com_str;
	}
	
	/**
	 * Method finding all combinations of the string stored in arr[]
	 * @return void
	 */
     private void StringCombination(char arr[], char data[],int start,
                                int end, int index, int r)
    {
    	 
        if (index == r)
        {
        	String temp="";
            for (int j=0; j<r; j++){
            	temp+=Character.toString(data[j]);
            }
            
            StringPermutation(temp);
            
            return;
        }
 
        for (int i=start; i<=end && end-i+1 >= r-index; i++)
        {
            data[index] = arr[i];
            StringCombination(arr, data, i+1, end, index+1, r);
        }
    }
     
     /**
      * Method finding all permutations of the input string
      * @return void
      */
     private void StringPermutation(String input)
     {
         StringPermutation("", input);
     }
     
     /**
      *  Method finding all permutations of the input string
      *  @return void
      */
     private void StringPermutation(String permutation, String input)
     {    
         if(input.length() == 0)
         {
        	 com_str.add(permutation);//add to the substrings list
         }
         else
         {
             for (int i = 0; i < input.length(); i++)
             {    
                 StringPermutation(permutation+input.charAt(i), input.substring(0, i)+input.substring(i+1, input.length()));
             }
         }
     }
    
}
