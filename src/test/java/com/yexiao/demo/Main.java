package com.yexiao.demo;

import com.alibaba.fastjson.annotation.JSONType;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yexiao.demo.base.domain.BaseEntity;
import com.yexiao.demo.base.domain.UserInfoBaseEntity;
import com.yexiao.demo.base.tree.CreateTreeData;
import com.yexiao.demo.base.tree.TreeNode;
import sun.util.resources.zh.TimeZoneNames_zh_CN;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.*;

/**
 * @author xuhf
 * @date 2020/8/21 16:55
 **/
public class Main {

    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode();
        treeNode.setId(1L);
        treeNode.setName("父级");
        treeNode.setParentId(null);

        TreeNode treeNode1 = new TreeNode();
        treeNode1.setId(2L);
        treeNode1.setName("子级");
        treeNode1.setParentId(1L);

        TreeNode treeNode2 = new TreeNode();
        treeNode2.setId(3L);
        treeNode2.setName("滋滋级");
        treeNode2.setParentId(2L);

        List<TreeNode> list = new ArrayList<>();
        list.add(treeNode);
        list.add(treeNode1);
        list.add(treeNode2);
        CreateTreeData createTreeData = new CreateTreeData();
        List<TreeNode> objects = (List<TreeNode>) createTreeData.TreeData(list);
        for(TreeNode item : objects){
            System.out.println(item);
        }


    }



}




