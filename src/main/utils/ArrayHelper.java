import java.text.DecimalFormat;
import java.util.Arrays;

/**
 * Created by piotrgrudzien on 4/1/17.
 */
public class ArrayHelper {

    private static DecimalFormat percentFormat = new DecimalFormat("##.####%");

    public static double sum(double[] arr) {
        double res = 0;
        for (int i = 0; i < arr.length; i++) {
            res += arr[i];
        }
        return res;
    }

    public static String print(double[] arr) {
        return Arrays.toString(arr);
    }

    public static int[] getTop5Indexes(double[] prediction) {
        int indexMax1 = 0;
        int indexMax2 = 0;
        int indexMax3 = 0;
        int indexMax4 = 0;
        int indexMax5 = 0;
        double max1 = Double.NEGATIVE_INFINITY;
        double max2 = Double.NEGATIVE_INFINITY;
        double max3 = Double.NEGATIVE_INFINITY;
        double max4 = Double.NEGATIVE_INFINITY;
        double max5 = Double.NEGATIVE_INFINITY;
        for (int i = 1; i < prediction.length; i++) {
            if(prediction[i] > max1) {
                indexMax5 = indexMax4; max5 = max4;
                indexMax4 = indexMax3; max4 = max3;
                indexMax3 = indexMax2; max3 = max2;
                indexMax2 = indexMax1; max2 = max1;
                indexMax1 = i; max1 = prediction[i];
            } else if(prediction[i] > max2) {
                indexMax5 = indexMax4; max5 = max4;
                indexMax4 = indexMax3; max4 = max3;
                indexMax3 = indexMax2; max3 = max2;
                indexMax2 = i; max2 = prediction[i];
            } else if(prediction[i] > max3) {
                indexMax5 = indexMax4; max5 = max4;
                indexMax4 = indexMax3; max4 = max3;
                indexMax3 = i; max3 = prediction[i];
            } else if(prediction[i] > max4) {
                indexMax5 = indexMax4; max5 = max4;
                indexMax4 = i; max4 = prediction[i];
            } else if(prediction[i] > max5) {
                indexMax5 = i; max5 = prediction[i];
            }
        }
        int[] res = {indexMax1, indexMax2, indexMax3, indexMax4, indexMax5};
        return res;
    }

    public static void reportTopPredictions(double[] prediction, Lookup lookup) {
        int[] top5Indexes = getTop5Indexes(prediction);
        double normConstant = expAndSum(prediction);
        System.out.print("Preds: ");
        for (int index : top5Indexes) {
            System.out.print(lookup.getIndexToStringMap().get(index) + " " +
//                    " [index " + index + "] " +
//                    + prediction[index] + " " +
                    percentFormat.format((double) Math.exp(prediction[index]) / normConstant) + " ");
        }
    }

    public static double expAndSum(double[] prediction) {
        double sum = 0;
        for (int i = 0; i < prediction.length; i++) {
            sum += Math.exp(prediction[i]);
        }
        return sum;
    }
}
