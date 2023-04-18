package cn.jasonone.service.impl;

import cn.jasonone.bean.Hall;
import cn.jasonone.mapper.HallMapper;
import cn.jasonone.service.HallService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.Setter;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class HallServiceImpl implements HallService {
    @Setter
    private SqlSession sqlSession;

    //分页插件
    @Override
    public PageInfo<Hall> HallSelect(Hall record, int pageNum, int pageSize) {
        HallMapper mapper=sqlSession.getMapper(HallMapper.class);
        List classList=mapper.findAll(record);//findAll是调用mapper里的函数
        PageHelper.startPage(pageNum,pageSize);
        PageHelper.clearPage();
        return new PageInfo<Hall>(classList);
    }
}
