package com.swe.duckware.megalexa;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.swe.duckware.megalexa.alexa.Workflow;
import java.util.ArrayList;
import java.util.List;

public class WorkflowActivity extends AppCompatActivity {

    private ArrayList<Workflow> mWorkflows = new ArrayList<>();

    private RecyclerView mRecyclerView;

    private WorkflowRecycleViewAdapter mWorkflowAdapter = new WorkflowRecycleViewAdapter(mWorkflows);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workflow);

        //RecyclerView setup
        mRecyclerView = findViewById(R.id.listWorkflow);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        mRecyclerView.setAdapter(mWorkflowAdapter);

        //Test some workflows
        List<Workflow> wf = new ArrayList<>();
        wf.add(new Workflow("prova WF 1"));
        wf.add(new Workflow("prova WF 2"));

        setWorkflows(wf);
    }

    public void setWorkflows(List<Workflow> workflows) {
        for(Workflow workflow : workflows) {
            if (!mWorkflows.contains(workflow)) {
                mWorkflows.add(workflow);
                mWorkflowAdapter.notifyItemInserted(
                        mWorkflows.indexOf(workflow)
                );
            }
        }
    }

}
