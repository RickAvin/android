package com.example.testandroid.Login;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.testandroid.Basicos.BaseActivity;
import com.example.testandroid.Main.ListaActivity;
import com.example.testandroid.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends BaseActivity {

    @BindView(R.id.txt_email) EditText email;
    @BindView(R.id.txt_password) EditText password;
    @BindView(R.id.btn_login) Button sigIn;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        getDatos();
        onClick();
    }

    @Override
    public int getLayout() {
        return R.layout.activity_login;
    }

    private void onClick(){
        sigIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validarCampos();
            }
        });
    }

    private void validarCampos(){
        String emailS = email.getText().toString();
        String pass = password.getText().toString();
        if(email == null || emailS.trim().equals("")){
            toast(getResources().getString(R.string.email_vacio));
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(emailS).matches()){
            toast(getResources().getString(R.string.email_incorrecto));
            return;
        }

        if(password == null || pass.trim().equals("")){
            toast(getResources().getString(R.string.contraseña_incorrecta));
            return;
        }


        loguearse(emailS,pass);
    }

    private void loguearse(String email,String password){
        if(email.equals("test@mail.com") && password.equals("test123")){
            setDatos(email, password);
            navegarALista();
        } else{
            toast(getResources().getString(R.string.datos_incorrectos));

        }
    }

    private void toast(String mensaje){
        Toast.makeText(LoginActivity.this,mensaje,Toast.LENGTH_LONG).show();
    }

    private void navegarALista(){
        Intent intent = new Intent(LoginActivity.this, ListaActivity.class);
        startActivity(intent);
        finish();
    }

    private void setDatos(String email,String password){
        SharedPreferences preferences = getSharedPreferences("tokenTest",MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("password",password);
        editor.putString("email",email);
        editor.commit();
    }

    private void getDatos(){
        SharedPreferences preferences = getSharedPreferences("tokenTest", MODE_PRIVATE);
        String pass = preferences.getString("password", "");
        String email = preferences.getString("email", "");
        if(!email.equals("") && !pass.equals("")){
            navegarALista();
        }
    }




}
