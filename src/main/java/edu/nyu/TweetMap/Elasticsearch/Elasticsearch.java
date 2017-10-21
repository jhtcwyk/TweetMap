package edu.nyu.TweetMap.Elasticsearch;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Collections;
import java.util.Map;

import org.elasticsearch.client.HttpAsyncResponseConsumerFactory;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.RestClient;
import org.apache.http.entity.ContentType;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.nio.entity.NStringEntity;

public class Elasticsearch {
    private static final String host = "search-tweets-sc4gugsx3mijjwacjfi62plga4.us-east-1.es.amazonaws.com"; // e.g. my-test-domain.us-east-1.es.amazonaws.com
    private static final RestClient client = RestClient.builder(new HttpHost(host, 443, "https")).build();
    public static void ElasticIndex(String json) throws IOException {
        String index = "tweets_type";
        String type = "tweet_type";
        
        System.out.println(json);

        //RestClient client = RestClient.builder(new HttpHost(host, 443, "https")).build();

        HttpEntity entity = new NStringEntity(json, ContentType.APPLICATION_JSON);

        Response response = client.performRequest("POST", "/" + index + "/" + type + "/",
            Collections.<String, String>emptyMap(), entity);

        System.out.println(response.toString());
    }
    public static void ElasticFetch(String lat, String lon, String range) {
        // TODO add asyc
        //Map<String, String> params = Collections.singletonMap("distance", "1km");
        Response response;
        try {
            //response = client.performRequest("GET", "/tweet/_search/{");
            String str = "{\n" + 
                    "  \"query\": {\n" + 
                    "    \"bool\": {\n" + 
                    "      \"must\": {\n" +                               
                    "       \"match_all\" : {}\n" + 
                    "},\n" +
                    "           \"filter\": {\n"+
                    "        \"geo_distance\": {\n" + 
                    "          \"distance\": \""+ range +"km\", \n" + 
                    "          \"location\": { \n" + 
                    "            \"lat\":  "+ lat +",\n" + 
                    "            \"lon\":  "+ lon +"\n" + 
                    "          }\n" + 
                    "        }\n" + 
                    "      }\n" + 
                    "    }\n" + 
                    "  }\n" + 
                    "}";
            HttpEntity entity = new NStringEntity(str, ContentType.APPLICATION_JSON);
            response = client.performRequest("GET", "/tweets_type/tweet_type/_search", Collections.<String, String>emptyMap(), entity);
            BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            String temp;
            while((temp = reader.readLine()) != null) {
                System.out.println(temp);
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } 
        
    }
   
}