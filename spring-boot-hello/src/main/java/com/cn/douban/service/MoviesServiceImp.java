package com.cn.douban.service;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.cn.douban.mapper.MovieMapper;
import com.cn.douban.model.MovieBean;
import com.cn.douban.util.GetJson;

/**
 * @author Admin
 * @date 2019/07/23
 */
@Service
public class MoviesServiceImp implements MoviesService {

    public static final String address =
        "https://Movie.douban.com/j/new_search_subjects?sort=U&range=0,10&tags=&start=";

    @Autowired
    private MovieMapper movieDao;

    @Override
    public String getMoviesInfo() {

        JSONArray respjson = null;
        int start;
        int total = 0;
        int end=1000;
        for (start = 0; start < end; start += 20) {
             StringBuilder adres = new StringBuilder();
             String doubanaddress = adres.append(address).append(start).toString();
            JSONObject dayLine;
            try {
                dayLine = new GetJson().getHttpJson(doubanaddress, 1);
                respjson = dayLine.getJSONArray("data");
            } catch (Exception e) {
                return null;
            }
            
            List<MovieBean> list = JSON.parseArray(respjson.toString(), MovieBean.class);
            if (start > end){
                System.out.println("已经爬取到底了");
                return null;
            }
            for (MovieBean movie : list) {
                movieDao.insertMoniesInfo(movie);
            }
            total=total+list.size();
            System.out.println("正在爬取中---共抓取:" + total + "条数据");
        }
        return null;
    }

}
