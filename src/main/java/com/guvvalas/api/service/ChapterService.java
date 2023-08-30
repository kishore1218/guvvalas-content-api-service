package com.guvvalas.api.service;

import com.guvvalas.api.model.Chapter;
import com.guvvalas.api.repository.ChapterRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ChapterService implements IChapterService{


    @Autowired
    private ChapterRepository chapterRepository;
    /**
     * @param chapter
     */
    @Override
    public void saveChapter(Chapter chapter) {
        chapterRepository.saveChapter(chapter);
    }

    /**
     *
     * @param chapter
     */
    @Override
    public void updateChapter(Chapter chapter) {
        chapterRepository.updateChapter(chapter);
    }

    /**
     * @param chapterId
     * @param content
     */
    @Override
    public void saveChapterContent(Integer chapterId, String content) {
        chapterRepository.saveChapterContent(chapterId,content);
    }


    /**
     * @return
     */
    @Override
    public List<Chapter> getChapters(Integer courseId) {
        return chapterRepository.getChapters(courseId);
    }

    /**
     * @param chapterId
     * @return
     */
    @Override
    public Chapter getChapter(Integer chapterId) {
        var res= chapterRepository.getChapter(chapterId);
        log.info("chapter--> {}",res);
        return res;
    }

    /**
     * @param chapterId
     */
    @Override
    public void deleteChapter(Integer chapterId) {
         chapterRepository.deleteChapter(chapterId);

    }
}
