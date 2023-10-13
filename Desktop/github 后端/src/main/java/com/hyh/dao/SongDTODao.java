package com.hyh.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hyh.domain.SongDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SongDTODao extends BaseMapper<SongDTO> {
}
