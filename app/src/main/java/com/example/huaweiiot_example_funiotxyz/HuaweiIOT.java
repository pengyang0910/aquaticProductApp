package com.example.huaweiiot_example_funiotxyz;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import java.util.Set;


public class HuaweiIOT {
    //请在下方完善信息
    String HUAWEINAME="hid_e7z6q2d0a3hzq_0";  //华为账号名
    String IAMINAME="test1";    //IAM账户名
    String IAMPASSWORD="test123456"; //IAM账户密码
    //    String project_id="6610cf90fb8177243a54ce43";  //产品ID
    String project_id="6610cf90fb8177243a54ce43";  //产品ID

    String device_id="6610cf90fb8177243a54ce43_aquatic_1";   //设备ID
    String service_id="BasicData";  //服务ID
    String commands="atmosphereLight";    //命令名称
    //↓可从控制台首页【总览】【接入信息】处获取HTTPS接入地址，例如xxxxxxx.iotda.cn-north-4.myhuaweicloud.com
    String endpoint="iotda.cn-north-4.myhuaweicloud.com";    //终端节点名称
    String token="";//仅作为全局变量使用，无需手动填写
    public HuaweiIOT()throws Exception//构造函数，自动调用
    {
        this.token=gettoken();
    }

    public String getAtt(String att,String mode) throws Exception{
        String strurl="";
        if(mode=="shadow")  strurl="https://%s/v5/iot/%s/devices/%s/shadow";
        if(mode=="status")  strurl="https://%s/v5/iot/%s/devices/%s";
        strurl = String.format(strurl, endpoint,project_id,device_id);
        URL url = new URL(strurl);
        HttpURLConnection urlCon = (HttpURLConnection)url.openConnection();
        urlCon.addRequestProperty("Content-Type", "application/json");
        urlCon.addRequestProperty("X-Auth-Token",token);
        urlCon.connect();
        InputStreamReader is = new InputStreamReader(urlCon.getInputStream());
        BufferedReader bufferedReader = new BufferedReader(is);
        StringBuffer strBuffer = new StringBuffer();
        String line = null;
        while ((line = bufferedReader.readLine()) != null) {
            strBuffer.append(line);
        }
        is.close();
        urlCon.disconnect();
        String result = strBuffer.toString();
        System.out.println(">>>>> result= " + result);
        if(mode=="shadow")
        {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readValue(result, JsonNode.class);
            JsonNode tempNode = jsonNode.get("shadow").get(0).get("reported").get("properties").get(att);
            String attvaluestr = tempNode.asText();
            return attvaluestr;
        }
        if(mode=="status")
        {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readValue(result, JsonNode.class);
            JsonNode statusNode = jsonNode.get("status");
            String statusstr = statusNode.asText();
            return statusstr;
        }
        return "error";
    }
    public String setCom(String com,String value) throws Exception{
        String strurl="";
        strurl="https://%s"+"/v5/iot/%s/devices/%s/commands";
        strurl = String.format(strurl, endpoint,project_id,device_id);
        URL url = new URL(strurl);
        HttpURLConnection urlCon = (HttpURLConnection)url.openConnection();
        urlCon.addRequestProperty("Content-Type", "application/json");
        urlCon.addRequestProperty("X-Auth-Token",token);
        urlCon.setRequestMethod("POST");
        urlCon.setDoOutput(true);
        urlCon.setUseCaches(false);
        urlCon.setInstanceFollowRedirects(true);
        urlCon.connect();
        String body = "{\"paras\":{\""+com+"\""+":\""+value+"\"},\"service_id\":\""+service_id+"\",\"command_name\":\""+com+"\"}";
        System.out.println("<<<< body= " + body);
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(urlCon.getOutputStream(),"UTF-8"));
        writer.write(body);
        writer.flush();
        writer.close();
        InputStreamReader is = new InputStreamReader(urlCon.getInputStream());
        BufferedReader bufferedReader = new BufferedReader(is);
        StringBuffer strBuffer = new StringBuffer();
        String line = null;
        while ((line = bufferedReader.readLine()) != null) {
            strBuffer.append(line);
        }
        is.close();
        urlCon.disconnect();
        String result = strBuffer.toString();
        return result;
    }
    public String gettoken( )throws Exception
    {
        String strurl="";
        strurl="https://iam.cn-north-4.myhuaweicloud.com"+"/v3/auth/tokens?nocatalog=false";
        String tokenstr="{"+"\""+"auth"+"\""+": {"+"\""+"identity"+"\""+": {"+"\""+"methods"+"\""+": ["+"\""+"password"+"\""+"],"+"\""+"password"+"\""+": {"+"\""+"user"+"\""+":{"+"\""+"domain\": {\"name\": \""+HUAWEINAME+"\"},\"name\": \""+IAMINAME+"\",\"password\": \""+IAMPASSWORD+"\"}}},\"scope\": {\"project\": {\"name\": \"cn-north-4\"}}}}";
        URL url = new URL(strurl);
        HttpURLConnection urlCon = (HttpURLConnection)url.openConnection();
        urlCon.addRequestProperty("Content-Type", "application/json;charset=utf8");
        urlCon.setDoOutput(true);
        urlCon.setRequestMethod("POST");
        urlCon.setUseCaches(false);
        urlCon.setInstanceFollowRedirects(true);
        urlCon.connect();
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(urlCon.getOutputStream(),"UTF-8"));
        writer.write(tokenstr);
        writer.flush();
        writer.close();
        Map headers = urlCon.getHeaderFields();
        Set<String> keys = headers.keySet();
        String token = urlCon.getHeaderField("X-Subject-Token");
        System.out.println("token= " + token);
        return  token;
    }
}
