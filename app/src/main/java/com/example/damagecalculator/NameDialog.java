package com.example.damagecalculator;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

public class NameDialog extends DialogFragment {

    public interface OnInputListener{
        void sendInput(String input);
    }
    public OnInputListener onInputListener;

    EditText characterName;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.dialog_name, container, false);
        FloatingActionButton done = view.findViewById(R.id.name_done_button);
        FloatingActionButton cancel = view.findViewById(R.id.name_cancel_button);
        characterName = view.findViewById(R.id.character_create_name);

        done.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String text = characterName.getText().toString();
                if(!text.isEmpty()) {
                    onInputListener.sendInput(text);
                    getDialog().dismiss();
                }
            }
        });

        cancel.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                getDialog().dismiss();
            }
        });

        return view;
    }

    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        try{
            onInputListener = (OnInputListener) getActivity();
        } catch (ClassCastException e){
            Log.e("NameDialog", "onAtttach: ClassCastException: " + e.getMessage());
        }
    }
}
