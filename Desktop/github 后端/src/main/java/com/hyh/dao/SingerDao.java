package com.hyh.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hyh.domain.Singer;

import org.apache.ibatis.annotations.Mapper;


import java.util.List;

@Mapper
public interface SingerDao extends BaseMapper<Singer> {

}
