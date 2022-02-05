package com.blog.service;

import com.blog.entity.Tag;

import java.util.List;

public interface TagService {
    //返回该用户所使用的标签
    List<Tag> getTagByUserid(int bloggerId);

    int getCount(int bloggerId);

    List<Tag> getTagByArticleId(int articleId);

    Tag getTag(int tagId);

    List<Tag> getAllTag();

    List<Tag>getTagListByAll(int tagId,String tagName);

    boolean addTag(String tagName);

    boolean updateTag(int tagId,String tagName);

    boolean deletetag(int tagId);
}
