import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

class Result {

    /*
     * Complete the 'hourglassSum' function below.
     *
     * The function is expected to return an INTEGER that is the sum
     * of the ints in the hourglass
     * that is of the greatest total sum
     * of all 16 hourglasses
     * The function accepts 2D_INTEGER_ARRAY arr as parameter.
     * hourglass graphic representation:
     * abc
     *  d
     * efg
     */

    public static int hourglassSum(List<List<Integer>> arr) {
        int largestSum = -Integer.MIN_VALUE;
        for(int x=0;x<=3;x++){
            for(int y=0;y<=3;y++){
                int currentSum = singleHourglassSum(arr, x, y);
                if(currentSum > largestSum){
                    largestSum = currentSum;
                }
            }
        }
        return largestSum;
    }

    public static int singleHourglassSum(List<List<Integer>> arr, int x, int y) {
        int sum = 0;
        sum = sum + arr.get(x).get(y);
        sum = sum + arr.get(x).get(y+1);
        sum = sum + arr.get(x).get(y+2);
        sum = sum + arr.get(x+1).get(y+1);
        sum = sum + arr.get(x+2).get(y);
        sum = sum + arr.get(x+2).get(y+1);
        sum = sum + arr.get(x+2).get(y+2);
        return sum;
    }

}

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        List<List<Integer>> arr = new ArrayList<>();

        IntStream.range(0, 6).forEach(i -> {
            try {
                arr.add(
                        Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                                .map(Integer::parseInt)
                                .collect(toList())
                );
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        int result = Result.hourglassSum(arr);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
