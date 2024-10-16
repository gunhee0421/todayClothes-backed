package org.project.todayclothes.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.project.todayclothes.dto.EventReqDto;
import org.project.todayclothes.exception.BusinessException;
import org.project.todayclothes.exception.code.EventErrorCode;
import org.project.todayclothes.global.Style;
import org.project.todayclothes.global.Type;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime startTime;
    private String location;
    @Enumerated(EnumType.STRING)
    private Type type;
    @Enumerated(EnumType.STRING)
    private Style style;
    private String imagePath;
    private String comment;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "review_id")
    private Review review;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "weather_id")
    private Weather weather;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public Event(EventReqDto eventReqDto, Weather weather,User user) {
        this.startTime = eventReqDto.getStartTime();
        this.location = eventReqDto.getLocation();
        this.type = eventReqDto.getType();
        this.style = eventReqDto.getStyle();
        this.weather = weather;
        this.user = user;
    }

    public void updateEvent(EventReqDto eventReqDto) {
        if (eventReqDto == null){
            throw new BusinessException(EventErrorCode.EVENT_UPDATE_FAILED);
        }
        this.startTime = eventReqDto.getStartTime();
        this.location = eventReqDto.getLocation();
        this.type = eventReqDto.getType();
        this.style = eventReqDto.getStyle();
    }

    public void updateWeather(EventReqDto eventReqDto) {
        if (this.weather == null) {
            this.weather = new Weather(eventReqDto);
        } else {
            this.weather.updateWeather(eventReqDto);
        }
    }

    public Event changeImagePath(String imagePath) {
        if (imagePath == null || imagePath.isEmpty()) {
            throw new BusinessException(EventErrorCode.EVENT_UPDATE_FAILED);
        }
        this.imagePath = imagePath;
        return this;
    }

    public Event associateReview(Review review) {
        if (this.review != null) {
            throw new IllegalStateException("Review is already associated with this event.");
        }
        this.review = review;
        return this;
    }
}
