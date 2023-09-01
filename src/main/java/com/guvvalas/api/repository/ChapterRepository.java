package com.guvvalas.api.repository;

import com.guvvalas.api.model.Chapter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 *
 */
@Slf4j
@Repository
public class ChapterRepository {


    private static final String COURSE_INSERT_SQL="INSERT INTO CHAPTER(CHAPTER_NAME,CHAPTER_CODE,DESCRIPTION,SEQ,COURSE_ID,CHAPTER_CONTENT,IS_PUBLISH) VALUES(:aChapterName,:code,:description,:seq,:courseId,:content,:isPublish)";

    private static final String GET_CHAPTER_SQL= "SELECT *FROM CHAPTER WHERE CHAPTER_CODE=:aUrl";


    private static final String GET_ALL_CHAPTERS_SQL="SELECT *FROM CHAPTER WHERE COURSE_ID=(SELECT ID FROM COURSE WHERE URL=:aUrl) order by seq" ;


    private static final String DELETE_CHAPTER_SQL="DELETE FROM CHAPTER WHERE ID=:aChapterId";

    private static final String UPDATE_CHAPTER_CONTENT_SQL="UPDATE CHAPTER SET CHAPTER_CONTENT=:content WHERE ID=:aChapterId";

    private static final String UPDATE_CHAPTER_SQL="UPDATE CHAPTER SET CHAPTER_NAME=:aChapterName,CHAPTER_CODE=:code,DESCRIPTION=:description,SEQ=:seq,CHAPTER_CONTENT=:content,IS_PUBLISH=:isPublish WHERE ID=:aChapterId";


    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    /**
     *
     * @param chapter
     */
    public void saveChapter(Chapter chapter){

        Map<String,Object> params=new HashMap<String,Object>();
        params.put("aChapterName",chapter.getName());
        params.put("description",chapter.getDescription());
        params.put("seq",chapter.getSequence());
        params.put("content",chapter.getContent());
        params.put("courseId",chapter.getCourseId());
        params.put("isPublish",chapter.getIsPublish());
        params.put("code",chapter.getCode());

        jdbcTemplate.update(COURSE_INSERT_SQL,params);
    }


    /**
     *
     * @param chapter
     */
    public void updateChapter(Chapter chapter){

        Map<String,Object> params=new HashMap<String,Object>();
        params.put("aChapterName",chapter.getName());
        params.put("description",chapter.getDescription());
        params.put("seq",chapter.getSequence());
        params.put("content",chapter.getContent());
        params.put("courseId",chapter.getCourseId());
        params.put("isPublish",chapter.getIsPublish());
        params.put("aChapterId",chapter.getId());
        params.put("code",chapter.getCode());

        jdbcTemplate.update(UPDATE_CHAPTER_SQL,params);
    }


    /**
     *
     * @param chapterId
     * @param content
     */
    public void saveChapterContent(Integer chapterId,String content){

        Map<String,Object> params=new HashMap<String,Object>();
        params.put("content",content);
        params.put("aChapterId",chapterId);
        jdbcTemplate.update(UPDATE_CHAPTER_CONTENT_SQL,params);
    }


    /**
     *
     * @param chapterId
     */
    public void deleteChapter(Integer chapterId){

        Map<String,Object> params=new HashMap<String,Object>();
        params.put("aChapterId",chapterId);
        jdbcTemplate.update(DELETE_CHAPTER_SQL,params);
    }


    /**
     *
     * @return
     */
    public List<Chapter> getChapters(String courseId){
        Map<String,Object> params=new HashMap<String,Object>();
        params.put("aUrl",courseId);
        return jdbcTemplate.query(GET_ALL_CHAPTERS_SQL, params, new RowMapper<Chapter>() {

            /**
             *
             * @param rs
             * @param rowNum
             * @return
             * @throws SQLException
             */
            @Override
            public Chapter mapRow(ResultSet rs, int rowNum) throws SQLException {

                return Chapter.builder()
                        .id(rs.getInt("ID"))
                        .courseId(rs.getInt("COURSE_ID"))
//                        .content(rs.getString("CHAPTER_CONTENT"))
                        .name(rs.getString("CHAPTER_NAME"))
                        .code(rs.getString("CHAPTER_CODE"))
                        .description(rs.getString("DESCRIPTION"))
                        .sequence(rs.getInt("SEQ"))
                        .isActive(rs.getBoolean("IS_ACTIVE"))
                        .isPublish(rs.getBoolean("IS_PUBLISH"))
                        .build();
            }
        });

    }

    /**
     *
     * @param chapterCode
     * @return
     */
    public Chapter getChapter(String chapterCode){
        Map<String,Object> params=new HashMap<String,Object>();
        params.put("aUrl",chapterCode);

        return jdbcTemplate.queryForObject(GET_CHAPTER_SQL, params, new RowMapper<Chapter>() {

            /**
             *
             * @param rs
             * @param rowNum
             * @return
             * @throws SQLException
             */
            @Override
            public Chapter mapRow(ResultSet rs, int rowNum) throws SQLException {

                return Chapter.builder()
                        .id(rs.getInt("ID"))
                        .courseId(rs.getInt("COURSE_ID"))
                        .content(rs.getString("CHAPTER_CONTENT"))
                        .name(rs.getString("CHAPTER_NAME"))
                        .code(rs.getString("CHAPTER_CODE"))
                        .description(rs.getString("DESCRIPTION"))
                        .sequence(rs.getInt("SEQ"))
                        .isActive(rs.getBoolean("IS_ACTIVE"))
                        .isPublish(rs.getBoolean("IS_PUBLISH"))
                        .build();
            }
        });

    }
}
