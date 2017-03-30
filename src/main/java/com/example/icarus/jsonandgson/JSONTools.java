package com.example.icarus.jsonandgson;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by icarus9527 on 2017/3/8.
 */

public class JSONTools {

    //生成JSONObject对象
    public JSONObject getStudentJsonObject(Student s){
        JSONObject object = new JSONObject();
        try {
            object.put("id",s.getId());
            object.put("name",s.getName());
            object.put("age",s.getAge());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return object;
    }


    //解析JSON数据
    public List<Student> getStudentList(String key, String jsonString){
        List<Student> list = new ArrayList<Student>();
        try {
            JSONObject jsonObject = new JSONObject(jsonString);
            JSONArray studentArray = jsonObject.getJSONArray(key);

            for(int i=0; i<studentArray.length(); i++ ){
                JSONObject studentObject = studentArray.getJSONObject(i);
                Student s = new Student();
                int id = studentObject.getInt("id");
                String name = studentObject.getString("name");
                int age = studentObject.getInt("age");
                s.setId(id);
                s.setName(name);
                s.setAge(age);
                list.add(s);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }


    //利用Gson生成Json类型的String
    public String createJsonString(Object values){
        Gson gson = new Gson();
        String str = gson.toJson(values);
        return str;
    }

    public static List<Student> getStudentList(String jsonString) {
            List<Student> list = new ArrayList<>();
            Gson gson = new Gson();
             list = gson.fromJson(jsonString, new TypeToken<List<Student>>(){}.getType());
             return list;
        }

}
