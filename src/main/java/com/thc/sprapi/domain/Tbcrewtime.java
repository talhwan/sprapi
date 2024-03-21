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
    @Setter @Column(nullable = false) private String cate; // 구분 (일회성 허용 / 상시)
    @Setter @Column(nullable = false) private int startage; // 시작나이
    @Setter @Column(nullable = false) private int finishage; // 시작나이
    @Setter @Column(nullable = false) private String startday; // 시작요일
    @Setter @Column(nullable = false) private String finishday; // 종료요일
    @Setter @Column(nullable = false) private String starttime; // 시작시간
    @Setter @Column(nullable = false) private String finishtime; // 종료시간


    protected Tbcrewtime(){}
    private Tbcrewtime(String tbcrewId, String title, int sequence, String cate
            , int startage
            , int finishage
            , String startday
            , String finishday
            , String starttime
            , String finishtime) {
        this.tbcrewId = tbcrewId;
        this.title = title;
        this.sequence = sequence;
        this.cate = cate;
        this.startage = startage;
        this.finishage = finishage;
        this.startday = startday;
        this.finishday = finishday;
        this.starttime = starttime;
        this.finishtime = finishtime;

    }
    public static Tbcrewtime of(String tbcrewId, String title, int sequence, String cate
            , int startage
            , int finishage
            , String startday
            , String finishday
            , String starttime
            , String finishtime) {
        return new Tbcrewtime(tbcrewId, title, sequence, cate
                , startage
                , finishage
                , startday
                , finishday
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
                .startage(getStartage())
                .finishage(getFinishage())
                .startday(getStartday())
                .finishday(getFinishday())
                .starttime(getStarttime())
                .finishtime(getFinishtime())
                .build();
    }

}
