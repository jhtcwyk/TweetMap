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

public class Main {
	public static void main(String [] args) {
//		TwitterStream stream = new TwitterStream();
//		Thread tweetStream = new Thread(stream);
//		tweetStream.start();
		System.out.println(Elasticsearch.ElasticFetchWithLocation("29.6185208", "-95.6090009", "10000"));

	}
}
