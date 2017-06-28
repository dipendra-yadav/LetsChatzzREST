package com.niit.chattzz.dao;

import java.util.List;

import com.niit.chattzz.domain.Blog;
import com.niit.chattzz.domain.BlogComment;
import com.niit.chattzz.domain.User;

public interface BlogDao {

	Blog createBlog(User user, Blog blog);

	List<Blog> getAllBlogs();

	Blog getBlogById(int id);

	List<BlogComment> getBlogComments(int id);

	Blog addBlogPostComment(User user, BlogComment blogComment);

}
