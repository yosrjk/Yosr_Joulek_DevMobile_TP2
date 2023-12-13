package com.example.yosr_joulek_lsi3_mesure_de_glycemie.controller;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.yosr_joulek_lsi3_mesure_de_glycemie.model.User;

public class LoginController {
    private static LoginController instance = null;
    private static User user;
    private static final String SHARED_PREFS="sharedPrefs";

    private LoginController() {

        super();
    }
    public static  final LoginController getInstance(Context context){
        if(LoginController.instance==null) {
            LoginController.instance = new LoginController();
        }
        recapUser(context);
        return LoginController.instance;

    }
    public static  void recapUser(Context context){
        SharedPreferences sharedPreferences=context.getSharedPreferences(SHARED_PREFS,context.MODE_PRIVATE);
        String username=sharedPreferences.getString("username","");
        String password=sharedPreferences.getString("password","");
        user=new User(username,password);
    }
    public void CreateUser(String password,String username,Context context){
        this.user =new User(password,username);
        SharedPreferences sharedPreferences=context.getSharedPreferences(SHARED_PREFS,context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString("username",username);
        editor.putString("password",password);
        editor.apply();

    }

    public String getUsername() {
        return user.getUsername();
    }

    public String getPassword() {
        return user.getPassword();
    }
}
