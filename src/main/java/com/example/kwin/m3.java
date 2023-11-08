package com.example.kwin;

import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

public class m3 extends AppCompatActivity {

    NotificationManager manager;
    NotificationCompat.Builder builder;
    private static String CHANNEL_ID = "channel1";
    private static String CHANEL_NAME = "Channel1";
    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.m3);
        // 대화신청 버튼 클릭 시
        Button btnQuit = findViewById(R.id.btn_noti);
        btnQuit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();
            }
        });

    }
    public void showNoti () {
        builder = null;
        manager = (NotificationManager)
                getSystemService(NOTIFICATION_SERVICE); //버전 오레오 이상일 경우
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            manager.createNotificationChannel(new NotificationChannel(CHANNEL_ID, CHANEL_NAME,
                    NotificationManager.IMPORTANCE_DEFAULT));
            builder = new NotificationCompat.Builder(this, CHANNEL_ID); //하위 버전일 경우
        } else {
            builder = new NotificationCompat.Builder(this);
        }
        //알림창 제목
        builder.setContentTitle("알림");
        //알림창 메시지
        builder.setContentText("멘토님의 대화신청이 도착했습니다!");
        //알림창 아이콘
        builder.setSmallIcon(R.drawable.kwin);
        Notification notification = builder.build();
        //알림창 실행
        manager.notify(1, notification);
    }

    private void showDialog() {
        AlertDialog.Builder msgBuilder = new AlertDialog.Builder(m3.this)
                .setTitle("멘티님에게").setMessage("대화 신청을 하시겠습니까?")
                .setPositiveButton("네", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(m3.this, "멘티님에게 1:1 대화신청 알람이 전송되었습니다", Toast.LENGTH_SHORT).show();
                        showNoti(); }


                })
                .setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(m3.this, "취소하였습니다", Toast.LENGTH_SHORT).show();
                    }
                });
        AlertDialog msgDlg = msgBuilder.create();
        msgDlg.show();
    }
}
