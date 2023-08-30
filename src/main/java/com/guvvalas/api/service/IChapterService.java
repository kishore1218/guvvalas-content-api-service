package com.guvvalas.api.service;

import com.guvvalas.api.model.Chapter;

import java.util.List;

/**
 *
 */
public interface IChapterService{

    /**
     *
     * @param chapter
     */
    public void saveChapter(Chapter chapter);


    /**
     *
     * @param chapter
     */
    public void updateChapter(Chapter chapter);


    /**
     *
     * @param chapterId
     * @param content
     */
    public void saveChapterContent(Integer chapterId,String content);

    /**
     *
     * @return
     */
    public List<Chapter> getChapters(Integer courseId);


    /**
     *
     * @param chapterId
     * @return
     */
    public Chapter getChapter(Integer chapterId);


    /**
     *
     * @param chapterId
     */
    public void deleteChapter(Integer chapterId);
}
