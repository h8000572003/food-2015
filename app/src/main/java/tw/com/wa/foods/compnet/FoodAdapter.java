package tw.com.wa.foods.compnet;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import tw.com.wa.foods.R;
import tw.com.wa.foods.domain.Food;
import tw.com.wa.foods.domain.FoodBuild;


/**
 * Created by Andy on 15/5/18.
 */
public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.ViewHolder> {

    private List<Food> foodList = FoodBuild.build();


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_layout, viewGroup, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, int i) {
        final Food f = foodList.get(i);

        viewHolder.lable.setText(String.format(f.getName() + ":%d", f.getNo()));
        viewHolder.lable.setBackgroundColor(Color.WHITE);

        if (f.getNo() > 0) {
            viewHolder.lable.setBackgroundColor(Color.LTGRAY);
        }

        viewHolder.lable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int no = f.getNo();
                f.setNo(++no);
                viewHolder.lable.setText(String.format(f.getName() + ":%d", f.getNo()));
                viewHolder.lable.setSelected(true);

            }
        });

        viewHolder.reduceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (f.getNo() > 0) {
                    int no = f.getNo();
                    f.setNo(--no);
                    viewHolder.lable.setText(String.format(f.getName() + ":%d", f.getNo()));
                    viewHolder.lable.setSelected(true);
                }
                if (f.getNo() == 0) {
                    viewHolder.lable.setSelected(false);
                }


            }
        });

    }

    @Override
    public int getItemCount() {
        return this.foodList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView lable;

        private Button reduceBtn;

        public ViewHolder(View itemView) {
            super(itemView);


            this.reduceBtn = (Button) itemView.findViewById(R.id.d_btn);
            this.lable = (TextView) itemView.findViewById(R.id.item_name);
        }
    }

    public void clean() {


        for (Food f : foodList) {
            f.setNo(0);
        }

        notifyDataSetChanged();
    }


    public List<Food> getFoodList() {
        return this.foodList;
    }


}
