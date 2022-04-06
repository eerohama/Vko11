package com.example.vko10;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.internal.ContextUtils;

import java.util.HashMap;
import java.util.Locale;

public class Settings extends AppCompatActivity {
    private Spinner sSize;
    private Spinner sColour;
    private Spinner sBackground;
    private Spinner sHeight;
    private Spinner sLanguage;
    private EditText edtText;
    private HashMap<String, Integer> colourCodes;
    private ChangeData CD;
    private Button btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        btn = (Button) findViewById(R.id.button2);
        edtText = (EditText) findViewById(R.id.editDisplayText);
        sSize = (Spinner) findViewById(R.id.spinnerSize);
        sColour = (Spinner) findViewById(R.id.spinnerColor);
        sBackground = (Spinner) findViewById(R.id.spinnerBackground);
        sHeight = (Spinner) findViewById(R.id.spinnerHeight);
        sLanguage = (Spinner) findViewById(R.id.spinnerLang);
        CD = ChangeData.getInstance();
        if(CD.getStatus() == null){
            btn.setText(getResources().getString(R.string.disable_editing));
        } else {
            btn.setText(CD.getStatus());
        }

        initializeSpinners();

        colourCodes = new HashMap<String, Integer>();
        colourCodes.put(getResources().getString(R.string.black), 0xff000000);
        colourCodes.put(getResources().getString(R.string.white), 0xffffffff);
        colourCodes.put(getResources().getString(R.string.blue), 0xff0000ff);
        colourCodes.put(getResources().getString(R.string.red), 0xffff0000);
        colourCodes.put(getResources().getString(R.string.yellow), 0xffffff00);
        colourCodes.put(getResources().getString(R.string.green), 0xff00ff00);
        colourCodes.put(getResources().getString(R.string.magenta), 0xffff00ff);

    }

    public void initializeSpinners(){
        String def = getResources().getString(R.string.defaultt);
        String[] size_list = {def, "4", "8", "12", "16", "20", "24", "28", "32", "36", "40"};
        String[] colours_list = {def, getResources().getString(R.string.black), getResources().getString(R.string.white), getResources().getString(R.string.blue), getResources().getString(R.string.red), getResources().getString(R.string.yellow), getResources().getString(R.string.green), getResources().getString(R.string.magenta)};
        String[] height_list = {def, "30", "60", "90", "120", "150", "180", "210", "240", "270", "300"};
        String[] language_list = {"English", "Suomi", "Svenska"};

        // String[] colours_list = {"Default", "Black", "White", "Blue", "Red", "Yellow", "Green", "Magenta"};

        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this,   android.R.layout.simple_spinner_dropdown_item, size_list);
        sSize.setAdapter(adapter1);

        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this,   android.R.layout.simple_spinner_dropdown_item, colours_list);
        sColour.setAdapter(adapter2);

        ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(this,   android.R.layout.simple_spinner_dropdown_item, colours_list);
        sBackground.setAdapter(adapter3);

        ArrayAdapter<String> adapter4 = new ArrayAdapter<String>(this,   android.R.layout.simple_spinner_dropdown_item, height_list);
        sHeight.setAdapter(adapter4);

        ArrayAdapter<String> adapter5 = new ArrayAdapter<String>(this,   android.R.layout.simple_spinner_dropdown_item, language_list);
        sLanguage.setAdapter(adapter5);
    }

    public void applyChanges(View view){
        String def = getResources().getString(R.string.defaultt);

        if(sColour.getSelectedItem().toString().equals(def) == false){
            int colour1 = colourCodes.get(sColour.getSelectedItem().toString());
            CD.changeTextColour(colour1);
        }
        if(sBackground.getSelectedItem().toString().equals(def) == false) {
            int colour2 = colourCodes.get(sBackground.getSelectedItem().toString());
            CD.changeBackgroundColour(colour2);
        }
        if(sSize.getSelectedItem().toString().equals(def) == false) {
            float f = Float.parseFloat(sSize.getSelectedItem().toString());
            CD.changeTextSize(f);
        }
        if(sHeight.getSelectedItem().toString().equals(def) == false) {
            int i = Integer.parseInt(sHeight.getSelectedItem().toString());
            CD.changeBackgroundHeight(i);
        }
        if(edtText.getText().length() != 0){
            CD.setDisplayText(edtText.getText().toString());
        }
    }

    /*
    public void changeLanguage(){
        Locale locale = new Locale("fi");
        Locale.setDefault(locale);
        Configuration config = getBaseContext().getResources().getConfiguration();
        config.locale = locale;

    }


    @Override
    protected void attachBaseContext(Context newBase) {
        Locale localeToSwitchTo = new Locale("fi");
        ContextWrapper localeUpdatedContext = ContextUtils.updateLocale(newBase, localeToSwitchTo);
        super.attachBaseContext(localeUpdatedContext);

    }
    

     */
    public void buttonClick(View view){
        String enable = getResources().getString(R.string.allow_editing);
        String disable = getResources().getString(R.string.disable_editing);

        if(btn.getText().equals(disable)){
            CD.disableEdit(getResources().getString(R.string.readable), enable);
            btn.setText(enable);
        } else {
            CD.enableEdit(getResources().getString(R.string.writeable), disable);
            btn.setText(disable);
        }
    }
}
