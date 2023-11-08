package com.example.kwin;


import static android.content.ContentValues.TAG;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

public class Add extends AppCompatActivity {

    private static final String CHANNEL_ID = "channel1";
    private static final String CHANEL_NAME = "Channel1";
    // 멘토 추가 시 공백 없이 입력하기 위한 함수
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add);
        EditText name=findViewById(R.id.name);
        EditText college=findViewById(R.id.college);
        EditText major=findViewById(R.id.major);
        EditText grade=findViewById(R.id.grade);
        EditText introduce=findViewById(R.id.introduce);
        EditText notice2=findViewById(R.id.notice2);

        Button btnQuit = findViewById(R.id.bt_insert);
        btnQuit.setOnClickListener(new View.OnClickListener() {
            @Override // 추가 버튼 클릭시
            public void onClick(View view) {
                final String username=name.getText().toString();
                final String usercol=college.getText().toString();
                final String userma=major.getText().toString();
                final String usergr=grade.getText().toString();
                final String userint=introduce.getText().toString();
                final String userno=notice2.getText().toString();
                // 입력칸이 하나라도 비었을시 알림이 뜸.
                if(username.equals("")||usercol.equals("")||userma.equals("")||usergr.equals("")||userint.equals("")||userno.equals("")){
                    AlertDialog.Builder dialog=new AlertDialog.Builder(Add.this);
                    dialog.setIcon(R.mipmap.ic_main);
                    dialog.setTitle("알림");
                    dialog.setMessage("빈칸없이 입력하시오.");
                    dialog.setNegativeButton("확인",null); dialog.show(); }

                else
                    showDialog();
            }
        });

    }


    private void showDialog() {
        AlertDialog.Builder msgBuilder = new AlertDialog.Builder(Add.this)
                .setTitle("멘토 추가").setMessage("멘토를 등록 하시겠습니까?")
                .setPositiveButton("네", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(Add.this, "멘토 등록 완료", Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(getApplicationContext(),
                                    com.example.kwin.AddmainActivity.class);
                            startActivity(intent);


                    }


                })
                .setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(Add.this, "취소하였습니다", Toast.LENGTH_SHORT).show();
                    }
                });
        AlertDialog msgDlg = msgBuilder.create();
        msgDlg.show();
    }
    // 입력받은 데이터 생성
    ListView listView;
    TextView textView;
    EditText editText;
    Button button;

    ArrayAdapter<String> adapter;
    ArrayList<String> array = new ArrayList<>();

    // ArrayList -> Json으로 변환
    private static final String SETTINGS_PLAYER_JSON = "settings_item_json";


    public void showNoti () {
        setContentView(R.layout.activity_main);

        editText = (EditText) findViewById(R.id.introduce);
        listView = (ListView) findViewById(R.id.list);

        array = getStringArrayPref(getApplicationContext(), SETTINGS_PLAYER_JSON);

        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, array);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                String str = array.get(position);
                textView.setText(str);
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str = editText.getText().toString();
                if (str.length() != 0) {
                    array.add(str + "");
                    adapter.notifyDataSetChanged();

                    editText.setText("");
                }
            }
        });
    }


    private void setStringArrayPref(Context context, String key, ArrayList<String> values) {

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = prefs.edit();
        JSONArray a = new JSONArray();

        for (int i = 0; i < values.size(); i++) {
            a.put(values.get(i));
        }

        if (!values.isEmpty()) {
            editor.putString(key, a.toString());
        } else {
            editor.putString(key, null);
        }

        editor.apply();
    }

    private ArrayList getStringArrayPref(Context context, String key) {

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        String json = prefs.getString(key, null);
        ArrayList urls = new ArrayList();

        if (json != null) {
            try {
                JSONArray a = new JSONArray(json);

                for (int i = 0; i < a.length(); i++) {
                    String url = a.optString(i);
                    urls.add(url);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return urls;
    }

    @Override
    protected void onPause() {
        super.onPause();

        setStringArrayPref(getApplicationContext(), SETTINGS_PLAYER_JSON, array);
        Log.d(TAG, "Put json");
    }
}
