package com.thc.sprapi.domain;

import com.thc.sprapi.dto.TbboardDto;
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
public class Tbboard extends AuditingFields {

    @Setter @Column(nullable = false) private String title; // 제목
    @Setter @Column(nullable = false, length = 10000) private String content; // 본문
    @Setter @Column(nullable = false) private String tbuserId;

    protected Tbboard(){}
    private Tbboard(String title, String content, String tbuserId) {
        this.title = title;
        this.content = content;
        this.tbuserId = tbuserId;
    }
    public static Tbboard of(String title, String content, String tbuserId) {
        return new Tbboard(title, content, tbuserId);
    }

    public TbboardDto.TbboardAfterCreateDto toAfterCreateDto() {
        return TbboardDto.TbboardAfterCreateDto.builder()
                .id(super.getId())
                .build();
    }
    public TbboardDto.TbboardAfterUpdateDto toAfterUpdateDto() {
        return TbboardDto.TbboardAfterUpdateDto.builder()
                .id(super.getId())
                .deleted(super.getDeleted())
                .title(getTitle())
                .content(getContent())
                .build();
    }

}
