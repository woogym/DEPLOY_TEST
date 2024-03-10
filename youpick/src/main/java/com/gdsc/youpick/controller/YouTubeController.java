package com.gdsc.youpick.controller;

import com.gdsc.youpick.dto.videoDto.Request.VideoTitleRequestDto;
import com.gdsc.youpick.dto.videoDto.Response.VideoIdResponseDto;
import com.gdsc.youpick.service.YouTubeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class YouTubeController {

    private final YouTubeService youTubeService;

    @PostMapping("/submit")
    public ResponseEntity<List<VideoIdResponseDto>> searchByTitle(@RequestBody VideoTitleRequestDto videoTitleRequestDto) throws Exception {
        List<VideoIdResponseDto> responseDto = youTubeService.searchByTitle(videoTitleRequestDto);

        return ResponseEntity.ok(responseDto);

    }
}
