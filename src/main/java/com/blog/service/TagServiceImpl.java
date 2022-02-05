package com.blog.service;

import com.blog.Dao.TagDao;
import com.blog.entity.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TagServiceImpl implements TagService{
    private TagDao tagDao;
    @Autowired
    public TagServiceImpl(TagDao tagDao){
        this.tagDao=tagDao;
    }
    @Override
    public List<Tag> getTagByUserid(int bloggerId) {
        List<Tag>tagList=null;
        try {
            tagList=tagDao.getTagByUser(bloggerId);
        }catch (Exception e){
            e.printStackTrace();
        }
        return tagList;
    }

    @Override
    public int getCount(int bloggerId) {
        int count=0;
        try {
            count=tagDao.getCount(bloggerId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }

    @Override
    public List<Tag> getTagByArticleId(int articleId) {
        List<Tag> tagList=null;
        try {
            tagList=tagDao.getTagByArticle(articleId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tagList;
    }

    @Override
    public Tag getTag(int tagId) {
        Tag tag=null;
        try {
            tag= tagDao.getTag(tagId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tag;
    }

    @Override
    public List<Tag> getAllTag() {
        List<Tag> allTag=null;
        try {
            allTag=tagDao.getAllTag();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return allTag;
    }

    @Override
    public List<Tag> getTagListByAll(int tagId, String tagName) {
        List<Tag>tagList=null;
        try{
            tagList=tagDao.getTagByAll(tagId,tagName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tagList;
    }

    @Override
    public boolean addTag(String tagName) {
        boolean success=false;
        try {
            if(tagDao.addNewTag(tagName)>0)success=true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return success;
    }

    @Override
    public boolean updateTag(int tagId, String tagName) {
        boolean success=false;
        try {
            if(tagDao.updateTag(tagId,tagName)>0)success=true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return success;
    }

    @Override
    public boolean deletetag(int tagId) {
        boolean success=false;
        try {
            if(tagDao.deleteTag(tagId)>0)success=true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return success;
    }
}
