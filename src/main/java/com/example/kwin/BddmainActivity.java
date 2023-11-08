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

public class BddmainActivity extends AppCompatActivity {
    // 멘티추가메뉴
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu2, menu);
        return true;
    }

    // 멘티 추가 시 Bdd.java 연결
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.mentee:
                Intent intent = new Intent(getApplicationContext(),
                        Bdd.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    ListView list;
    String[] titles = {
            "#진로고민 #전공공부법 #알려줄분_구함 #법대생 #멘탈관리법",
            "#뷰티유튜버를_꿈꾸는 #코덕 #1학년 #화장품추천_좋아요 #패션공유",
            "#새내기캠퍼스_적응중 #학구열높음 #코딩하는_문과생 #정보융합학부 #복수전공_예정",
            "#그림초보 #그림그리는취미 #아직은_배우는중 #영감얻는법 #그림잘그리는법",
            "#독서가취미 #도서추천 #추리소설환영 #국어국문과",
            "#클래식기타 #한달차 #악보공유_원해요 #같이_연습할분",
            "#꾸안꾸룩연출법 #유행하는패션공유 #스트릿패션 #힙한패션 #악세사리추천",
            "#주식 #주린이 #종목추천 #경제신문_읽는법 #20대_1억모으기",
            "#운동 #축구 #풋살할분모집 #입대_한달전 #현실조언구함",
            "#사진잘찍는법 #카메라랑_친해지는법 #꿀팁공유 #원해요 #카메라추천 #필터추천 #나만의_감성사진_건지는법",
            "#이런식으로 #멘티도 #추가할수있다"
    } ;
    Integer[] images = {
            R.drawable.n1,
            R.drawable.n2,
            R.drawable.n3,
            R.drawable.n4,
            R.drawable.n5,
            R.drawable.n6,
            R.drawable.n7,
            R.drawable.n8,
            R.drawable.n9,
            R.drawable.n10,
            R.drawable.ic_image
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bactivity_main);


        CustomList adapter = new
                CustomList(BddmainActivity.this);
        list = (ListView) findViewById(R.id.list);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Intent intent = new Intent(getApplicationContext(),
                        m4.class);
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
