package com.thc.sprapi.domain;

import com.thc.sprapi.dto.TbcrewtimeDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@ToString(callSuper = true)
@Table(indexes = {
        @Index(columnList = "title")
        ,@Index(columnList = "createdAt")
        ,@Index(columnList = "modifiedAt")
})
@Entity
public class Tbcrewtime extends AuditingFields {

    @Setter @Column(nullable = false) private String tbcrewId;
    @Setter @Column(nullable = false) private String title; // 제목
    @Setter @Column(nullable = false) private int sequence; // 순서
    @Setter @Column(nullable = false) private String process; // 진행도
    @Setter @Column(nullable = false) private String cate; // 구분 (일회성 허용 / 상시)
    @Setter @Column(nullable = false) private int startage; // 시작나이
    @Setter @Column(nullable = false) private int finishage; // 시작나이
    @Setter @Column(nullable = false) private String day0; // 일요일
    @Setter @Column(nullable = false) private String day1; // 월요일
    @Setter @Column(nullable = false) private String day2; // 화요일
    @Setter @Column(nullable = false) private String day3; // 수요일
    @Setter @Column(nullable = false) private String day4; // 목요일
    @Setter @Column(nullable = false) private String day5; // 금요일
    @Setter @Column(nullable = false) private String day6; // 토요일
    @Setter @Column(nullable = false) private String starttime; // 시작시간
    @Setter @Column(nullable = false) private String finishtime; // 종료시간


    protected Tbcrewtime(){}
    private Tbcrewtime(String tbcrewId, String title, int sequence, String cate, String process
            , int startage
            , int finishage
            , String day0
            , String day1
            , String day2
            , String day3
            , String day4
            , String day5
            , String day6
            , String starttime
            , String finishtime) {
        this.tbcrewId = tbcrewId;
        this.title = title;
        this.sequence = sequence;
        this.cate = cate;
        this.process = process;
        this.startage = startage;
        this.finishage = finishage;
        this.day0 = day0;
        this.day1 = day1;
        this.day2 = day2;
        this.day3 = day3;
        this.day4 = day4;
        this.day5 = day5;
        this.day6 = day6;
        this.starttime = starttime;
        this.finishtime = finishtime;

    }
    public static Tbcrewtime of(String tbcrewId, String title, int sequence, String cate, String process
            , int startage
            , int finishage
            , String day0
            , String day1
            , String day2
            , String day3
            , String day4
            , String day5
            , String day6
            , String starttime
            , String finishtime) {
        return new Tbcrewtime(tbcrewId, title, sequence, cate, process
                , startage
                , finishage
                , day0
                , day1
                , day2
                , day3
                , day4
                , day5
                , day6
                , starttime
                , finishtime);
    }

    public TbcrewtimeDto.TbcrewtimeAfterCreateDto toAfterCreateDto() {
        return TbcrewtimeDto.TbcrewtimeAfterCreateDto.builder()
                .id(super.getId())
                .build();
    }
    public TbcrewtimeDto.TbcrewtimeAfterUpdateDto toAfterUpdateDto() {
        return TbcrewtimeDto.TbcrewtimeAfterUpdateDto.builder()
                .id(super.getId())
                .deleted(super.getDeleted())
                .title(getTitle())
                .sequence(getSequence())
                .cate(getCate())
                .process(getProcess())
                .startage(getStartage())
                .finishage(getFinishage())
                .day0(getDay0())
                .day1(getDay1())
                .day2(getDay2())
                .day3(getDay3())
                .day4(getDay4())
                .day5(getDay5())
                .day6(getDay6())
                .starttime(getStarttime())
                .finishtime(getFinishtime())
                .build();
    }

}
