package com.yexiao.demo.jsonSchema;

import com.fasterxml.jackson.databind.JsonNode;
import com.networknt.schema.JsonSchema;
import com.networknt.schema.ValidationMessage;
import java.io.IOException;
import java.util.Set;

class Sample extends BaseJsonSchemaValidatorTest {

    public void test() throws IOException {
        String jsonSchema = "{\n" +
                "  \"$schema\": \"http://json-schema.org/draft-04/schema#\",\n" +
                "  \"type\": \"object\",\n" +
                "  \"definitions\": {\n" +
                "    \"actor\": {\n" +
                "      \"type\": \"array\",\n" +
                "      \"items\": {\n" +
                "        \"type\": \"object\",\n" +
                "        \"required\": [\n" +
                "          \"name\",\n" +
                "          \"id\",\n" +
                "          \"type\"\n" +
                "        ],\n" +
                "        \"properties\": {\n" +
                "          \"name\": {\n" +
                "            \"type\": \"string\"\n" +
                "          },\n" +
                "          \"id\": {\n" +
                "            \"type\": \"string\"\n" +
                "          },\n" +
                "          \"type\": {\n" +
                "            \"type\": \"string\",\n" +
                "            \"enum\": [\n" +
                "              \"user\",\n" +
                "              \"director\",\n" +
                "              \"office\",\n" +
                "              \"role\",\n" +
                "              \"myself\"\n" +
                "            ]\n" +
                "          }\n" +
                "        }\n" +
                "      }\n" +
                "    },\n" +
                "    \"approvers\": {\n" +
                "      \"type\": \"array\",\n" +
                "      \"items\": {\n" +
                "        \"type\": \"object\",\n" +
                "        \"required\": [\n" +
                "          \"name\",\n" +
                "          \"id\"\n" +
                "        ],\n" +
                "        \"properties\": {\n" +
                "          \"name\": {\n" +
                "            \"type\": \"string\"\n" +
                "          },\n" +
                "          \"id\": {\n" +
                "            \"type\": \"string\"\n" +
                "          }\n" +
                "        }\n" +
                "      }\n" +
                "    }\n" +
                "  },\n" +
                "  \"required\": [\n" +
                "    \"basicSetting\",\n" +
                "    \"processData\",\n" +
                "    \"advancedSetting\"\n" +
                "  ],\n" +
                "  \"properties\": {\n" +
                "    \"basicSetting\": {\n" +
                "      \"type\": \"object\",\n" +
                "      \"required\": [\n" +
                "        \"flowName\"\n" +
                "      ],\n" +
                "      \"properties\": {\n" +
                "        \"flowName\": {\n" +
                "          \"type\": \"string\"\n" +
                "        }\n" +
                "      }\n" +
                "    },\n" +
                "    \"processData\": {\n" +
                "      \"type\": \"object\",\n" +
                "      \"required\": [\n" +
                "        \"type\",\n" +
                "        \"content\",\n" +
                "        \"nodeId\",\n" +
                "        \"properties\"\n" +
                "      ],\n" +
                "      \"properties\": {\n" +
                "        \"type\": {\n" +
                "          \"type\": \"string\",\n" +
                "          \"enum\": [\n" +
                "            \"start\",\n" +
                "            \"approver\",\n" +
                "            \"condition\",\n" +
                "            \"copy\",\n" +
                "            \"empty\",\n" +
                "            \"circulation\"\n" +
                "          ]\n" +
                "        },\n" +
                "        \"content\": {\n" +
                "          \"type\": \"string\"\n" +
                "        },\n" +
                "        \"nodeId\": {\n" +
                "          \"type\": \"string\"\n" +
                "        },\n" +
                "        \"pervId\": {\n" +
                "          \"type\": \"string\"\n" +
                "        },\n" +
                "        \"properties\": {\n" +
                "          \"type\": \"object\",\n" +
                "          \"dependencies\": {\n" +
                "            \"assigneeType\": [\n" +
                "              \"counterSign\",\n" +
                "              \"rejectionMod\",\n" +
                "              \"approvers\"\n" +
                "            ],\n" +
                "            \"counterSign\": [\n" +
                "              \"assigneeType\"\n" +
                "            ],\n" +
                "            \"alternativeHandler\": [\n" +
                "              \"defaultHandler\"\n" +
                "            ],\n" +
                "            \"defaultHandler\": [\n" +
                "              \"alternativeHandler\"\n" +
                "            ]\n" +
                "          },\n" +
                "          \"properties\": {\n" +
                "            \"defaultHandler\": {\n" +
                "              \"$ref\": \"#/definitions/actor\"\n" +
                "            },\n" +
                "            \"alternativeHandler\": {\n" +
                "              \"$ref\": \"#/definitions/actor\"\n" +
                "            },\n" +
                "            \"menbers\": {\n" +
                "              \"$ref\": \"#/definitions/approvers\"\n" +
                "            },\n" +
                "            \"initiator\": {\n" +
                "              \"$ref\": \"#/definitions/actor\"\n" +
                "            },\n" +
                "            \"title\": {\n" +
                "              \"type\": \"string\"\n" +
                "            },\n" +
                "            \"counterSign\": {\n" +
                "              \"type\": \"number\",\n" +
                "              \"enum\": [\n" +
                "                1,\n" +
                "                2\n" +
                "              ]\n" +
                "            },\n" +
                "            \"rejectionMod\": {\n" +
                "              \"type\": \"number\",\n" +
                "              \"enum\": [\n" +
                "                1,\n" +
                "                2\n" +
                "              ]\n" +
                "            },\n" +
                "            \"assigneeType\": {\n" +
                "              \"type\": \"string\",\n" +
                "              \"enum\": [\n" +
                "                \"user\",\n" +
                "                \"director\",\n" +
                "                \"office\",\n" +
                "                \"role\",\n" +
                "                \"myself\"\n" +
                "              ]\n" +
                "            },\n" +
                "            \"directorLevel\": {\n" +
                "              \"type\": \"number\"\n" +
                "            },\n" +
                "            \"approvers\": {\n" +
                "              \"$ref\": \"#/definitions/approvers\"\n" +
                "            },\n" +
                "            \"priority\": {\n" +
                "              \"type\": \"number\"\n" +
                "            }\n" +
                "          }\n" +
                "        },\n" +
                "        \"childNode\": {\n" +
                "          \"$ref\": \"#/properties/processData\"\n" +
                "        },\n" +
                "        \"conditionNodes\": {\n" +
                "          \"type\": \"array\",\n" +
                "          \"items\": {\n" +
                "            \"$ref\": \"#/properties/processData\"\n" +
                "          }\n" +
                "        }\n" +
                "      }\n" +
                "    },\n" +
                "    \"advancedSetting\": {\n" +
                "      \"type\": \"object\",\n" +
                "      \"properties\": {\n" +
                "        \"autoRepeat\": {\n" +
                "          \"type\": \"string\"\n" +
                "        },\n" +
                "        \"myAuditAutoPass\": {\n" +
                "          \"type\": \"boolean\"\n" +
                "        }\n" +
                "      }\n" +
                "    }\n" +
                "  }\n" +
                "}";


        String json = "\"name\": \"智慧政务事业部\",\n" +
                "          \"id\": \"2e57ea64f02346eab5e4c5660e823a0d\",\n" +
                "          \"type\": \"office\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"name\": \"周昱\",\n" +
                "          \"id\": \"b427e3c4af7a4825b4753906537719ea\",\n" +
                "          \"type\": \"user\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"name\": \"淦述义\",\n" +
                "          \"id\": \"676f5e980db940c0b670e187e3acceff\",\n" +
                "          \"type\": \"user\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"name\": \"物联网事业部\",\n" +
                "          \"id\": \"85c1fba5ec804025874969b885f03b4e\",\n" +
                "          \"type\": \"office\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"name\": \"胡海亮\",\n" +
                "          \"id\": \"c41fa23932d04bd9ace7f37406dda220\",\n" +
                "          \"type\": \"user\"\n" +
                "        }\n" +
                "      ],\n" +
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
                "    \"nodeId\": \"3749be401c999c43371e14ab35391ad8\",\n" +
                "    \"childNode\": {\n" +
                "      \"type\": \"empty\",\n" +
                "      \"content\": \"\",\n" +
                "      \"properties\": {},\n" +
                "      \"nodeId\": \"666b9773407943783085e01f663dc05d\",\n" +
                "      \"prevId\": \"3749be401c999c43371e14ab35391ad8\",\n" +
                "      \"childNode\": {\n" +
                "        \"prevId\": \"666b9773407943783085e01f663dc05d\",\n" +
                "        \"type\": \"approver\",\n" +
                "        \"nodeId\": \"705445a5d0dc613229bac7233d3a7a3e\",\n" +
                "        \"content\": \"第3级领导\",\n" +
                "        \"properties\": {\n" +
                "          \"counterSign\": 2,\n" +
                "          \"optionalMultiUser\": false,\n" +
                "          \"optionalRange\": \"all\",\n" +
                "          \"approvers\": [],\n" +
                "          \"rejectionMod\": 1,\n" +
                "          \"assigneeType\": \"director\",\n" +
                "          \"title\": \"审批人\",\n" +
                "          \"formOperates\": [\n" +
                "            {\n" +
                "              \"formId\": \"name\",\n" +
                "              \"formOperate\": 1\n" +
                "            },\n" +
                "            {\n" +
                "              \"formId\": \"number\",\n" +
                "              \"formOperate\": 1\n" +
                "            },\n" +
                "            {\n" +
                "              \"formId\": \"user\",\n" +
                "              \"formOperate\": 1\n" +
                "            },\n" +
                "            {\n" +
                "              \"formId\": \"userName\",\n" +
                "              \"formOperate\": 1\n" +
                "            },\n" +
                "            {\n" +
                "              \"formId\": \"time\",\n" +
                "              \"formOperate\": 1\n" +
                "            },\n" +
                "            {\n" +
                "              \"formId\": \"projectName\",\n" +
                "              \"formOperate\": 1\n" +
                "            },\n" +
                "            {\n" +
                "              \"formId\": \"office\",\n" +
                "              \"formOperate\": 1\n" +
                "            },\n" +
                "            {\n" +
                "              \"formId\": \"allmoney\",\n" +
                "              \"formOperate\": 1\n" +
                "            },\n" +
                "            {\n" +
                "              \"formId\": \"flag\",\n" +
                "              \"formOperate\": 1\n" +
                "            },\n" +
                "            {\n" +
                "              \"formId\": \"comment\",\n" +
                "              \"formOperate\": 1\n" +
                "            },\n" +
                "            {\n" +
                "              \"formId\": \"procInsId\",\n" +
                "              \"formOperate\": 1\n" +
                "            },\n" +
                "            {\n" +
                "              \"formId\": \"costDetail\",\n" +
                "              \"formOperate\": 1\n" +
                "            },\n" +
                "            {\n" +
                "              \"formId\": \"happenDate\",\n" +
                "              \"formOperate\": 1\n" +
                "            },\n" +
                "            {\n" +
                "              \"formId\": \"type\",\n" +
                "              \"formOperate\": 1\n" +
                "            },\n" +
                "            {\n" +
                "              \"formId\": \"money\",\n" +
                "              \"formOperate\": 1\n" +
                "            },\n" +
                "            {\n" +
                "              \"formId\": \"content\",\n" +
                "              \"formOperate\": 1\n" +
                "            },\n" +
                "            {\n" +
                "              \"formId\": \"remarks\",\n" +
                "              \"formOperate\": 1\n" +
                "            },\n" +
                "            {\n" +
                "              \"formId\": \"filePath\",\n" +
                "              \"formOperate\": 1\n" +
                "            }\n" +
                "          ],\n" +
                "          \"directorLevel\": 3\n" +
                "        },\n" +
                "        \"childNode\": {\n" +
                "          \"prevId\": \"705445a5d0dc613229bac7233d3a7a3e\",\n" +
                "          \"type\": \"approver\",\n" +
                "          \"nodeId\": \"2a2ef17c9509cd58ecbedea9f4202ae0\",\n" +
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
                "          },\n" +
                "          \"childNode\": {\n" +
                "            \"prevId\": \"2a2ef17c9509cd58ecbedea9f4202ae0\",\n" +
                "            \"type\": \"approver\",\n" +
                "            \"nodeId\": \"159ce052e4d64678d0c8d6e21ca51de7\",\n" +
                "            \"content\": \"高层领导角色\",\n" +
                "            \"properties\": {\n" +
                "              \"counterSign\": 2,\n" +
                "              \"optionalMultiUser\": false,\n" +
                "              \"optionalRange\": \"all\",\n" +
                "              \"approvers\": [\n" +
                "                {\n" +
                "                  \"name\": \"高层领导角色\",\n" +
                "                  \"id\": \"7733ecb1dc48441780c75126611c9207\"\n" +
                "                }\n" +
                "              ],\n" +
                "              \"rejectionMod\": 1,\n" +
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
                "      \"conditionNodes\": [\n" +
                "        {\n" +
                "          \"type\": \"condition\",\n" +
                "          \"content\": \"[发起人: 智慧政务事业部]\\n\",\n" +
                "          \"properties\": {\n" +
                "            \"title\": \"条件1\",\n" +
                "            \"conditions\": [],\n" +
                "            \"initiator\": [\n" +
                "              {\n" +
                "                \"type\": \"office\",\n" +
                "                \"name\": \"智慧政务事业部\",\n" +
                "                \"id\": \"2e57ea64f02346eab5e4c5660e823a0d\"\n" +
                "              }\n" +
                "            ],\n" +
                "            \"priority\": 0,\n" +
                "            \"isDefault\": false\n" +
                "          },\n" +
                "          \"nodeId\": \"f0bb10c0bbcc454f50df77660f8dd41e\",\n" +
                "          \"prevId\": \"666b9773407943783085e01f663dc05d\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"type\": \"condition\",\n" +
                "          \"content\": \"[发起人: 周昱]\\n\",\n" +
                "          \"properties\": {\n" +
                "            \"title\": \"条件2\",\n" +
                "            \"conditions\": [],\n" +
                "            \"initiator\": [\n" +
                "              {\n" +
                "                \"type\": \"user\",\n" +
                "                \"name\": \"周昱\",\n" +
                "                \"id\": \"b427e3c4af7a4825b4753906537719ea\"\n" +
                "              }\n" +
                "            ],\n" +
                "            \"priority\": 1,\n" +
                "            \"isDefault\": false\n" +
                "          },\n" +
                "          \"nodeId\": \"9daba2cc220f8e16b08dbcb65cc6df51\",\n" +
                "          \"prevId\": \"666b9773407943783085e01f663dc05d\"\n" +
                "        }\n" +
                "      ]\n" +
                "    },\n" +
                "    \"conditionNodes\": [\n" +
                "      {\n" +
                "        \"prevId\": \"3749be401c999c43371e14ab35391ad8\",\n" +
                "        \"type\": \"condition\",\n" +
                "        \"nodeId\": \"fac4dc06643c0245976f6ff1f3f4e691\",\n" +
                "        \"content\": \"[发起人: 智慧政务事业部,周昱,物联网事业部]\\n\",\n" +
                "        \"properties\": {\n" +
                "          \"isDefault\": false,\n" +
                "          \"initiator\": [\n" +
                "            {\n" +
                "              \"name\": \"智慧政务事业部\",\n" +
                "              \"id\": \"2e57ea64f02346eab5e4c5660e823a0d\",\n" +
                "              \"type\": \"office\"\n" +
                "            },\n" +
                "            {\n" +
                "              \"name\": \"周昱\",\n" +
                "              \"id\": \"b427e3c4af7a4825b4753906537719ea\",\n" +
                "              \"type\": \"user\"\n" +
                "            },\n" +
                "            {\n" +
                "              \"name\": \"物联网事业部\",\n" +
                "              \"id\": \"85c1fba5ec804025874969b885f03b4e\",\n" +
                "              \"type\": \"office\"\n" +
                "            }\n" +
                "          ],\n" +
                "          \"title\": \"条件1\",\n" +
                "          \"conditions\": [],\n" +
                "          \"priority\": 0\n" +
                "        }\n" +
                "      },\n" +
                "      {\n" +
                "        \"prevId\": \"3749be401c999c43371e14ab35391ad8\",\n" +
                "        \"type\": \"condition\",\n" +
                "        \"nodeId\": \"2f1941a8c178e296afd8b7bdfa465d61\",\n" +
                "        \"content\": \"[发起人: 淦述义]\\n\",\n" +
                "        \"properties\": {\n" +
                "          \"isDefault\": false,\n" +
                "          \"initiator\": [\n" +
                "            {\n" +
                "              \"name\": \"淦述义\",\n" +
                "              \"id\": \"676f5e980db940c0b670e187e3acceff\",\n" +
                "              \"type\": \"user\"\n" +
                "            }\n" +
                "          ],\n" +
                "          \"title\": \"条件2\",\n" +
                "          \"conditions\": [],\n" +
                "          \"priority\": 1\n" +
                "        }\n" +
                "      }\n" +
                "    ]\n" +
                "  },\n" +
                "  \"advancedSetting\": {\n" +
                "    \"autoRepeat\": \"3\",\n" +
                "    \"myAuditAutoPass\": true,\n" +
                "    \"remarkTip\": \"这里是填写提示\",\n" +
                "    \"remarkRequired\": true,\n" +
                "    \"notVisibleForSponsor\": true\n" +
                "  }\n" +
                "}";
        JsonSchema schema = getJsonSchemaFromStringContent(jsonSchema);
        JsonNode node = getJsonNodeFromStringContent(json);
        Set<ValidationMessage> errors = schema.validate(node);
        if(errors == null || errors.size() == 0){
            System.out.println("数据格式正确");
        }else {
            System.out.println(errors);
        }
    }

    public static void main(String[] args) throws IOException {
        Sample sample = new Sample();
        sample.test();
    }

}