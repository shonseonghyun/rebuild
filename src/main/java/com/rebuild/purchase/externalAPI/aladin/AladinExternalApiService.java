package com.rebuild.purchase.externalAPI.aladin;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rebuild.purchase.SearchQuery;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.List;

@Service
public class
AladinExternalApiService extends BookSearchApiHandler
//        implements ExternalApiService
{

    private final String SEARCH_URL = "https://www.aladin.co.kr/ttb/api/ItemSearch.aspx";
    private final String TTBKEY= "ttbsunghyun78951915001";
    private final String VERSION="20131101";

    @Override
    public List<SearchedAladinBookDTO> searchBooks(SearchQuery searchQuery) {

        ObjectMapper objectMapper = new ObjectMapper();
        Object objValue = null;
        SearchedAladinDTO searchedAladinDTO = new SearchedAladinDTO();

        try{
            URI uri = new URI(SEARCH_URL);
            uri = new URIBuilder(uri)
                    .addParameter("ttbkey",TTBKEY)
                    .addParameter("version",VERSION)
                    .addParameter("Query",searchQuery.getQuery())
                    .addParameter("QueryType",searchQuery.getTarget())
                    .addParameter("MaxResults","10")
                    .addParameter("start","1")
                    .addParameter("SearchTarget","Book")
                    .addParameter("output","JS")
                    .build();

            HttpClient httpClient = HttpClientBuilder.create().build();
            HttpResponse httpResponse = httpClient.execute(new HttpGet(uri));
            HttpEntity entity = httpResponse.getEntity();

//            content = EntityUtils.toString(entity);
//            System.out.println(content);

            //JSON데이터를 매핑하지 못하는 에러 발생을 위해 추가
//            응답 json 객체에 존재하지 않는 필드는 매핑 제외
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);

            String content = EntityUtils.toString(entity);
            searchedAladinDTO = objectMapper.readValue(content, SearchedAladinDTO.class);
        }catch (Exception e){
            System.out.println(e);
        }
        return searchedAladinDTO.getItem();
    }
}
