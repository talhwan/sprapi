package com.thc.sprapi.domain;

import com.thc.sprapi.dto.TbbannerDto;
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
public class Tbbanner extends AuditingFields {

    @Setter @Column(nullable = false) private String title; // 제목
    @Setter @Column(nullable = false) private int sequence; // 순서
    @Setter @Column(nullable = false) private String cate; // 구분
    @Setter @Column(nullable = false) private String mpic; // 사진
    @Setter @Column(nullable = false) private String url; // URL
    @Setter @Column(nullable = false, length=2000000) @Lob private String content; // 본문

    protected Tbbanner(){}
    private Tbbanner(String title, int sequence, String cate, String mpic, String url, String content) {
        this.title = title;
        this.sequence = sequence;
        this.cate = cate;
        this.mpic = mpic;
        this.url = url;
        this.content = content;
    }
    public static Tbbanner of(String title, int sequence, String cate, String mpic, String url, String content) {
        return new Tbbanner(title, sequence, cate, mpic, url, content);
    }

    public TbbannerDto.TbbannerAfterCreateDto toAfterCreateDto() {
        return TbbannerDto.TbbannerAfterCreateDto.builder()
                .id(super.getId())
                .build();
    }
    public TbbannerDto.TbbannerAfterUpdateDto toAfterUpdateDto() {
        return TbbannerDto.TbbannerAfterUpdateDto.builder()
                .id(super.getId())
                .deleted(super.getDeleted())
                .title(getTitle())
                .sequence(getSequence())
                .cate(getCate())
                .mpic(getMpic())
                .content(getContent())
                .build();
    }

}
