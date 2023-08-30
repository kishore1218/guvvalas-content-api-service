package com.guvvalas.api.controller;

import com.guvvalas.api.model.Chapter;
import com.guvvalas.api.service.IChapterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

@Slf4j
@RestController
public class ChapterController {


    @Autowired
    private IChapterService chapterService;

    /**
     *
     * @return
     */
    @GetMapping("/chapters/{courseId}")
    public ResponseEntity<Mono<List<Chapter>>> getChapters(@PathVariable("courseId") Integer courseId){
        log.info("getChapters--->");
        var response=Mono.fromCallable(()-> chapterService.getChapters(courseId)).onErrorResume(ex->{
            return Mono.error(ex);
        });
        return ResponseEntity.ok(response);
    }


    /**
     *
     * @return
     */
    @GetMapping("/chapter/{chapterId}")
    public ResponseEntity<Mono<Chapter>> getChapter(@PathVariable("chapterId") Integer chapterId){
        log.info("getChapters--->");
        var response=Mono.fromCallable(()-> chapterService.getChapter(chapterId)).onErrorResume(ex->{
            return Mono.error(ex);
        });
        return ResponseEntity.ok(response);
    }


    /**
     *
     * @return
     */
    @GetMapping("course/{courseId}/{chapterCode}")
    public ResponseEntity<Mono<Chapter>> getCourseChapter(@PathVariable("chapterCode") Integer chapterCode){
        log.info("getChapters--->");
        var response=Mono.fromCallable(()-> chapterService.getChapter(chapterCode)).onErrorResume(ex->{
            return Mono.error(ex);
        });
        return ResponseEntity.ok(response);
    }

    @PostMapping("/chapterContent/{chapterId}")
    public ResponseEntity<Mono<Void>> saveChapterContent(@RequestBody String content,@PathVariable("chapterId") Integer chapterId){
        log.info("saveChapterContent--->");
        chapterService.saveChapterContent(chapterId,content);
        return ResponseEntity.ok(Mono.empty());
    }
    /**
     *
     * @param chapter
     * @return
     */
    @PostMapping("/chapter")
    public ResponseEntity<Mono<Void>> chapter(@RequestBody Chapter chapter){

        log.info("chapter--->");
        if(chapter.getId()!=null && chapter.getId()>0){
            chapterService.updateChapter(chapter);
        }else{
            chapterService.saveChapter(chapter);
        }
        return  ResponseEntity.ok(Mono.empty());
    }


    /**
     *
     * @param chapterId
     * @return
     */
    @DeleteMapping("/chapter/{chapterId}")
    public ResponseEntity<Mono<Void>> deleteChapter(@PathVariable("chapterId") Integer chapterId){
        log.info("getChapters--->");
        var response=Mono.fromCallable(()-> {
            chapterService.deleteChapter(chapterId);
            return Mono.empty();
        }).onErrorResume(ex->{
            return Mono.error(ex);
        });
        return ResponseEntity.ok(Mono.empty());
    }

}
