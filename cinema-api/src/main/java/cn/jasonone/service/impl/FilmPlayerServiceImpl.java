package cn.jasonone.service.impl;

import cn.jasonone.bean.FilmPlayer;
import cn.jasonone.mapper.FilmPlayerMapper;
import cn.jasonone.service.FilmPlayerService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.Setter;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class FilmPlayerServiceImpl implements FilmPlayerService {
    @Setter
    private SqlSession sqlSession;
    @Override
    public PageInfo<FilmPlayer> FilmPlayerSelect(FilmPlayer filmPlayer, int pageInfo, int pageNum) {
        FilmPlayerMapper mapper = sqlSession.getMapper(FilmPlayerMapper.class);
        List filmPlayerList = mapper.findAll(filmPlayer);
        PageHelper.startPage(pageInfo,pageNum);
        PageHelper.clearPage();
        return new PageInfo<FilmPlayer>(filmPlayerList);
    }

    @Override
    public List<String> findPlayerName(String playerName) {
        FilmPlayerMapper mapper = sqlSession.getMapper(FilmPlayerMapper.class);
        List<String> playerNameList = mapper.findPlayerName(playerName);
        return playerNameList;
    }
}
