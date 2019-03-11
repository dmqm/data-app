package com.ycwl.qiny.data.elasticsearch;

import org.apache.http.HttpHost;
import org.elasticsearch.ElasticsearchException;
import org.elasticsearch.action.admin.indices.get.GetIndexRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
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
    //    private static String directoryPath = "C:\\Users\\Qiny\\Documents\\Tencent Files\\978761353\\FileRecv\\2012-4-1";
    private static String directoryPath = "C:\\Users\\justq\\Documents\\Tencent Files\\978761353\\FileRecv\\2012-4\\2012-4";
    private static Logger log = LoggerFactory.getLogger(Application.class);
    private static String index = "electric-data-test";

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost("121.49.107.155", 10292, "http")));
        log.info("client test result is : " + clientTest(client, index));
        List<String> files = DataUtil.getAllFile(directoryPath, false);
        log.info("file size is :" + files.size());
        for (int i = 0; i < files.size(); i++) {
            String file = files.get(i);
            log.info("now is in file :" + i);
            List<Map> list = DataUtil.getDataFromExcel(file);
            Map<String, Object> source = new LinkedHashMap<>();
            list.forEach(map -> {
                source.put("application_id", 123);
                source.put("application_name", "electric-app");
                source.putAll(map);
                IndexRequest request = new IndexRequest(index, "doc")
                        .source(source);
                IndexResponse response = null;
                try {
                    response = client.index(request, RequestOptions.DEFAULT);
                } catch (ElasticsearchException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
    }

    public static boolean clientTest(RestHighLevelClient client, String index) {
        GetIndexRequest request = new GetIndexRequest();
        request.indices(index);
        try {
            return client.indices().exists(request, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
