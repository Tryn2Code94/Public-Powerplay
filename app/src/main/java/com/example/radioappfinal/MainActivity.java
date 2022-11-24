package com.example.radioappfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private Button loginButton = (Button) findViewById(R.id.b_goToSongInfo);
    EditText editUser;
    EditText editPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loginButton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {

               editUser = (EditText) findViewById(R.id.editTextUserName);
               editPassword =(EditText) findViewById(R.id.editTextUserName);
               if(editUser.length() == 0){
                    openDialogMissingUserName();
               }
               if(editPassword.length() == 0){
                   openDialogMissingPassword();
               }
               else {
                   checkUser(editUser, editPassword);
                   openUserPage(userType);
               }
           }
        });
    }

    public String userType;
    public boolean authenticated;
    public String nickName;

    public void openUserPage(String userType) {
        if (authenticated = true) {
            if(userType == "auditor") {
                Intent intent = new Intent(this, Activity_SongInfo.class);
                startActivity(intent);
            }
            if(userType == "moderator") {
                Intent intent = new Intent(this, Activity_currentRating.class);
                startActivity(intent);
            }
        }
        else{
            openDialogUnauthentified();
        }
    }
    public void checkUser(EditText editUser, EditText editPassword){
        if(editUser.toString() == "Thommy" && editPassword.toString() == "strenggeheim") {
            userType = "moderator";
            authenticated = true;
            nickName = "Tommy";
        }
        if(editUser.toString() == "Irmgard" && editPassword.toString() == "geheim"){
            userType = "auditor";
            authenticated = true;
            nickName = "Tante Irmgard";
        }
        else{
            authenticated = false;
            userType = "unknown";
        }
    }
    public void openDialogMissingUserName(){
        DialogMissingUserName dialogMissingUserName = new DialogMissingUserName();
        dialogMissingUserName.show(getSupportFragmentManager(), "Missing user name");
    }
    public void openDialogMissingPassword(){
        DialogMissingPassword dialogMissingPassword = new DialogMissingPassword();
        dialogMissingPassword.show(getSupportFragmentManager(), "Missing Password");
    }
    public void openDialogUnauthentified(){
        DialogUnauthentified dialogUnauthentified = new DialogUnauthentified();
        dialogUnauthentified.show(getSupportFragmentManager(), "Unknown user");
    }

}