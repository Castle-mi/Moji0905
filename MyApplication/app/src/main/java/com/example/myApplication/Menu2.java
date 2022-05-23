package com.example.myApplication;

import android.content.Intent;
import android.content.Context;

import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.speech.tts.TextToSpeech;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
import java.util.ArrayList;


import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;

public class Menu2 extends AppCompatActivity {

    Context cThis;
    Intent intent;
    SpeechRecognizer mRecognizer;

    TextToSpeech tts;

    EditText startStation;
    EditText finishStation;

    ImageButton sttBtn;
    ImageButton fnsBtn;
    Button sttBtn_txt;

    String key="Z3vnEoNpVs46712lc9pY4BwofmRW6PBWWb0UlLRRasU";
    String startId;
    String finishId;
    int startNum;
    int finishNum;



    //TextView textView;
    final int PERMISSION = 1;
    private final int RESULT_SPEECH = 1000;

    int check=0;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu2);

        cThis = this;

        //버튼설정
        startStation = (EditText) findViewById(R.id.startStaion);
        sttBtn = (ImageButton) findViewById(R.id.imagebutton1);

        finishStation = (EditText) findViewById(R.id.finishStation);
        fnsBtn = (ImageButton) findViewById(R.id.imagebutton2);


        sttBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                mRecognizer = SpeechRecognizer.createSpeechRecognizer(cThis);
                mRecognizer.setRecognitionListener(listener);

                check = 1;

                intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_PREFERENCE, "ko-KR");
                intent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE, getPackageName());
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_WEB_SEARCH);
                intent.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, 5);

                intent.putExtra(RecognizerIntent.EXTRA_PROMPT, getString(R.string.app_name));

                mRecognizer.startListening(intent);
                //startActivityForResult(intent, RESULT_SPEECH);
            }
        });

        fnsBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                mRecognizer = SpeechRecognizer.createSpeechRecognizer(cThis);
                mRecognizer.setRecognitionListener(listener);

                check = 2;

                intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_PREFERENCE, "ko-KR");
                intent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE, getPackageName());
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_WEB_SEARCH);
                intent.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, 5);

                intent.putExtra(RecognizerIntent.EXTRA_PROMPT, getString(R.string.app_name));

                mRecognizer.startListening(intent);
                //startActivityForResult(intent, RESULT_SPEECH);
            }
        });


    }
    
    public void search(View view){
        //사용자한테 출발, 도착역 알아오기
        startId=startStation.getText().toString();
        finishId=finishStation.getText().toString();

        new Thread(new Runnable() {
            @Override
            public void run() {
                //검색한 출발역, 도착역 id 얻기
                getStationId(startId, finishId);
                
                //정보 출력
                userWant(startNum, finishNum);
            }
        });


        
    }
    
    public void getStationId(String start, String finish){
        String stationUrl;
    }

    public void userWant(int start, int finish){

    }
    
    
    private RecognitionListener listener = new RecognitionListener() {
        @Override
        public void onReadyForSpeech(Bundle bundle) {

            //음성인식 시작할 때 알림창 뜨게 하려면 이 부분 어떻게 해야하는지 생각하기
            Toast.makeText(getApplicationContext(), "지금부터 말을 해주세요", Toast.LENGTH_LONG).show();

        }

        @Override
        public void onBeginningOfSpeech() {
        }

        @Override
        public void onRmsChanged(float v) {

        }

        @Override
        public void onBufferReceived(byte[] bytes) {

        }

        @Override
        public void onEndOfSpeech() {

        }

        @Override
        public void onError(int i) {
            String message;

            switch(i){
                case SpeechRecognizer.ERROR_AUDIO:
                    message = "오디오 에러";
                    break;
                case SpeechRecognizer.ERROR_CLIENT:
                    message = "클라이언트 에러";
                    break;
                case SpeechRecognizer.ERROR_INSUFFICIENT_PERMISSIONS:
                    message = "퍼미션 없음";
                    break;
                case SpeechRecognizer.ERROR_NETWORK:
                    message = "네트워크 에러";
                    break;
                case SpeechRecognizer.ERROR_NETWORK_TIMEOUT:
                    message = "네트워크 타임아웃";
                    break;
                case SpeechRecognizer.ERROR_NO_MATCH:
                    message = "찾을 수 없음";
                    break;
                case SpeechRecognizer.ERROR_RECOGNIZER_BUSY:
                    message = "RECOGNIZER가 바쁨";
                    break;
                case SpeechRecognizer.ERROR_SERVER:
                    message = "서버가 이상함";
                    break;
                case SpeechRecognizer.ERROR_SPEECH_TIMEOUT:
                    message = "말하는 시간초과";
                    break;
                default:
                    message = "알 수 없는 오류임";
                    break;
            }
            Toast.makeText(getApplicationContext(), "천천히 다시 말해주세요 : " + message, Toast.LENGTH_LONG).show();
        }

        @Override
        public void onResults(Bundle bundle) {
            ArrayList<String> mResult = bundle.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
            //System.out.println(SpeechRecognizer.RESULTS_RECOGNITION);

            if(check==1) {
                startStation.setText(mResult.get(0));

                for (int j = 1; j < mResult.size(); j++) {
                    startStation.setText(mResult.get(j));
                }
            }
            else if(check==2){
                finishStation.setText(mResult.get(0));

                for (int j = 1; j < mResult.size(); j++) {
                    finishStation.setText(mResult.get(j));
                }
            }
        }

        @Override
        public void onPartialResults(Bundle bundle) {

        }

        @Override
        public void onEvent(int i, Bundle bundle) {

        }
    };

    //음성메세지 출력용
    private void FuncVoiceOut(String OutMsg) {
        if (OutMsg.length() < 1) return;

        tts.setPitch(1.0f);  //목소리 톤 1.0
        tts.setSpeechRate(1.0f);   //목소리 속도
        tts.speak(OutMsg, TextToSpeech.QUEUE_FLUSH, null);
    }

}




