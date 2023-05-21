package com.rebuild.purchase.externalAPI.kakao;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rebuild.purchase.SearchQuery;
import com.rebuild.purchase.externalAPI.aladin.BookSearchApiHandler;
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
public class KakaoExternalApiService extends BookSearchApiHandler
//        implements ExternalApiService
{

    private final String KAKAO_SEARCHURI = "https://dapi.kakao.com/v3/search/book?";
//    private final String KAKAO_AUTHORIZATION_WRONG ="KakaoAK 864f674e30bb5fca4ab3e06811261317ㄴㄴㄴ";
    private final String KAKAO_AUTHORIZATION ="KakaoAK 864f674e30bb5fca4ab3e06811261317";

    @Override
    public List<SearchedKakaoBookDTO> searchBooks(SearchQuery searchQuery) {
        ObjectMapper objectMapper = new ObjectMapper();
        Object objValue = null;
        SearchedKakoDTO searchedKakoDTO = new SearchedKakoDTO();



        try {
            URI uri = new URI(KAKAO_SEARCHURI);
            uri = new URIBuilder(uri)
                    .addParameter("target",searchQuery.getTarget())
                    .addParameter("query",searchQuery.getQuery())
                    .addParameter("page","1")
                    .build();

            //HTTPClient 생성
            HttpClient httpClient = HttpClientBuilder.create().build();
            HttpGet getRequest = new HttpGet(uri);
            getRequest.addHeader("Authorization",KAKAO_AUTHORIZATION);

            //통신요청
            HttpResponse httpResponse = httpClient.execute(getRequest);
            HttpEntity httpEntity = httpResponse.getEntity();

            String content= EntityUtils.toString(httpEntity);

            //JSON데이터를 매핑하지 못하는 에러 발생을 위해 추가
            //응답 json 객체에 존재하지 않는 필드는 매핑 제외 = api 응답 필드 중 원하지 않는 필드는 매핑처리 X
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);

            searchedKakoDTO = objectMapper.readValue(content,SearchedKakoDTO.class);
            //statusCode세팅
//            String statusCode = String.valueOf(httpResponse.getStatusLine().getStatusCode());
//            searchedDTOKAKAO.setResultCode(statusCode);
//            searchedDTOKAKAO.setResultMessae("성공");

        } catch (Exception e) {
//            searchedDTOKAKAO.setResultCode("500");
//            searchedDTOKAKAO.setResultMessae(e.getMessage());
//            throw new RuntimeException(e);
        }
        return searchedKakoDTO.getDocuments();
    }
}
