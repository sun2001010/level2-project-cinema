package cn.jasonone.service.impl;

import cn.jasonone.bean.Films;
import cn.jasonone.mapper.FilmsMapper;
import cn.jasonone.service.FilmsService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.Setter;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

/**
 *
 */
public class FilmsServiceImpl implements FilmsService {
    @Setter
    private SqlSession sqlSession;

    @Override
    public void insert(Films films) {
        System.out.println(films);
        sqlSession.getMapper(FilmsMapper.class).insert(films);
    }

    @Override
    public void deleteById(Integer fId) {
        sqlSession.getMapper(FilmsMapper.class).deleteByPrimaryKey(Long.valueOf(fId));
//        FilmsMapper filmsMapper = sqlSession.getMapper(FilmsMapper.class);
//        int id = filmsMapper.deleteByPrimaryKey(Long.valueOf(fId));
//        sqlSession.delete("deleteById", fId);
//        System.out.println(id);
//        if (id == fId) {
//            sqlSession.delete("deleteById", fId);
//        } else {
//            System.out.println("影片不存在！");
//        }
    }

    @Override
    public void selectById(Integer fId) {
        sqlSession.getMapper(FilmsMapper.class).selectByPrimaryKey(Long.valueOf(fId));
    }


    @Override
    public void updateById(Films films) {

        sqlSession.getMapper(FilmsMapper.class).updateByPrimaryKey(films);

    }

    @Override
    public PageInfo<Films> select(Films films, int pageInfo, int pageNum) {
        List filmsList = sqlSession.getMapper(FilmsMapper.class).findAll(films);
        PageHelper.startPage(pageInfo, pageNum);
        return new PageInfo<Films>(filmsList);
    }
}
