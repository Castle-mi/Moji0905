package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;


public class MainMenu extends AppCompatActivity {

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainmenu);

        Button MainMenubtn1=(Button) findViewById(R.id.menu1);
        Button MainMenubtn2=(Button) findViewById(R.id.menu2);

        MainMenubtn1.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent=new Intent(getApplicationContext(),Menu1.class);
                startActivity(intent);
            }
        });

        MainMenubtn2.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent=new Intent(getApplicationContext(),Menu2.class);
                startActivity(intent);
            }
        });
    }

}
