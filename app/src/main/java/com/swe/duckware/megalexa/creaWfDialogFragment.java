package com.swe.duckware.megalexa;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;


public class creaWfDialogFragment extends DialogFragment {



    private EditText mEditText;

    public interface passaNomeActivity{
        void onOk(String text);
    }

    public creaWfDialogFragment() {

        // Empty constructor is required for DialogFragment

        // Make sure not to add arguments to the constructor

        // Use `newInstance` instead as shown below

    }



    public static creaWfDialogFragment newInstance(String title) {

        creaWfDialogFragment frag = new creaWfDialogFragment();

        Bundle args = new Bundle();

        args.putString("title", title);

        frag.setArguments(args);

        return frag;

    }



    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.dialog_fragment_creazione_nuovo_workflow, container);
    }



    @Override

    public void onViewCreated(View view, Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);

        // Get field from view

        mEditText = (EditText) view.findViewById(R.id.txt_your_name);

        // Fetch arguments from bundle and set title

        String title = getArguments().getString("title", "Enter Name");

        getDialog().setTitle(title);

        // Show soft keyboard automatically and request focus to field

        mEditText.requestFocus();
        Button ok = (Button) view.findViewById(R.id.ok_nome);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                passaNomeActivity listener = (passaNomeActivity) getActivity();

                listener.onOk(mEditText.getText().toString());

                // Close the dialog and return back to the parent activity

                dismiss();
            }
        });
        getDialog().getWindow().setSoftInputMode(

                WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);



    }

}