package com.thc.sprapi.domain;

import com.thc.sprapi.dto.TbpopupDto;
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
public class Tbpopup extends AuditingFields {

    @Setter @Column(nullable = false) private String title; // 제목
    @Setter @Column(nullable = false) private int sequence; // 순서
    @Setter @Column(nullable = false) private String cate; // 구분
    @Setter @Column(nullable = false) private String mpic; // 사진
    @Setter @Column(nullable = false) private String url; // URL
    @Setter @Column(nullable = false, length=2000000) @Lob private String content; // 본문

    protected Tbpopup(){}
    private Tbpopup(String title, int sequence, String cate, String mpic, String url, String content) {
        this.title = title;
        this.sequence = sequence;
        this.cate = cate;
        this.mpic = mpic;
        this.url = url;
        this.content = content;
    }
    public static Tbpopup of(String title, int sequence, String cate, String mpic, String url, String content) {
        return new Tbpopup(title, sequence, cate, mpic, url, content);
    }

    public TbpopupDto.TbpopupAfterCreateDto toAfterCreateDto() {
        return TbpopupDto.TbpopupAfterCreateDto.builder()
                .id(super.getId())
                .build();
    }
    public TbpopupDto.TbpopupAfterUpdateDto toAfterUpdateDto() {
        return TbpopupDto.TbpopupAfterUpdateDto.builder()
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
