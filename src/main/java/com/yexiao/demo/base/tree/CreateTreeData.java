package com.yexiao.demo.base.tree;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author xuhf
 * @date 2020/8/20 15:32
 **/
public class CreateTreeData {

    /**
     * 泛型树的封装
     * @param data
     * @return 返回一颗标准定义的树
     */
    public static  <E extends TreeNode> List<E> TreeData(List<E> data) {
        /** 判断数据是否为空 **/
        if (data.isEmpty()) {
            return null;
        }
        /** 最终返回的树结构 **/
        List<E> orgTree = new ArrayList<>();
        /** 将泛型中的数据转换为treeNode对象 **/
        /** 迭代遍历树中的根节点 **/
        Iterator<? extends TreeNode> iterator = data.iterator();
        while (iterator.hasNext()) {
            TreeNode treeNode = iterator.next();
            /** 判断数据是否为根节点，根节点父级一般为null或0 **/
            if ( null == treeNode.getParentId() || "0".equals(treeNode.getParentId())) {
                /** 将根节点放入最终树结构中 **/
                try {
                    Method add = orgTree.getClass().getDeclaredMethod("add", Object.class);
                    add.invoke(orgTree,treeNode);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                /** 遍历相应的子节点 **/
                childrenNodeInfo(treeNode, data);
            }
        }
        return orgTree;
    }

    /**
     * 遍历根节点下的数据及子节点
     * @param treeNode 当前节点数据
     * @param nodes 需要遍历的数据
     */
    private static <E extends TreeNode> void childrenNodeInfo(TreeNode treeNode,List<E> nodes) {
        /** 子节点/数据结构 **/
        List<TreeNode> childrenList = new ArrayList<>();
        Iterator<? extends TreeNode> iterator = nodes.iterator();
        while (iterator.hasNext()) {
            TreeNode node = iterator.next();
            if (null != node.getParentId() && null != treeNode.getId()
                    && treeNode.getId().equals(node.getParentId())) {
                childrenList.add(node);
                childrenNodeInfo(node, nodes);
            }
        }
        treeNode.setChildren(childrenList);
    }
}
