package com.ycwl.qiny.data.elasticsearch;

import org.apache.http.HttpHost;
import org.elasticsearch.ElasticsearchException;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.search.fetch.subphase.FetchSourceContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@SpringBootApplication
public class Application {
    private static String directoryPath = "C:\\Users\\Qiny\\Documents\\Tencent Files\\978761353\\FileRecv\\2012-4-1";
    private static Logger log = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost("localhost", 10192, "http")));
        log.info("client test result is : " + clientTest(client));
        List<String> files = DataUtil.getAllFile(directoryPath, false);
        for (String file : files) {
            List<Map> list = DataUtil.getDataFromExcel(file);
            Map<String, Object> source = new LinkedHashMap<>();
            list.forEach(map -> {
                source.put("application_id", 123);
                source.put("application_name", "electric-app");
                IndexRequest request = new IndexRequest("java-rest-test", "doc")
                        .source(map);
                IndexResponse response = null;
                try {
                    response = client.index(request, RequestOptions.DEFAULT);
                    log.info(response.toString());
                } catch (ElasticsearchException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
    }

    public static boolean clientTest(RestHighLevelClient client) {
        GetRequest getRequest = new GetRequest(
                "posts",
                "doc",
                "1");
        getRequest.fetchSourceContext(new FetchSourceContext(false));
        getRequest.storedFields("_none_");
        boolean exists = false;
        try {
            exists = client.exists(getRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return exists;
    }
}
