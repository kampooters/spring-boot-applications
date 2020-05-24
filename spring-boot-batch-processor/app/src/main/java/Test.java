import com.org.HashTable;

import java.util.ArrayList;
import java.util.List;

public class Test {

    public static void main (String[] args) throws java.lang.Exception
    {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        String input = br.readLine();
//        System.out.println(input);
//        List<String> bracketList = brackets(Integer.valueOf(input));
//        for(int i=0; i<bracketList.size(); i++){
//            System.out.println(bracketList.get(i));
//        }

//        List<Integer> rowsList = new ArrayList<>();
//        rowsList.add(4);
//        rowsList.add(1);
//        rowsList.add(2);
//        rowsList.add(3);
//        rowsList.add(1);
//        int totalYield = getTotalYield(rowsList);
//        System.out.println("-------");
//        System.out.println(totalYield);

        HashTable<Integer, Integer> hashTable = new HashTable<Integer, Integer>();
        hashTable.put(1, 1);
        hashTable.put(2, 2);
        hashTable.put(3, 3);
        hashTable.put(4, 4);
        hashTable.put(5, 5);

        System.out.println( hashTable.get(1));

        //This line will remove 1
        hashTable.evict();
        System.out.println(hashTable.get(1));

        System.out.print("remove-");
        System.out.println(hashTable.remove(2));
        System.out.println(hashTable.remove(2));


    }
    /**
     * @author Abdulrehman
     * brackets calculates the bracket combination and works as server method
     */
    public static List<String> brackets(int number){
        List<String> bracketList = new ArrayList<>();
        calculateRecursiveBrackets("",0,0,number, bracketList);
        return bracketList;
    }


    /**
     * @author Abdulrehman
     * brackets calculates the bracket combination and works as main serving client method.
    It works recursivly to find the permutations
     */
    public static void calculateRecursiveBrackets(String result, int open, int close, int pairs, List<String> bracketList){
        if((open == pairs) && (close == pairs)){
            bracketList.add(result);
//         System.out.println(result);
        }else{
            if(open < pairs){
                calculateRecursiveBrackets(result+"(",open+1, close, pairs,bracketList);
            }
            if(close < open){
                calculateRecursiveBrackets(result+")",open, close+1, pairs,bracketList);
            }
        }
    }




    /**
     * @author Abdulrehman
     * This method gets the total carrots those can be yeileded from a field
     */
    public static int getTotalYield(List<Integer> carrotRowsList){
        int totalYield = 0;
        if(null == carrotRowsList || carrotRowsList.size() <= 0){
            return totalYield;
        }if(carrotRowsList.size() <= 2){
            totalYield = carrotRowsList.get(0);
            return totalYield;
        }

        for(int i=0; i<carrotRowsList.size();){
            totalYield = carrotRowsList.get(i);
            i = i+1;
        }
        return totalYield;
    }
}
