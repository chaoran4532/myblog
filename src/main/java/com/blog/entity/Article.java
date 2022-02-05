package com.blog.entity;



import com.blog.util.DateUtils;

import java.text.ParseException;
import java.util.Date;

public class Article implements Comparable {

	private int id;
	private String title;
	private int authorId;
	private String author;
	private int categoryId;
	private String category;
	private String time;
	private int star;
	private int comment;
	private int visit;
	private String content;
	private String introduction;
	public Article() {

	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public int getAuthorId() {
		return authorId;
	}

	public void setAuthorId(int authorId) {
		this.authorId = authorId;
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	@Override
	public int compareTo(Object o) {
		if (o instanceof Article) {
			Article article = (Article) o;

			Date this_date = null;
			Date other_date = null;
			try {
				this_date = DateUtils.getDate(this.time);
				other_date = DateUtils.getDate(article.getTime());
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			return -this_date.compareTo(other_date);
		}
		return 0;
	}

	public int getId() {
		return id;
	}

	@Override
	public String toString() {
		return "Article{" +
				"id=" + id +
				", title='" + title + '\'' +
				", author='" + author + '\'' +
				", categoryId=" + categoryId +
				", category='" + category + '\'' +
				", time='" + time + '\'' +
				", star=" + star +
				", comment=" + comment +
				", visit=" + visit +
				", introduction='" + introduction + '\'' +
				"}\n";
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}
	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public int getStar() {
		return star;
	}

	public void setStar(int star) {
		this.star = star;
	}

	public int getComment() {
		return comment;
	}

	public void setComment(int comment) {
		this.comment = comment;
	}

	public int getVisit() {
		return visit;
	}

	public void setVisit(int visit) {
		this.visit = visit;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
