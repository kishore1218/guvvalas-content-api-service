package com.guvvalas.api.repository;


import com.guvvalas.api.model.Chapter;
import com.guvvalas.api.model.Course;
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
public class CourseRepository {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    private static final String QUERY="select *from imps_txn where tnx_id=:atnxId";

    private static final String COURSE_INSERT="INSERT INTO COURSE(COURSE_NAME,DESCRIPTION,LOGO_PATH,URL,IS_ACTIVE,IS_PUBLISH) VALUES(:aCourseName,:description,:logo,:url,:isActive,:isPublish)";

    private static final String GET_COURSE_BY_ID ="SELECT *FROM COURSE WHERE ID=:courseId ";

    private static final String GET_ALL_COURSES ="SELECT *FROM COURSE order by COURSE_NAME";

    private static final String UPDATE_COURSE="UPDATE COURSE SET COURSE_NAME=:courseName,DESCRIPTION=:desc,LOGO_PATH=:logo,URL=:url,IS_PUBLISH:ispublish WHERE ID=:courseId";


    /**
     *
     * @return
     */
    public List<Course> getCourses(){

        log.info("getCourses-->");

        var courses=jdbcTemplate.query(GET_ALL_COURSES, new HashMap<String,Object>(), new RowMapper<Course>() {

            /**
             *
             * @param rs
             * @param rowNum
             * @return
             * @throws SQLException
             */
            @Override
            public Course mapRow(ResultSet rs, int rowNum) throws SQLException {

                return Course.builder()
                        .courseId(rs.getInt("ID"))
                        .url(rs.getString("URL"))
                        .icon(rs.getString("LOGO_PATH"))
                        .name(rs.getString("COURSE_NAME"))
                        .description(rs.getString("DESCRIPTION"))
                        .isActive(rs.getBoolean("IS_ACTIVE"))
                        .isPublish(rs.getBoolean("IS_PUBLISH"))
                        .build();
            }
        });
        return courses;
    }

    /**
     *
     * @param id
     * @return
     */
    public Course getCourse(Integer id){

        Map<String,Object> params=new HashMap<String,Object>();
        params.put("courseId",id);

        var course=jdbcTemplate.queryForObject(GET_COURSE_BY_ID, params, new RowMapper<Course>() {
            /**
             *
             * @param rs
             * @param rowNum
             * @return
             * @throws SQLException
             */
            @Override
            public Course mapRow(ResultSet rs, int rowNum) throws SQLException {

                return Course.builder()
                        .courseId(rs.getInt("ID"))
                        .url(rs.getString("URL"))
                        .icon(rs.getString("LOGO_PATH"))
                        .name(rs.getString("COURSE_NAME"))
                        .description(rs.getString("DESCRIPTION"))
                        .isActive(rs.getBoolean("IS_ACTIVE"))
                        .isPublish(rs.getBoolean("IS_PUBLISH"))
                        .build();
            }
        });
        return course;
    }

    /**
     *
     * @param course
     */
    public void saveCourse(Course course){

        Map<String,Object> params=new HashMap<String,Object>();
        params.put("aCourseName",course.getName());
        params.put("url",course.getUrl());
        params.put("logo",course.getIcon());
        params.put("description",course.getDescription());
        params.put("isActive",course.getIsActive());
        params.put("isPublish",course.getIsPublish());

       var res= jdbcTemplate.update(COURSE_INSERT,params);

    }

    public void updateCourse(Course course){

        Map<String,Object> params=new HashMap<String,Object>();
        params.put("aCourseName",course.getName());
        params.put("url",course.getUrl());
        params.put("logo",course.getIcon());
        params.put("desc",course.getDescription());
        params.put("isActive",course.getIsActive());
        params.put("courseId",course.getCourseId());
        params.put("isPublish",course.getIsPublish());
        
        var res= jdbcTemplate.update(UPDATE_COURSE,params);

    }
}
