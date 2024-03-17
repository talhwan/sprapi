package com.thc.sprapi.domain;

import com.thc.sprapi.dto.TbcrewDto;
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
public class Tbcrew extends AuditingFields {

    @Setter @Column(nullable = false) private String title; // 제목
    @Setter @Column(nullable = false) private String process; // 진행사항
    @Setter @Column(nullable = false) private String cate; // 구분
    @Setter @Column(nullable = false) private String mpic; // 대표사진
    @Setter @Column(nullable = false, length=2000000) @Lob private String content; // 본문

    protected Tbcrew(){}
    private Tbcrew(String title, String process, String cate, String mpic, String content) {
        this.title = title;
        this.process = process;
        this.cate = cate;
        this.mpic = mpic;
        this.content = content;
    }
    public static Tbcrew of(String title, String process, String cate, String mpic, String content) {
        return new Tbcrew(title, process, cate, mpic, content);
    }

    public TbcrewDto.TbcrewAfterCreateDto toAfterCreateDto() {
        return TbcrewDto.TbcrewAfterCreateDto.builder()
                .id(super.getId())
                .build();
    }
    public TbcrewDto.TbcrewAfterUpdateDto toAfterUpdateDto() {
        return TbcrewDto.TbcrewAfterUpdateDto.builder()
                .id(super.getId())
                .deleted(super.getDeleted())
                .title(getTitle())
                .process(getProcess())
                .cate(getCate())
                .mpic(getMpic())
                .content(getContent())
                .build();
    }

}
