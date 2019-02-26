package com.swe.duckware.megalexa;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.swe.duckware.megalexa.alexa.Workflow;

import java.util.List;

public class workflowAdapter extends RecyclerView.Adapter<workflowAdapter.MyViewHolder> {
    private List<Workflow> workflowList;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView nomeWf;
        public MyViewHolder(View v) {
            super(v);
            nomeWf= (TextView) itemView.findViewById(R.id.list_item_workflow_labelName);

        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public workflowAdapter(List<Workflow> mConnList) {
        workflowList =mConnList;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public workflowAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                           int viewType) {
        // create a new view
        View v =  LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_workflow, parent, false);
        //...
        return new MyViewHolder(v);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Workflow workflow = workflowList.get(position);
        holder.nomeWf.setText(workflow.getWfName());
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return workflowList.size();
    }
}