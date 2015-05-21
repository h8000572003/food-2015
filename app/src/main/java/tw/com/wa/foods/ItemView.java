package tw.com.wa.foods;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import tw.com.wa.foods.tw.com.wa.foods.domain.Food;

/**
 * Created by Andy on 15/5/18.
 */
public class ItemView extends LinearLayout {
    private int no = 0;
    private TextView label = null;
    private Button addBtn;
    private Button reduceBtn;
    private Food food = null;

    public int getNo() {
        return no;
    }

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }

    private void init(Context context) {

        final LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);


        mInflater.inflate(R.layout.item_layout, this, true);



        this.reduceBtn = (Button) this.findViewById(R.id.d_btn);
        this.label = (TextView) this.findViewById(R.id.item_name);


        this.addBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                label.setText(String.format(food.getName() + " :%d ", ++no));
            }
        });

        this.reduceBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                if (no > 0) {
                    label.setText(String.format(food.getName() + " :%d ", --no));
                } else {
                    // do nothiing
                }

            }
        });


    }

    public ItemView(Context context) {
        super(context);

    }

    public ItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.init(context);
    }
}
