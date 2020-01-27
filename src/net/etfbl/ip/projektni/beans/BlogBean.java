package net.etfbl.ip.projektni.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

import net.etfbl.ip.projektni.controller.MongoUtil;
import net.etfbl.ip.projektni.dto.Blog;
import net.etfbl.ip.projektni.dto.Komentar;

public class BlogBean implements Serializable{

	private static final long serialVersionUID = 1L;
	private Blog blog = new Blog();
	

	public Blog getBlog() {
		return blog;
	}

	public void setBlog(Blog blog) {
		this.blog = blog;
	}

	public ArrayList<Blog> getBlogovi() {
		ArrayList<Blog> retVal=new ArrayList<>();
		retVal=MongoUtil.getAllBlog();
		Collections.sort(retVal);
		return retVal;

	}
	
	public void insertBlog(Blog b) {
		
		MongoUtil.insertBlog(b);
	}
	
	public void updateBlog(String idBlog,Komentar kom) {
		
		MongoUtil.updateBlog(idBlog, kom);
	}

}
