package com.example.shark.pa1;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView textView;
    private EditText id_text;
    private EditText pw_text;
    private Button button;
    private TextView error_text;
    static final String USER_ID = "2014314356";
    static final String USER_PW = "1234";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView)findViewById(R.id.login_text);
        id_text = (EditText)findViewById(R.id.login_id);
        pw_text = (EditText)findViewById(R.id.login_pw);
        button = (Button)findViewById(R.id.login_button);
        error_text = (TextView)findViewById(R.id.error);
        final Intent intent = new Intent(MainActivity.this, TodoActivity.class);
        id_text.setOnKeyListener(new View.OnKeyListener(){
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event){
                if((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    return true;
                }
                return false;
            }
        });

        pw_text.setOnKeyListener(new View.OnKeyListener(){
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event){
                if((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    hidekeyboard(pw_text);
                    button.performClick();
                    return true;
                }
                return false;
            }
        }); button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(id_text.getText().toString().equals(USER_ID) && pw_text.getText().toString().equals(USER_PW)) {
                    error_text.setText(" ");
                    startActivity(intent);
                }
                else{
                    error_text.setText("아이디 또는 패스워드가 \n일치하지 않습니다.");
                }
            }
        });


    }
    private void hidekeyboard(EditText ex){
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(ex.getWindowToken(), 0);
    }
}
