import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Point {
	
	private  Map<String, Integer> point_map;
	private  HashMap<String, Integer> sorted_map;
	public Point(){
		
		point_map = new HashMap<String, Integer>();
				point_map.put("a", Integer.valueOf(1));
				point_map.put("e", Integer.valueOf(1));
				point_map.put("i", Integer.valueOf(1));
				point_map.put("l", Integer.valueOf(1));
				point_map.put("n", Integer.valueOf(1));
				point_map.put("o", Integer.valueOf(1));
				point_map.put("r", Integer.valueOf(1));
				point_map.put("s", Integer.valueOf(1));
				point_map.put("t", Integer.valueOf(1));
				point_map.put("u", Integer.valueOf(1));
				point_map.put("d", Integer.valueOf(2));
				point_map.put("g", Integer.valueOf(2));
				point_map.put("b", Integer.valueOf(3));
				point_map.put("c", Integer.valueOf(3));
			    	point_map.put("m", Integer.valueOf(3));
			    	point_map.put("p", Integer.valueOf(3));
			    	point_map.put("f", Integer.valueOf(4));
			    	point_map.put("h", Integer.valueOf(4));
			    	point_map.put("v", Integer.valueOf(4));
			    	point_map.put("w", Integer.valueOf(4));
			    	point_map.put("y", Integer.valueOf(4));
			    	point_map.put("k", Integer.valueOf(5));
			    	point_map.put("j", Integer.valueOf(8));
			    	point_map.put("x", Integer.valueOf(8));
			    	point_map.put("q", Integer.valueOf(10));
			    	point_map.put("z", Integer.valueOf(10));
	}
	 /**
     * Method returning the total score
     *
     * @return Scrabble score of an input string 
     */
	private int getPoint(String str){
		int result=0;
		int temp;

		char arr[] = str.toCharArray();
		for(char c: arr){
		    temp=0;
			try{
		       temp=(int)point_map.get(Character.toString(c));
			}catch(NullPointerException e){
				System.out.println(c+" : "+temp +" (error)");
			}
			result+=temp;
			//System.out.println(c+" : "+temp);
		}
	
		//System.out.println("Score for "+str+" : "+result);
		return result;
		
	}
	/**
     * Method sorting May by Value
     *
     * @return  HashMap of sorted Map
     */
	private HashMap sortByValues(Map<String, Integer> temp_map) { 
	       List list = new LinkedList(temp_map.entrySet());
	       // Defined Custom Comparator here
	       Collections.sort(list, new Comparator() {
	            public int compare(Object o1, Object o2) {
	               return ((Comparable) ((Map.Entry) (o2)).getValue())
	 	                  .compareTo(((Map.Entry) (o1)).getValue());
	            }
	       });

	       /* Copy the sorted list in HashMap
	       *  using LinkedHashMap to preserve the insertion order
	       */
	       HashMap sortedHashMap = new LinkedHashMap();
	       for (Iterator it = list.iterator(); it.hasNext();) {
	              Map.Entry entry = (Map.Entry) it.next();
	              sortedHashMap.put(entry.getKey(), entry.getValue());
	       } 
	       return sortedHashMap;
	  }
	

	/**
     * Method sorting the input list and return the sorted list
     *
     * @return  ArrayList of sorted list
     */
	public List<String> sortByScore(List<String> list){
		Map<String, Integer> temp_map = new HashMap<String, Integer>();
		List<String> result_list = new ArrayList<>();
		
	    for (String str: list)
	    	temp_map.put(str, getPoint(str));
	    
	    sorted_map = sortByValues(temp_map); 
	    Set set = sorted_map.entrySet();
	    Iterator iterator = set.iterator();
	    while(iterator.hasNext()) {
	           Map.Entry me = (Map.Entry)iterator.next();
	           result_list.add((String)(me.getKey()));
	      }
	    return result_list;
	}
}
