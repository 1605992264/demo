package com.yexiao.demo.flow.utils;

/**
 * @author weix
 * @version 1.0
 * @description
 * @date 2020/6/29 17:17
 * @since JDK1.8
 */
public class ConstantEnum {

    /**>>>>>>>>>>>>>>>>>>>>>> 流程定义状态  <<<<<<<<<<<<<<<<<<<<<<<<**/


    /**
     * 流程定义状态  2 = 草稿
     */
    public static final String DRAFT_STATE = "2";

    /**
     * 流程定义状态  0 = 停用
     */
    public static final String STOP_STATE = "0";

    /**
     * 流程定义状态  1 = 发布
     */
    public static final String PUBLISH_STATE = "1";

    /**
     * 1 = 开始节点
     */
    public static final String START_NODE = "1";

    /**
     * 2 = 审批节点
     */
    public static final String APPROVAL_NODE = "2";

    /**
     * 3 = 条件节点
     */
    public static final String CONDITIONAL_NODE = "3";

    /**
     *  4 = 抄送节点
     */
    public static final String COPY_NODE = "4";

    /**
     * 8 = 空节点
     */
    public static final String EMPTY_NODE = "8";




    /**>>>>>>>>>>>>>>>>>>>>>> 审批类型(包括前端后端)  <<<<<<<<<<<<<<<<<<<<<<<<**/


    /**
     * 审批类型 1 = 指定用户(后端)
     */
    public static final String AUDIT_APPOINT_CLIENT = "1";

    /**
     * 审批类型 1 = 指定用户(前端)
     */
    public static final String AUDIT_NEW_APPOINT_CLIENT = "user";


    /**
     * 审批类型 2 = 角色(后端)
     */
    public static final String AUDIT_ROLE = "2";

    /**
     * 审批类型 2 = 角色(前端)
     */
    public static final String AUDIT_NEW_ROLE = "role";

    /**
     * 审批类型 3 = 部门(后端)
     */
    public static final String AUDIT_DEPARTMENT = "3";

    /**
     * 审批类型 3 = 部门(前端)
     */
    public static final String AUDIT_NEW_DEPARTMENT = "office";

    /**
     * 审批类型 4 = 部门领导(后端)
     */
    public static final String AUDIT_DEPARTMENT_LEADER = "4";

    /**
     * 审批类型 4 = 部门领导(前端)
     */
    public static final String AUDIT_NEW_DEPARTMENT_LEADER = "director";

    /**
     * 审批类型 5 = 发起人自己(后端)
     */
    public static final String AUDIT_ONESELF = "5";

    /**
     * 审批类型 5 = 发起人自己(前端)
     */
    public static final String AUDIT_NEW_ONESELF = "myself";

    /**
     * 审批类型 6 = 发起人自选(后端)
     */
    public static final String AUDIT_OPTIONAL = "6";

    /**
     * 审批类型 6 = 发起人自选(前端)
     */
    public static final String AUDIT_NEW_OPTIONAL = "optional";


    /**>>>>>>>>>>>>>>>>>>>>>> 审批方式  <<<<<<<<<<<<<<<<<<<<<<<<**/


    /**
     * 审批方式 1 = 或签
     */
    public static final String OR_SIGN_METHOD = "1";

    /**
     * 审批方式 2 = 会签
     */
    public static final String AND_SIGN_METHOD = "2";

    /**
     * 审批方式 3 = 依次审批
     */
    public static final String SUCCESSIVE_SIGN_METHOD = "3";

    /**>>>>>>>>>>>>>>>>>>>>>> 参与人类型  <<<<<<<<<<<<<<<<<<<<<<<<**/


    /**
     * 参与人类型 1 = 用户
     */
    public static final String ACTOR_CLIENT = "1";

    /**
     * 参与人类型 2 = 部门领导
     */
    public static final String ACTOR_DEPT_LEADER = "2";

    /**
     * 参与人类型 3 = 角色
     */
    public static final String ACTOR_ROLE = "3";

    /**
     * 参与人类型 4 = 所有人
     */
    public static final String ACTOR_ALL_CLIENT = "4";

    /**
     * 参与人类型 5 = 部门
     */
    public static final String ACTOR_DEPT_CLIENT = "5";

    /**
     * 参与人类型 6 = 发起人自选
     */
    public static final String ACTOR_MYSELF = "6";


    /**>>>>>>>>>>>>>>>>>>>>>> 条件比较类型  <<<<<<<<<<<<<<<<<<<<<<<<**/



    /**
     * 条件比较类型 4 = 大于(针对前端)
     */
    public static final String COMPARE_GT = "4";

    /**
     * 条件比较类型 5 = 大于等于(针对前端)
     */
    public static final String COMPARE_GT_EQ = "5";

    /**
     * 条件比较类型 1 = 小于
     */
    public static final String COMPARE_LT = "1";

    /**
     * 条件比较类型 2 = 小于等于
     */
    public static final String COMPARE_LT_EQ = "2";

    /**
     * 条件比较类型 3 = 等于
     */
    public static final String COMPARE_EQ = "3";

    /**
     * 条件比较类型 6 = 包含于
     */
    public static final String COMPARE_INCLUDE = "6";

    /**
     * 条件比较类型 7 = 不包含于
     */
    public static final String COMPARE_DIS_INCLUDE = "7";

    /**
     * 条件比较类型 8= 大于(针对后端)
     */
    public static final String COMPARE_NEW_GT = "8";

    /**
     * 条件比较类型 9 = 大于等于(针对前端)
     */
    public static final String COMPARE_NEW_GT_EQ = "9";


    /**>>>>>>>>>>>>>>>>>>>>>> 变量类型  <<<<<<<<<<<<<<<<<<<<<<<<**/


    /**
     * 变量类型 1 = int
     */
    public static final String VARIABLE_TYPE_INT = "int";

    /**
     * 变量类型 2 = String
     */
    public static final String VARIABLE_TYPE_STRING = "string";

    /**
     * 变量类型 3 = float
     */
    public static final String VARIABLE_TYPE_FLOAT = "float";

    /**
     * 变量类型 4 = double
     */
    public static final String VARIABLE_TYPE_DOUBLE = "4";


    /**>>>>>>>>>>>>>>>>>>>>>> 是否存在分支  <<<<<<<<<<<<<<<<<<<<<<<<**/


    /**
     * 是否存在分支 1 = 存在
     */
    public static final String EXIST_BRANCH = "1";

    /**
     * 是否存在分支 0 = 不存在
     */
    public static final String NON_EXIST_BRANCH = "0";



    /**>>>>>>>>>>>>>>>>>>>>>> 流程实例状态  <<<<<<<<<<<<<<<<<<<<<<<<**/


    /**
     * 流程实例状态 1=开始
     */
    public static final String  INSTANCE_START = "1";

    /**
     * 流程实例状态 2 = 进行中
     */
    public static final String  INSTANCE_ONGOING = "2";

    /**
     * 流程实例状态 3 = 结束
     */
    public static final String  INSTANCE_END = "3";



    /**>>>>>>>>>>>>>>>>>>>>>> 操作类型  <<<<<<<<<<<<<<<<<<<<<<<<**/



    /**
     *  操作类型 1 = 开始
      */
    public static final String OPERATE_TYPE_START = "1";

    /**
     *  操作类型 2 = 自动通过
     */
    public static final String OPERATE_TYPE_AUTO_COMMIT = "2";

    /**
     * 操作类型 3 = 抄送
     */
    public static final String OPERATE_TYPE_COPY = "3";

    /**
     * 操作类型 4 = 通过
     */
    public static final String OPERATE_TYPE_PASS = "4";

    /**
     * 操作类型 5 = 驳回
     */
    public static final String OPERATE_TYPE_REJECT = "5";

    /**
     * 操作类型 6 = 结束
     */
    public static final String OPERATE_TYPE_END = "6";
}
