package com.dante.learn.amazon.dynamodb;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import org.junit.Test;

import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.model.AttributeDefinition;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.KeySchemaElement;
import com.amazonaws.services.dynamodbv2.model.KeyType;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import com.dante.learn.amazon.dynamodb.entity.CatalogItem;

public class CrudWithEntity {
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

	static DynamoDBMapper mapper = new DynamoDBMapper(client);
	
	static String tableName = "ProductCatalog";
	static Table table = dynamoDB.getTable(tableName);

	@Test
	public void createTable() {

		try {
			List<AttributeDefinition> attributeDefinitions = new ArrayList<AttributeDefinition>();
			attributeDefinitions.add(new AttributeDefinition()
					.withAttributeName("Id").withAttributeType("N"));

			List<KeySchemaElement> keySchema = new ArrayList<KeySchemaElement>();
			keySchema.add(new KeySchemaElement().withAttributeName("Id")
					.withKeyType(KeyType.HASH));

			CreateTableRequest request = new CreateTableRequest()
					.withTableName(tableName).withKeySchema(keySchema)
					.withAttributeDefinitions(attributeDefinitions)
					.withProvisionedThroughput(new ProvisionedThroughput()
							.withReadCapacityUnits(5L)
							.withWriteCapacityUnits(6L));

			System.out.println("Creating table " + tableName);
			Table table = dynamoDB.createTable(request);
			table.waitForActive();
			System.out.println("Success.  Table status: "
					+ table.getDescription().getTableStatus());
			
		} catch (InterruptedException e) {
			System.err.println("Unable to create table: ");
			System.err.println(e.getMessage());
		}
	}
	
	@Test
	public void putItem() {
		
		System.out.println("Putting item");
		CatalogItem item = new CatalogItem();
		item.setId(103);
		item.setTitle("Book 102 Title");
		item.setISBN("222-2222222222");
		item.setBookAuthors(new HashSet<String>(Arrays.asList("Author 1", "Author 2")));
		item.setSomeProp("Test");

		mapper.save(item);   
		System.out.println("Putting item suceed!!");
	}
	
	@Test
	public void retriveItem() {
		
		System.out.println("Retrieving item");
		CatalogItem itemRetrieved = mapper.load(CatalogItem.class, 102);
        System.out.println(itemRetrieved);
        System.out.println("Retrieving item suceed!!");
	}
	
	@Test
	public void retriveItemList() {
		
		System.out.println("Retrieving item");
		CatalogItem partitionKey = new CatalogItem();
		partitionKey.setId(102);

		DynamoDBQueryExpression<CatalogItem> queryExpression = new DynamoDBQueryExpression<CatalogItem>()
		    .withHashKeyValues(partitionKey);

		List<CatalogItem> itemList = mapper.query(CatalogItem.class, queryExpression);

		for (int i = 0; i < itemList.size(); i++) {
		    System.out.println(itemList.get(i).getTitle());
		    System.out.println(itemList.get(i).getBookAuthors());
		}
		System.out.println("Retrieving item suceed!!");
		
	}
	
	@Test
	public void updateItem() {
        // Update the item.
		System.out.println("Updating item");
		CatalogItem itemRetrieved = mapper.load(CatalogItem.class, 102);
        itemRetrieved.setISBN("622-2222222222");
        itemRetrieved.setBookAuthors(new HashSet<String>(Arrays.asList("Author1", "Author3")));
        mapper.save(itemRetrieved);
        System.out.println("Item updated:");
        System.out.println(itemRetrieved);
        System.out.println("Updating item suceed!!!");
	}
	
	@Test
	public void retriveUpdatedItem() {
		System.out.println("Updating item");
        DynamoDBMapperConfig config = new DynamoDBMapperConfig(DynamoDBMapperConfig.ConsistentReads.CONSISTENT);
        CatalogItem updatedItem = mapper.load(CatalogItem.class, 102, config);
        System.out.println("Retrieved the previously updated item:");
        System.out.println(updatedItem);
        System.out.println("Updating item suceed!!!");
	}
	
	@Test
	public void deleteItem() {
		DynamoDBMapperConfig config = new DynamoDBMapperConfig(
				DynamoDBMapperConfig.ConsistentReads.CONSISTENT);
		CatalogItem updatedItem = mapper.load(CatalogItem.class, 103, config);

		// Delete the item.
		mapper.delete(updatedItem);

		// Try to retrieve deleted item.
		CatalogItem deletedItem = mapper.load(CatalogItem.class,
				updatedItem.getId(), config);
		if (deletedItem == null) {
			System.out.println("Done - Sample item is deleted.");
		}
	}
}
