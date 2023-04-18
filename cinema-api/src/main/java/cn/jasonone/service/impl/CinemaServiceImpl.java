package cn.jasonone.service.impl;

import cn.jasonone.bean.Cinema;
import cn.jasonone.mapper.CinemaMapper;
import cn.jasonone.service.CinemaService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.Setter;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class CinemaServiceImpl implements CinemaService {
    @Setter
    private SqlSession sqlSession;

    //分页插件
    @Override
    public PageInfo<Cinema> CinemaSelect(Cinema record, int pageNum, int pageSize) {
        CinemaMapper mapper=sqlSession.getMapper(CinemaMapper.class);
        List classList=mapper.findAll(record);//findAll是调用mapper里的函数
        PageHelper.startPage(pageNum,pageSize);
        PageHelper.clearPage();
        return new PageInfo<Cinema>(classList);
    }
}
