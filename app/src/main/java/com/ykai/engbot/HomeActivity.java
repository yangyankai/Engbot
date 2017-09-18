package com.ykai.engbot;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.baidu.android.voicedemo.activity.ActivityMain;
import com.baidu.tts.sample.MainActivity;
import com.ykai.englishdialog.myapplication.ChatMainActivity;
import com.ykai.englishdialog.myapplication.ChatUtil;

/**
 * 取保手机有网
 * Created by ykai on 17/9/16.
 */
public class HomeActivity extends Activity {
    private Activity _this;

    EditText editText;
    TextView textView;

    Button btnVoice;
    Button btnTTS;
    Button btnChat;

    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (null != msg.obj) {
                textView.setText("" + msg.obj.toString());
            } else {
                textView.setText("null");
            }

        }
    };
    private String TAG = "yyk";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity);
        _this = this;


        editText = (EditText) findViewById(R.id.edit);
        textView = (TextView) findViewById(R.id.text);

        btnVoice = (Button) findViewById(R.id.voice_2_txt_btn);
        btnChat = (Button) findViewById(R.id.chat_btn);
        btnTTS = (Button) findViewById(R.id.txt_2_voice_btn);


        findViewById(R.id.voice_2_txt_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(_this, ActivityMain.class);
                startActivity(intent);
            }
        });

        btnChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new Thread(new Runnable() {
                    @Override
                    public void run() {


                        String strParam = editText.getText().toString();

                        ChatUtil.iCallBack = new ChatUtil.ICallBack() {
                            @Override
                            public void onSmartChatBack(String s) {


                                Log.d(TAG, "onSmartChatBack: " + s);
                                Message message = new Message();
                                message.obj = s;
                                mHandler.sendMessage(message);

                            }
                        };
                        ChatUtil.getEnglistReturn(strParam);

                    }
                }).start();

        }
            }

            );

            findViewById(R.id.txt_2_voice_btn)

            .

            setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick (View view){

                    Intent intent = new Intent(_this, MainActivity.class);
                    startActivity(intent);
                }
            }

            );


        }
    }
