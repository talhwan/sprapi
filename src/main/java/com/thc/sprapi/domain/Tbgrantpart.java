package com.thc.sprapi.domain;

import com.thc.sprapi.dto.TbgrantpartDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@ToString(callSuper = true)
@Table(indexes = {
        @Index(columnList = "tbgrantId")
        ,@Index(columnList = "createdAt")
        ,@Index(columnList = "modifiedAt")
},uniqueConstraints={
        @UniqueConstraint(
                name="UQ_tbgrantpart_tbgrant_type_content"
                ,columnNames={"tbgrantId", "type", "content"}
        )
})
@Entity
public class Tbgrantpart extends AuditingFields {

    @Setter @Column(nullable = false) private String tbgrantId; // 게시판의 pk
    @Setter @Column(nullable = false) private String type;
    @Setter @Column(nullable = false, length = 10000) private String content;

    protected Tbgrantpart(){}
    private Tbgrantpart(String tbgrantId, String type, String content) {
        this.tbgrantId = tbgrantId;
        this.type = type;
        this.content = content;
    }
    public static Tbgrantpart of(String tbgrantId, String type, String content) {
        return new Tbgrantpart(tbgrantId, type, content);
    }
    /*
    public static Tbgrantpart of(String tbgrantId) {
        return new Tbgrantpart(tbgrantId, "", "");
    }
     */

    public TbgrantpartDto.TbgrantpartAfterCreateDto toAfterCreateDto() {
        return TbgrantpartDto.TbgrantpartAfterCreateDto.builder()
                .id(super.getId())
                .build();
    }
    public TbgrantpartDto.TbgrantpartAfterUpdateDto toAfterUpdateDto() {
        return TbgrantpartDto.TbgrantpartAfterUpdateDto.builder()
                .id(super.getId())
                .deleted(super.getDeleted())
                .tbgrantId(getTbgrantId())
                .type(getType())
                .content(getContent())
                .build();
    }

}
