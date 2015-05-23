package tw.com.wa.foods.compnet;

import android.content.Context;
import android.text.Html;
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
    private FoodAdapterV1.OnClickListener listener;

    public interface OnClickListener {
        void click(List<Food> foods);
    }


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
                            R.id.d_btn), convertView);


            //設置容器內容
            convertView.setTag(viewTag);


        } else {
            viewTag = (ViewTag) convertView.getTag();
        }


        final Food food =
                foodList.get(position);

        //設定內容文字
        viewTag.addBtn.setText(food.getName());

        viewTag.getRootView().setOnClickListener(new AddEvent(viewTag, food));
        viewTag.getReduceBtn().setOnClickListener(new ReduceEvent(viewTag, food));


        setShowView(food, viewTag);


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
            setShowView(food, tag);

            if (listener != null) {
                listener.click(foodList);
            }
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
            }
            setShowView(food, tag);

            if (listener != null) {
                listener.click(foodList);
            }
        }
    }

    private void setShowView(Food food, ViewTag viewTag) {
        if (food.getNo() == 0) {


            StringBuffer buffer = new StringBuffer();
            buffer.append("<font color=#000000>" + food.getName() + "</font>");
            buffer.append("<font color=#FF0000>");
            buffer.append("$" + food.getDollar());
            buffer.append("</font>");

            viewTag.getAddBtn().setText(Html.fromHtml(buffer.toString()));
            viewTag.getReduceBtn().setEnabled(false);
            viewTag.getReduceBtn().setText("");
            viewTag.getRootView().setBackgroundResource(R.color.white);
        } else {
            viewTag.getReduceBtn().setEnabled(true);

            StringBuffer buffer = new StringBuffer();
            buffer.append("<font color=#FFFFF380>" + food.getName() + "</font>");
            buffer.append("<font color=#FF0000>");
            buffer.append("$" + food.getDollar());
            buffer.append("</font>");

            viewTag.getAddBtn().setText(Html.fromHtml(buffer.toString()));


            viewTag.getReduceBtn().setText(food.getNo() + "");

            viewTag.getRootView().setBackgroundResource(R.color.clickValue);
        }
    }

    public void clean() {


        for (Food f : this.foodList) {
            f.setNo(0);
        }
        if (this.listener != null) {
            this.listener.click(this.foodList);
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


        public View getRootView() {
            return rootView;
        }

        public void setRootView(View rootView) {
            this.rootView = rootView;
        }
    }


    public void setOnClickListener(FoodAdapterV1.OnClickListener listener) {
        this.listener = listener;
    }


    public List<Food> getFoodList() {
        return foodList;
    }
}
