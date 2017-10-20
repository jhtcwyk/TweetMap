package edu.nyu.TweetMap.DynampDB;

import java.util.Arrays;

import  edu.nyu.TweetMap.TweetUtil.*;


import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.model.AttributeDefinition;
import com.amazonaws.services.dynamodbv2.model.KeySchemaElement;
import com.amazonaws.services.dynamodbv2.model.KeyType;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import com.amazonaws.services.dynamodbv2.model.ScalarAttributeType;

import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.PutItemOutcome;

public class TweetDynamoDB {
	
	private static final AmazonDynamoDB client;
	private static final DynamoDB dynamoDB;
	
	 static {
		 

	        client = AmazonDynamoDBClientBuilder.standard()
	        	.withRegion("us-east-1")
	            .build();

	        dynamoDB = new DynamoDB(client);

//	        String tableName = "Tweets";
//
//	        try {
//	            System.out.println("Attempting to create table; please wait...");
//	            Table table = dynamoDB.createTable(tableName,
//	                Arrays.asList(new KeySchemaElement("id", KeyType.HASH), // Partition
//	                                                                          // key
//	                    new KeySchemaElement("date", KeyType.RANGE)), // Sort key
//	                Arrays.asList(new AttributeDefinition("id", ScalarAttributeType.N),
//	                    new AttributeDefinition("date", ScalarAttributeType.S)),
//	                	//new AttributeDefinition("text", ScalarAttributeType.S),
//	                	//new AttributeDefinition("username", ScalarAttributeType.S),
//	                	//new AttributeDefinition("location", ScalarAttributeType.S)),
//	                new ProvisionedThroughput(10L, 10L));
//	            table.waitForActive();
//	            System.out.println("Success.  Table status: " + table.getDescription().getTableStatus());
//
//	        }
//	        catch (Exception e) {
//	            System.err.println("Unable to create table: ");
//	            System.err.println(e.getMessage());
//	        }


	    }
	 
     
     static public void addNewItem(Tweet t) {
     	Table table = dynamoDB.getTable("Tweets");
     	long id = t.getId();
        String date = t.getDate();
        String text = t.getText();
        String location = t.getLocation().toString();
        String username = t.getUsername();

        try {
            //System.out.println("Adding a new item...");
            PutItemOutcome outcome = table
                .putItem(new Item().withPrimaryKey("id", id, "date", date).withString("text", text).withString("location", location).withString("username", username));

            //System.out.println("PutItem succeeded:\n" + outcome.getPutItemResult());

        }
        catch (Exception e) {
            System.err.println("Unable to add item: " + id + " " + date + " " + text);
            System.err.println(e.getMessage());
        }
     	
     }
}
