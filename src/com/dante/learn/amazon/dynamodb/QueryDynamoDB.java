package com.dante.learn.amazon.dynamodb;

import java.util.HashMap;
import java.util.Iterator;

import org.junit.Test;

import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.ItemCollection;
import com.amazonaws.services.dynamodbv2.document.Page;
import com.amazonaws.services.dynamodbv2.document.QueryOutcome;
import com.amazonaws.services.dynamodbv2.document.ScanOutcome;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.document.spec.QuerySpec;
import com.amazonaws.services.dynamodbv2.document.spec.ScanSpec;
import com.amazonaws.services.dynamodbv2.document.utils.NameMap;
import com.amazonaws.services.dynamodbv2.document.utils.ValueMap;

public class QueryDynamoDB {
	/**
	 * Use for localhost
	 */
	static AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard()
			.withEndpointConfiguration(
					new AwsClientBuilder.EndpointConfiguration(
							"http://localhost:8000", "ap-northeast-2"))
			.build();

	/**
	 * Use for service
	 */
//	static AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard()
//			.withRegion(Regions.US_WEST_2).build();

	static DynamoDB dynamoDB = new DynamoDB(client);

	static String tableName = "Movies";
	static Table table = dynamoDB.getTable(tableName);
	
    static int year = 2015;
    static String title = "A movie test 6.";
	
    @Test
	public void queryItemDemo() {
		HashMap<String, String> nameMap = new HashMap<String, String>();
		nameMap.put("#yr", "year");

		HashMap<String, Object> valueMap = new HashMap<String, Object>();
		valueMap.put(":yyyy", 2015);

		QuerySpec querySpec = new QuerySpec()
				.withKeyConditionExpression("#yr = :yyyy")
				.withNameMap(nameMap)
				.withValueMap(valueMap);

		ItemCollection<QueryOutcome> items = null;
		Iterator<Item> iterator = null;
		Item item = null;

		try {
			System.out.println("Movies from 2015");
			items = table.query(querySpec);

			iterator = items.iterator();
			while (iterator.hasNext()) {
				item = iterator.next();
				System.out.println(item.getNumber("year") + ": "
						+ item.getString("title"));
			}

		} catch (Exception e) {
			System.err.println("Unable to query movies from 1985");
			System.err.println(e.getMessage());
		}

		valueMap.put(":yyyy", 2015);
		valueMap.put(":letter1", "A");
		valueMap.put(":letter2", "Z");

		querySpec.withProjectionExpression("#yr, title, info.genres, info.actors[0]")
				.withKeyConditionExpression("#yr = :yyyy and title between :letter1 and :letter2")
				.withNameMap(nameMap).withValueMap(valueMap);

		try {
			System.out.println("Movies from 1992 - titles A-L, with genres and lead actor");
			items = table.query(querySpec);

			iterator = items.iterator();
			while (iterator.hasNext()) {
				item = iterator.next();
				System.out.println(item.getNumber("year") + ": "
						+ item.getString("title") + " " + item.getMap("info"));
			}

		} catch (Exception e) {
			System.err.println("Unable to query movies from 1992:");
			System.err.println(e.getMessage());
		}
	}
	
	@Test
	public void queryItemWithCondition() {
		
		/**
		 * year is a reserved key, you  must to use HashMap to contain it
		 */
		HashMap<String, String> nameMap = new HashMap<String, String>();
		nameMap.put("#yr", "year");

		HashMap<String, Object> valueMap = new HashMap<String, Object>();
		valueMap.put(":yyyy", 2015);
		valueMap.put(":rating1", 0);
		valueMap.put(":rating2", 10);
		
		QuerySpec spec = new QuerySpec()
				.withKeyConditionExpression("#yr = :yyyy")
				.withFilterExpression("info.rating between :rating1 and :rating2")
				.withNameMap(nameMap)
				.withValueMap(valueMap)
				.withConsistentRead(true);

		ItemCollection<QueryOutcome> items = table.query(spec);
		Iterator<Item> iterator = items.iterator();
		
		if(iterator.hasNext() == false) {
			System.out.println("Empty");
		} else {
			while (iterator.hasNext()) {
				System.out.println(iterator.next().toJSONPretty());
			}
		}

	}
	
	@Test
	public void queryWithPageSize() {
		/**
		 * year is a reserved key, you  must to HashMap to contain it
		 */
		HashMap<String, String> nameMap = new HashMap<String, String>();
		nameMap.put("#yr", "year");

		HashMap<String, Object> valueMap = new HashMap<String, Object>();
		valueMap.put(":yyyy", 2015);
		valueMap.put(":rating1", 0);
		valueMap.put(":rating2", 10);
		
		QuerySpec spec = new QuerySpec()
				.withKeyConditionExpression("#yr = :yyyy")
				.withFilterExpression("info.rating between :rating1 and :rating2")
				.withNameMap(nameMap)
				.withValueMap(valueMap)
				.withConsistentRead(true)
				.withMaxPageSize(2);

		ItemCollection<QueryOutcome> items = table.query(spec);
		
		// Process each page of results
		int pageNum = 0;
		for (Page<Item, QueryOutcome> page : items.pages()) {
		        
		    System.out.println("\nPage: " + ++pageNum);

		    // Process each item on the current page
		    Iterator<Item> item = page.iterator();
		    while (item.hasNext()) {
		        System.out.println(item.next().toJSONPretty());
		    }
		}

	}
	
	/**
	 * Scan will get all data, and then get required data
	 * Query will get required data, not get all data
	 */
	public static void scanItem() {
		
		ScanSpec scanSpec = new ScanSpec()
				.withProjectionExpression("#yr, title, info.genres, info.actors[0]")
				.withFilterExpression("#yr between :start_yr and :end_yr")
				.withNameMap(new NameMap().with("#yr", "year"))
				.withValueMap(new ValueMap().withNumber(":start_yr", 1950)
				.withNumber(":end_yr", 2016));

		try {
			ItemCollection<ScanOutcome> items = table.scan(scanSpec);

			Iterator<Item> iter = items.iterator();
			while (iter.hasNext()) {
				Item item = iter.next();
				System.out.println(item.toString());
			}

		} catch (Exception e) {
			System.err.println("Unable to scan the table:");
			System.err.println(e.getMessage());
		}
	}
}
