package com.yexiao.demo.base.tree;



import java.util.List;

/**
 * @author xuhf
 * @date 2020/8/20 15:33
 **/

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<TreeNode> getChildren() {
        return children;
    }

    public void setChildren(List<TreeNode> children) {
        this.children = children;
    }
}
