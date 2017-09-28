package com.jh.app.twapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    private EditText mEdt_Id, mEdt_Pw;
    private Button mBtn_Login, mBtn_Forgot, mBtn_Sign, mBtn_fb, mBtn_google;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEdt_Id = (EditText) findViewById(R.id.edt_id);
        mEdt_Pw = (EditText) findViewById(R.id.edt_pw);
        mBtn_Login  = (Button) findViewById(R.id.btn_login);
        mBtn_Sign   = (Button) findViewById(R.id.btn_signin);
        mBtn_Forgot = (Button) findViewById(R.id.btn_forgot_idpw);
        mBtn_fb     = (Button) findViewById(R.id.btn_facebook);
        mBtn_google = (Button) findViewById(R.id.btn_google);

        mBtn_Login.setOnClickListener(btn_Listener);
        mBtn_Sign.setOnClickListener(btn_Listener);
    }

    View.OnClickListener btn_Listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String id = mEdt_Id.getText().toString();
            String pw = mEdt_Pw.getText().toString();
            ServerConnect svr_conn = new ServerConnect();
            String  return_str = null;

            switch (v.getId()) {
                case R.id.btn_login:
                    try {
                        return_str = svr_conn.execute(id, pw, "login").get();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }
                    break;
                case R.id.btn_signin:
                    Intent call_sign = new Intent(MainActivity.this, SignInActivity.class);
                    startActivityForResult(call_sign, 100);   // Request Code : 100
                    break;
            }
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode) {
            case 101 :
                AlertDialog.Builder sign_dlg = new AlertDialog.Builder(MainActivity.this);
                sign_dlg.setPositiveButton("Verify", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                sign_dlg.setMessage("Your Account is Registered on " + data);
                sign_dlg.show();
                break;
        }
    }
}
