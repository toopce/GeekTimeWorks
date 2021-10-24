package com.example.jdbctrial.repository.impl;

import com.example.jdbctrial.entity.TryInIdeaEntity;
import com.example.jdbctrial.dao.ITryInIdeaDao;
import com.example.jdbctrial.repository.MPTryInIdeaRepository;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @since 2021-10-24
 */
@Service
public class TryInIdeaRepositoryImpl extends ServiceImpl<ITryInIdeaDao, TryInIdeaEntity> implements MPTryInIdeaRepository {




    public List<TryInIdeaEntity> getlist(){

        //查
        List<TryInIdeaEntity> list = this.list();
        return list;

    };
}
