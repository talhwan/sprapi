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

    protected Tbcmt(){}
    private Tbcmt(String tbboardId, String content) {
        this.tbboardId = tbboardId;
        this.content = content;
    }
    public static Tbcmt of(String tbboardId, String content) {
        return new Tbcmt(tbboardId, content);
    }
    public static Tbcmt of(String tbboardId) {
        return new Tbcmt(tbboardId, "");
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
