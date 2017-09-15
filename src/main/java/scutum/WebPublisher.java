package scutum;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class WebPublisher {
    private final String url;

    public WebPublisher(String url) {
        this.url = url;
    }

    public String publish(String jsonInput) {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        String result = null;

        HttpPost request = new HttpPost(url);
        StringEntity params;
        try {
            params = new StringEntity(jsonInput);

            request.addHeader("content-type", "application/x-www-form-urlencoded");
            request.setEntity(params);
            CloseableHttpResponse response = httpClient.execute(request);

            httpClient.close();
            //handle response here...
            result = response.toString();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

        }
        return  result;
    }
}
