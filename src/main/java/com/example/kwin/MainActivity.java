package com.example.kwin;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    // 멘토추가메뉴
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    // 멘토 추가 시 Add.java 연결
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.mento:
                Intent intent = new Intent(getApplicationContext(),
                        Add.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    ListView list;
    String[] titles = {
            "#정융과탑 #파이썬장인 #소통 #아낌없이주는나무 #DS전공 #학점관리",
            "#밴드 #일렉트릭기타 #락앤롤",
            "#보드타는법 #스케이트보드추천 #100여가지기술보유 #홍대출몰",
            "#취준생 #스펙쌓는법 #전정대환영 #열정맨 #진로고민상담 #언제든환영",
            "#스트릿우먼파이터 #춤을_배워보자 #몸치환영 #춤으로_나를표현하기",
            "#운동에_꽤나진심 #우이천런닝맨 #같이_운동할분 #정신건강",
            "#몸의균형맞추는법 #필라테스 #요가 #플라잉요가 #스트레스_해소법",
            "#VT전공 #디자인제작 #프론트엔드를_꿈꾸는 #정융",
            "#졸업예정자 #막학기 #무엇이든_물어보살 #광운대맛집정복 #자취n년차 #꿀팁전수",
            "#노래잘부르는법 #사실춤도전문가 #광운대장범준 #하고싶다면_지르고봐 #가성진성두성"
    };
    Integer[] images = {
            R.drawable.m1,
            R.drawable.m2,
            R.drawable.m3,
            R.drawable.m4,
            R.drawable.m5,
            R.drawable.m6,
            R.drawable.m7,
            R.drawable.m8,
            R.drawable.m9,
            R.drawable.m10
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        CustomList adapter = new
                CustomList(MainActivity.this);
        list = (ListView) findViewById(R.id.list);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Intent intent = new Intent(getApplicationContext(),
                        m1.class);
                startActivity(intent);
            }

        });
    }

    public class CustomList extends ArrayAdapter<String> {
        private final Activity context;

        public CustomList(Activity context) {
            super(context, R.layout.listitem, titles); // listitem에 titles 뿌려주기
            this.context = context;
        }

        @Override
        public View getView(int position, View view, ViewGroup parent) {
            LayoutInflater inflater = context.getLayoutInflater();
            View rowView = inflater.inflate(R.layout.listitem, null, true);
            ImageView imageView = (ImageView) rowView.findViewById(R.id.image);
            TextView title = (TextView) rowView.findViewById(R.id.title);


            title.setText(titles[position]);
            imageView.setImageResource(images[position]);
            return rowView;
        }

    }

}
