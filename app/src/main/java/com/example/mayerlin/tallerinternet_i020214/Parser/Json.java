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
        
        List<User> postList = new ArrayList<>();

        for (int i = 0; i < jsonArray.length(); i++){

            JSONObject item = jsonArray.getJSONObject(i);

            Post post = new Post();
            post.setId(item.getInt("id"));
            post.setTitle(item.getString("title"));
            post.setBody(item.getString("body"));

            postList.add(post);

        }

        return postList;
    }
}
