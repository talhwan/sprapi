package com.thc.sprapi.domain;

import com.thc.sprapi.dto.TbpostcmtDto;
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
        @Index(columnList = "tbpostId")
        ,@Index(columnList = "createdAt")
        ,@Index(columnList = "modifiedAt")
})
@Entity
public class Tbpostcmt extends AuditingFields {

    @Setter @Column(nullable = false) private String tbpostId; // 게시판의 pk
    @Setter @Column(nullable = false) private String tbuserId; //작성자!
    @Setter @Column(nullable = false, length = 10000) private String content;

    protected Tbpostcmt(){}
    private Tbpostcmt(String tbpostId, String tbuserId, String content) {
        this.tbpostId = tbpostId;
        this.tbuserId = tbuserId;
        this.content = content;
    }
    public static Tbpostcmt of(String tbpostId, String tbuserId, String content) {
        return new Tbpostcmt(tbpostId, tbuserId, content);
    }

    public TbpostcmtDto.TbpostcmtAfterCreateDto toAfterCreateDto() {
        return TbpostcmtDto.TbpostcmtAfterCreateDto.builder()
                .id(super.getId())
                .build();
    }
    public TbpostcmtDto.TbpostcmtAfterUpdateDto toAfterUpdateDto() {
        return TbpostcmtDto.TbpostcmtAfterUpdateDto.builder()
                .id(super.getId())
                .deleted(super.getDeleted())
                .tbpostId(getTbpostId())
                .tbuserId(getTbuserId())
                .content(getContent())
                .build();
    }

}
