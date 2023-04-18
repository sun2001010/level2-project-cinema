package cn.jasonone.service;

import cn.jasonone.bean.Comments;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.session.SqlSession;

import java.io.IOException;
import java.util.List;

public interface CommentsService {
    void setSqlSession(SqlSession session);

    PageInfo<Comments> CommentsSelect(String fName, int pageInfo, int pageNum);

    void addComments(Comments record);

    void delete(Long id) throws IOException;
}
