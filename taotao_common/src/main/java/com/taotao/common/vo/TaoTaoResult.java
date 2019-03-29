package com.taotao.common.vo;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;

/**
 * 自定义响应结构
 */
public class TaoTaoResult {
    //定义jackson对象
    private static final ObjectMapper Mapper = new ObjectMapper();

    //响应业务的状态
    private Integer status;

    //响应消息
    private String msg;

    //响应中的数据
    private Object data;

    //构造函数
    private TaoTaoResult(Integer status, String msg, Object data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }
    //构造函数的重载 相当于设置默认值
    private TaoTaoResult(Object data){
        this.status = 200;
        this.msg = "OK";
        this.data = data;
    }
    //new的对象只能由build获得
    public static TaoTaoResult build(Integer status, String msg, Object data) {
        return new TaoTaoResult(status, msg, data);
    }
    //类内方法的重载
    public static TaoTaoResult build(Integer status,String  msg){
        return new TaoTaoResult(status,msg,null);
    }

    //查询执行成功的时候的方法
    public static TaoTaoResult ok(){
        return new TaoTaoResult(null);
    }

    public static TaoTaoResult ok(Object data){
        return new TaoTaoResult(data);
    }

    //get,set
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    /**
     * 将json结果集转化为TaotaoResult对象
     *
     * @param jsonData json数据
     * @param clazz TaotaoResult中的object类型
     * @return
     */
    public static TaoTaoResult formatToPojo(String jsonData, Class<?> clazz) {
        try {
            if (clazz == null) {
                return Mapper.readValue(jsonData, TaoTaoResult.class);
            }
            JsonNode jsonNode = Mapper.readTree(jsonData);
            JsonNode data = jsonNode.get("data");
            Object obj = null;
            if (clazz != null) {
                if (data.isObject()) {
                    obj = Mapper.readValue(data.traverse(), clazz);
                } else if (data.isTextual()) {
                    obj = Mapper.readValue(data.asText(), clazz);
                }
            }
            return build(jsonNode.get("status").intValue(), jsonNode.get("msg").asText(), obj);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 没有object对象的转化
     *
     * @param json
     * @return
     */
    public static TaoTaoResult format(String json) {
        try {
            return Mapper.readValue(json, TaoTaoResult.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Object是集合转化
     *
     * @param jsonData json数据
     * @param clazz 集合中的类型
     * @return
     */
    public static TaoTaoResult formatToList(String jsonData, Class<?> clazz) {
        try {
            JsonNode jsonNode = Mapper.readTree(jsonData);
            JsonNode data = jsonNode.get("data");
            Object obj = null;
            if (data.isArray() && data.size() > 0) {
                obj = Mapper.readValue(data.traverse(),
                        Mapper.getTypeFactory().constructCollectionType(List.class, clazz));
            }
            return build(jsonNode.get("status").intValue(), jsonNode.get("msg").asText(), obj);
        } catch (Exception e) {
            return null;
        }
    }
}