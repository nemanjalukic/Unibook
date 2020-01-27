package net.etfbl.ip.projektni.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.bson.Document;
import org.bson.types.ObjectId;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

import net.etfbl.ip.projektni.dto.Blog;
import net.etfbl.ip.projektni.dto.Komentar;

public class MongoUtil {
		public static final String SERVER="localhost";

	    public static  MongoClient getConnection() {
			MongoClient mongoClient = new MongoClient(SERVER, 27017);
	        return mongoClient;
	    }
	    
	    public static ArrayList<Blog> getAllBlog() {
	    	ArrayList<Blog> blogovi=new ArrayList<>();
	    	MongoDatabase mongoDB = getConnection().getDatabase("projektni_blog");
	    	boolean collectionExists = getConnection().getDatabase("projektni_blog").listCollectionNames()
				    .into(new ArrayList<String>()).contains("blogovi");
			if(!collectionExists)
			mongoDB.createCollection("blogovi");
			else {
			MongoCollection<Document> collection = mongoDB.getCollection("blogovi");
			
			
			MongoCursor<Document> cursor = collection.find().iterator();
			try {
				while (cursor.hasNext()) {
					String s= cursor.next().toJson();
					Blog b=parseJson(s);
					blogovi.add(b);
				}
			} finally {
				cursor.close();
			}}
			return blogovi;
	    }
	    
	    
	    public static Blog parseJson(String json) {
	    	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	    	
	    	try {
				JSONObject obj = new JSONObject(json);
				String objID=obj.getJSONObject("_id").getString("$oid");
				int userID = obj.getInt("userID");
				String tema=obj.getString("tema");
				String datum=obj.getString("datum");
				JSONArray arr = obj.getJSONArray("komentari");
				ArrayList<Komentar> komentari=new ArrayList<>();
				for (int i = 0; i < arr.length(); i++)
				{
				    int user_id_komentar = arr.getJSONObject(i).getInt("user_id_komentar");
				    String komentar=arr.getJSONObject(i).getString("komentar");
				    komentari.add(new Komentar(user_id_komentar,komentar));
				}
				
					return new Blog(objID,userID,tema,komentari,formatter.parse(datum));
		
				
				
				
			} catch (JSONException | ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	
	    	return null;
	    }
public static void insertBlog(Blog b) {
	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	MongoDatabase mongoDB = getConnection().getDatabase("projektni_blog");
	boolean collectionExists = getConnection().getDatabase("projektni_blog").listCollectionNames()
		    .into(new ArrayList<String>()).contains("blogovi");
	if(!collectionExists)
	mongoDB.createCollection("blogovi");
	List<Document> uni=Arrays.asList();;
	MongoCollection<Document> collection = mongoDB.getCollection("blogovi");
	Document document = new Document("userID", b.getUserID()).append("tema", b.getTema()).append("komentari",uni).append("datum", formatter.format(b.getVrijemeKreiranja()));
	collection.insertOne(document);
	 
	
	
}	


public static void updateBlog(String id,Komentar kom) {
	MongoDatabase mongoDB = getConnection().getDatabase("projektni_blog");
	boolean collectionExists = getConnection().getDatabase("projektni_blog").listCollectionNames()
		    .into(new ArrayList<String>()).contains("blogovi");
	if(!collectionExists) 
	mongoDB.createCollection("blogovi");
	
	MongoCollection<Document> collection = mongoDB.getCollection("blogovi");
	
	Document vraiDoc = collection.find(
		    Filters.eq("_id", new ObjectId(id))
		).first();
	List<Document> komentari;
	if(vraiDoc.get("komentari")!=null) {
	komentari=(List<Document>)vraiDoc.get("komentari");
	komentari.add(new Document("user_id_komentar",kom.getUserID()).append("komentar", kom.getKomentar()));
	vraiDoc.replace("komentari", komentari);
	}
	else {
		komentari=Arrays.asList(new Document("user_id_komentar",kom.getUserID()).append("komentar", kom.getKomentar()));
		vraiDoc.append("komentari", komentari);
	}
	collection.replaceOne( Filters.eq("_id", new ObjectId(id)), vraiDoc);
}

	    
public static void testInsert() {
	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	MongoDatabase mongoDB = getConnection().getDatabase("projektni_blog");
	boolean collectionExists = getConnection().getDatabase("projektni_blog").listCollectionNames()
		    .into(new ArrayList<String>()).contains("blogovi");
	if(!collectionExists) {
	mongoDB.createCollection("blogovi");
	}
	else {
		mongoDB.drop();
		mongoDB.createCollection("blogovi");
	}
	MongoCollection<Document> collection = mongoDB.getCollection("blogovi");
	
	 List<Document> uni=Arrays.asList(new Document("user_id_komentar",2).append("komentar", "Markovic"), new Document("user_id_komentar",3).append("komentar", "Markovicdsfsdf"));
	 Document document = new Document("userID", 1)
	 .append("tema", "Markovic").append("datum", formatter.format(new Date())).
	 append("komentari",uni);
	 collection.insertOne(document);
	 
	 List<Document> uni1=Arrays.asList();
	 Document document1 = new Document("userID", 1)
	 .append("tema", "Markovic").append("datum", formatter.format(new Date())).
	 append("komentari",uni1);
	 collection.insertOne(document1);

	

}
}
