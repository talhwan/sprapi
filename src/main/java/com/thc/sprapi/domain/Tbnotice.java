package com.thc.sprapi.domain;

import com.thc.sprapi.dto.TbnoticeDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Blob;

@Getter
@ToString(callSuper = true)
@Table(indexes = {
        @Index(columnList = "title")
        ,@Index(columnList = "createdAt")
        ,@Index(columnList = "modifiedAt")
})
@Entity
public class Tbnotice extends AuditingFields {

    @Setter @Column(nullable = false) private String title; // 제목
    @Setter @Column(nullable = false) private String cate; // 구분
    @Setter @Column(nullable = false) private String mpic; // 대표사진
    @Setter @Column(nullable = false, length=2000000) @Lob private String content; // 본문

    protected Tbnotice(){}
    private Tbnotice(String title, String cate, String mpic, String content) {
        this.title = title;
        this.cate = cate;
        this.mpic = mpic;
        this.content = content;
    }
    public static Tbnotice of(String title, String cate, String mpic, String content) {
        return new Tbnotice(title, cate, mpic, content);
    }

    public TbnoticeDto.TbnoticeAfterCreateDto toAfterCreateDto() {
        return TbnoticeDto.TbnoticeAfterCreateDto.builder()
                .id(super.getId())
                .build();
    }
    public TbnoticeDto.TbnoticeAfterUpdateDto toAfterUpdateDto() {
        return TbnoticeDto.TbnoticeAfterUpdateDto.builder()
                .id(super.getId())
                .deleted(super.getDeleted())
                .title(getTitle())
                .cate(getCate())
                .mpic(getMpic())
                .content(getContent())
                .build();
    }

}
