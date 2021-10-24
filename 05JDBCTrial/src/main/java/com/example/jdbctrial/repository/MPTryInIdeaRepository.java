package com.example.jdbctrial.repository;

import com.example.jdbctrial.entity.TryInIdeaEntity;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author <a href="https://fengwenyi.com">Erwin Feng</a>
 * @since 2021-10-24
 */
public interface MPTryInIdeaRepository extends IService<TryInIdeaEntity> {

    public List<TryInIdeaEntity> getlist();

}
