package com.example.memoryafterservice;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.memoryafterservice.dto.LoginReq;
import com.example.memoryafterservice.dto.MemberReq;
import com.example.memoryafterservice.retrofit.RetrofitClient;
import com.kakao.auth.ISessionCallback;
import com.kakao.network.ErrorResult;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.MeV2ResponseCallback;
import com.kakao.usermgmt.response.MeV2Response;
import com.kakao.util.exception.KakaoException;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.security.MessageDigest;
import java.util.Objects;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    private Toast toast;
    private EditText inputEditId;
    private EditText inputEditPw;
    private Button buttonLogin;
//    public final String pref_name;

    private ISessionCallback mSessionCallback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        inputEditId = findViewById(R.id.idEditText);
        inputEditPw = findViewById(R.id.pwEditText);
        buttonLogin = findViewById(R.id.LoginButton);

//        int color = Color.parseColor("#ECECEC");

//        inputEditPw.setBackgroundColor(color);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                doLogin();
            }
        });

        mSessionCallback = new ISessionCallback() {
            @Override
            public void onSessionOpened() {

                UserManagement.getInstance().me(new MeV2ResponseCallback()
                {
                    @Override
                    public void onFailure(ErrorResult errorResult) {
                        Toast.makeText(LoginActivity.this, "????????? ????????? ????????? ??????????????????..",
                                Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onSessionClosed(ErrorResult errorResult) {
                        Toast.makeText(LoginActivity.this, "????????? ???????????????..?????? ??????????????????",
                                Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onSuccess(MeV2Response result) {

                        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                        intent.putExtra("profileImg", result.getKakaoAccount().getProfile().getProfileImageUrl());
                        intent.putExtra("email", result.getKakaoAccount().getEmail());
                        startActivity(intent);

                        Toast.makeText(LoginActivity.this, "???????????????!",
                                Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onSessionOpenFailed(KakaoException exception) {

            }
        };

        Objects.requireNonNull(getSupportActionBar()).hide();
        toast = Toast.makeText(this, "?????? ?????? ??? ?????????.", Toast.LENGTH_SHORT);

        String text = getResources().getString(R.string.LoginFindPwId);
        SpannableString str = new SpannableString(text);

        Intent moveId = new Intent(this, FindIdActivity.class);
        Intent movePw = new Intent(this, FindPwActivity.class);

        ClickableSpan findId = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View view) {
                startActivity(moveId);
            }
        };
        ClickableSpan findPw = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View view) {
                startActivity(movePw);
            }
        };

        str.setSpan(findId, 13, 19, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        str.setSpan(findPw, 23, 27, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        TextView findIdPw = findViewById(R.id.FindIdPw);
        findIdPw.setText(str, TextView.BufferType.SPANNABLE);
        findIdPw.setMovementMethod(LinkMovementMethod.getInstance());
    }

    public void doLogin() {
        final String userid = inputEditId.getText().toString().trim();
        String password = inputEditPw.getText().toString().trim();

        if (userid.isEmpty()) {
            inputEditId.setError("???????????? ?????????????????????.");
            inputEditId.requestFocus();
            return;
        } else if (password.isEmpty()) {
            inputEditPw.setError("??????????????? ?????????????????????.");
            inputEditPw.requestFocus();
            return;
        }

        Call<ResponseBody> call = RetrofitClient
                .getInstance()
                .getAuthApi()
                .login(new LoginReq(userid, password));

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                String s = "";
                String msg = "";
                JSONObject member;
                String name = "";
                String phone = "";
                Long id = Long.valueOf(0);
                try {
                    s = response.body().string();
//                    Log.d("myTag", s);
                    JSONObject json = new JSONObject(s);
                    Log.d("myTag1", json.toString());
                    msg = json.getString("message");
//                    Log.d("myTag2", msg);
                    member = json.getJSONObject("member");
                    name = member.getString("name");
                    phone = member.getString("phone");
                    id = member.getLong("id");
//                    Log.d("myTag3", name);
                } catch (IOException | JSONException e) {
                    e.printStackTrace();
                }

                if ("success".equals(msg)) {
                    Toast.makeText(LoginActivity.this, "????????? ??????", Toast.LENGTH_LONG).show();
                    SharedPreferences prefs = getSharedPreferences("prefVars",MODE_PRIVATE);
                    SharedPreferences.Editor editor = prefs.edit();
                    editor.putString("prefName", name);
                    editor.putString("prefUserid", userid);
                    editor.putString("prefPhone", phone);
                    editor.putLong("prefId", id);
                    editor.commit();
                    startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                    finish();
                } else {
                    Toast.makeText(LoginActivity.this, "????????? ?????? ", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(LoginActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();

            }
        });


    }
//    public void doLogin(View view){
//        toast.show();
//        Intent intent = new Intent(this, HomeActivity.class);
//        startActivity(intent);
//        finish();
//    }


    public void launchTOUActivity(View view){
        Intent intent = new Intent(this, TermOfUseActivity.class);
        intent.putExtra("loggedIn", false);
        startActivity(intent);
    }

    private void getAppKeyHash(){
        try{
            PackageInfo info = getPackageManager().getPackageInfo(getPackageName(), PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures){
                MessageDigest md;
                md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                String something = new String(Base64.encode(md.digest(), 0));
                Log.e("Hash key", something);
            }
        } catch (Exception e) {
            Log.e("name not found", e.toString());
        }
    }

    public void DoSocialLogin(View view){
        toast.show();
    }
}