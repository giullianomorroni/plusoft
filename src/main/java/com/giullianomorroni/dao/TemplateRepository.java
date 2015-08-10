package com.giullianomorroni.dao;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.bson.types.ObjectId;

import com.giullianomorroni.resource.Field;
import com.giullianomorroni.resource.Option;
import com.giullianomorroni.resource.Template;
import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;

public class TemplateRepository {

	@SuppressWarnings("unchecked")
	public Template find(String id) {
		Template results = new Template();
		MongoCollection<Document> collection = MongoConnection.collection("template");

		BasicDBObject query = new BasicDBObject(); 
		query.put("_id", new ObjectId(id));

		FindIterable<Document> data = collection.find(query);
		if(data == null)
			return results;

		MongoCursor<Document> iterator = data.iterator();
		while (iterator.hasNext()) {
			Document poi = iterator.next();
			results.setId(poi.getObjectId("_id").toString());
			results.setTitle((String) poi.get("title"));
			List<Field> fields = (List<Field>) poi.get("fields");
			results.getFields().addAll(fields);
		}
		return results;
	}

	@SuppressWarnings("unchecked")
	public List<Template> list() {
		List<Template> results = new ArrayList<Template>();
		MongoCollection<Document> collection = MongoConnection.collection("template");

		FindIterable<Document> data = collection.find();
		if(data == null)
			return results;

		MongoCursor<Document> iterator = data.iterator();
		while (iterator.hasNext()) {
			Document poi = iterator.next();
			Template t = new Template();
			t.setTitle((String) poi.get("title"));
			t.setId(poi.getObjectId("_id").toString());
			List<Field> fields = (List<Field>) poi.get("fields");
			t.getFields().addAll(fields);
			results.add(t);
		}
		return results;
	}

	/**
	 * 
	 * @param template
	 */
	public void create(Template template) {
		try {
			MongoCollection<Document> collection = MongoConnection.collection("template");
			Document document = new Document();
			document.put("title", template.getTitle());

			List<Document> fields = new ArrayList<Document>();
			for (Field field : template.getFields()) {
				Document d = new Document();
				d.put("label", field.getLabel());
				d.put("required", field.getRequired());
				d.put("readOnly", field.getReadOnly());
				d.put("placeHolder", field.getPlaceHolder());
				d.put("maxLenght", field.getMaxLenght());
				d.put("type", field.getType());
				d.put("defaulfValue", field.getDefaulfValue());

				List<Document> options = new ArrayList<Document>();
				for (Option option : field.getOptions()) {
					Document sd = new Document();
					d.put("text", option.getText());
					d.put("value", option.getValue());
					d.put("selected", option.getSelected());
					options.add(sd);
				}
				d.put("options", options);
				fields.add(d);
			}
			document.put("fields", fields);
			collection.insertOne(document);
		} catch (Exception e) {
			throw e;
		}
	}

}
