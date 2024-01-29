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
    //@Setter @Column(nullable = false) private String tbuserId;

    protected Tbboard(){}
    private Tbboard(String title, String content) {
        this.title = title;
        this.content = content;
    }
    public static Tbboard of(String title, String content) {
        return new Tbboard(title, content);
    }
    public static Tbboard of(String title) {
        return new Tbboard(title, "");
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
