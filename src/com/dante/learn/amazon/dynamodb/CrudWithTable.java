package com.dante.learn.amazon.dynamodb;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.junit.Test;

import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.document.TableCollection;
import com.amazonaws.services.dynamodbv2.model.AttributeDefinition;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.KeySchemaElement;
import com.amazonaws.services.dynamodbv2.model.KeyType;
import com.amazonaws.services.dynamodbv2.model.ListTablesResult;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import com.amazonaws.services.dynamodbv2.model.ScalarAttributeType;
import com.amazonaws.services.dynamodbv2.model.TableDescription;

public class CrudWithTable {

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
//			.withRegion(Regions.AP_NORTHEAST_2).build();

	static DynamoDB dynamoDB = new DynamoDB(client);

	static String tableName = "Book";
	static Table table = dynamoDB.getTable(tableName);
	
	public static void main(String[] args) {
		CrudWithTable test = new CrudWithTable();
		test.getListTable();
	}
	@Test
	public void createTable() {

		try {
			System.out.println("Attempting to create table; please wait...");

			// Create attribute (it's like column on table)
			List<AttributeDefinition> attributeDefinitions = new ArrayList<AttributeDefinition>();
			attributeDefinitions
					.add(new AttributeDefinition().withAttributeName("year")
							.withAttributeType(ScalarAttributeType.N));
//			attributeDefinitions
//					.add(new AttributeDefinition().withAttributeName("title")
//							.withAttributeType(ScalarAttributeType.S));

			// Partition key and sort key
			List<KeySchemaElement> keySchema = new ArrayList<KeySchemaElement>();
			keySchema.add(new KeySchemaElement().withAttributeName("year")
					.withKeyType(KeyType.HASH));
//			keySchema.add(new KeySchemaElement().withAttributeName("title")
//					.withKeyType(KeyType.RANGE));

			CreateTableRequest request = new CreateTableRequest()
					.withTableName(tableName).withKeySchema(keySchema)
					.withAttributeDefinitions(attributeDefinitions)
					.withProvisionedThroughput(new ProvisionedThroughput()
							.withReadCapacityUnits(5L)
							.withWriteCapacityUnits(6L));

			System.out.println("Issuing CreateTable request for " + tableName);
			Table table = dynamoDB.createTable(request);

			System.out.println("Waiting for " + tableName
					+ " to be created...this may take a while...");
			table.waitForActive();

			getTableInformation();
			System.out.println("Success.  Table status: "
					+ table.getDescription().getTableStatus());

		} catch (Exception e) {
			System.err.println("Unable to create table: ");
			System.err.println(e.getMessage());
		}
	}

	@Test
	public void getListTable() {
		TableCollection<ListTablesResult> tables2 = dynamoDB.listTables();
		Iterator<Table> iterator = tables2.iterator();

		System.out.println("Listing table names");

		while (iterator.hasNext()) {
			Table table2 = iterator.next();
			System.out.println(table2.getTableName());
		}
	}

	@Test
	void getTableInformation() {

		System.out.println("Describing " + tableName);

		TableDescription tableDescription = dynamoDB.getTable(tableName)
				.describe();
		System.out.format("Name: %s:\n" + "Status: %s \n"
				+ "Provisioned Throughput (read capacity units/sec): %d \n"
				+ "Provisioned Throughput (write capacity units/sec): %d \n",
				tableDescription.getTableName(),
				tableDescription.getTableStatus(),
				tableDescription.getProvisionedThroughput()
						.getReadCapacityUnits(),
				tableDescription.getProvisionedThroughput()
						.getWriteCapacityUnits());
	}

	/**
	 * Note: Just rename table by backup/restore, cann't directly rename any

	 * table on DynamoDB
	 */
	@Test
	void updateExampleTable() {

		
		System.out.println("Modifying provisioned throughput for " + tableName);

		try {
			ProvisionedThroughput provisionedThroughput = new ProvisionedThroughput()
					.withReadCapacityUnits(15L).withWriteCapacityUnits(12L);

			table.updateTable(provisionedThroughput);

			table.waitForActive();
		} catch (Exception e) {
			System.err.println("UpdateTable request failed for " + tableName);
			System.err.println(e.getMessage());
		}
	}

	void deleteExampleTable() {

		try {
			System.out.println("Issuing DeleteTable request for " + tableName);
			table.delete();

			System.out.println("Waiting for " + tableName
					+ " to be deleted...this may take a while...");

			table.waitForDelete();
			System.out.println("Finished deletion");
		} catch (Exception e) {
			System.err.println("DeleteTable request failed for " + tableName);
			System.err.println(e.getMessage());
		}
	}
}