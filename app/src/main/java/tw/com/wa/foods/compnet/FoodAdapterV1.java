package tw.com.wa.foods.compnet;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import tw.com.wa.foods.R;
import tw.com.wa.foods.domain.Food;
import tw.com.wa.foods.domain.FoodBuild;

/**
 * Created by 1109001 on 2015/5/22.
 */
public class FoodAdapterV1 extends BaseAdapter {

    private List<Food> foodList = FoodBuild.build();
    private LayoutInflater myInflater;

    public FoodAdapterV1(Context ct) {
        myInflater = LayoutInflater.from(ct);
    }

    @Override
    public int getCount() {
        return foodList.size();
    }

    @Override
    public Object getItem(int position) {
        return foodList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        //自訂類別，表達個別listItem中的view物件集合。


        ViewTag viewTag = null;


        if (convertView == null) {
            //取得listItem容器 view
            convertView = myInflater.inflate(R.layout.item_layout, null);

            //建構listItem內容view
            viewTag = new ViewTag(
                    (TextView) convertView.findViewById(
                            R.id.item_name),
                    (Button) convertView.findViewById(
                            R.id.d_btn), convertView.findViewById(R.id.card_view));


            //設置容器內容
            convertView.setTag(viewTag);


        } else {
            viewTag = (ViewTag) convertView.getTag();
        }


        final Food food =
                foodList.get(position);

        //設定內容文字
        viewTag.addBtn.setText(food.getName());

        viewTag.getAddBtn().setOnClickListener(new AddEvent(viewTag, food));
        viewTag.getReduceBtn().setOnClickListener(new ReduceEvent(viewTag, food));


        if (food.getNo() > 0) {
            viewTag.addBtn.setText(String.format("%d,%s", food.getNo(), food.getName()));
            viewTag.rootView.setBackgroundColor(Color.LTGRAY);
        } else {
            viewTag.addBtn.setText(String.format("%s", food.getName()));
            viewTag.rootView.setBackgroundColor(Color.WHITE);
        }


        return convertView;
    }

    private class AddEvent implements View.OnClickListener {

        private ViewTag tag;
        private Food food;

        public AddEvent(ViewTag tag, Food food) {
            this.tag = tag;
            this.food = food;
        }

        @Override
        public void onClick(View v) {
            food.setNo(food.getNo() + 1);
            tag.getAddBtn().setText(String.format("%d,%s", food.getNo(), food.getName()));
            tag.rootView.setBackgroundColor(Color.LTGRAY);
        }
    }

    private class ReduceEvent implements View.OnClickListener {

        private ViewTag tag;
        private Food food;

        public ReduceEvent(ViewTag tag, Food food) {
            this.tag = tag;
            this.food = food;
        }

        @Override
        public void onClick(View v) {


            if (food.getNo() > 0) {
                food.setNo(food.getNo() - 1);


                if (food.getNo() == 0) {
                    tag.addBtn.setText(String.format("%s", food.getName()));
                    tag.rootView.setBackgroundColor(Color.WHITE);
                } else {
                    tag.getAddBtn().setText(String.format("%d,%s", food.getNo(), food.getName()));
                    tag.rootView.setBackgroundColor(Color.LTGRAY);
                }


            }
        }
    }

    public void clean() {


        for (Food f : foodList) {
            f.setNo(0);
        }

        notifyDataSetChanged();
    }


    //自訂類別，表達個別listItem中的view物件集合。
    class ViewTag {
        TextView addBtn;
        Button reduceBtn;
        private View rootView;

        public ViewTag(TextView addBtn, Button reduceBtn, View rootView) {
            this.addBtn = addBtn;
            this.reduceBtn = reduceBtn;
            this.rootView = rootView;
        }

        public TextView getAddBtn() {
            return addBtn;
        }

        public void setAddBtn(TextView addBtn) {
            this.addBtn = addBtn;
        }

        public Button getReduceBtn() {
            return reduceBtn;
        }

        public void setReduceBtn(Button reduceBtn) {
            this.reduceBtn = reduceBtn;
        }
    }


    public List<Food> getFoodList() {
        return foodList;
    }
}
