/**
 * Created by piotrgrudzien on 4/1/17.
 */
public class ArrayHelper {

    public static double sum(double[] arr) {
        double res = 0;
        for (int i = 0; i < arr.length; i++) {
            res += arr[i];
        }
        return res;
    }
}
