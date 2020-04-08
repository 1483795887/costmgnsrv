package com.costmgn.costmgnsrv.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

public class PostHelper {
    private MockMvc mockMvc;
    private MockHttpSession session;

    public PostHelper(WebApplicationContext context) {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
        session = new MockHttpSession();
    }

    public <T> WebApiResponse<T> postData(String url,
                                          Object object,
                                          Class<T> tClass,
                                          boolean isList)
            throws Exception {
        String jsonStr = JSON.toJSONString(object);
        MvcResult result = mockMvc.perform(
                post(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonStr)
                        .session(session)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn();
        result.getResponse().setCharacterEncoding("utf-8");
        String string = result.getResponse().getContentAsString();
        JSONObject jsonObject = JSONObject.parseObject(string);
        WebApiResponse webApiResponse
                = jsonObject.toJavaObject(WebApiResponse.class);
        if (tClass != Boolean.class) {  //Boolean不能转为JSONObject
            if (isList) {
                JSONArray array = jsonObject.getJSONArray("data");
                List<T> data = array.toJavaList(tClass);
                webApiResponse.setData(data);
            } else {
                JSONObject jsonData = jsonObject.getJSONObject("data");
                T data;
                if (jsonData != null)
                    data = jsonData.toJavaObject(tClass);
                else
                    data = null;
                webApiResponse.setData(data);
            }
        }
        return webApiResponse;
    }

    public <T> WebApiResponse<T>
    getData(String url, Object object, Class<T> tClass) throws Exception {
        return postData(url, object, tClass, false);
    }

    public WebApiResponse<Boolean>
    getBoolean(String url, Object object) throws Exception {
        return postData(url, object, Boolean.class, false);
    }

    public <T> WebApiResponse<T>
    getList(String url, Object object, Class<T> itemClass) throws Exception {
        return postData(url, object, itemClass, true);
    }

    public Object getSessionItem(String name) {
        return session.getAttribute(name);
    }

    public void setSessionItem(String name, Object object) {
        session.setAttribute(name, object);
    }
}
