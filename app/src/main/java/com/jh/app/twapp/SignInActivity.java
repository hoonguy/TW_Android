package com.jh.app.twapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.concurrent.ExecutionException;

public class SignInActivity extends AppCompatActivity {

    private EditText    mEdt_SignId, mEdt_SignPw, mEdt_SignChkPw;
    private Button mBtn_Crt_Acnt;
    private String      mSign_Result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        mEdt_SignId = (EditText) findViewById(R.id.edt_sign_id);
        mEdt_SignPw = (EditText) findViewById(R.id.edt_sign_pw);
        mEdt_SignChkPw  = (EditText) findViewById(R.id.edt_sign_cnf_pw);
        mBtn_Crt_Acnt   = (Button) findViewById(R.id.btn_creat_account);

        mBtn_Crt_Acnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                twMember sMember = new twMember();
                sMember.setMem_id(mEdt_SignId.getText().toString());
                sMember.setMem_pw(mEdt_SignPw.getText().toString());
                String chkpw = mEdt_SignChkPw.getText().toString();
                if (sMember.getMem_pw().equals(chkpw)) {
                    ServerConnect svr_conn = new ServerConnect();
                    try {
                        mSign_Result = svr_conn.execute(sMember.getMem_id(), sMember.getMem_pw(), "signin").get();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }
                } else { Toast.makeText(SignInActivity.this, "Not Verified Password, Try Again", Toast.LENGTH_SHORT).show(); }
            }
        });

        Intent sign_intent = getIntent();
        sign_intent.putExtra("Sign_Result", mSign_Result);
        setResult(101, sign_intent);
    }
}
