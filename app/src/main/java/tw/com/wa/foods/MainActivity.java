package tw.com.wa.foods;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.util.List;

import tw.com.wa.foods.tw.com.wa.foods.domain.Food;


public class MainActivity extends ActionBarActivity {


    private static final String TAG = "MainActivity";
    private RecyclerView mRecyclerView;
    private FoodAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;


    private Button cleanBtn = null;
    private Button resultBtn = null;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 500) {
            mAdapter.clean();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        mAdapter = new FoodAdapter();
        mRecyclerView.setAdapter(mAdapter);

        this.cleanBtn = (Button) this.findViewById(R.id.cleanBtn);
        this.resultBtn = (Button) this.findViewById(R.id.resultBtn);


        this.cleanBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAdapter.clean();
            }
        });

        // ===============
        // 1x
        // 2x
        //         dx
        //  ================
        this.resultBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(MainActivity.this, ResultActivity.class);


                int totalMoney = 0;

                StringBuffer bur = new StringBuffer();
                bur.append("============\n");
                List<Food> foods = mAdapter.getFoodList();


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

                it.putExtra("message", bur.toString());

                Log.v(TAG, bur.toString());
                startActivityForResult(it, 500);
            }
        });

    }



}
