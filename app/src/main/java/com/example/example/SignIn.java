package com.example.example;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.Nullable;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;
import java.util.regex.Pattern;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.example.data.LoginData;
import com.example.example.data.LoginResponse;
import com.example.example.network.RetrofitClient;
import com.example.example.network.ServiceApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SignIn extends AppCompatActivity {

    private ServiceApi service;
    private ProgressBar ProgressView;

    Button btn_register, btn_signin;

    //이메일과 비밀번호
    private EditText Edittext_email;
    private EditText Edittext_pw;

    //이메일이랑 비밀번호 입력 잘 됐는지 확인
    boolean testEmail = false, testPw = false, testSign=false;

    String email, password;

    View underbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        service = RetrofitClient.getClient().create(ServiceApi.class);
        ProgressView = (ProgressBar) findViewById(R.id.login_progress);

            //스플래쉬
        Intent intent = new Intent(this, SplashActivity.class);
        startActivity(intent);

            // 이메일, 패스워드
        Edittext_email = (EditText) findViewById(R.id.Edittext_email);
        Edittext_pw = (EditText) findViewById(R.id.Edittext_pw);
        btn_signin = (Button) findViewById(R.id.btn_signin);

        email = Edittext_email.getText().toString();
        password = Edittext_pw.getText().toString();

            // 아이디 이메일 양식인지 확인
        Edittext_email.setOnFocusChangeListener(new View.OnFocusChangeListener(){

            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus) {
                    Edittext_email.setTextColor(getResources().getColor(R.color.soft_black));
                }

                else{
                    Pattern p = Pattern.compile("^[_a-zA-Z0-9-\\.]+@[\\.a-zA-Z0-9-]+\\.[a-zA-Z]+$");
                    Matcher m = p.matcher((Edittext_email).getText().toString());

                    if (!m.matches()){
                        Toast.makeText(SignIn.this,"Please enter in email format.",Toast.LENGTH_LONG).show();
                        Edittext_email.setTextColor(getResources().getColor(R.color.coral_red));
                        testEmail = false;
                    }

                    else
                        testEmail = true;
                    InputMethodManager input = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
                    input.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);

                }
            }
        });

        Edittext_email.addTextChangedListener(new TextWatcher() {
            String str;
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                str =s.toString();
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Pattern p = Pattern.compile("^[_a-zA-Z0-9-\\.]+@[\\.a-zA-Z0-9-]+\\.[a-zA-Z]+$");
                Matcher m = p.matcher((Edittext_email).getText().toString());

                if (!m.matches()){
                    testEmail = false;
                }
                else
                    testEmail = true;
                if(testPw && testEmail){
                    testSign = true;
                    btn_signin.setBackgroundResource(R.drawable.button_background_full);
                    btn_signin.setTextColor(getResources().getColor(R.color.white));
                }
                else{
                    testSign = false;
                    btn_signin.setBackgroundResource(R.drawable.button_background_border);
                    btn_signin.setTextColor(getResources().getColor(R.color.blue));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        Edittext_pw.addTextChangedListener(new TextWatcher() {
            String str;
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                str = s.toString();
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                    if(s.length()>1){
                        testPw = true;
                    }
                    else{
                        testPw = false;
                    }
                //testEmail, testPw 모두 true 이면 sign in 활성화
                if(testPw && testEmail){
                    testSign = true;
                    btn_signin.setBackgroundResource(R.drawable.button_background_full);
                    btn_signin.setTextColor(getResources().getColor(R.color.white));
                }
                else{
                    testSign = false;
                    btn_signin.setBackgroundResource(R.drawable.button_background_border);
                    btn_signin.setTextColor(getResources().getColor(R.color.blue));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        // 로그인 버튼 이벤트
        btn_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //testSign이 true 일 때만 로그인 가능
                email = Edittext_email.getText().toString();
                password = Edittext_pw.getText().toString();
                if(testSign){
                    startLogin(new LoginData(email, password));
                    showProgress(true);
                }
            }
        });

    }

    private void startLogin(LoginData data) {
        service.userLogin(data).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                LoginResponse result = response.body();
                Toast.makeText(SignIn.this, result.getMessage(), Toast.LENGTH_SHORT).show();
                showProgress(false);

                if (result.getCode() == 200) {
                    Intent intent = new Intent(SignIn.this, MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Toast.makeText(SignIn.this, "로그인 에러 발생", Toast.LENGTH_SHORT).show();
                Log.e("로그인 에러 발생", t.getMessage());
                showProgress(false);
            }
        });
    }
    private void showProgress(boolean show) {
        ProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
    }

        //register 버튼. 누르면 register로
    public void onButtonClick(View view2){
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }
}
