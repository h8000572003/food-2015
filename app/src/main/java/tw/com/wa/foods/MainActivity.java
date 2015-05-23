package tw.com.wa.foods;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;

import java.util.List;

import tw.com.wa.foods.compnet.FoodAdapterV1;
import tw.com.wa.foods.domain.Food;
import tw.com.wa.foods.util.CommonUtils;


public class MainActivity extends ActionBarActivity {


    private static final String TAG = "MainActivity";


    private Button cleanBtn = null;
    private Button resultBtn = null;
    private FoodAdapterV1 foodAdapterV1 = null;

    private GridView gridView;

    private String OUT_TEMPLATE = " %s \t\t %s \t\t %s\n";


    private boolean isCheckPaid() {
        return true;
    }

    /**
     * 資料異動，更動畫面
     *
     * @param foods
     */
    private void setBtnView(List<Food> foods) {

        int totla = CommonUtils.countAllDollar(foods);

        if (totla == 0) {
            resultBtn.setText("算錢");
            resultBtn.setVisibility(View.GONE);
        } else {
            final String text = "<font color=#FF0000>$ %s</font>";
            final String out = String.format(text, String.valueOf(CommonUtils.countAllDollar(foods)));

            resultBtn.setText(Html.fromHtml(out));
            resultBtn.setVisibility(View.VISIBLE);
        }

    }

    private void handTabPhone() {
        this.gridView = (GridView) this.findViewById(R.id.gridView);
        this.resultBtn = (Button) this.findViewById(R.id.resultBtn);

        this.foodAdapterV1 = new FoodAdapterV1(this);
        this.foodAdapterV1.setOnClickListener(new FoodAdapterV1.OnClickListener() {
            @Override
            public void click(List<Food> foods) {
                MainActivity.this.setBtnView(foods);
            }
        });

        this.gridView.setAdapter(this.foodAdapterV1);

        this.resultBtn = (Button) this.findViewById(R.id.resultBtn);

        this.resultBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                StringBuffer bur = new StringBuffer();

                final List<Food> foods = foodAdapterV1.getFoodList();


                bur.append(String.format(OUT_TEMPLATE, "數量", "名稱", "金額"));

                for (Food f : foods) {
                    if (f.getNo() > 0) {
                        bur.append(String.format(OUT_TEMPLATE, f.getNo() + "", f.getName(), f.getDollar() * f.getNo() + ""));
                    }
                }

                bur.append(String.format("$%d\n", CommonUtils.countAllDollar(foods)));

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("總清單");
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
        CommonUtils.doNothing("處理平板暫不處理手機");
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
