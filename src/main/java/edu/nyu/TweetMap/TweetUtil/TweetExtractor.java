package edu.nyu.TweetMap.TweetUtil;

import java.util.ArrayList;
import java.util.List;

import twitter4j.JSONArray;
import twitter4j.JSONException;
import twitter4j.JSONObject;

public class TweetExtractor {
    public static final void extractorTweet (String response) {
        JSONObject jObj = null;
        List<String> res = new ArrayList<>();
        try {
            jObj = new JSONObject(response);            
            if (jObj != null && jObj.has("hits")) {
                JSONObject jObj2 = jObj.getJSONObject("hits");
                JSONArray jArr = jObj2.getJSONArray("hits");
                for (int i = 0 ; i < jArr.length() ; i++) {
                    res.add(jArr.getString(i));
                }    
                
            }
            else {
                System.out.println("Empty!");
            }
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }            
        

        //System.out.println("tweets: " + res.toString());
        for (String str : res) {
            System.out.println(str);
        }
    }
}
