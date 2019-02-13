package com.company.shir.myastrologicalkit;

import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class YesNoFragment extends DialogFragment {

    private String question;
    private YesNoFragmentListener yesNoFragmentListener;
    private Button btnYes, btnNo;
    

    public void setQuestion(String question) {
        this.question = question;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_yesno, container, false);
        TextView lblQuestion = view.findViewById(R.id.lblQuestion);
        if (question != null) {
            lblQuestion.setText(question);
        }
        btnYes = view.findViewById(R.id.btnYes);
        btnNo = view.findViewById(R.id.btnNo);
        btnYes.setOnClickListener(listener);
        btnNo.setOnClickListener(listener);
        return view;
    }

    private final View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            boolean isYes = false;
            switch (v.getId()) {
                case R.id.btnYes:
                    isYes = true;
                    break;
                case R.id.btnNo:
                    isYes = false;
                    break;
            }
            if (yesNoFragmentListener != null)
                yesNoFragmentListener.onChoose(isYes);
            dismiss();
        }
    };

    public void setYesNoFragmentListener(YesNoFragmentListener yesNoFragmentListener){
        this.yesNoFragmentListener = yesNoFragmentListener;
    }

    public  interface YesNoFragmentListener{
        void onChoose(boolean isYes);
    }
}

