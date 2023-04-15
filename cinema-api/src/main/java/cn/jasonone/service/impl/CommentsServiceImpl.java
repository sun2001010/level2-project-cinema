package cn.jasonone.service.impl;

import cn.jasonone.bean.Collect;
import cn.jasonone.bean.Comments;
import cn.jasonone.mapper.CollectMapper;
import cn.jasonone.mapper.CommentsMapper;
import cn.jasonone.service.CommentsService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.Setter;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class CommentsServiceImpl implements CommentsService {
    @Setter
    private SqlSession sqlSession;

    @Override
    public PageInfo<Comments> CommentsSelect(Comments comments, int pageInfo, int pageNum) {
        CommentsMapper mapper = sqlSession.getMapper(CommentsMapper.class);
        List<Comments> commentstList = mapper.findAll(comments);
        PageHelper.startPage(pageInfo,pageNum);
        return new PageInfo<Comments>(commentstList);
    }

    @Override
    public void addComments(Comments record) {
        CommentsMapper mapper = sqlSession.getMapper(CommentsMapper.class);
        mapper. insertSelective(record);
    }
}
