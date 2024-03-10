package com.gdsc.youpick.dto.videoDto.Request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class VideoTitleRequestDto {

    private String title;
    private String videoPaidProductPlacement;
}
