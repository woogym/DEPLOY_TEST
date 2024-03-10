package com.gdsc.youpick.domain;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;


@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
@Builder
@EntityListeners(AuditingEntityListener.class)
@Table(name = "Videos")
public class Video {

    @Id
    @Column(name = "VIDEO_ID")
    private String Id;

    @Column(name = "VIDEO_TITLE")
    private String title;

    @Column(name = "PUBLISH_AT")
    private LocalDateTime publishAt;

    @Column(name = "VIDEO_URL")
    private String url;

    @Column(name = "ADVERTISING")
    private String videoPaidProductPlacement;

    @Column(name = "IS_PAID_PLACEMENT")
    private boolean isPaidPlacement;
}
