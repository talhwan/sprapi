package com.thc.sprapi.domain;

import com.thc.sprapi.dto.TbcmtDto;
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
        @Index(columnList = "tbboardId")
        ,@Index(columnList = "createdAt")
        ,@Index(columnList = "modifiedAt")
})
@Entity
public class Tbcmt extends AuditingFields {

    @Setter @Column(nullable = false) private String tbboardId; // 게시판의 pk
    @Setter @Column(nullable = false, length = 10000) private String content;
    @Setter @Column(nullable = false) private String tbuserId; //작성자!

    protected Tbcmt(){}
    private Tbcmt(String tbboardId, String content, String tbuserId) {
        this.tbboardId = tbboardId;
        this.content = content;
        this.tbuserId = tbuserId;
    }
    public static Tbcmt of(String tbboardId, String content, String tbuserId) {
        return new Tbcmt(tbboardId, content, tbuserId);
    }

    public TbcmtDto.TbcmtAfterCreateDto toAfterCreateDto() {
        return TbcmtDto.TbcmtAfterCreateDto.builder()
                .id(super.getId())
                .build();
    }
    public TbcmtDto.TbcmtAfterUpdateDto toAfterUpdateDto() {
        return TbcmtDto.TbcmtAfterUpdateDto.builder()
                .id(super.getId())
                .deleted(super.getDeleted())
                .tbboardId(getTbboardId())
                .content(getContent())
                .build();
    }

}
