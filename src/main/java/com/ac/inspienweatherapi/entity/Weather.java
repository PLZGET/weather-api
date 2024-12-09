package com.ac.inspienweatherapi.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Weather {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @CreationTimestamp
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @Column(name = "date_time", nullable = false)
    private LocalDateTime dateTime; // 날짜 및 시간

    @Column(name = "region", nullable = false)
    private String region;     // 지역

    @Column(name = "weather", nullable = false)
    private String weather;  // 날씨

    @Column(name = "temperature", nullable = false)
    private Double temperature; // 온도
}
