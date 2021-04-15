package com.yexiao.demo.jsonSchema;

import cn.hutool.core.io.IoUtil;
import com.fasterxml.jackson.databind.JsonNode;
import com.networknt.schema.JsonSchema;
import com.networknt.schema.ValidationMessage;

import java.io.InputStreamReader;
import java.util.Set;

class Main extends BaseJsonSchemaValidatorTest {

    public void test() throws Exception {
        String jsonSchema = IoUtil.read(new InputStreamReader(Main.class.getResourceAsStream("/static/jsonSchema/jsonSchema.json")));
        String json = IoUtil.read(new InputStreamReader(Main.class.getResourceAsStream("/static/jsonSchema/flow_json.json")));
        JsonSchema schema = getJsonSchemaFromStringContent(jsonSchema);
        JsonNode node = getJsonNodeFromStringContent(json);
        Set<ValidationMessage> errors = schema.validate(node);
        if(errors == null || errors.size() == 0){
            System.out.println("数据格式正确");
        }else {
            System.out.println(errors);
        }
    }

    public static void main(String[] args) throws Exception {
        Main main = new Main();
        main.test();
    }

}