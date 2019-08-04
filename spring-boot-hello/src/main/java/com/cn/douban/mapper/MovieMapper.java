package com.cn.douban.mapper;

import java.util.List;


import com.cn.douban.model.MovieBean;

public interface MovieMapper {

    void insertMoniesInfo(MovieBean movie);

    List<MovieBean> findAll();
}
