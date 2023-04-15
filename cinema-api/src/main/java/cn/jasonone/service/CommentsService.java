package cn.jasonone.service;

import cn.jasonone.bean.Comments;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public interface CommentsService {
    void setSqlSession(SqlSession session);

    PageInfo<Comments> CommentsSelect(Comments comments, int pageInfo, int pageNum);

    void addComments(Comments record);
}
