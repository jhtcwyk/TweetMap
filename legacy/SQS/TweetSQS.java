package edu.nyu.TweetMap.SQS;


import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.AmazonSQSException;
import com.amazonaws.services.sqs.model.CreateQueueRequest;
import com.amazonaws.services.sqs.model.SendMessageRequest;

import java.util.List;

public class TweetSQS {

    private static final AmazonSQS sqs;
    private static String queue_url = "";

    static {   
        sqs = AmazonSQSClientBuilder.standard()
        							.withRegion("us-east-1")
        							.build();
    }

    private static void createQueue() {
        System.out.println("Creating new Querue.");
        CreateQueueRequest create_request = new CreateQueueRequest("Tweets");
        try {
            sqs.createQueue(create_request);
        } catch (AmazonSQSException e) {
        	System.err.println(e.getMessage());
            if (!e.getErrorCode().equals("QueueAlreadyExists")) {
                throw e;
            }
        }
        String queue_url = sqs.getQueueUrl("Tweets").getQueueUrl();
        System.out.println("Created a new SQS: " + queue_url);
    }

    private static void fetchMessageQueue() {
        System.out.println("Fetch a SQS queue if exists, otherwise create a new one.");
        while (queue_url.equals("")) {
            List<String> queue_urls = sqs.listQueues("Tweets").getQueueUrls();
            if (queue_urls.isEmpty()) {
                createQueue();
            }
            else {
            	queue_url = queue_urls.get(0);
            }
        }
        System.out.println("Fetch a SQS queue: " + queue_url);
    }

    public static void sendMessage(String json) {
        if (queue_url.equals("")) {
            fetchMessageQueue();
        }
        SendMessageRequest send_msg_request = new SendMessageRequest()
                .withQueueUrl(queue_url)
                .withMessageBody(json);
        sqs.sendMessage(send_msg_request);
    }
}
