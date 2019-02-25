package com.swe.duckware.megalexa;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ConnectorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connector);

        ((TextView) findViewById(R.id.workflowFatherName))
                .setText( getIntent().getStringExtra("workflow_name") );
    }
}
