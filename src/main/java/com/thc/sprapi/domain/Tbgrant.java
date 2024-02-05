package com.thc.sprapi.domain;

import com.thc.sprapi.dto.TbgrantDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
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
public class Tbgrant extends AuditingFields {

    @Setter @Column(nullable = false) private String title; // 제목
    @Setter @Column(nullable = false) private String cate; // 구분
    @Setter @Column(nullable = false, length = 10000) private String content; // 본문

    protected Tbgrant(){}
    private Tbgrant(String title, String cate, String content) {
        this.title = title;
        this.cate = cate;
        this.content = content;
    }
    public static Tbgrant of(String title, String cate, String content) {
        return new Tbgrant(title, cate, content);
    }

    public TbgrantDto.TbgrantAfterCreateDto toAfterCreateDto() {
        return TbgrantDto.TbgrantAfterCreateDto.builder()
                .id(super.getId())
                .build();
    }
    public TbgrantDto.TbgrantAfterUpdateDto toAfterUpdateDto() {
        return TbgrantDto.TbgrantAfterUpdateDto.builder()
                .id(super.getId())
                .deleted(super.getDeleted())
                .title(getTitle())
                .cate(getCate())
                .content(getContent())
                .build();
    }

}
