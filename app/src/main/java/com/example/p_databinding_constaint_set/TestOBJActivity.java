package com.example.p_databinding_constaint_set;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TestOBJActivity extends AppCompatActivity {
    private ConstraintLayout container;
    private Button btnTest;
    private ConstraintUtils constraintUtils;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_o_b_j);

        init();
    }

    private void init() {
        container = findViewById(R.id.conTainer);
        btnTest =  findViewById(R.id.btnTest);
        constraintUtils = new ConstraintUtils(this,container);
        btnTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                test();
            }
        });
    }

    public void test() {
        constraintUtils.setBarHeight(R.id.conBar3,0.3f);
    }
}
