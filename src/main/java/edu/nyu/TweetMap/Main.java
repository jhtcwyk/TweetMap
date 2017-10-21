package edu.nyu.TweetMap;

import edu.nyu.TweetMap.stream.TwitterStream;

import org.elasticsearch.client.Response;
import org.elasticsearch.client.RestClient;
import org.apache.http.entity.ContentType;

import java.io.IOException;
import java.util.Collections;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.nio.entity.NStringEntity;
import edu.nyu.TweetMap.Elasticsearch.*;



import edu.nyu.TweetMap.Elasticsearch.*;

public class Main {
	public static void main(String [] args) {
//		TwitterStream stream = new TwitterStream();
//		Thread tweetStream = new Thread(stream);
//		tweetStream.start();

		
		RestClient client = RestClient.builder(new HttpHost("", 443, "https")).build();
		//Map<String, String> params = Collections.singletonMap("date", "true");
		try {
			Response response = client.performRequest("POST", "/tweet/tweet/_search");
			System.out.println(response.toString());
			System.out.println(response.getEntity().toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//, params);

		Elasticsearch.ElasticFetch();

	}
}
