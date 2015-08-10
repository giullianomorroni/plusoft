package com.giullianomorroni.dao;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;
import com.mongodb.WriteConcern;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class MongoConnection {

	private static MongoClient mongo;
	private static String DATA_BASE_NAME = "TEMPLATE";

	static {
		try {
			ServerAddress server = new ServerAddress("127.0.0.1", 27017);
			mongo = new MongoClient(server);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static MongoCollection<Document> collection(String nome) {
		MongoDatabase db = mongo.getDatabase(DATA_BASE_NAME);
		mongo.setWriteConcern(WriteConcern.SAFE);
		MongoCollection<Document> collection = db.getCollection(nome);
		if (collection == null){
			db.createCollection(nome);
			collection = db.getCollection(nome);
		}
		return collection;
	}

	public static MongoDatabase connect() {
		MongoDatabase db = mongo.getDatabase(DATA_BASE_NAME);
		mongo.setWriteConcern(WriteConcern.SAFE);
		return db;
	}

}
