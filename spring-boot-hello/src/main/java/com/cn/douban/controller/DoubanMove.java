 package com.cn.douban.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cn.douban.service.MoviesService;

/**
 * @author Admin
 * @date 2019/07/23
 */
@RestController
public class DoubanMove {
     
     @Autowired
     private MoviesService moviesService;
     
    @RequestMapping(value="/getMovies",method=RequestMethod.GET)
    public String ObtainDouban() {
         
        return moviesService.getMoviesInfo();
     }
 
     
}
