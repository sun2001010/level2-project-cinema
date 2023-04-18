package cn.jasonone.service.impl;

import cn.jasonone.bean.Collect;
import cn.jasonone.mapper.CollectMapper;
import cn.jasonone.service.CollectService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.Setter;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class CollectServiceImpl implements CollectService {
    @Setter
    private SqlSession sqlSession;

    @Override
    public PageInfo<Collect> CollectSelect(Collect collect, int pageInfo, int pageNum) {
        CollectMapper mapper = sqlSession.getMapper(CollectMapper.class);
        List<Collect> collectList = mapper.findAll(collect);
        PageHelper.startPage(pageInfo,pageNum);
        PageHelper.clearPage();
        return new PageInfo<Collect>(collectList);
    }

    @Override
    public void addCollect(Collect collect) {
        CollectMapper mapper = sqlSession.getMapper(CollectMapper.class);
        mapper.addCollect(collect);
    }

    @Override
    public int findNum(String fName) {
        CollectMapper mapper = sqlSession.getMapper(CollectMapper.class);
        return mapper.findNum(fName);
    }
}
