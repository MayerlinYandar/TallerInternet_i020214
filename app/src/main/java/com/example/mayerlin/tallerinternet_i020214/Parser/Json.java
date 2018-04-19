package com.example.mayerlin.tallerinternet_i020214.Parser;

import com.example.mayerlin.tallerinternet_i020214.Model.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mayerlin on 19/04/2018.
 */

public class Json {
    public static List<User> getData(String content) throws JSONException {

        JSONArray jsonArray = new JSONArray(content);
        List<User> userList = new ArrayList<>();

        for (int i = 0; i < jsonArray.length(); i++){

            JSONObject item = jsonArray.getJSONObject(i);

            User user = new User();
            user.setNombre(item.getString("nombre"));
           user.setCodigo(item.getString("codigo"));
            user.setPass(item.getString("pass"));
            user.setCorreo(item.getString("correo"));
            user.setEdad(item.getString("edad"));
            userList.add(user);

        }

        return userList;
    }
}
