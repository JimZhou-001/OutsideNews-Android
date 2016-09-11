package com.blogspot.jimzhou001.outsidenews;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class FeedbackActivity extends Activity {

    private ImageView feedback_back;
    private Button commit_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        feedback_back = (ImageView)findViewById(R.id.feedback_back);
        feedback_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        commit_button = (Button)findViewById(R.id.commit);
        commit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!(((EditText)findViewById(R.id.feedbackContent)).getText().toString().isEmpty())) {
                    Toast.makeText(FeedbackActivity.this, "非常感谢您的反馈，我们将尽快作出改善", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

}
