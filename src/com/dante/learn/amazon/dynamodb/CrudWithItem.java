package com.dante.learn.amazon.dynamodb;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import org.junit.Test;

import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.PrimaryKey;
import com.amazonaws.services.dynamodbv2.document.PutItemOutcome;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.document.UpdateItemOutcome;
import com.amazonaws.services.dynamodbv2.document.spec.DeleteItemSpec;
import com.amazonaws.services.dynamodbv2.document.spec.GetItemSpec;
import com.amazonaws.services.dynamodbv2.document.spec.UpdateItemSpec;
import com.amazonaws.services.dynamodbv2.document.utils.ValueMap;
import com.amazonaws.services.dynamodbv2.model.ReturnValue;

public class CrudWithItem {
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

	static Table table = dynamoDB.getTable("Movies");
	
    static int year = 2015;
    static String title = "A movie test 2.";
	
	/*
	 * {
		   year: 2015,
		   title: "The Big New Movie",
		   info: { 
		        plot: "Nothing happens at all.",
		        rating: 0
		   }
		}
	 */
	@Test
	public void createNewItemWithSortKey() {
		
		final Map<String, Object> infoMap = new HashMap<String, Object>();
        infoMap.put("plot", "Nothing happens at all.");
        infoMap.put("rating", 0);
        try {
            System.out.println("Adding a new item...");
            PutItemOutcome outcome = table
                .putItem(new Item().withPrimaryKey("year", year, "title", title).withMap("info", infoMap));

            System.out.println("PutItem succeeded:\n" + outcome.getPutItemResult());

        }
        catch (Exception e) {
            System.err.println("Unable to add item: " + year + " " + title);
            System.err.println(e.getMessage());
        }
	}
	
	@Test
	public void createNewItem() {
		
		final Map<String, Object> infoMap = new HashMap<String, Object>();
        infoMap.put("plot", "Nothing happens at all.");
        infoMap.put("rating", 0);
        try {
            System.out.println("Adding a new item...");
            PutItemOutcome outcome = table
                .putItem(new Item().withPrimaryKey("year", year).withMap("info", infoMap));

            System.out.println("PutItem succeeded:\n" + outcome.getPutItemResult());

        }
        catch (Exception e) {
            System.err.println("Unable to add item: " + year + " " + title);
            System.err.println(e.getMessage());
        }
	}

	@Test
	public void createNewItemFull() {

		Item item = new Item().withPrimaryKey("year", year, "title", title)
				.withString("Title", "Book 120 Title")
				.withString("ISBN", "120-1111111111")
				.withStringSet("Authors",new HashSet<String>(Arrays.asList("Author1", "Author2")))
				.withNumber("Price", 20)
				.withString("Dimensions", "8.5x11.0x.75")
				.withNumber("PageCount", 500)
				.withBoolean("InPublication", false)
				.withString("ProductCategory", "Book");
		
        try {
            System.out.println("Adding a new item...");
            PutItemOutcome outcome = table.putItem(item);

            System.out.println("PutItem succeeded:\n" + outcome.getPutItemResult());

        }
        catch (Exception e) {
            System.err.println("Unable to add item: " + year + " " + title);
            System.err.println(e.getMessage());
        }
        
	}
	
	/**
	 * Read
	 */
	@Test
	public void readAnItem() {
		 GetItemSpec spec = new GetItemSpec().withPrimaryKey("year", year, "title", title);

	        try {
	            System.out.println("Attempting to read the item...");
	            Item outcome = table.getItem(spec);
	            System.out.println("GetItem succeeded: " + outcome.toJSONPretty());

	        }
	        catch (Exception e) {
	            System.err.println("Unable to read item: " + year + " " + title);
	            System.err.println(e.getMessage());
	        }
	}
	
	/**
	 * Creat, update and delete
	 */
	@Test
	public void updateItem() {
		
		/**
		 * updateExpression command
		 */
		String updateExpression = "add #p :val1 remove info.plot set info.rating = :r, info.actors = :a ";
		
		/**
		 * Condition
		 */
		String conditionExpression = "#ip = :val2"; 
		
		/**
		 * Name and value
		 */
		Map<String, String> nameMap = new HashMap<>();
		nameMap.put("#p", "Price");
		nameMap.put("#ip", "year");
		
		Map<String, Object> valueMap = new HashMap<>();
		valueMap.put(":val1", 69);
		valueMap.put(":val2", 2015);
		valueMap.put(":r", 7.5);
		valueMap.put(":a", Arrays.asList("Larry", "Moe", "Curly"));
		
		UpdateItemSpec updateItemSpec = new UpdateItemSpec()
				.withPrimaryKey("year", year, "title", title)
				.withUpdateExpression(updateExpression)
				.withConditionExpression(conditionExpression)
				.withNameMap(nameMap)
				.withValueMap(valueMap)
				.withReturnValues(ReturnValue.UPDATED_NEW);

		try {
			System.out.println("Updating the item...");
			UpdateItemOutcome outcome = table.updateItem(updateItemSpec);
			System.out.println("UpdateItem succeeded:\n" + outcome.getItem().toJSONPretty());

		} catch (Exception e) {
			System.err.println("Unable to update item: " + year + " " + title);
			System.err.println(e.getMessage());
		}
	}
	
	public static void icrementAtomicCounter() {
		UpdateItemSpec updateItemSpec = new UpdateItemSpec()
				.withPrimaryKey("year", year, "title", title)
				.withUpdateExpression("set info.rating = info.rating + :val")
				.withValueMap(new ValueMap().withNumber(":val", 1))
				.withReturnValues(ReturnValue.UPDATED_NEW);

		try {
			System.out.println("Incrementing an atomic counter...");
			UpdateItemOutcome outcome = table.updateItem(updateItemSpec);
			System.out.println("UpdateItem succeeded:\n"
					+ outcome.getItem().toJSONPretty());

		} catch (Exception e) {
			System.err.println("Unable to update item: " + year + " " + title);
			System.err.println(e.getMessage());
		}
	}
	
	public static void deleteItem() {
		DeleteItemSpec deleteItemSpec = new DeleteItemSpec()
				.withPrimaryKey(new PrimaryKey("year", year, "title", title))
				.withConditionExpression("info.rating <= :val")
				.withValueMap(new ValueMap().withNumber(":val", 5.0));

		// Conditional delete (we expect this to fail)

		try {
			System.out.println("Attempting a conditional delete...");
			table.deleteItem(deleteItemSpec);
			System.out.println("DeleteItem succeeded");
		} catch (Exception e) {
			System.err.println("Unable to delete item: " + year + " " + title);
			System.err.println(e.getMessage());
		}
	}

}
