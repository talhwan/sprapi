package com.thc.sprapi.domain;

import com.thc.sprapi.dto.TbpostfileDto;
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
public class Tbpostfile extends AuditingFields {

    @Setter @Column(nullable = false) private String tbpostId; // 게시판의 pk
    @Setter @Column(nullable = false) private String type;
    @Setter @Column(nullable = false, length = 10000) private String content;

    protected Tbpostfile(){}
    private Tbpostfile(String tbpostId, String type, String content) {
        this.tbpostId = tbpostId;
        this.type = type;
        this.content = content;
    }
    public static Tbpostfile of(String tbpostId, String type, String content) {
        return new Tbpostfile(tbpostId, type, content);
    }
    /*
    public static Tbpostfile of(String tbpostId) {
        return new Tbpostfile(tbpostId, "", "");
    }
     */

    public TbpostfileDto.TbpostfileAfterCreateDto toAfterCreateDto() {
        return TbpostfileDto.TbpostfileAfterCreateDto.builder()
                .id(super.getId())
                .build();
    }
    public TbpostfileDto.TbpostfileAfterUpdateDto toAfterUpdateDto() {
        return TbpostfileDto.TbpostfileAfterUpdateDto.builder()
                .id(super.getId())
                .deleted(super.getDeleted())
                .tbpostId(getTbpostId())
                .type(getType())
                .content(getContent())
                .build();
    }

}
