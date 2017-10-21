package edu.nyu.TweetMap.stream;

import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.JsonArray;

import  edu.nyu.TweetMap.TweetUtil.*;

import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;

import edu.nyu.TweetMap.Elasticsearch.*;

public class TwitterStream implements Runnable{
	private final twitter4j.TwitterStream stream;
	private final TwitterStatusListener listener;
	public TwitterStream() {
		stream = this.getStream();
		listener = new TwitterStatusListener() {
            @Override
            public void onStatus(Status status) {
                if (status.getGeoLocation() != null) {
                    String date = TweetDateConverter.convert(status.getCreatedAt());
                    Tweet tweet = new Tweet(status.getId(), status.getUser().getScreenName(),
                            status.getText(), date, status.getGeoLocation());

                    //System.out.println(tweet.toString());
                    sendToES(tweet);
                }
            }
		};
		this.stream.addListener(listener);
	}
	
	private twitter4j.TwitterStream getStream(){
		 ConfigurationBuilder configurationBuilder = new ConfigurationBuilder();
	     configurationBuilder.setOAuthConsumerKey("BQPKAeLI4lIPwQOnNUZNm1Upd")
	                		 .setOAuthConsumerSecret("tW6ROqeTKlnMo8AMm97BPB0n9QRLZhKlACE7pVTeEruCAUJP67")
	                		 .setOAuthAccessToken("777988292365152256-27k8NplOUEwE8gOVKl8JOW0VgEDqcP7")
	                		 .setOAuthAccessTokenSecret("tEHv93r3eY04osTgUOpeX2aU9rfYEx3mMP35jvacgIYQI");
	     return new TwitterStreamFactory(configurationBuilder.build()).getInstance();
	}
	
    @Override
    public void run() {
        stream.sample("en");
    }
    
    private void sendToES(Tweet t) {

        try {
            Elasticsearch.ElasticIndex(new Gson().toJson(t));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
