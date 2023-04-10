package cn.jasonone.service;

import cn.jasonone.bean.Cinema;

public interface CinemaService {
    void insert(Cinema cinema);
    void deleteById(Cinema cinema);

    void updateById(Cinema cinema);

    void select(Cinema cinema);
}
