package edu.nyu.TweetMap;

import edu.nyu.TweetMap.stream.TwitterStream;
import edu.nyu.TweetMap.Elasticsearch.*;
public class Main {
	public static void main(String [] args) {
//		TwitterStream stream = new TwitterStream();
//		Thread tweetStream = new Thread(stream);
//		tweetStream.start();
		Elasticsearch.ElasticFetch();
	}
}
