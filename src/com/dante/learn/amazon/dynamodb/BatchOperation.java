package com.dante.learn.amazon.dynamodb;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.BatchGetItemOutcome;
import com.amazonaws.services.dynamodbv2.document.BatchWriteItemOutcome;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.PrimaryKey;
import com.amazonaws.services.dynamodbv2.document.TableKeysAndAttributes;
import com.amazonaws.services.dynamodbv2.document.TableWriteItems;
import com.amazonaws.services.dynamodbv2.document.internal.InternalUtils;
import com.amazonaws.services.dynamodbv2.document.spec.BatchGetItemSpec;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.BatchGetItemRequest;
import com.amazonaws.services.dynamodbv2.model.BatchGetItemResult;
import com.amazonaws.services.dynamodbv2.model.KeysAndAttributes;
import com.amazonaws.services.dynamodbv2.model.WriteRequest;

public class BatchOperation {
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
	// static AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard()
	// .withRegion(Regions.AP_NORTHEAST_2).build();

	static DynamoDB dynamoDB = new DynamoDB(client);
	
    static String companyTable = "Company2";
    static String movieTable = "Movies";
    
    static int year = 2015;
    static String title = "A movie test 9.";
    
    @Test
   public void writeMultipleItemsBatchWrite() {
        try {

            // Add a new item to Forum
        	Item item1 = new Item().withPrimaryKey("year", year, "title", title).withNumber("testCompany1", 1);
            TableWriteItems companyTableWriteItems = new TableWriteItems(companyTable).withItemsToPut(item1);;

			/**
			 * Add a new item, and delete an existing item, from Movies This
			 * table has a partition key and range key, so need to specify both
			 * of them
			 */

            Item item2 = new Item().withPrimaryKey("year", year, "title", title)
					.withString("testMovies1", "This is movie 1")
					.withStringSet("Tags", new HashSet<String>(Arrays.asList("cache", "in-memory")));
            TableWriteItems movieTableWriteItems = new TableWriteItems(movieTable).withItemsToPut(item2)
            	    .withHashAndRangeKeysToDelete("year", "title", year, "A movie test 8.");
          

            System.out.println("Making the request.");
            BatchWriteItemOutcome outcome = dynamoDB.batchWriteItem(companyTableWriteItems, movieTableWriteItems);

            do {

                // Check for unprocessed keys which could happen if you exceed
                // provisioned throughput

                Map<String, List<WriteRequest>> unprocessedItems = outcome.getUnprocessedItems();

                if (outcome.getUnprocessedItems().size() == 0) {
                    System.out.println("No unprocessed items found");
                }
                else {
                    System.out.println("Retrieving the unprocessed items");
                    outcome = dynamoDB.batchWriteItemUnprocessed(unprocessedItems);
                }

            } while (outcome.getUnprocessedItems().size() > 0);

        }
        catch (Exception e) {
            System.err.println("Failed to retrieve items: ");
            e.printStackTrace(System.err);
        }

    }
    
    @Test
    public void retrieveMultipleItemsBatchGet() {

        try {

        	/**
        	 * addHashOnlyPrimaryKeys() method Just work if Table have only primary key and dont have sort key
        	 */
            TableKeysAndAttributes companyTableKeysAndAttributes = new TableKeysAndAttributes(companyTable);
            // Add a partition key
            companyTableKeysAndAttributes.addHashOnlyPrimaryKeys("year", 2015, 2016, 2017);

            TableKeysAndAttributes movieTableKeysAndAttributes = new TableKeysAndAttributes(movieTable);
            // Add a partition key and a sort key
            movieTableKeysAndAttributes.addHashAndRangePrimaryKeys("year", "title", 
            		2015, "A movie test 2.", 
            		2015, "A movie test 3.", 
            		2015, "A movie test 9.");

//            System.out.println("Making the request with getPrimaryKeys: " + forumTableKeysAndAttributes.getPrimaryKeys());
//            System.out.println("Making the request with getAttributeNames: " + forumTableKeysAndAttributes.getAttributeNames());

            BatchGetItemOutcome outcome = dynamoDB.batchGetItem(companyTableKeysAndAttributes, movieTableKeysAndAttributes);

            
            Map<String, KeysAndAttributes> unprocessed = null;

            do {
                for (String tableName : outcome.getTableItems().keySet()) {
                    System.out.println("Items in table " + tableName);
                    List<Item> items = outcome.getTableItems().get(tableName);
                    if(!(0 < items.size())) {
                    	System.out.println("Empty");
                    	break;
                    }
                    
                    for (Item item : items) {
                        System.out.println(item.toJSONPretty());
                    }
                }

                // Check for unprocessed keys which could happen if you exceed
                // provisioned
                // throughput or reach the limit on response size.
                unprocessed = outcome.getUnprocessedKeys();

                if (unprocessed.isEmpty()) {
                    System.out.println("No unprocessed keys found");
                }
                else {
                    System.out.println("Retrieving the unprocessed keys");
                    outcome = dynamoDB.batchGetItemUnprocessed(unprocessed);
                }

            } while (!unprocessed.isEmpty());

        }
        catch (Exception e) {
            System.err.println("Failed to retrieve items.");
            System.err.println(e.getMessage());
        }

    }
    
    public BatchGetItemOutcome batchGetItem(TableKeysAndAttributes... tableKeysAndAttributes)
    {
      return doBatchGetItem(new BatchGetItemSpec()
        .withTableKeyAndAttributes(tableKeysAndAttributes));
    }
    
    private BatchGetItemOutcome doBatchGetItem(BatchGetItemSpec spec)
    {
      Collection<TableKeysAndAttributes> tableKeysAndAttributesCol = spec.getTableKeysAndAttributes();
      
      Map<String, KeysAndAttributes> requestItems = spec.getUnprocessedKeys();
      if ((requestItems == null) || (requestItems.size() == 0))
      {
        requestItems = new LinkedHashMap();
      }
      if (tableKeysAndAttributesCol != null) {
        for (TableKeysAndAttributes tableKeysAndAttributes : tableKeysAndAttributesCol)
        {
          Set<String> attrNames = tableKeysAndAttributes.getAttributeNames();
          
          List<PrimaryKey> pks = tableKeysAndAttributes.getPrimaryKeys();
          List<Map<String, AttributeValue>> keys = new ArrayList(pks.size());
          for (PrimaryKey pk : pks) {
            keys.add(InternalUtils.toAttributeValueMap(pk));
          }
          



          KeysAndAttributes keysAndAttrs = new KeysAndAttributes().withAttributesToGet(attrNames).withConsistentRead(Boolean.valueOf(tableKeysAndAttributes.isConsistentRead())).withKeys(keys).withProjectionExpression(tableKeysAndAttributes.getProjectionExpression()).withExpressionAttributeNames(tableKeysAndAttributes.getNameMap());
          
          requestItems.put(tableKeysAndAttributes.getTableName(), keysAndAttrs);
        }
      }
      
      BatchGetItemRequest req = ((BatchGetItemRequest)spec.getRequest()).withRequestItems(requestItems);
      BatchGetItemResult result = client.batchGetItem(req);
      return new BatchGetItemOutcome(result);
    }
}
