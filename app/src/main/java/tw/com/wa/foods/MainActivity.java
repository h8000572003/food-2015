package tw.com.wa.foods;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import java.util.List;

import tw.com.wa.foods.compnet.FoodAdapter;
import tw.com.wa.foods.compnet.FoodAdapterV1;
import tw.com.wa.foods.domain.Food;


public class MainActivity extends ActionBarActivity {


    private static final String TAG = "MainActivity";
    private RecyclerView mRecyclerView;
    private FoodAdapter mAdapter;
    private LayoutManager mLayoutManager;


    private Button cleanBtn = null;
    private Button resultBtn = null;
    private FoodAdapterV1 foodAdapterV1 = null;

    private GridView gridView;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 500) {
            mAdapter.clean();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private boolean isCheckPaid() {
        return true;
    }

    private void handTabPhone() {


        this.gridView = (GridView) this.findViewById(R.id.gridView);
        this.resultBtn = (Button) this.findViewById(R.id.resultBtn);

        this.foodAdapterV1 = new FoodAdapterV1(this);

        this.gridView.setAdapter(this.foodAdapterV1);

        this.resultBtn = (Button) this.findViewById(R.id.resultBtn);

        this.resultBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                int totalMoney = 0;

                StringBuffer bur = new StringBuffer();
                bur.append("============\n");
                final List<Food> foods = foodAdapterV1.getFoodList();


                for (Food f : foods) {
                    if (f.getNo() > 0) {
                        bur.append(f.getNo());
                        bur.append(" X ");
                        bur.append(f.getName());
                        bur.append("(");
                        bur.append(f.getDollar());
                        bur.append(")");

                        bur.append(" : " + f.getDollar() * f.getNo());
                        bur.append("\n");
                        totalMoney += f.getDollar() * f.getNo();
                    }
                }
                bur.append("============\n");
                bur.append(String.format("$%d\n", totalMoney));

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setMessage(bur.toString());
                builder.setNegativeButton("確定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        foodAdapterV1.clean();
                    }
                });
                builder.setPositiveButton("有錯在修改", null);

                builder.show();

            }
        });

    }

    private void handlePhone() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);



        setContentView(R.layout.activity_main);

        if (this.isCheckPaid()) {
            this.handTabPhone();
        } else {
            this.handlePhone();
        }


    }


}
