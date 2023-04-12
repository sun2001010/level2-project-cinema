package cn.jasonone.service.impl;

import cn.jasonone.bean.Films;
import cn.jasonone.mapper.FilmsMapper;
import cn.jasonone.service.PageService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.Setter;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class PageServiceImpl implements PageService {
   @Setter
   private SqlSession sqlSession;
    @Override
    public PageInfo<Films> FilmSelect(Films films, int pageInfo, int pageNum) {
        FilmsMapper mapper = sqlSession.getMapper(FilmsMapper.class);
        List filmList = mapper.findAll(films);
        PageHelper.startPage(pageInfo,pageNum);
        return new PageInfo<Films>(filmList);
    }
}
