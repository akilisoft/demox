package  com.akilisoft.demox.ui.login;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.akilisoft.demox.MainActivity;
import com.akilisoft.demox.R;


public class LoginActivity extends AppCompatActivity {

    private TextView tx1;
    private int counter = 3;
    private TableSQLiteController tableSQLiteController;
    private Button loginButton;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final EditText usernameEditText = findViewById(R.id.editTextTextPersonName);
        final EditText passwordEditText = findViewById(R.id.editTextTextPassword);
        loginButton = findViewById(R.id.button);
        tableSQLiteController = new TableSQLiteController(this);

        tx1 = findViewById(R.id.textView4);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                login(usernameEditText.getText().toString(),passwordEditText.getText().toString());
                /**
                if(usernameEditText.getText().toString().equals("admin") &&
                        passwordEditText.getText().toString().equals("admin")) {
                    //Toast.makeText(getApplicationContext(),"Redirecting...",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getBaseContext(), MainActivity.class));
                }else{
                    Toast.makeText(getApplicationContext(), "Indentifiants invalides",Toast.LENGTH_SHORT).show();
                    //insertUser("test","test","78232322","M","test","test",  "test","test","burkina");

                    tx1.setVisibility(View.VISIBLE);
                    tx1.setBackgroundColor(Color.RED);
                    counter--;
                    tx1.setText(Integer.toString(counter));

                    if (counter == 0) {
                        loginButton.setEnabled(false);
                    }
                }
                **/
            }
        });


    }

    public void login(String email,String password)
    {
        boolean isAuth = tableSQLiteController.auth(email,password);
        if(isAuth){

            Intent i = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(i);
            finish();
        }else {

            tx1.setVisibility(View.VISIBLE);
            tx1.setBackgroundColor(Color.RED);
            counter--;
            tx1.setText(Integer.toString(counter));


            if (counter == 0) {
                loginButton.setEnabled(false);
            }

            final AlertDialog.Builder alertDialog = new AlertDialog.Builder(LoginActivity.this);
            alertDialog.setTitle("Note d'information");
            alertDialog.setMessage("Donneés incorrect");

            insertUser("test","test","78232322","M",email,password,  "test","test","burkina");
            alertDialog.setPositiveButton("OK",new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog,int whichButton)
                {
                    //finish();
                }});
            alertDialog.create();
            alertDialog.show();
        }

    }

    public void insertUser(String nom,String prenom,String phone,String sexe,String email,String password, String role,String country,String city)
    {

        if(!(email.isEmpty()  || password.isEmpty() || role.isEmpty())){

            try {

                tableSQLiteController.userCreate(nom,prenom,phone,sexe,email,password,role,country,city,"");
                Toast.makeText(getApplicationContext(), "user created",Toast.LENGTH_SHORT).show();

            }catch (Exception e){
                e.printStackTrace();
            }

        }else {
            Toast.makeText(getApplicationContext(), "user not created",Toast.LENGTH_SHORT).show();
            //requireMessage();
        }


    }


}