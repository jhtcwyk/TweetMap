package edu.nyu.TweetMap;

import edu.nyu.TweetMap.stream.TwitterStream;

public class Main {
	public static void main(String [] args) {
		TwitterStream stream = new TwitterStream();
		Thread tweetStream = new Thread(stream);
		tweetStream.start();
	}
}
