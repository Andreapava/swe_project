package com.swe.duckware.megalexa;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.swe.duckware.megalexa.alexa.Workflow;
import java.util.ArrayList;
import java.util.List;

public class WorkflowActivity extends AppCompatActivity implements  creaWfDialogFragment.passaNomeActivity{

    private ArrayList<Workflow> mWorkflows = new ArrayList<>();

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mWorkflowAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private List<Workflow> wf = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workflow);
        mRecyclerView = (RecyclerView) findViewById(R.id.listWorkflow);
        //RecyclerView setup

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);

        // specify an adapter (see also next example)
        mWorkflowAdapter = new workflowAdapter(wf);
        mRecyclerView.setAdapter(mWorkflowAdapter);
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
    @Override
    public void onOk(String text){
        wf.add(new Workflow(text));
        mWorkflowAdapter.notifyDataSetChanged();
    }

    public void addWorkflow(View view) {
        FragmentManager fm = getSupportFragmentManager();

        creaWfDialogFragment editNameDialogFragment = creaWfDialogFragment.newInstance("Some Title");

        editNameDialogFragment.show(fm, "fragment_edit_name");
    }

}
