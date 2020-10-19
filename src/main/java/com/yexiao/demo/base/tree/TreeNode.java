package com.yexiao.demo.base.tree;

import lombok.Data;

import java.util.List;

/**
 * @author xuhf
 * @date 2020/8/20 15:33
 **/
@Data
public class TreeNode {

    /**
     * id 主键
     * */
    private Long id;

    /**
     * parentId 父级id
     * */
    private Long parentId;

    /**
     * 名称
     * */
    private String name;

    /**
     * 子集
     * */
    private List<TreeNode> children;

}
