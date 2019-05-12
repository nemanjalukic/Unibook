package net.etfbl.ip.projektni.controller;

import net.etfbl.ip.projektni.dto.Blog;
import net.etfbl.ip.projektni.dto.Komentar;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MongoUtil mu= new MongoUtil();
		mu.testInsert();
		
		for(Blog b : mu.getAllBlog()) {
			
			System.out.println(b.toString());
		}
		//MongoUtil.updateBlog("5c6b54becdb8be1fa0e174a9",(new Komentar(1, "novi komentar")));
		
for(Blog b : mu.getAllBlog()) {
			
			System.out.println(b.toString());
		}
	}

}
