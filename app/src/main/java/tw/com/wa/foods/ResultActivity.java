package tw.com.wa.foods;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.TextView;

/**
 * Created by Andy on 15/5/18.
 */
public class ResultActivity extends ActionBarActivity {

    private TextView textView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result_layout);

        this.textView = (TextView) this.findViewById(R.id.textView);

        String message = getIntent().getStringExtra("message");
        this.textView.setText(message);
    }
}
