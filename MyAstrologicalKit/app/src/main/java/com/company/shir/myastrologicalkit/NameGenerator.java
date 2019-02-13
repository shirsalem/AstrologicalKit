package com.company.shir.myastrologicalkit;

import android.app.ActionBar;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class NameGenerator extends Fragment {
    private static final String TAG = "NameGenerator";
    public static final String SAVED_NAME = "savedName";
    private Notifyable notifyable;

    private EditText edtName;
    private TextView lblName;
    private TextView lblExplanation;
    private ImageView imgNumber;
    private TextView txtExplanation;
    private Button btnGenerate;
    private Button btnGoToNameList;
    private SharedPreferences prefs;
    private ListView listNames;
    private ActivityTransfarable activityTransfarable;
    private int summeryOfLetters;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.namegenerator_fragment, container, false);

        //id defining for views
        edtName = view.findViewById(R.id.edtName);
        lblName = view.findViewById(R.id.lblName);
        lblExplanation = view.findViewById(R.id.lblExplanation);
        imgNumber = view.findViewById(R.id.imgNumber);
        txtExplanation = view.findViewById(R.id.txtExplanation);
        btnGenerate = view.findViewById(R.id.btnGenerate);
        btnGoToNameList = view.findViewById(R.id.btnGoToNameList);
        listNames = view.findViewById(R.id.listNames);

        prefs = getActivity().getSharedPreferences("prefs", Context.MODE_PRIVATE);

        btnGenerate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //get text from text edit, also set all letters to lowercase in order that the summery will be the same all the time(also when its upperCase letters).
                String name = edtName.getText().toString().toLowerCase();
                //setting the user name into the letters char array.
                char[] letters = name.toCharArray();
                //calling the nameGenerate method
                nameGenerate(letters);
                //cleaning the name from the edit text
                edtName.getText().clear();
                //showing the typed name to the user, only upperCases
                lblName.setText(name.toUpperCase());
                //hides the keyboard while pressing the Generate button
                hideKeyboard();
                //turning on/off the saving name (based on validation)
                if (summeryOfLetters == 0)
                    btnGoToNameList.setEnabled(false);
                else
                    btnGoToNameList.setEnabled(true);

            }
        });


        saveTheName();
        final SharedPreferences.Editor editor = prefs.edit();

        btnGoToNameList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String savedName = lblName.getText().toString();
                activityTransfarable.updatePrefs("&" + savedName);
                notifyable.doNotified(savedName);
                btnGoToNameList.setEnabled(false);
                Toast.makeText(getActivity(), "Name successfully saved!", Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }


    //let's hide the keyboard (so we can use this again)
    public void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getView().getWindowToken(), 0);
    }

    public void nameGenerate(char[] letters) {
        //the calculation method:
        /*letters sum is a variable that count the letters: if 'shir' is a 4 letters name then 's':1, 'h':2..
        and its go though the array.*/
        int letterSum = 0;
        for (int i = 0; i < letters.length; i++) {
            letterSum++;

            //setting validations:
            if (letters[i] >= 0 && letters[i] <= 64)
                letters[i] = 0;
            if (letters[i] >= 91 && letters[i] <= 96)
                letters[i] = 0;
            if (letters[i] >= 123)
                letters[i] = 0;
        }

        //summery of letters = conclusion of all letters together
//        int summeryOfLetters = 0;
        summeryOfLetters = 0;
        for (int j = 0; j < letterSum; j++) {
            summeryOfLetters += (int) letters[j];
        }

        /**--          --          --          --          --          --          --**/

        //show the summery of letters to the user
        lblExplanation.setText("Your name letters summery is: " + summeryOfLetters + "\nYour numerological number is:");
        //setting the number pictures:
        if (summeryOfLetters >= 1 && summeryOfLetters <= 300) {
            imgNumber.setImageResource(R.drawable.one);
            //explanation of the value (located in string.xml):
            txtExplanation.setText(getString(R.string.optionOne));
        } else if (summeryOfLetters > 300 && summeryOfLetters <= 400) {
            imgNumber.setImageResource(R.drawable.two);
            //explanation of the value (located in string.xml):
            txtExplanation.setText(getString(R.string.optionTwo));
        } else if (summeryOfLetters > 400 && summeryOfLetters <= 500) {
            imgNumber.setImageResource(R.drawable.three);
            //explanation of the value (located in string.xml):
            txtExplanation.setText(getString(R.string.optionThree));
        } else if (summeryOfLetters > 500 && summeryOfLetters <= 600) {
            imgNumber.setImageResource(R.drawable.four);
            //explanation of the value (located in string.xml):
            txtExplanation.setText(getString(R.string.optionFour));
        } else if (summeryOfLetters > 600 && summeryOfLetters <= 700) {
            imgNumber.setImageResource(R.drawable.five);
            //explanation of the value (located in string.xml):
            txtExplanation.setText(getString(R.string.optionFive));
        } else if (summeryOfLetters > 700 && summeryOfLetters <= 800) {
            imgNumber.setImageResource(R.drawable.six);
            //explanation of the value (located in string.xml):
            txtExplanation.setText(getString(R.string.optionSix));
        } else if (summeryOfLetters > 800 && summeryOfLetters <= 900) {
            imgNumber.setImageResource(R.drawable.seven);
            //explanation of the value (located in string.xml):
            txtExplanation.setText(getString(R.string.optionSeven));
        } else if (summeryOfLetters > 900 && summeryOfLetters <= 1000) {
            imgNumber.setImageResource(R.drawable.eight);
            //explanation of the value (located in string.xml):
            txtExplanation.setText(getString(R.string.optionEight));
        } else if (summeryOfLetters > 1000) {
            imgNumber.setImageResource(R.drawable.nine);
            //explanation of the value (located in string.xml):
            txtExplanation.setText(getString(R.string.optionNine));
        } else if (summeryOfLetters == 0) {
            imgNumber.setImageResource(0);
            lblExplanation.setText("Sorry that is not a name,\nPlease enter any name");
            //explanation of the value:
            txtExplanation.setText("");
        }
    }

    //saving the names to SharedPreferences
    public void saveTheName() {
        prefs = this.getActivity().getSharedPreferences("prefs", Context.MODE_PRIVATE);
        String savedName = prefs.getString("savedName", null);
        if (savedName == null) {
            Toast.makeText(getActivity(), "Sorry, field is empty", Toast.LENGTH_SHORT).show();
        }
    }

    private void getPrefs() {
        String savedName = prefs.getString(SAVED_NAME, null);
    }

    public void setInterfaces(Notifyable notifyable, ActivityTransfarable activityTransfarable) {
        this.notifyable = notifyable;
        this.activityTransfarable = activityTransfarable;
    }
}

