package com.example.sportsclub.Auth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import io.paperdb.Paper;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sportsclub.AdminCategoryActivity;
import com.example.sportsclub.HomeActivity2;
import com.example.sportsclub.Model.Users;

import com.example.sportsclub.Prevalent.Prevalent;
import com.example.sportsclub.R;
import com.example.sportsclub.Testpage;
import com.example.sportsclub.listview.firstpage;
import com.example.sportsclub.ui.home.Dashboard;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rey.material.widget.CheckBox;

public class LoginActivity extends AppCompatActivity {

       private EditText InputPhoneNumber, InputPassword;
       private Button LoginButton;
       private ProgressDialog loadingBar;
       private TextView AdminLink, NotAdminLink;

      private String parentDbName = "Users";
      private com.rey.material.widget.CheckBox chkBoxRememberMe;
      //  private  CheckBox chkBoxRememberMe;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        LoginButton= (Button) findViewById(R.id.login_btn);
        InputPassword = (EditText) findViewById(R.id.login_password_input);
        InputPhoneNumber = (EditText) findViewById(R.id.login_phone_number_input);
        AdminLink = (TextView) findViewById(R.id.admin_panel_link);
        NotAdminLink = (TextView) findViewById(R.id.not_admin_panel_link);
        loadingBar = new ProgressDialog(this);

        chkBoxRememberMe = (CheckBox) findViewById(R.id.remember_me);
        Paper.init(this);

        LoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginUser();

            }
        });
      // I am in admin to this
        AdminLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                LoginButton.setText("Login Admin");
                AdminLink.setVisibility(View.INVISIBLE);
                NotAdminLink.setVisibility(View.VISIBLE);
                parentDbName = "Admins";

            }
        });
     // I am not an admin to this
        NotAdminLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                LoginButton.setText("Login");
                AdminLink.setVisibility(View.VISIBLE);
                NotAdminLink.setVisibility(View.INVISIBLE);
                parentDbName = "Users";

            }
        });
    }

    private void LoginUser()

    {
        //Creating login user
        String phone = InputPhoneNumber.getText().toString();
        String password = InputPassword.getText().toString();

         if(TextUtils.isEmpty(phone))
         {
            Toast.makeText(this, "Please write your phone number...", Toast.LENGTH_SHORT).show();
         }
           else if(TextUtils.isEmpty(password))
          {
             Toast.makeText(this, "Please write your password...", Toast.LENGTH_SHORT).show();
          }
           else
         {
             loadingBar.setTitle("Login Account");
             loadingBar.setMessage("please wait, while we are checking the credentials.");
             loadingBar.setCanceledOnTouchOutside(false);
             loadingBar.show();

             AllowAccessToAccount(phone,password);

         }
    }

    private void AllowAccessToAccount(final String phone, final String password) {

        if(chkBoxRememberMe.isChecked())
        {
            //Calling paper class
            Paper.book().write(Prevalent.UserPhoneKey, phone);
            Paper.book().write(Prevalent.UserPasswordKey, password);
        }
        // Database ref  getting variable and variable creating the value of database
        final DatabaseReference RootRef;
        RootRef = FirebaseDatabase.getInstance().getReference();

        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot)
            {
                if(snapshot.child(parentDbName).child(phone).exists())
                {
                    //Getting phone number and password through child in firebase
                    Users usersData = snapshot.child(parentDbName).child(phone).getValue(Users.class);

                    if(usersData.getPhone().equals(phone))
                    {
                        if(usersData.getPassword().equals(password))
                        {
                            if(parentDbName.equals("Admins"))
                            {
                                //Admin user and password is correct
                                Toast.makeText(LoginActivity.this, "Welcome Admin, you are " +
                                        "logged in Successfully...", Toast.LENGTH_SHORT).show();
                                loadingBar.dismiss();

                                startActivity(new Intent(LoginActivity.this, AdminCategoryActivity.class));
                            }
                            else if (parentDbName.equals("Users"))
                            {
                                //When password is correct
                                Toast.makeText(LoginActivity.this, "logged in Successfully...", Toast.LENGTH_SHORT).show();
                                loadingBar.dismiss();
                                //Next activity
                                Intent intent = new Intent(LoginActivity.this, Dashboard.class);
                                startActivity(intent);
                            }

                        }
                        else
                        {
                            //When password is incorrect
                            loadingBar.dismiss();
                            Toast.makeText(LoginActivity.this, "Password is incorrect", Toast.LENGTH_SHORT).show();
                        }

                    }

                }
                else
                {
                    //When phone number don't exist
                    Toast.makeText(LoginActivity.this, "Account with this " + phone + "number do not exist.", Toast.LENGTH_SHORT).show();
                    loadingBar.dismiss();

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
