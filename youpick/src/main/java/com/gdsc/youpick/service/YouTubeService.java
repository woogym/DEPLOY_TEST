package com.gdsc.youpick.service;

import com.gdsc.youpick.domain.Video;
import com.gdsc.youpick.dto.videoDto.Request.VideoTitleRequestDto;
import com.gdsc.youpick.dto.videoDto.Response.VideoIdResponseDto;
import com.gdsc.youpick.repository.VideoRepository;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.SearchListResponse;
import com.google.api.services.youtube.model.SearchResult;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class YouTubeService {
    private final YouTube youTube;
    private final VideoRepository videoRepository;
    private final RestTemplate restTemplate;


    @Value("${youtube.api.key}")
    private String apiKey;

    public List<VideoIdResponseDto> searchByTitle(VideoTitleRequestDto videoTitleRequestDto) throws Exception {
        String title = videoTitleRequestDto.getTitle();
        String videoPaidProductPlacement = videoTitleRequestDto.getVideoPaidProductPlacement();

        List<String> part = Collections.singletonList("snippet");
        YouTube.Search.List search = youTube.search().list(part);
        search.setKey(apiKey);
        search.setQ(title);
        search.setVideoSyndicated(videoPaidProductPlacement);
        search.setType(Collections.singletonList("video"));
        search.setMaxResults(5L);
        search.setVideoDuration("short");

        SearchListResponse searchListResponse = search.execute();
        List<SearchResult> searchResultList = searchListResponse.getItems();

        List<VideoIdResponseDto> videos = new ArrayList<>();
        for (SearchResult result : searchResultList) {
            VideoIdResponseDto video = convertToVideo(result);
            videos.add(video);
            videoRepository.save(convertToVideoEntity(video));
        }

//        String djangoUrl = "https://eb79-39-7-18-188.ngrok-free.app/api/video-info/";
//        restTemplate.postForEntity(djangoUrl, videos, Void.class);

        return videos;
    }

    public Video convertToVideoEntity(VideoIdResponseDto videoIdResponseDto) {
        return Video.builder()
                .Id(videoIdResponseDto.getId())
                .build();
    }

    private VideoIdResponseDto convertToVideo(SearchResult searchResult) {
        return VideoIdResponseDto.builder()
                .Id(searchResult.getId().getVideoId())
                .build();
    }
}
