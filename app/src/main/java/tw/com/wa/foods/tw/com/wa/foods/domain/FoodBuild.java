package tw.com.wa.foods.tw.com.wa.foods.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andy on 15/5/18.
 */
public class FoodBuild {


    public static List<Food> build() {
        final List<Food> foodList = new ArrayList();
        foodList.add(new Food("排骨意麵", 65));
        foodList.add(new Food("排骨河粉", 65));
        foodList.add(new Food("排骨冬粉", 65));
        foodList.add(new Food("排骨泡飯", 65));
        foodList.add(new Food("招牌肉粽", 40));
        foodList.add(new Food("蛋黃肉粽", 55));
        foodList.add(new Food("排骨酥湯", 50));
        foodList.add(new Food("貢丸湯", 25));
        foodList.add(new Food("蘿蔔湯", 30));
        foodList.add(new Food("燙青菜", 30));
        foodList.add(new Food("小菜", 30));
        foodList.add(new Food("黑白切", 40));
        foodList.add(new Food("滷肉飯", 25));
        foodList.add(new Food("控肉飯", 85));
        foodList.add(new Food("咖哩肉飯", 75));
        foodList.add(new Food("筒仔米糕", 30));
        foodList.add(new Food("碗粿", 30));
        foodList.add(new Food("乾意麵", 35));
        foodList.add(new Food("乾河粉", 35));
        foodList.add(new Food("乾冬粉", 35));
        foodList.add(new Food("冬瓜茶", 15));


        return foodList;
    }
}
