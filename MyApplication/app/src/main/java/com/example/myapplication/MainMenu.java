package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import static android.speech.tts.TextToSpeech.ERROR;

import java.util.Locale;


public class MainMenu extends AppCompatActivity {
    private TextToSpeech sp;

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainmenu);

        Button MainMenubtn1=(Button) findViewById(R.id.menu1);
        Button MainMenubtn2=(Button) findViewById(R.id.menu2);


        sp = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status != ERROR) {
                    sp.setLanguage(Locale.KOREAN);
                }
            }
        });



        MainMenubtn1.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                String totalSpeak = "카메라가 정면을 향하도록 해주세요";
                sp.speak(totalSpeak,TextToSpeech.QUEUE_FLUSH, null);
                sp.setSpeechRate(1.0f); //1배속으로 읽기


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
