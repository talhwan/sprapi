package com.thc.sprapi.domain;

import com.thc.sprapi.dto.TbpostDto;
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
public class Tbpost extends AuditingFields {

    @Setter @Column(nullable = false) private String title; // 제목
    @Setter @Column(nullable = false) private String cate; // 구분
    @Setter @Column(nullable = false, length = 10000) private String content; // 본문
    @Setter @Column(nullable = false) private String tbuserId; //작성자!

    protected Tbpost(){}
    private Tbpost(String title, String cate, String content, String tbuserId) {
        this.title = title;
        this.cate = cate;
        this.content = content;
        this.tbuserId = tbuserId;
    }
    public static Tbpost of(String title, String cate, String content, String tbuserId) {
        return new Tbpost(title, cate, content, tbuserId);
    }

    public TbpostDto.TbpostAfterCreateDto toAfterCreateDto() {
        return TbpostDto.TbpostAfterCreateDto.builder()
                .id(super.getId())
                .build();
    }
    public TbpostDto.TbpostAfterUpdateDto toAfterUpdateDto() {
        return TbpostDto.TbpostAfterUpdateDto.builder()
                .id(super.getId())
                .deleted(super.getDeleted())
                .title(getTitle())
                .cate(getCate())
                .content(getContent())
                .build();
    }

}
