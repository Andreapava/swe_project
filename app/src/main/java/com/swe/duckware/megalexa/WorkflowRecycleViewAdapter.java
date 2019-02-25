package com.swe.duckware.megalexa;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.swe.duckware.megalexa.alexa.Workflow;

import java.util.List;

public class WorkflowRecycleViewAdapter extends RecyclerView.Adapter<WorkflowRecycleViewAdapter.ViewHolder> {

    private final List<Workflow> mWorkflows;

    public WorkflowRecycleViewAdapter(List<Workflow> workflows) {
        mWorkflows = workflows;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_workflow, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.workflow = mWorkflows.get(position);

        holder.workflowName.setText( mWorkflows.get(position).toString() );
        holder.workflowName.setOnClickListener(view -> {
            Intent intent = new Intent(holder.workflowName.getContext(), ConnectorActivity.class);
            intent.putExtra("workflow_name", holder.workflowName.getText());

            holder.workflowName.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return mWorkflows.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public final View parentView;
        public final TextView workflowName;
        public Workflow workflow;

        public ViewHolder(View itemView) {
            super(itemView);

            this.parentView = itemView;
            this.workflowName = itemView.findViewById(R.id.list_item_workflow_labelName);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + workflowName.getText() + "'";
        }
    }

}
