package cn.jasonone.service.impl;

import cn.jasonone.bean.Collect;
import cn.jasonone.bean.Comments;
import cn.jasonone.mapper.CollectMapper;
import cn.jasonone.mapper.CommentsMapper;
import cn.jasonone.mapper.OrderMapper;
import cn.jasonone.service.CommentsService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.Setter;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class CommentsServiceImpl implements CommentsService {
    @Setter
    private SqlSession sqlSession;

    @Override
    public PageInfo<Comments> CommentsSelect(String fName, int pageInfo, int pageNum) {
        CommentsMapper mapper = sqlSession.getMapper(CommentsMapper.class);
        List<Comments> commentstList = mapper.findAll(fName);
        PageHelper.startPage(pageInfo,pageNum);
        PageHelper.clearPage();
        return new PageInfo<Comments>(commentstList);
    }

    @Override
    public void addComments(Comments record) {
        CommentsMapper mapper = sqlSession.getMapper(CommentsMapper.class);
        mapper. insertSelective(record);
    }

    @Override
    public void delete(Long id) throws IOException {
        try (InputStream is = Resources.getResourceAsStream("mybatis-config.xml")) {
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
            try (SqlSession session = sqlSessionFactory.openSession()) {
                CommentsMapper mapper = session.getMapper(CommentsMapper.class);
                mapper.deleteByPrimaryKey(id);
                session.commit();
            }
        }
    }
}
