package com.example.icarus.jsonandgson;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private TextView textView;
    private Button btn1,btn2,btn3,btn4;
    private JSONObject object;
    private JSONTools mJSONTools = new JSONTools();
    String jsonString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

    }

    private void initView() {
        textView = (TextView) findViewById(R.id.result);
        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);
        btn3 = (Button) findViewById(R.id.btn3);
        btn4 = (Button) findViewById(R.id.btn4);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Student> list = getStudentList();
                JSONArray jsonArray = new JSONArray();
                for (Student s:list){
                    JSONObject stuObj = mJSONTools.getStudentJsonObject(s);
                    jsonArray.put(stuObj);
                }
                object = new JSONObject();
                try {
                    object.put("Student",jsonArray);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                textView.setText(object.toString());
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jsonString = object.toString();

                List<Student> list = mJSONTools.getStudentList("Student",jsonString);
                String str ="";
                for (Student s:list){
                    str =str + s.getId()+"  "+s.getName()+"  "+s.getAge()+"  ";
                }

                textView.setText(str);
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Student> list = getStudentList();
                jsonString = mJSONTools.createJsonString(list);
                textView.setText(jsonString);
            }
        });

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Student> list = mJSONTools.getStudentList(jsonString);
                String str ="";
                for (Student s:list){
                    str =str + s.getId()+"  "+s.getName()+"  "+s.getAge()+"  ";
                }

                textView.setText(str);
            }
        });

    }



    public List<Student> getStudentList(){
        List<Student> studentList = new ArrayList<Student>();
        Student s1 = new Student(1,"zhangsan",18);
        Student s2 = new Student(1,"lisi",19);
        Student s3 = new Student(1,"wangwu",20);
        Student s4 = new Student(1,"zhaoliu",21);
        studentList.add(s1);
        studentList.add(s2);
        studentList.add(s3);
        studentList.add(s4);
        return studentList;
    }

}
