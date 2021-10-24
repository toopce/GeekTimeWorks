package com.example.jdbctrial.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

import jdk.nashorn.internal.objects.annotations.Getter;
import jdk.nashorn.internal.objects.annotations.Setter;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author <a href="https://fengwenyi.com">Erwin Feng</a>
 * @since 2021-10-24
 */
@Data
@Accessors(chain = true)
@TableName("try_in_idea")
public class TryInIdeaEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("name")
    private String name;

    /**
     * 1-男2-女
     */
    @TableField("sex")
    private Integer sex;

    @TableField("is_delete")
    private Integer isDelete;


}
