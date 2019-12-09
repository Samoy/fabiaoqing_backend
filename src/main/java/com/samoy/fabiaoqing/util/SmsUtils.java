package com.samoy.fabiaoqing.util;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * SmsUtils
 *
 * @author Samoy
 * @date 2019/10/17
 */
@Component
public class SmsUtils {
    @Value("${bmob.requestSmsUrl}")
    private String requestSmsUrl;
    @Value("${bmob.verifySmsUrl}")
    private String verifySmsUrl;
    @Value("${bmob.applicationId}")
    private String applicationId;
    @Value("${bmob.restApiKey}")
    private String restApiKey;
    @Value("${bmob.smsTemplate}")
    private String smsTemplate;

    @Resource
    private RestTemplate restTemplate;

    /**
     * 发送验证码短信
     *
     * @param telephone 手机号码
     * @return 短信验证码id或者错误信息，如是数字，则为短信id，如非数字，则为错误信息
     */
    public String sendSms(String telephone) {
        SmsRequest smsRequest = new SmsRequest();
        smsRequest.setTemplate(smsTemplate);
        smsRequest.setMobilePhoneNumber(telephone);
        HttpEntity<SmsRequest> requestHttpEntity = new HttpEntity<>(smsRequest, genSmsHeaders());
        try {
            ResponseEntity<SmsResponse> responseEntity = restTemplate.postForEntity(requestSmsUrl, requestHttpEntity, SmsResponse.class);
            return Objects.requireNonNull(responseEntity.getBody()).getSmsId();
        } catch (RestClientException exception) {
            if (exception instanceof HttpClientErrorException) {
                String responseStr = ((HttpClientErrorException) exception).getResponseBodyAsString();
                SmsResponse response = JSONObject.parseObject(responseStr, SmsResponse.class);
                return response.getError();
            }
            return exception.getLocalizedMessage();
        }
    }

    /**
     * 验证码是否合法
     *
     * @param telephone 手机号
     * @param code      验证码
     * @return 是否合法，若为"ok"则合法
     */
    public String verifySms(String telephone, String code) {
        SmsRequest smsRequest = new SmsRequest();
        smsRequest.setMobilePhoneNumber(telephone);
        smsRequest.setTemplate(smsTemplate);
        HttpEntity<SmsRequest> requestHttpEntity = new HttpEntity<>(smsRequest, genSmsHeaders());
        try {
            ResponseEntity<SmsResponse> responseEntity = restTemplate.postForEntity(verifySmsUrl + "/" + code, requestHttpEntity, SmsResponse.class);
            return Objects.requireNonNull(responseEntity.getBody()).getMsg();
        } catch (RestClientException exception) {
            if (exception instanceof HttpClientErrorException) {
                String responseStr = ((HttpClientErrorException) exception).getResponseBodyAsString();
                SmsResponse response = JSONObject.parseObject(responseStr, SmsResponse.class);
                return response.getError();
            }
            return exception.getLocalizedMessage();
        }
    }

    private HttpHeaders genSmsHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-Bmob-Application-Id", applicationId);
        headers.set("X-Bmob-REST-API-Key", restApiKey);
        headers.set("Content-Type", "application/json");
        return headers;
    }
}

@Data
class SmsRequest {
    private String mobilePhoneNumber;
    private String template;
}

@Data
class SmsResponse {
    private String smsId;
    private Integer code;
    private String msg;
    private String error;
}
