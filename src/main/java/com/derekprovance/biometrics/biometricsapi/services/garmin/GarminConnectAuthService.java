package com.derekprovance.biometrics.biometricsapi.services.garmin;

import okhttp3.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class GarminConnectAuthService {

    private String username;
    private String password;

    private Pattern pattern = Pattern.compile("(?<=\\?ticket=)(.*)(?=\";)");
    private OkHttpClient client = new OkHttpClient();
    private final RestTemplate restTemplate = new RestTemplate();
    private String session;
    private String userId;


    @Autowired
    public GarminConnectAuthService(
            @Value("${garmin.username}") String username,
            @Value("${garmin.password}") String password,
            @Value("${garmin.session") String session,
            @Value("${garmin.user.id}") String userId
    ) {
        this.username = username;
        this.password = password;
        this.session = session;
        this.userId = userId;
    }

    public String getUsername() {
        return this.username;
    }

    public String getUserId() {
        return this.userId;
    }

    public String getSessionCookie() {
        return String.format("SESSIONID=%s", this.session);
    }

    //TODO - get token acquisition functioning
    public void acquireToken() {
        OkHttpClient client = new OkHttpClient();

        MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
        RequestBody body = RequestBody.create(mediaType, String.format("username=%s&password=%s&embed=false", username, password));
        Request request = new Request.Builder()
                .url("https://sso.garmin.com/sso/signin?service=https%3A%2F%2Fconnect.garmin.com%2Fmodern%2F&clientId=GarminConnect")
                .post(body)
                .addHeader("authority", "sso.garmin.com")
                .addHeader("origin", "https://sso.garmin.com")
                .addHeader("content-type", "application/x-www-form-urlencoded")
                .addHeader("user-agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_14_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/77.0.3865.90 Safari/537.36")
                .addHeader("Accept", "*/*")
                .addHeader("Cache-Control", "no-cache")
                .addHeader("Host", "sso.garmin.com")
                .build();

        try {
            Response response = client.newCall(request).execute();
            String bodyStr = Objects.requireNonNull(response.body()).string();

            Matcher matcher = pattern.matcher(bodyStr);
            if(matcher.find()) {
//                acquireSessionId(matcher.group(0));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
