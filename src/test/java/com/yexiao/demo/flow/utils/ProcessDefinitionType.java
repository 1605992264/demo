package com.yexiao.demo.flow.utils;

/** 流程定义表中的常量
 * @author weix
 * @version 1.0
 * @description
 * @date 2020/7/6 10:15
 * @since JDK1.8
 */
public class ProcessDefinitionType {

    /**
     * 审批人去重   1 = 同一个审批人在流程中出现多次时，仅保留第一个，2 = 同一个审批人仅在连续出现时，自动去重，0 = 不去重'
     */
    public static final String REMAIN_AUTO_REPEAT = "1";

    /**
     * 审批人去重   1 = 同一个审批人在流程中出现多次时，仅保留第一个，2 = 同一个审批人仅在连续出现时，自动去重，0 = 不去重'
     */
    public static final String  CONTINUOUS_AUTO_REPEAT = "2";

    /**
     * 审批人去重   1 = 同一个审批人在流程中出现多次时，仅保留第一个，2 = 同一个审批人仅在连续出现时，自动去重，0 = 不去重'
     */
    public static final String NON_AUTO_REPEAT = "0";

    /**
     * 发起人审批时自动通过  1 = 自动通过，0 = 非自动
     */
    public static final String MY_AUDIT_AUTO_PASS = "1";

    /**
     * 发起人审批时自动通过  1 = 自动通过，0 = 非自动
     */
    public static final String NON_MY_AUDIT_AUTO_PASS = "0";

    /**
     * 审批意见是否必填 1 = 必填, 0 = 不必填
     */
    public static final String VISIBLE_FOR_SPONSOR = "1";

    /**
     * 审批意见是否必填 1 = 必填, 0 = 不必填
     */
    public static final String NON_VISIBLE_FOR_SPONSOR = "0";

    /**
     * 审批意见对发起人是否可见   1 = 可见，0 = 不可见
     */
    public static final String REMARK_REQUIRED = "1";

    /**
     * 审批意见对发起人是否可见   1 = 可见，0 = 不可见
     */
    public static final String NON_REMARK_REQUIRED = "0";
}
