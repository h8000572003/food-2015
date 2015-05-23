package tw.com.wa.foods.util;

import android.util.Log;

import java.util.List;

import tw.com.wa.foods.domain.Food;

/**
 * Created by Andy on 15/5/23.
 */
public class CommonUtils {
    private static final String TAG = "CommonUtils";

    /**
     * 計算金額
     * @param foods
     * @return
     */
    public static int countAllDollar(List<Food> foods) {
        int total = 0;
        for (Food f : foods) {
            total += f.getNo() * f.getDollar();
        }
        return total;
    }

    /**
     * 沒做任何事情僅紀錄
     * @param text
     */
    public static void doNothing(String text) {
        Log.i(TAG, text);
    }


}
