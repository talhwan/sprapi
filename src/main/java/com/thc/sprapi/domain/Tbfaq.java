package com.thc.sprapi.domain;

import com.thc.sprapi.dto.TbfaqDto;
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
public class Tbfaq extends AuditingFields {

    @Setter @Column(nullable = false) private String title; // 제목
    @Setter @Column(nullable = false) private int sequence; // 순서
    @Setter @Column(nullable = false) private String cate; // 구분
    @Setter @Column(nullable = false, length=2000000) @Lob private String content; // 본문

    protected Tbfaq(){}
    private Tbfaq(String title, int sequence, String cate, String content) {
        this.title = title;
        this.sequence = sequence;
        this.cate = cate;
        this.content = content;
    }
    public static Tbfaq of(String title, int sequence, String cate, String content) {
        return new Tbfaq(title, sequence, cate, content);
    }

    public TbfaqDto.TbfaqAfterCreateDto toAfterCreateDto() {
        return TbfaqDto.TbfaqAfterCreateDto.builder()
                .id(super.getId())
                .build();
    }
    public TbfaqDto.TbfaqAfterUpdateDto toAfterUpdateDto() {
        return TbfaqDto.TbfaqAfterUpdateDto.builder()
                .id(super.getId())
                .deleted(super.getDeleted())
                .title(getTitle())
                .sequence(getSequence())
                .cate(getCate())
                .content(getContent())
                .build();
    }

}
