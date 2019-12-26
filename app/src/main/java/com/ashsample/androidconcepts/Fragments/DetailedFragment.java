package com.ashsample.androidconcepts.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.ashsample.androidconcepts.R;

public class DetailedFragment extends Fragment {
    public static final String ARGS_TEXT="key_text";

    public static  String args_text_val;


    public static DetailedFragment newInstance(String text){
        Log.i("Ashiq","newInstance===::"+text);
        DetailedFragment dfrag = new DetailedFragment();
        Bundle temp = new Bundle();
        temp.putString(ARGS_TEXT,text);
        dfrag.setArguments(temp);
        return dfrag;

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        Log.i("Ashiq","onCreate=============fragment");
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onAttach(Context context) {
        Log.i("Ashiq","onAttach=============");
        super.onAttach(context);
    }

    @Override
    public void onStop() {
        Log.i("Ashiq","onStop=============::"+args_text_val);
        super.onStop();
    }


    @Override
    public void onStart() {
        Log.i("Ashiq","onStart=============::"+args_text_val);
        super.onStart();
    }

    @Override
    public void onResume() {
        Log.i("Ashiq","onResume=============::"+args_text_val);
        super.onResume();
    }

    @Override
    public void onPause() {
        Log.i("Ashiq","onpause=============::"+args_text_val);
        super.onPause();
    }

    @Override
    public void onDestroy() {
        Log.i("Ashiq","onDestroy=============::"+args_text_val);
        super.onDestroy();
    }

    @Override
    public void onDestroyView() {
        Log.i("Ashiq","onDestroyView=============::"+args_text_val);
        super.onDestroyView();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.detailed_fragment,container,false);
        TextView text = view.findViewById(R.id.text_view_fragment);
        args_text_val = getArguments().getString(ARGS_TEXT);
        text.setText(args_text_val);
        Log.i("Ashiq","onCreateView=============::"+args_text_val);
        return view;
    }
}
