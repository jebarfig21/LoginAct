package com.example.loginact.ui.login;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.PowerManager;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.TextView;
import com.example.loginact.R;
import java.util.Locale;

public class Ejercicios extends Activity {

    TextView title;
    TextView tiempo;
    boolean soundExcersice = true;
    boolean soundRest = true;
    int repeticion = 1;
    final int restTime = 10000; //Millis de descanso
    final int activeTime = 50000; //Millis de descanso
    final int totalTime = restTime+activeTime; //Millis de descanso
    CountDownTimer contador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.gong);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ejercicios);
        final PowerManager powerManager = (PowerManager) getSystemService(POWER_SERVICE);
        final PowerManager.WakeLock wl = powerManager.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK,
                "MyApp::MyWakelockTag");
        title = (TextView) findViewById(R.id.titulo);
        tiempo = (TextView) findViewById(R.id.contador);

        contador = new CountDownTimer(totalTime, 10) {
            public void onTick(long millisRest) {
                wl.acquire();
                if (millisRest > restTime) {
                    if (soundExcersice) {
                        mediaPlayer.start();
                        soundExcersice = false;
                    }
                    title.setText("Ejercicio " + repeticion);
                    tiempo.setText("" + (millisRest - restTime) / 1000 + ":" + (millisRest - restTime) % 1000);
                } else {

                    if (soundRest) {
                        mediaPlayer.start();
                        soundRest = false;
                    }

                    if (repeticion == 10) {
                        title.setText("done!");
                        wl.release();
                        cancel();
                    }

                    title.setText("Descanso " + repeticion);
                    tiempo.setText("" + millisRest / 1000 + ":" + millisRest % 1000);
                }

            }


            public void onFinish() {
                repeticion++;
                soundExcersice =true;
                soundRest = true;
                start();

            }
        };
    }

    public void empezar (View v) {
        contador.start();
        }

 public void parar(View v) {
     title.setText("Iniciar");
     tiempo.setText("00:00");
     repeticion = 1;
     contador.cancel();
     soundExcersice = true;
     soundRest = true;
 }

 }

