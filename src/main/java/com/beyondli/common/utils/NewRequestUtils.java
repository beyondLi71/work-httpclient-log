package com.beyondli.common.utils;

import com.beyondli.entity.enums.ValidEnum;
import com.beyondli.entity.po.httplog.HttpLogOutPO;
import com.beyondli.entity.po.http.HttpResult;
import org.apache.http.*;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.*;
import org.apache.http.conn.HttpHostConnectException;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.*;
import java.net.SocketTimeoutException;
import java.net.URLDecoder;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Http模拟调用请求工具
 * Created by beyondLi
 * Date 2021年1月20日14:29:51
 * Desc .
 *
 * 使用HttpClient发送请求、接收响应很简单，一般需要如下几步即可。
 *
 * 1. 创建HttpClient对象。
 *
 * 2. 创建请求方法的实例，并指定请求URL。如果需要发送GET请求，创建HttpGet对象；如果需要发送POST请求，创建HttpPost对象。
 *
 * 3. 如果需要发送请求参数，可调用HttpGet、HttpPost共同的setParams(HetpParams
 * params)方法来添加请求参数；对于HttpPost对象而言，也可调用setEntity(HttpEntity entity)方法来设置请求参数。
 *
 * 4. 调用HttpClient对象的execute(HttpUriRequest request)发送请求，该方法返回一个HttpResponse。
 *
 * 5. 调用HttpResponse的getAllHeaders()、getHeaders(String
 * name)等方法可获取服务器的响应头；调用HttpResponse的getEntity()方法可获取HttpEntity对象，该对象包装了服务器的响应内容。程序可通过该对象获取服务器的响应内容。
 *
 * 6. 释放连接。无论执行方法是否成功，都必须释放连接
 *
 */
@Component
public class NewRequestUtils {

    /**
     * get请求
     * @param uri
     * @return
     */
    public static HttpResult doGet(String uri) {
        // 创建GET请求方法
        HttpGet get = new HttpGet(uri);
        CloseableHttpClient client = HttpClients.createDefault();
        HttpResult httpResult = getHttpClient(get, client);
        return httpResult;
    }

    /**
     * get请求
     * @param uri
     * @param headerMaps 头部信息
     * @return
     */
    public static HttpResult doGet(String uri, Map<String, String> headerMaps) {
        // 创建GET请求方法
        HttpGet get = new HttpGet(uri);
        headerMaps.forEach((k, v) -> get.setHeader(k, v));
        CloseableHttpClient client = HttpClients.createDefault();
        HttpResult httpResult = getHttpClient(get, client);
        return httpResult;
    }

    /**
     * get请求
     * @param url
     * @param userName basic验证账号
     * @param password basic验证密码
     * @return
     */
    public static HttpResult doGetWithBasic(String url, String userName, String password) {
        HttpGet get = new HttpGet(url);
        CloseableHttpClient client = setHttpClientBasic(userName, password);
        return getHttpClient(get, client);
    }

    /**
     * get请求
     * @param url
     * @param headerMaps 头部信息
     * @param userName basic验证账号
     * @param password basic验证密码
     * @return
     */
    public static HttpResult doGetWithBasic(String url, Map<String, String> headerMaps, String userName, String password) {
        HttpGet get = new HttpGet(url);
        headerMaps.forEach((k, v) -> get.setHeader(k, v));
        CloseableHttpClient client = setHttpClientBasic(userName, password);
        return getHttpClient(get, client);
    }
//======================================================================================================================
    /**
     * post请求(form表单提交)
     * @param url
     * @param param 请求体参数
     * @return
     */
    public static HttpResult doPostWithForm(String url, Map<String, String> param) {
        HttpPost post = new HttpPost(url);
        CloseableHttpClient client = HttpClients.createDefault();
        return postHttpClientWithForm(post, param, client);
    }

    /**
     * post请求(form表单提交)
     * @param url
     * @param param 请求体参数
     * @param headerMaps 头部参数
     * @return
     */
    public static HttpResult doPostWithForm(String url, Map<String, String> param, Map<String, String> headerMaps) {
        HttpPost post = new HttpPost(url);
        headerMaps.forEach((k, v) -> post.setHeader(k, v));
        CloseableHttpClient client = HttpClients.createDefault();
        return postHttpClientWithForm(post, param, client);
    }
    /**
     * post请求(Json模式)
     * @param url
     * @param jsonParam json字符串
     * @return
     */
    public static HttpResult doPostWithJson(String url, String jsonParam) {
        HttpPost post = new HttpPost(url);
        CloseableHttpClient client = HttpClients.createDefault();
        return postHttpClient(post, jsonParam, client);
    }

    /**
     * post请求(Json模式)
     * @param url
     * @param jsonParam json字符串
     * @param headerMaps 头部信息
     * @return
     */
    public static HttpResult doPostWithJson(String url, String jsonParam, Map<String, String> headerMaps) {
        HttpPost post = new HttpPost(url);
        headerMaps.forEach((k, v) -> post.setHeader(k, v));
        CloseableHttpClient client = HttpClients.createDefault();
        return postHttpClient(post, jsonParam, client);
    }
    /**
     * PUT请求(JSON模式)
     * @param url
     * @param jsonParam json字符串
     * @return
     */
    public static HttpResult doPutWithJson(String url, String jsonParam) {
        HttpPut put = new HttpPut(url);
        CloseableHttpClient client = HttpClients.createDefault();
        return putHttpClient(put, jsonParam, client);
    }

    /**
     * PUT请求(JSON模式)
     * @param url
     * @param jsonParam json字符串
     * @param headerMaps 头部信息
     * @return
     */
    public static HttpResult doPutWithJson(String url, String jsonParam, Map<String, String> headerMaps) {
        HttpPut put = new HttpPut(url);
        headerMaps.forEach((k, v) -> put.setHeader(k, v));
        CloseableHttpClient client = HttpClients.createDefault();
        return putHttpClient(put, jsonParam, client);
    }

    //======================================================================================================================
    private static HttpResult getHttpClient(HttpGet get, CloseableHttpClient client) {
        HttpLogOutPO httpLogOutPO = getReqHttpLogOut(get); //初始化请求数据，并且封装对象

        //设置超时时间
        get.setConfig(setTimeout());
        //封装HttpResult实体类
        HttpResult httpResult = new HttpResult();

        try {
            //发送请求
            HttpResponse response = client.execute(get);
            HttpEntity entity = response.getEntity();
            httpResult.setStatusCode(response.getStatusLine().getStatusCode() + "");
            httpResult.setContent(EntityUtils.toString(entity, "UTF-8"));
            setRespHttpLogOut(httpResult, httpLogOutPO);
            httpResult.setHttpLogOutPO(httpLogOutPO);
            if (client != null) {
                client.close();
            }
        } catch (HttpHostConnectException e) {
            //证明url不存在 伪404
            //HttpHostConnectException
            sethttpResultInfo(httpResult,"EXC-404", e.getMessage(), new HttpLogOutPO(get.getRequestLine().getUri(), "GET", "", "", "EXC-404", e.getMessage(), -1l, LocalDateTime.now(), LocalDateTime.now(), "VALID"));
        } catch (InterruptedIOException e) {
            //证明url 连接超时 伪504
            //SocketTimeoutException
            sethttpResultInfo(httpResult,"EXC-504", e.getMessage(), new HttpLogOutPO(get.getRequestLine().getUri(), "GET", "", "", "EXC-504", e.getMessage(), -1l, LocalDateTime.now(), LocalDateTime.now(), "VALID"));
        } catch (IOException e) {
            //其他
            sethttpResultInfo(httpResult,"EXC-ERROR", e.getMessage(), new HttpLogOutPO(get.getRequestLine().getUri(), "GET", "", "", "EXC-ERROR", e.getMessage(), -1l, LocalDateTime.now(), LocalDateTime.now(), "VALID"));
        }
        return httpResult;
    }

    private static CloseableHttpClient setHttpClientBasic(String name, String password){
        CredentialsProvider provider = new BasicCredentialsProvider();
        UsernamePasswordCredentials credentials = new UsernamePasswordCredentials(name, password);
        provider.setCredentials(AuthScope.ANY, credentials);
        return  HttpClients.custom().setDefaultCredentialsProvider(provider).build();
    }

    //======================================================================================================================
    private static HttpResult postHttpClientWithForm(HttpPost post, Map<String, String> params, CloseableHttpClient client) {
        //封装HttpResult实体类
        HttpResult httpResult = new HttpResult();

        try {
            ArrayList<BasicNameValuePair> pairList = new ArrayList<>();
            params.forEach((k, v) -> pairList.add(new BasicNameValuePair(k, v)));


            if (params != null && params.size() > 0) {
                post.setEntity(new UrlEncodedFormEntity(pairList, "UTF-8"));
            }

            HttpLogOutPO httpLogOutPO = getReqHttpLogOut(post); //初始化请求数据，并且封装对象
            //设置超时时间
            post.setConfig(setTimeout());
            //发送请求
            HttpResponse response = client.execute(post);
            HttpEntity entity = response.getEntity();


            //设置参数
            httpResult.setStatusCode(response.getStatusLine().getStatusCode() + "");
            httpResult.setContent(EntityUtils.toString(entity, "UTF-8"));
            setRespHttpLogOut(httpResult, httpLogOutPO);
            httpResult.setHttpLogOutPO(httpLogOutPO);

            if (client != null) {
                client.close();
            }
        } catch (HttpHostConnectException e) {
            //证明url不存在 伪404
            //HttpHostConnectException
            sethttpResultInfo(httpResult,"EXC-404", e.getMessage(), new HttpLogOutPO(post.getRequestLine().getUri(), "POST", "", params.toString(), "EXC-404", e.getMessage(), -1l, LocalDateTime.now(), LocalDateTime.now(), "VALID"));
        } catch (InterruptedIOException e) {
            //证明url 连接超时 伪504
            //SocketTimeoutException
            sethttpResultInfo(httpResult,"EXC-504", e.getMessage(), new HttpLogOutPO(post.getRequestLine().getUri(), "POST", "", params.toString(), "EXC-504", e.getMessage(), -1l, LocalDateTime.now(), LocalDateTime.now(), "VALID"));
        } catch (IOException e) {
            //其他
            sethttpResultInfo(httpResult,"EXC-ERROR", e.getMessage(), new HttpLogOutPO(post.getRequestLine().getUri(), "POST", "", params.toString(), "EXC-ERROR", e.getMessage(), -1l, LocalDateTime.now(), LocalDateTime.now(), "VALID"));
        }

        return httpResult;
    }

    private static HttpResult postHttpClient(HttpPost post, String param, CloseableHttpClient client) {
        //封装HttpResult实体类
        HttpResult httpResult = new HttpResult();

        post.setHeader("Accept", "application/json");
        post.setHeader("Content-Type", "application/json");


        if (!StringUtils.isEmpty(param) && !"null".equalsIgnoreCase(param)) {
            post.setEntity(new StringEntity(param, "UTF-8"));
        }
        try {
            HttpLogOutPO httpLogOutPO = getReqHttpLogOut(post); //初始化请求数据，并且封装对象
            //设置超时时间
            post.setConfig(setTimeout());
            //发送请求
            HttpResponse response = client.execute(post);
            HttpEntity entity = response.getEntity();
            httpResult.setStatusCode(response.getStatusLine().getStatusCode() + "");
            httpResult.setContent(EntityUtils.toString(entity, "UTF-8"));
            setRespHttpLogOut(httpResult, httpLogOutPO);
            httpResult.setHttpLogOutPO(httpLogOutPO);

            if (client != null) {
                client.close();
            }
        }catch (HttpHostConnectException e) {
            //证明url不存在 伪404
            //HttpHostConnectException
            sethttpResultInfo(httpResult, "EXC-404", e.getMessage(), new HttpLogOutPO(post.getRequestLine().getUri(), "POST", "", param, "EXC-404", e.getMessage(), -1l, LocalDateTime.now(), LocalDateTime.now(), "VALID"));
        } catch (InterruptedIOException e) {
            //证明url 连接超时 伪504
            //SocketTimeoutException
            sethttpResultInfo(httpResult,"EXC-504", e.getMessage(), new HttpLogOutPO(post.getRequestLine().getUri(), "POST", "", param, "EXC-504", e.getMessage(), -1l, LocalDateTime.now(), LocalDateTime.now(), "VALID"));
        } catch (IOException e) {
            //其他
            sethttpResultInfo(httpResult,"EXC-ERROR", e.getMessage(), new HttpLogOutPO(post.getRequestLine().getUri(), "POST", "", param, "EXC-ERROR", e.getMessage(), -1l, LocalDateTime.now(), LocalDateTime.now(), "VALID"));
        }

        return httpResult;
    }
    //======================================================================================================================
    private static HttpResult putHttpClient(HttpPut put, String param, CloseableHttpClient client) {
        //封装HttpResult实体类
        HttpResult httpResult = new HttpResult();
        try {

            // 创建PUT请求方法
            put.setHeader("Accept", "application/json");
            put.setHeader("Content-Type", "application/json");
            put.setEntity(new StringEntity(param, "UTF-8"));
            HttpLogOutPO httpLogOutPO = getReqHttpLogOut(put); //初始化请求数据，并且封装对象
            //设置超时时间
            put.setConfig(setTimeout());
            //发送请求
            HttpResponse response = client.execute(put);
            HttpEntity entity = response.getEntity();
            //设置参数
            httpResult.setStatusCode(response.getStatusLine().getStatusCode() + "");
            httpResult.setContent(EntityUtils.toString(entity, "UTF-8"));
            setRespHttpLogOut(httpResult, httpLogOutPO);
            httpResult.setHttpLogOutPO(httpLogOutPO);

            if (client != null) {
                client.close();
            }
        }catch (HttpHostConnectException e) {
            //证明url不存在 伪404
            //HttpHostConnectException
            sethttpResultInfo(httpResult,"EXC-404", e.getMessage(), new HttpLogOutPO(put.getRequestLine().getUri(), "PUT", "", param, "EXC-404", e.getMessage(), -1l, LocalDateTime.now(), LocalDateTime.now(), "VALID"));
        } catch (InterruptedIOException e) {
            //证明url 连接超时 伪504
            //SocketTimeoutException
            sethttpResultInfo(httpResult,"EXC-504", e.getMessage(), new HttpLogOutPO(put.getRequestLine().getUri(), "PUT", "", param, "EXC-504", e.getMessage(), -1l, LocalDateTime.now(), LocalDateTime.now(), "VALID"));
        } catch (IOException e) {
            //其他
            sethttpResultInfo(httpResult,"EXC-ERROR", e.getMessage(), new HttpLogOutPO(put.getRequestLine().getUri(), "PUT", "", param, "EXC-ERROR", e.getMessage(), -1l, LocalDateTime.now(), LocalDateTime.now(), "VALID"));
        }
        return httpResult;
    }
//======================================================================================================================
    /**
     * 初始化请求数据，并且封装对象，针对POST/PUT方法
     * @param httpBase
     * @return
     * @throws IOException
     */
    private static HttpLogOutPO getReqHttpLogOut(HttpEntityEnclosingRequestBase httpBase) throws IOException {

        HttpLogOutPO httpLogOutPO = new HttpLogOutPO();
        httpLogOutPO.setReqUrl(httpBase.getRequestLine().getUri());     //url
        httpLogOutPO.setReqMethod(httpBase.getMethod());                 //请求方法
        if (httpBase.getEntity() != null) {
            httpLogOutPO.setReqParams(URLDecoder.decode(new BufferedReader(new InputStreamReader(httpBase.getEntity().getContent(), "utf-8")).lines().collect(Collectors.joining(System.lineSeparator())), "UTF-8"));
            httpLogOutPO.setReqType(httpBase.getEntity().getContentType().getValue());      // 请求类型
        }

        httpLogOutPO.setRespTime(System.currentTimeMillis());//记录请求时间
        LocalDateTime createTime = LocalDateTime.now();
        httpLogOutPO.setCreateTime(createTime);        // 创建时间
        httpLogOutPO.setUpdateTime(createTime);        // 更新时间
        httpLogOutPO.setStat(ValidEnum.VALID.name());  // 状态

        return httpLogOutPO;
    }

    /**
     * 初始化请求数据，并且封装对象，针对GET方法
     * @param httpBase
     * @return
     */
    private static HttpLogOutPO getReqHttpLogOut(HttpRequestBase httpBase) {

        HttpLogOutPO httpLogOutPO = new HttpLogOutPO();
        httpLogOutPO.setReqUrl(httpBase.getRequestLine().getUri());     //url
        httpLogOutPO.setReqMethod(httpBase.getMethod());                //请求方法
        httpLogOutPO.setRespTime(System.currentTimeMillis());           //记录请求时间
        LocalDateTime createTime = LocalDateTime.now();
        httpLogOutPO.setCreateTime(createTime);        // 创建时间
        httpLogOutPO.setUpdateTime(createTime);        // 更新时间
        httpLogOutPO.setStat(ValidEnum.VALID.name());  // 状态

        return httpLogOutPO;
    }

    /**
     * 设置响应体内容, 并且封装对象
     * @param httpResult
     * @param httpLogOutPO
     */
    private static void setRespHttpLogOut(HttpResult httpResult, HttpLogOutPO httpLogOutPO) {
        httpLogOutPO.setRespResult(httpResult.getContent());
        httpLogOutPO.setRespCode(String.valueOf(httpResult.getStatusCode()));
        httpLogOutPO.setRespTime(System.currentTimeMillis() - httpLogOutPO.getRespTime());
    }

    /**
     * 设置超时时间
     * @return
     */
    private static RequestConfig setTimeout() {
        Integer time = 15000;
        return RequestConfig.custom().setConnectTimeout(time).setConnectionRequestTimeout(time).setSocketTimeout(time).build();
    }

    /**
     * 设置httpResult信息
     * @param httpResult
     * @param statusCode
     * @param content
     * @param httpLogOutPO
     */
    private static void sethttpResultInfo(HttpResult httpResult, String statusCode, String content, HttpLogOutPO httpLogOutPO) {
        httpResult.setStatusCode(statusCode);
        httpResult.setContent(content);
        httpResult.setHttpLogOutPO(httpLogOutPO);
    }
//======================================================================================================================
    /*    private static void setExceptionHttpResult(HttpResult httpResult, String code, String url, String message, String method) {
        LocalDateTime now = LocalDateTime.now();
        httpResult.setStatusCode(code);
        httpResult.setContent(message);
        HttpLogOutPO httpLogOutPO = new HttpLogOutPO(url, method, "", "", code, message, System.currentTimeMillis(), now, now, ValidEnum.VALID.name());
        httpResult.setHttpLogOutPO(httpLogOutPO);
    }*/


    /*    private static HttpResult getBasic(HttpGet get, String userName, String password) {
        CloseableHttpClient client = setHttpClientBasic(userName, password);
        HttpLogOutPO httpLogOutPO = getReqHttpLogOut(get); //初始化请求数据，并且封装对象

        //设置超时时间
        get.setConfig(setTimeout());
        //封装HttpResult实体类
        HttpResult httpResult = new HttpResult();

        try {
            //发送请求
            HttpResponse response = client.execute(get);
            HttpEntity entity = response.getEntity();
            httpResult.setStatusCode(response.getStatusLine().getStatusCode() + "");
            httpResult.setContent(EntityUtils.toString(entity, "UTF-8"));
            setRespHttpLogOut(httpResult, httpLogOutPO);
            httpResult.setHttpLogOutPO(httpLogOutPO);
            if (client != null) {
                client.close();
            }
        } catch (HttpHostConnectException e) {
            //证明url不存在 伪404
            //HttpHostConnectException
            sethttpResultInfo(httpResult,"EXC-404", e.getMessage(), new HttpLogOutPO(get.getRequestLine().getUri(), "GET", "", "", "EXC-404", e.getMessage(), -1l, LocalDateTime.now(), LocalDateTime.now(), "VALID"));
        } catch (InterruptedIOException e) {
            //证明url 连接超时 伪504
            //SocketTimeoutException
            sethttpResultInfo(httpResult,"EXC-504", e.getMessage(), new HttpLogOutPO(get.getRequestLine().getUri(), "GET", "", "", "EXC-504", e.getMessage(), -1l, LocalDateTime.now(), LocalDateTime.now(), "VALID"));
        } catch (IOException e) {
            //其他
            sethttpResultInfo(httpResult,"EXC-ERROR", e.getMessage(), new HttpLogOutPO(get.getRequestLine().getUri(), "GET", "", "", "EXC-ERROR", e.getMessage(), -1l, LocalDateTime.now(), LocalDateTime.now(), "VALID"));
        }
        return httpResult;
    }*/
}
