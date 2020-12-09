package com.yexiao.demo.base.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 可以把json对象转成json模板(json数据格式)
 *
 * @author xuhf
 * @date 2020/12/3 14:53
 **/
public class JsonUtils {

    public static void main(String[] args) {
        String json = "{\n" +
                "  \"basicSetting\": {\n" +
                "    \"flowName\": \"费用报销\",\n" +
                "    \"flowImg\": 0,\n" +
                "    \"flowGroup\": 2,\n" +
                "    \"flowRemark\": \"审批意见填写\"\n" +
                "  },\n" +
                "  \"formData\": {\n" +
                "    \"fields\": [\n" +
                "      {\n" +
                "        \"formId\": \"name\",\n" +
                "        \"default\": \"DEFINE_STR:$CONSTNAME_$CONSTDATE_费用报销\",\n" +
                "        \"hidden\": false,\n" +
                "        \"searchFiled\": true,\n" +
                "        \"isCondition\": false,\n" +
                "        \"disabled\": true,\n" +
                "        \"label\": \"报销单名称\",\n" +
                "        \"type\": \"input\",\n" +
                "        \"required\": true,\n" +
                "        \"listFiled\": true\n" +
                "      },\n" +
                "      {\n" +
                "        \"formId\": \"number\",\n" +
                "        \"default\": \"\",\n" +
                "        \"searchFiled\": true,\n" +
                "        \"isCondition\": false,\n" +
                "        \"disabled\": true,\n" +
                "        \"label\": \"报销单编号\",\n" +
                "        \"placeholder\": \"保存时自动生成\",\n" +
                "        \"type\": \"input\",\n" +
                "        \"required\": false,\n" +
                "        \"listFiled\": true\n" +
                "      },\n" +
                "      {\n" +
                "        \"formId\": \"user\",\n" +
                "        \"default\": \"DEFINE_STR:$CONSTUSERID\",\n" +
                "        \"hidden\": true,\n" +
                "        \"searchFiled\": false,\n" +
                "        \"isCondition\": false,\n" +
                "        \"disabled\": true,\n" +
                "        \"label\": \"报销人\",\n" +
                "        \"type\": \"input\",\n" +
                "        \"required\": true,\n" +
                "        \"listFiled\": true\n" +
                "      },\n" +
                "      {\n" +
                "        \"formId\": \"userName\",\n" +
                "        \"default\": \"DEFINE_STR:$CONSTNAME\",\n" +
                "        \"searchFiled\": false,\n" +
                "        \"isCondition\": false,\n" +
                "        \"disabled\": true,\n" +
                "        \"label\": \"报销人\",\n" +
                "        \"type\": \"input\",\n" +
                "        \"required\": true,\n" +
                "        \"listFiled\": true\n" +
                "      },\n" +
                "      {\n" +
                "        \"formId\": \"time\",\n" +
                "        \"default\": \"DEFINE_STR:$CONSTDATETIME\",\n" +
                "        \"searchFiled\": false,\n" +
                "        \"isCondition\": false,\n" +
                "        \"disabled\": true,\n" +
                "        \"label\": \"报销时间\",\n" +
                "        \"type\": \"datetime\",\n" +
                "        \"required\": true,\n" +
                "        \"listFiled\": false\n" +
                "      },\n" +
                "      {\n" +
                "        \"formId\": \"projectName\",\n" +
                "        \"default\": \"1\",\n" +
                "        \"searchFiled\": false,\n" +
                "        \"isCondition\": false,\n" +
                "        \"dict\": {\n" +
                "          \"dictData\": [],\n" +
                "          \"dictType\": \"project_no\"\n" +
                "        },\n" +
                "        \"disabled\": false,\n" +
                "        \"label\": \"关联项目\",\n" +
                "        \"type\": \"select\",\n" +
                "        \"required\": true,\n" +
                "        \"listFiled\": false\n" +
                "      },\n" +
                "      {\n" +
                "        \"formId\": \"office\",\n" +
                "        \"default\": \"DEFINE_STR:$CONSTOFFICE\",\n" +
                "        \"searchFiled\": false,\n" +
                "        \"isCondition\": false,\n" +
                "        \"disabled\": true,\n" +
                "        \"label\": \"所属部门\",\n" +
                "        \"type\": \"input\",\n" +
                "        \"required\": true,\n" +
                "        \"listFiled\": true\n" +
                "      },\n" +
                "      {\n" +
                "        \"formId\": \"allmoney\",\n" +
                "        \"default\": \"DEFINE_STR:$ARRAYcostDetail>money\",\n" +
                "        \"searchFiled\": true,\n" +
                "        \"isCondition\": true,\n" +
                "        \"disabled\": true,\n" +
                "        \"label\": \"报销总金额(元)\",\n" +
                "        \"type\": \"inputNumber\",\n" +
                "        \"required\": true,\n" +
                "        \"listFiled\": true\n" +
                "      },\n" +
                "      {\n" +
                "        \"formId\": \"flag\",\n" +
                "        \"default\": \"1\",\n" +
                "        \"hidden\": true,\n" +
                "        \"searchFiled\": true,\n" +
                "        \"isCondition\": false,\n" +
                "        \"dict\": {\n" +
                "          \"dictData\": [\n" +
                "            {\n" +
                "              \"label\": \"通过\",\n" +
                "              \"value\": \"1\"\n" +
                "            },\n" +
                "            {\n" +
                "              \"label\": \"不通过\",\n" +
                "              \"value\": \"2\"\n" +
                "            }\n" +
                "          ]\n" +
                "        },\n" +
                "        \"disabled\": false,\n" +
                "        \"label\": \"是否通过\",\n" +
                "        \"type\": \"select\",\n" +
                "        \"required\": true,\n" +
                "        \"listFiled\": true\n" +
                "      },\n" +
                "      {\n" +
                "        \"formId\": \"comment\",\n" +
                "        \"default\": \"\",\n" +
                "        \"hidden\": true,\n" +
                "        \"searchFiled\": true,\n" +
                "        \"isCondition\": false,\n" +
                "        \"disabled\": false,\n" +
                "        \"label\": \"审批意见\",\n" +
                "        \"type\": \"textarea\",\n" +
                "        \"required\": true,\n" +
                "        \"listFiled\": true\n" +
                "      },\n" +
                "      {\n" +
                "        \"formId\": \"procInsId\",\n" +
                "        \"default\": \"\",\n" +
                "        \"hidden\": true,\n" +
                "        \"searchFiled\": true,\n" +
                "        \"isCondition\": false,\n" +
                "        \"disabled\": false,\n" +
                "        \"label\": \"实例id\",\n" +
                "        \"type\": \"input\",\n" +
                "        \"required\": true,\n" +
                "        \"listFiled\": true\n" +
                "      },\n" +
                "      {\n" +
                "        \"formId\": \"costDetail\",\n" +
                "        \"default\": \"\",\n" +
                "        \"childArray\": [\n" +
                "          {\n" +
                "            \"formId\": \"happenDate\",\n" +
                "            \"default\": \"\",\n" +
                "            \"label\": \"发生日期\",\n" +
                "            \"type\": \"date\",\n" +
                "            \"required\": true\n" +
                "          },\n" +
                "          {\n" +
                "            \"formId\": \"type\",\n" +
                "            \"default\": \"1\",\n" +
                "            \"dict\": {\n" +
                "              \"dictData\": [],\n" +
                "              \"dictType\": \"cost_type\"\n" +
                "            },\n" +
                "            \"label\": \"费用类型\",\n" +
                "            \"type\": \"select\",\n" +
                "            \"required\": true\n" +
                "          },\n" +
                "          {\n" +
                "            \"formId\": \"money\",\n" +
                "            \"default\": \"\",\n" +
                "            \"isMoney\": true,\n" +
                "            \"label\": \"报销金额(元)\",\n" +
                "            \"type\": \"inputNumber\",\n" +
                "            \"required\": true\n" +
                "          },\n" +
                "          {\n" +
                "            \"formId\": \"content\",\n" +
                "            \"default\": \"\",\n" +
                "            \"label\": \"报销内容\",\n" +
                "            \"type\": \"input\",\n" +
                "            \"required\": true\n" +
                "          },\n" +
                "          {\n" +
                "            \"formId\": \"remarks\",\n" +
                "            \"default\": \"\",\n" +
                "            \"label\": \"费用说明\",\n" +
                "            \"type\": \"textarea\",\n" +
                "            \"required\": true\n" +
                "          },\n" +
                "          {\n" +
                "            \"formId\": \"filePath\",\n" +
                "            \"default\": \"\",\n" +
                "            \"label\": \"费用票据\",\n" +
                "            \"type\": \"upload\",\n" +
                "            \"required\": true\n" +
                "          }\n" +
                "        ],\n" +
                "        \"searchFiled\": false,\n" +
                "        \"isCondition\": false,\n" +
                "        \"disabled\": false,\n" +
                "        \"label\": \"费用明细\",\n" +
                "        \"type\": \"childTabel\",\n" +
                "        \"required\": false,\n" +
                "        \"listFiled\": false\n" +
                "      }\n" +
                "    ]\n" +
                "  },\n" +
                "  \"processData\": {\n" +
                "    \"type\": \"start\",\n" +
                "    \"content\": \"所有人\",\n" +
                "    \"properties\": {\n" +
                "      \"initiator\": \"all\",\n" +
                "      \"title\": \"发起人\",\n" +
                "      \"formOperates\": [\n" +
                "        {\n" +
                "          \"formId\": \"name\",\n" +
                "          \"formOperate\": 2\n" +
                "        },\n" +
                "        {\n" +
                "          \"formId\": \"number\",\n" +
                "          \"formOperate\": 2\n" +
                "        },\n" +
                "        {\n" +
                "          \"formId\": \"user\",\n" +
                "          \"formOperate\": 2\n" +
                "        },\n" +
                "        {\n" +
                "          \"formId\": \"userName\",\n" +
                "          \"formOperate\": 2\n" +
                "        },\n" +
                "        {\n" +
                "          \"formId\": \"time\",\n" +
                "          \"formOperate\": 2\n" +
                "        },\n" +
                "        {\n" +
                "          \"formId\": \"projectName\",\n" +
                "          \"formOperate\": 2\n" +
                "        },\n" +
                "        {\n" +
                "          \"formId\": \"office\",\n" +
                "          \"formOperate\": 2\n" +
                "        },\n" +
                "        {\n" +
                "          \"formId\": \"allmoney\",\n" +
                "          \"formOperate\": 2\n" +
                "        },\n" +
                "        {\n" +
                "          \"formId\": \"flag\",\n" +
                "          \"formOperate\": 2\n" +
                "        },\n" +
                "        {\n" +
                "          \"formId\": \"comment\",\n" +
                "          \"formOperate\": 2\n" +
                "        },\n" +
                "        {\n" +
                "          \"formId\": \"procInsId\",\n" +
                "          \"formOperate\": 2\n" +
                "        },\n" +
                "        {\n" +
                "          \"formId\": \"costDetail\",\n" +
                "          \"formOperate\": 2\n" +
                "        },\n" +
                "        {\n" +
                "          \"formId\": \"happenDate\",\n" +
                "          \"formOperate\": 2\n" +
                "        },\n" +
                "        {\n" +
                "          \"formId\": \"type\",\n" +
                "          \"formOperate\": 2\n" +
                "        },\n" +
                "        {\n" +
                "          \"formId\": \"money\",\n" +
                "          \"formOperate\": 2\n" +
                "        },\n" +
                "        {\n" +
                "          \"formId\": \"content\",\n" +
                "          \"formOperate\": 2\n" +
                "        },\n" +
                "        {\n" +
                "          \"formId\": \"remarks\",\n" +
                "          \"formOperate\": 2\n" +
                "        },\n" +
                "        {\n" +
                "          \"formId\": \"filePath\",\n" +
                "          \"formOperate\": 2\n" +
                "        }\n" +
                "      ]\n" +
                "    },\n" +
                "    \"nodeId\": \"26c7edc9c8751ec8fe7a145c1f7162e8\",\n" +
                "    \"childNode\": {\n" +
                "      \"prevId\": \"26c7edc9c8751ec8fe7a145c1f7162e8\",\n" +
                "      \"type\": \"approver\",\n" +
                "      \"nodeId\": \"5eb886a02560862cda41f630328950d8\",\n" +
                "      \"content\": \"出纳角色\",\n" +
                "      \"properties\": {\n" +
                "        \"counterSign\": 1,\n" +
                "        \"optionalMultiUser\": false,\n" +
                "        \"optionalRange\": \"all\",\n" +
                "        \"approvers\": [\n" +
                "          {\n" +
                "            \"name\": \"出纳角色\",\n" +
                "            \"id\": \"8028257f92c4434e97411eda5998136b\"\n" +
                "          }\n" +
                "        ],\n" +
                "        \"rejectionMod\": 2,\n" +
                "        \"assigneeType\": \"role\",\n" +
                "        \"title\": \"审批人\",\n" +
                "        \"formOperates\": [\n" +
                "          {\n" +
                "            \"formId\": \"name\",\n" +
                "            \"formOperate\": 1\n" +
                "          },\n" +
                "          {\n" +
                "            \"formId\": \"number\",\n" +
                "            \"formOperate\": 1\n" +
                "          },\n" +
                "          {\n" +
                "            \"formId\": \"user\",\n" +
                "            \"formOperate\": 1\n" +
                "          },\n" +
                "          {\n" +
                "            \"formId\": \"userName\",\n" +
                "            \"formOperate\": 1\n" +
                "          },\n" +
                "          {\n" +
                "            \"formId\": \"time\",\n" +
                "            \"formOperate\": 1\n" +
                "          },\n" +
                "          {\n" +
                "            \"formId\": \"projectName\",\n" +
                "            \"formOperate\": 1\n" +
                "          },\n" +
                "          {\n" +
                "            \"formId\": \"office\",\n" +
                "            \"formOperate\": 1\n" +
                "          },\n" +
                "          {\n" +
                "            \"formId\": \"allmoney\",\n" +
                "            \"formOperate\": 1\n" +
                "          },\n" +
                "          {\n" +
                "            \"formId\": \"flag\",\n" +
                "            \"formOperate\": 1\n" +
                "          },\n" +
                "          {\n" +
                "            \"formId\": \"comment\",\n" +
                "            \"formOperate\": 1\n" +
                "          },\n" +
                "          {\n" +
                "            \"formId\": \"procInsId\",\n" +
                "            \"formOperate\": 1\n" +
                "          },\n" +
                "          {\n" +
                "            \"formId\": \"costDetail\",\n" +
                "            \"formOperate\": 1\n" +
                "          },\n" +
                "          {\n" +
                "            \"formId\": \"happenDate\",\n" +
                "            \"formOperate\": 1\n" +
                "          },\n" +
                "          {\n" +
                "            \"formId\": \"type\",\n" +
                "            \"formOperate\": 1\n" +
                "          },\n" +
                "          {\n" +
                "            \"formId\": \"money\",\n" +
                "            \"formOperate\": 1\n" +
                "          },\n" +
                "          {\n" +
                "            \"formId\": \"content\",\n" +
                "            \"formOperate\": 1\n" +
                "          },\n" +
                "          {\n" +
                "            \"formId\": \"remarks\",\n" +
                "            \"formOperate\": 1\n" +
                "          },\n" +
                "          {\n" +
                "            \"formId\": \"filePath\",\n" +
                "            \"formOperate\": 1\n" +
                "          }\n" +
                "        ]\n" +
                "      }\n" +
                "    },\n" +
                "    \"conditionNodes\": [\n" +
                "      {\n" +
                "        \"prevId\": \"26c7edc9c8751ec8fe7a145c1f7162e8\",\n" +
                "        \"type\": \"condition\",\n" +
                "        \"nodeId\": \"086c933903cea703be3cb9a22795e527\",\n" +
                "        \"content\": \"[发起人: 研发中心]\\n\",\n" +
                "        \"properties\": {\n" +
                "          \"isDefault\": false,\n" +
                "          \"initiator\": [\n" +
                "            {\n" +
                "              \"name\": \"研发中心\",\n" +
                "              \"id\": \"44199cfcc76c4ffc836f0846c7de675a\",\n" +
                "              \"type\": \"office\"\n" +
                "            }\n" +
                "          ],\n" +
                "          \"title\": \"条件1\",\n" +
                "          \"conditions\": [],\n" +
                "          \"priority\": 0\n" +
                "        },\n" +
                "        \"childNode\": {\n" +
                "          \"prevId\": \"086c933903cea703be3cb9a22795e527\",\n" +
                "          \"type\": \"approver\",\n" +
                "          \"nodeId\": \"32ce2110d31c63633d5dff24d644fa9f\",\n" +
                "          \"content\": \"赖杰瑶\",\n" +
                "          \"properties\": {\n" +
                "            \"counterSign\": 2,\n" +
                "            \"optionalMultiUser\": false,\n" +
                "            \"optionalRange\": \"all\",\n" +
                "            \"approvers\": [\n" +
                "              {\n" +
                "                \"name\": \"赖杰瑶\",\n" +
                "                \"id\": \"6d724d509ab449368f373e68da7bafe3\"\n" +
                "              }\n" +
                "            ],\n" +
                "            \"rejectionMod\": 2,\n" +
                "            \"assigneeType\": \"user\",\n" +
                "            \"title\": \"审批人\",\n" +
                "            \"formOperates\": [\n" +
                "              {\n" +
                "                \"formId\": \"name\",\n" +
                "                \"formOperate\": 1\n" +
                "              },\n" +
                "              {\n" +
                "                \"formId\": \"number\",\n" +
                "                \"formOperate\": 1\n" +
                "              },\n" +
                "              {\n" +
                "                \"formId\": \"user\",\n" +
                "                \"formOperate\": 1\n" +
                "              },\n" +
                "              {\n" +
                "                \"formId\": \"userName\",\n" +
                "                \"formOperate\": 1\n" +
                "              },\n" +
                "              {\n" +
                "                \"formId\": \"time\",\n" +
                "                \"formOperate\": 1\n" +
                "              },\n" +
                "              {\n" +
                "                \"formId\": \"projectName\",\n" +
                "                \"formOperate\": 1\n" +
                "              },\n" +
                "              {\n" +
                "                \"formId\": \"office\",\n" +
                "                \"formOperate\": 1\n" +
                "              },\n" +
                "              {\n" +
                "                \"formId\": \"allmoney\",\n" +
                "                \"formOperate\": 1\n" +
                "              },\n" +
                "              {\n" +
                "                \"formId\": \"flag\",\n" +
                "                \"formOperate\": 1\n" +
                "              },\n" +
                "              {\n" +
                "                \"formId\": \"comment\",\n" +
                "                \"formOperate\": 1\n" +
                "              },\n" +
                "              {\n" +
                "                \"formId\": \"procInsId\",\n" +
                "                \"formOperate\": 1\n" +
                "              },\n" +
                "              {\n" +
                "                \"formId\": \"costDetail\",\n" +
                "                \"formOperate\": 1\n" +
                "              },\n" +
                "              {\n" +
                "                \"formId\": \"happenDate\",\n" +
                "                \"formOperate\": 1\n" +
                "              },\n" +
                "              {\n" +
                "                \"formId\": \"type\",\n" +
                "                \"formOperate\": 1\n" +
                "              },\n" +
                "              {\n" +
                "                \"formId\": \"money\",\n" +
                "                \"formOperate\": 1\n" +
                "              },\n" +
                "              {\n" +
                "                \"formId\": \"content\",\n" +
                "                \"formOperate\": 1\n" +
                "              },\n" +
                "              {\n" +
                "                \"formId\": \"remarks\",\n" +
                "                \"formOperate\": 1\n" +
                "              },\n" +
                "              {\n" +
                "                \"formId\": \"filePath\",\n" +
                "                \"formOperate\": 1\n" +
                "              }\n" +
                "            ]\n" +
                "          },\n" +
                "          \"childNode\": {\n" +
                "            \"prevId\": \"32ce2110d31c63633d5dff24d644fa9f\",\n" +
                "            \"type\": \"approver\",\n" +
                "            \"nodeId\": \"fbe472ddf7b0ab6fd917dea0267d8635\",\n" +
                "            \"content\": \"财务角色\",\n" +
                "            \"properties\": {\n" +
                "              \"counterSign\": 1,\n" +
                "              \"optionalMultiUser\": false,\n" +
                "              \"optionalRange\": \"all\",\n" +
                "              \"approvers\": [\n" +
                "                {\n" +
                "                  \"name\": \"财务角色\",\n" +
                "                  \"id\": \"c91f5a1ece1540a59cdf1f47513da511\"\n" +
                "                }\n" +
                "              ],\n" +
                "              \"rejectionMod\": 2,\n" +
                "              \"assigneeType\": \"role\",\n" +
                "              \"title\": \"审批人\",\n" +
                "              \"formOperates\": [\n" +
                "                {\n" +
                "                  \"formId\": \"name\",\n" +
                "                  \"formOperate\": 1\n" +
                "                },\n" +
                "                {\n" +
                "                  \"formId\": \"number\",\n" +
                "                  \"formOperate\": 1\n" +
                "                },\n" +
                "                {\n" +
                "                  \"formId\": \"user\",\n" +
                "                  \"formOperate\": 1\n" +
                "                },\n" +
                "                {\n" +
                "                  \"formId\": \"userName\",\n" +
                "                  \"formOperate\": 1\n" +
                "                },\n" +
                "                {\n" +
                "                  \"formId\": \"time\",\n" +
                "                  \"formOperate\": 1\n" +
                "                },\n" +
                "                {\n" +
                "                  \"formId\": \"projectName\",\n" +
                "                  \"formOperate\": 1\n" +
                "                },\n" +
                "                {\n" +
                "                  \"formId\": \"office\",\n" +
                "                  \"formOperate\": 1\n" +
                "                },\n" +
                "                {\n" +
                "                  \"formId\": \"allmoney\",\n" +
                "                  \"formOperate\": 1\n" +
                "                },\n" +
                "                {\n" +
                "                  \"formId\": \"flag\",\n" +
                "                  \"formOperate\": 1\n" +
                "                },\n" +
                "                {\n" +
                "                  \"formId\": \"comment\",\n" +
                "                  \"formOperate\": 1\n" +
                "                },\n" +
                "                {\n" +
                "                  \"formId\": \"procInsId\",\n" +
                "                  \"formOperate\": 1\n" +
                "                },\n" +
                "                {\n" +
                "                  \"formId\": \"costDetail\",\n" +
                "                  \"formOperate\": 1\n" +
                "                },\n" +
                "                {\n" +
                "                  \"formId\": \"happenDate\",\n" +
                "                  \"formOperate\": 1\n" +
                "                },\n" +
                "                {\n" +
                "                  \"formId\": \"type\",\n" +
                "                  \"formOperate\": 1\n" +
                "                },\n" +
                "                {\n" +
                "                  \"formId\": \"money\",\n" +
                "                  \"formOperate\": 1\n" +
                "                },\n" +
                "                {\n" +
                "                  \"formId\": \"content\",\n" +
                "                  \"formOperate\": 1\n" +
                "                },\n" +
                "                {\n" +
                "                  \"formId\": \"remarks\",\n" +
                "                  \"formOperate\": 1\n" +
                "                },\n" +
                "                {\n" +
                "                  \"formId\": \"filePath\",\n" +
                "                  \"formOperate\": 1\n" +
                "                }\n" +
                "              ]\n" +
                "            }\n" +
                "          }\n" +
                "        }\n" +
                "      },\n" +
                "      {\n" +
                "        \"prevId\": \"26c7edc9c8751ec8fe7a145c1f7162e8\",\n" +
                "        \"type\": \"condition\",\n" +
                "        \"nodeId\": \"faca047b9fd2537ecba2f4047ea872e2\",\n" +
                "        \"content\": \"[发起人: 李敏]\\n\",\n" +
                "        \"properties\": {\n" +
                "          \"isDefault\": false,\n" +
                "          \"initiator\": [\n" +
                "            {\n" +
                "              \"name\": \"李敏\",\n" +
                "              \"id\": \"c80d901a1cee4f60a67c1f0c95f43011\",\n" +
                "              \"type\": \"user\"\n" +
                "            }\n" +
                "          ],\n" +
                "          \"title\": \"条件2\",\n" +
                "          \"conditions\": [],\n" +
                "          \"priority\": 1\n" +
                "        },\n" +
                "        \"childNode\": {\n" +
                "          \"prevId\": \"faca047b9fd2537ecba2f4047ea872e2\",\n" +
                "          \"type\": \"approver\",\n" +
                "          \"nodeId\": \"209a4bf49c5c6cc4b539fed649ff5996\",\n" +
                "          \"content\": \"财务角色\",\n" +
                "          \"properties\": {\n" +
                "            \"counterSign\": 2,\n" +
                "            \"optionalMultiUser\": false,\n" +
                "            \"optionalRange\": \"all\",\n" +
                "            \"approvers\": [\n" +
                "              {\n" +
                "                \"name\": \"财务角色\",\n" +
                "                \"id\": \"c91f5a1ece1540a59cdf1f47513da511\"\n" +
                "              }\n" +
                "            ],\n" +
                "            \"rejectionMod\": 1,\n" +
                "            \"assigneeType\": \"role\",\n" +
                "            \"title\": \"审批人\",\n" +
                "            \"formOperates\": [\n" +
                "              {\n" +
                "                \"formId\": \"name\",\n" +
                "                \"formOperate\": 1\n" +
                "              },\n" +
                "              {\n" +
                "                \"formId\": \"number\",\n" +
                "                \"formOperate\": 1\n" +
                "              },\n" +
                "              {\n" +
                "                \"formId\": \"user\",\n" +
                "                \"formOperate\": 1\n" +
                "              },\n" +
                "              {\n" +
                "                \"formId\": \"userName\",\n" +
                "                \"formOperate\": 1\n" +
                "              },\n" +
                "              {\n" +
                "                \"formId\": \"time\",\n" +
                "                \"formOperate\": 1\n" +
                "              },\n" +
                "              {\n" +
                "                \"formId\": \"projectName\",\n" +
                "                \"formOperate\": 1\n" +
                "              },\n" +
                "              {\n" +
                "                \"formId\": \"office\",\n" +
                "                \"formOperate\": 1\n" +
                "              },\n" +
                "              {\n" +
                "                \"formId\": \"allmoney\",\n" +
                "                \"formOperate\": 1\n" +
                "              },\n" +
                "              {\n" +
                "                \"formId\": \"flag\",\n" +
                "                \"formOperate\": 1\n" +
                "              },\n" +
                "              {\n" +
                "                \"formId\": \"comment\",\n" +
                "                \"formOperate\": 1\n" +
                "              },\n" +
                "              {\n" +
                "                \"formId\": \"procInsId\",\n" +
                "                \"formOperate\": 1\n" +
                "              },\n" +
                "              {\n" +
                "                \"formId\": \"costDetail\",\n" +
                "                \"formOperate\": 1\n" +
                "              },\n" +
                "              {\n" +
                "                \"formId\": \"happenDate\",\n" +
                "                \"formOperate\": 1\n" +
                "              },\n" +
                "              {\n" +
                "                \"formId\": \"type\",\n" +
                "                \"formOperate\": 1\n" +
                "              },\n" +
                "              {\n" +
                "                \"formId\": \"money\",\n" +
                "                \"formOperate\": 1\n" +
                "              },\n" +
                "              {\n" +
                "                \"formId\": \"content\",\n" +
                "                \"formOperate\": 1\n" +
                "              },\n" +
                "              {\n" +
                "                \"formId\": \"remarks\",\n" +
                "                \"formOperate\": 1\n" +
                "              },\n" +
                "              {\n" +
                "                \"formId\": \"filePath\",\n" +
                "                \"formOperate\": 1\n" +
                "              }\n" +
                "            ]\n" +
                "          }\n" +
                "        }\n" +
                "      },\n" +
                "      {\n" +
                "        \"prevId\": \"26c7edc9c8751ec8fe7a145c1f7162e8\",\n" +
                "        \"type\": \"condition\",\n" +
                "        \"nodeId\": \"43f8fb566a0f489bed03981285b9fc4a\",\n" +
                "        \"content\": \"[发起人: 梅小龙]\\n\",\n" +
                "        \"properties\": {\n" +
                "          \"isDefault\": false,\n" +
                "          \"initiator\": [\n" +
                "            {\n" +
                "              \"name\": \"梅小龙\",\n" +
                "              \"id\": \"f0cecc30d86a40e0babd87a8bf0005f8\",\n" +
                "              \"type\": \"user\"\n" +
                "            }\n" +
                "          ],\n" +
                "          \"title\": \"条件\",\n" +
                "          \"conditions\": [],\n" +
                "          \"priority\": 2\n" +
                "        }\n" +
                "      },\n" +
                "      {\n" +
                "        \"prevId\": \"26c7edc9c8751ec8fe7a145c1f7162e8\",\n" +
                "        \"type\": \"condition\",\n" +
                "        \"nodeId\": \"cdc9eff858cc1ede164064e6a64ed8be\",\n" +
                "        \"content\": \"其他情况进入此流程\",\n" +
                "        \"properties\": {\n" +
                "          \"isDefault\": true,\n" +
                "          \"title\": \"条件\",\n" +
                "          \"conditions\": [],\n" +
                "          \"priority\": 3\n" +
                "        },\n" +
                "        \"childNode\": {\n" +
                "          \"prevId\": \"cdc9eff858cc1ede164064e6a64ed8be\",\n" +
                "          \"type\": \"approver\",\n" +
                "          \"nodeId\": \"f1fef44f7efd170a22e866056cb88969\",\n" +
                "          \"content\": \"高层领导角色\",\n" +
                "          \"properties\": {\n" +
                "            \"counterSign\": 1,\n" +
                "            \"optionalMultiUser\": false,\n" +
                "            \"optionalRange\": \"all\",\n" +
                "            \"approvers\": [\n" +
                "              {\n" +
                "                \"name\": \"高层领导角色\",\n" +
                "                \"id\": \"7733ecb1dc48441780c75126611c9207\"\n" +
                "              }\n" +
                "            ],\n" +
                "            \"rejectionMod\": 2,\n" +
                "            \"assigneeType\": \"role\",\n" +
                "            \"title\": \"审批人\",\n" +
                "            \"formOperates\": [\n" +
                "              {\n" +
                "                \"formId\": \"name\",\n" +
                "                \"formOperate\": 1\n" +
                "              },\n" +
                "              {\n" +
                "                \"formId\": \"number\",\n" +
                "                \"formOperate\": 1\n" +
                "              },\n" +
                "              {\n" +
                "                \"formId\": \"user\",\n" +
                "                \"formOperate\": 1\n" +
                "              },\n" +
                "              {\n" +
                "                \"formId\": \"userName\",\n" +
                "                \"formOperate\": 1\n" +
                "              },\n" +
                "              {\n" +
                "                \"formId\": \"time\",\n" +
                "                \"formOperate\": 1\n" +
                "              },\n" +
                "              {\n" +
                "                \"formId\": \"projectName\",\n" +
                "                \"formOperate\": 1\n" +
                "              },\n" +
                "              {\n" +
                "                \"formId\": \"office\",\n" +
                "                \"formOperate\": 1\n" +
                "              },\n" +
                "              {\n" +
                "                \"formId\": \"allmoney\",\n" +
                "                \"formOperate\": 1\n" +
                "              },\n" +
                "              {\n" +
                "                \"formId\": \"flag\",\n" +
                "                \"formOperate\": 1\n" +
                "              },\n" +
                "              {\n" +
                "                \"formId\": \"comment\",\n" +
                "                \"formOperate\": 1\n" +
                "              },\n" +
                "              {\n" +
                "                \"formId\": \"procInsId\",\n" +
                "                \"formOperate\": 1\n" +
                "              },\n" +
                "              {\n" +
                "                \"formId\": \"costDetail\",\n" +
                "                \"formOperate\": 1\n" +
                "              },\n" +
                "              {\n" +
                "                \"formId\": \"happenDate\",\n" +
                "                \"formOperate\": 1\n" +
                "              },\n" +
                "              {\n" +
                "                \"formId\": \"type\",\n" +
                "                \"formOperate\": 1\n" +
                "              },\n" +
                "              {\n" +
                "                \"formId\": \"money\",\n" +
                "                \"formOperate\": 1\n" +
                "              },\n" +
                "              {\n" +
                "                \"formId\": \"content\",\n" +
                "                \"formOperate\": 1\n" +
                "              },\n" +
                "              {\n" +
                "                \"formId\": \"remarks\",\n" +
                "                \"formOperate\": 1\n" +
                "              },\n" +
                "              {\n" +
                "                \"formId\": \"filePath\",\n" +
                "                \"formOperate\": 1\n" +
                "              }\n" +
                "            ]\n" +
                "          },\n" +
                "          \"childNode\": {\n" +
                "            \"prevId\": \"f1fef44f7efd170a22e866056cb88969\",\n" +
                "            \"type\": \"approver\",\n" +
                "            \"nodeId\": \"a8d9dfa2973a0247cc767a161cd1d839\",\n" +
                "            \"content\": \"李敏,梅小龙\",\n" +
                "            \"properties\": {\n" +
                "              \"counterSign\": 2,\n" +
                "              \"optionalMultiUser\": false,\n" +
                "              \"optionalRange\": \"all\",\n" +
                "              \"approvers\": [\n" +
                "                {\n" +
                "                  \"name\": \"李敏\",\n" +
                "                  \"id\": \"c80d901a1cee4f60a67c1f0c95f43011\"\n" +
                "                },\n" +
                "                {\n" +
                "                  \"name\": \"梅小龙\",\n" +
                "                  \"id\": \"f0cecc30d86a40e0babd87a8bf0005f8\"\n" +
                "                }\n" +
                "              ],\n" +
                "              \"rejectionMod\": 1,\n" +
                "              \"assigneeType\": \"user\",\n" +
                "              \"title\": \"审批人会签\",\n" +
                "              \"formOperates\": [\n" +
                "                {\n" +
                "                  \"formId\": \"name\",\n" +
                "                  \"formOperate\": 1\n" +
                "                },\n" +
                "                {\n" +
                "                  \"formId\": \"number\",\n" +
                "                  \"formOperate\": 1\n" +
                "                },\n" +
                "                {\n" +
                "                  \"formId\": \"user\",\n" +
                "                  \"formOperate\": 1\n" +
                "                },\n" +
                "                {\n" +
                "                  \"formId\": \"userName\",\n" +
                "                  \"formOperate\": 1\n" +
                "                },\n" +
                "                {\n" +
                "                  \"formId\": \"time\",\n" +
                "                  \"formOperate\": 1\n" +
                "                },\n" +
                "                {\n" +
                "                  \"formId\": \"projectName\",\n" +
                "                  \"formOperate\": 1\n" +
                "                },\n" +
                "                {\n" +
                "                  \"formId\": \"office\",\n" +
                "                  \"formOperate\": 1\n" +
                "                },\n" +
                "                {\n" +
                "                  \"formId\": \"allmoney\",\n" +
                "                  \"formOperate\": 1\n" +
                "                },\n" +
                "                {\n" +
                "                  \"formId\": \"flag\",\n" +
                "                  \"formOperate\": 1\n" +
                "                },\n" +
                "                {\n" +
                "                  \"formId\": \"comment\",\n" +
                "                  \"formOperate\": 1\n" +
                "                },\n" +
                "                {\n" +
                "                  \"formId\": \"procInsId\",\n" +
                "                  \"formOperate\": 1\n" +
                "                },\n" +
                "                {\n" +
                "                  \"formId\": \"costDetail\",\n" +
                "                  \"formOperate\": 1\n" +
                "                },\n" +
                "                {\n" +
                "                  \"formId\": \"happenDate\",\n" +
                "                  \"formOperate\": 1\n" +
                "                },\n" +
                "                {\n" +
                "                  \"formId\": \"type\",\n" +
                "                  \"formOperate\": 1\n" +
                "                },\n" +
                "                {\n" +
                "                  \"formId\": \"money\",\n" +
                "                  \"formOperate\": 1\n" +
                "                },\n" +
                "                {\n" +
                "                  \"formId\": \"content\",\n" +
                "                  \"formOperate\": 1\n" +
                "                },\n" +
                "                {\n" +
                "                  \"formId\": \"remarks\",\n" +
                "                  \"formOperate\": 1\n" +
                "                },\n" +
                "                {\n" +
                "                  \"formId\": \"filePath\",\n" +
                "                  \"formOperate\": 1\n" +
                "                }\n" +
                "              ]\n" +
                "            },\n" +
                "            \"childNode\": {\n" +
                "              \"prevId\": \"a8d9dfa2973a0247cc767a161cd1d839\",\n" +
                "              \"type\": \"approver\",\n" +
                "              \"nodeId\": \"78942dd4e4902a318cc0a839e262ba9c\",\n" +
                "              \"content\": \"财务角色\",\n" +
                "              \"properties\": {\n" +
                "                \"counterSign\": 2,\n" +
                "                \"optionalMultiUser\": false,\n" +
                "                \"optionalRange\": \"all\",\n" +
                "                \"approvers\": [\n" +
                "                  {\n" +
                "                    \"name\": \"财务角色\",\n" +
                "                    \"id\": \"c91f5a1ece1540a59cdf1f47513da511\"\n" +
                "                  }\n" +
                "                ],\n" +
                "                \"rejectionMod\": 2,\n" +
                "                \"assigneeType\": \"role\",\n" +
                "                \"title\": \"审批人\",\n" +
                "                \"formOperates\": [\n" +
                "                  {\n" +
                "                    \"formId\": \"name\",\n" +
                "                    \"formOperate\": 1\n" +
                "                  },\n" +
                "                  {\n" +
                "                    \"formId\": \"number\",\n" +
                "                    \"formOperate\": 1\n" +
                "                  },\n" +
                "                  {\n" +
                "                    \"formId\": \"user\",\n" +
                "                    \"formOperate\": 1\n" +
                "                  },\n" +
                "                  {\n" +
                "                    \"formId\": \"userName\",\n" +
                "                    \"formOperate\": 1\n" +
                "                  },\n" +
                "                  {\n" +
                "                    \"formId\": \"time\",\n" +
                "                    \"formOperate\": 1\n" +
                "                  },\n" +
                "                  {\n" +
                "                    \"formId\": \"projectName\",\n" +
                "                    \"formOperate\": 1\n" +
                "                  },\n" +
                "                  {\n" +
                "                    \"formId\": \"office\",\n" +
                "                    \"formOperate\": 1\n" +
                "                  },\n" +
                "                  {\n" +
                "                    \"formId\": \"allmoney\",\n" +
                "                    \"formOperate\": 1\n" +
                "                  },\n" +
                "                  {\n" +
                "                    \"formId\": \"flag\",\n" +
                "                    \"formOperate\": 1\n" +
                "                  },\n" +
                "                  {\n" +
                "                    \"formId\": \"comment\",\n" +
                "                    \"formOperate\": 1\n" +
                "                  },\n" +
                "                  {\n" +
                "                    \"formId\": \"procInsId\",\n" +
                "                    \"formOperate\": 1\n" +
                "                  },\n" +
                "                  {\n" +
                "                    \"formId\": \"costDetail\",\n" +
                "                    \"formOperate\": 1\n" +
                "                  },\n" +
                "                  {\n" +
                "                    \"formId\": \"happenDate\",\n" +
                "                    \"formOperate\": 1\n" +
                "                  },\n" +
                "                  {\n" +
                "                    \"formId\": \"type\",\n" +
                "                    \"formOperate\": 1\n" +
                "                  },\n" +
                "                  {\n" +
                "                    \"formId\": \"money\",\n" +
                "                    \"formOperate\": 1\n" +
                "                  },\n" +
                "                  {\n" +
                "                    \"formId\": \"content\",\n" +
                "                    \"formOperate\": 1\n" +
                "                  },\n" +
                "                  {\n" +
                "                    \"formId\": \"remarks\",\n" +
                "                    \"formOperate\": 1\n" +
                "                  },\n" +
                "                  {\n" +
                "                    \"formId\": \"filePath\",\n" +
                "                    \"formOperate\": 1\n" +
                "                  }\n" +
                "                ]\n" +
                "              }\n" +
                "            }\n" +
                "          }\n" +
                "        }\n" +
                "      }\n" +
                "    ]\n" +
                "  },\n" +
                "  \"advancedSetting\": {\n" +
                "    \"autoRepeat\": \"2\",\n" +
                "    \"myAuditAutoPass\": true,\n" +
                "    \"remarkTip\": \"这里是填写提示\",\n" +
                "    \"remarkRequired\": true,\n" +
                "    \"notVisibleForSponsor\": true\n" +
                "  }\n" +
                "}";
        ObjectJsonData objectJsonData = ObjectHandler(JSONObject.parseObject(json));
        objectJsonData.name = "root";
        System.out.println(objectJsonData);
    }

    /**
     * 根据值做相应的处理
     *
     * @param value json
     * @param key json对象名
     * @return 返回处理后的JsonData
     * */
    private static JsonData TypeHandler(String value,String key){
        JSONType jsonType = getJSONType(value);
        JsonData jsonData = null;
        switch (jsonType){
            case Array:
                jsonData = ArrayHandler(JSONArray.parseArray(value));
                jsonData.name = key;
                //System.out.println(key + "类型为Array");
                break;
            case Object:
                jsonData = ObjectHandler(JSONObject.parseObject(value));
                jsonData.name = key;
                //System.out.println(key + "类型为Object");
                break;
            case String:
                //System.out.println(key + "类型为String");
                jsonData = new StringJsonData();
                jsonData.name = key;
                break;
            default:
        }
        return jsonData;
    }

    /**
     * jsonObject处理 封装成 ObjectJsonData
     *
     * @param jsonObject 要处理的jsonObject
     * @return 处理后的ObjectJsonData对象
     * */
    private static ObjectJsonData ObjectHandler(JSONObject jsonObject){
        ObjectJsonData objectJsonData = new ObjectJsonData();
        List<JsonData> list = new LinkedList<>();
        for(String key : jsonObject.keySet()) {
            JsonData jsonData = TypeHandler(jsonObject.getString(key), key);
            list.add(jsonData);
        }
        objectJsonData.objectData = list;
        return objectJsonData;
    }

    /**
     * jsonArray处理 封装成 ArrayJsonData
     *
     * @param jsonArray 要处理的jsonArray
     * @return 处理后的ArrayJsonData对象
     * */
    private static ArrayJsonData ArrayHandler(JSONArray jsonArray){
        ArrayJsonData arrayJsonData = new ArrayJsonData();
        List<ObjectJsonData> list = new LinkedList<>();
        ObjectJsonData newJsonData = new ObjectJsonData();
        for(int i=0;i<jsonArray.size();i++){
            ObjectJsonData objectJsonData = ObjectHandler(jsonArray.getJSONObject(i));
            if(i >= 1){
                newJsonData = fillIncomplete(newJsonData,objectJsonData);
            }else {
                newJsonData = objectJsonData;
            }
        }
        newJsonData.name = "items";
        list.add(newJsonData);
        arrayJsonData.arrayData = list;
        return arrayJsonData;
    }

    /**
     * 获取value(json)的类型
     *
     * @param value 传入的json值
     * @return 返回json类型
     * */
    private static JSONType getJSONType(String value){
        JSONType type = JSONType.String;
        if(value == null || "".equals(value)){
            return type;
        }
        try {
            JSONObject.parseObject(value);
            type = JSONType.Object;
        }catch (Exception e){
            try {
                JSONArray.parseArray(value);
                type = JSONType.Array;
            }catch (Exception e1){
            }
        }
        return type;
    }

    /**
     * 对比两个jsonData的差异,把缺失的数据填充到oldJson中
     *
     * @param oldJson 以前的
     * @param nowJson 当前的
     * @return 完整的jsonData
     * */
    private static ObjectJsonData fillIncomplete(ObjectJsonData oldJson,ObjectJsonData nowJson){
        // 非空
        if(oldJson.objectData == null){
            oldJson.objectData = new ArrayList<>();
        }
        if(nowJson.objectData != null) {
            nowJson.objectData.forEach(item -> {
                if (item instanceof ArrayJsonData) {
                    int contain = oldJson.indexOf(item);
                    if (contain == -1) {
                        oldJson.objectData.add(item);
                    } else {
                        // 存在就比较 替换调原来的
                        ObjectJsonData jsonData = ((ArrayJsonData) oldJson.objectData.get(contain)).arrayData.get(0);
                        ObjectJsonData objectJsonData = ((ArrayJsonData) item).arrayData.get(0);
                        ((ArrayJsonData) oldJson.objectData.get(contain)).arrayData.remove(0);
                        ((ArrayJsonData) oldJson.objectData.get(contain)).arrayData.add(fillIncomplete(jsonData, objectJsonData));
                    }
                } else if (item instanceof ObjectJsonData) {
                    int contain = oldJson.indexOf(item);
                    if (contain == -1) {
                        oldJson.objectData.add(item);
                    } else {
                        // 存在就比较 替换调原来的
                        JsonData jsonData = oldJson.objectData.get(contain);
                        oldJson.objectData.remove(contain);
                        oldJson.objectData.add(fillIncomplete((ObjectJsonData) jsonData, (ObjectJsonData) item));
                    }
                } else {
                    if (oldJson.indexOf(item) == -1) {
                        oldJson.objectData.add(item);
                    }
                }
            });
        }
        return oldJson;
    }

    enum JSONType{
        String,
        Object,
        Array
    }

    private static class JsonData{
        public String name;

        @Override
        public String toString() {
            return name;
        }

        @Override
        public boolean equals(Object obj) {
            if(obj instanceof  JsonData){
                return this.name.equals(((JsonData) obj).name);
            }
            return false;
        }

        @Override
        public int hashCode() {
            return name.length();
        }

    }

    private static class StringJsonData extends  JsonData{
        private final JSONType type = JSONType.String;

        @Override
        public String toString() {
            return name + ":" + type;
        }

    }
    private static class ObjectJsonData extends  JsonData{
        private final JSONType type = JSONType.Object;
        public List<JsonData> objectData;

        /**
         * 返回jsonData 在objectData中的位置
         * @param jsonData jsonData对象
         * @return 返回在objectData中的位置
         * */
        public int indexOf(JsonData jsonData){
            for(int i = 0 ; i < objectData.size() ; i++){
                if(objectData.get(i).equals(jsonData)){
                    return i;
                }
            }
            return -1;
        }

        @Override
        public String toString() {
            return name + ":" + type +
                    "objectData = {" + objectData + "}";
        }
    }

    private static class ArrayJsonData extends  JsonData{
        private final JSONType type = JSONType.Array;
        public List<ObjectJsonData> arrayData;

        @Override
        public String toString() {
            return name + ":" + type +
                    "arrayData = {" + arrayData + "}";
        }

    }
}

