package com.example.vko10;

import android.widget.EditText;
import android.widget.TextView;



public class ChangeData {
    private static ChangeData instance = null;
    private TextView txtView;
    private EditText edtTxt;
    private String status;

    public ChangeData(){

    }

    public static ChangeData getInstance() {
        if(instance == null){
            instance = new ChangeData();
        }
        return instance;
    }


    public String getStatus(){
        return status;
    }
    public void setEditText(EditText edt){
        this.edtTxt = edt;
    }

    public void disableEdit(String text, String enable){
        edtTxt.setFocusable(false);
        edtTxt.setEnabled(false);
        txtView.setText(edtTxt.getText());
        edtTxt.setText(text);
        edtTxt.setHint("");
        status = enable;

    }

    public void enableEdit(String text, String disable){
        edtTxt.setFocusableInTouchMode(true);
        edtTxt.setEnabled(true);
        edtTxt.setText("");
        edtTxt.setHint(text);
        status = disable;
    }

    public void setTextView(TextView t){
        this.txtView = t;
    }

    public void setDisplayText(String text){
        txtView.setText(text);
    }

    public void changeTextColour(int colour){
        txtView.setTextColor(colour);
    }

    public void changeTextSize(float size){
        txtView.setTextSize(size);
    }

    public void changeBackgroundColour(int colour){
        txtView.setBackgroundColor(colour);
    }

    public void changeBackgroundHeight(int h){
        txtView.setHeight(h);
    }

}
