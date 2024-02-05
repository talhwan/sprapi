package com.thc.sprapi.domain;

import com.thc.sprapi.dto.TbgrantuserDto;
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
        }
        ,uniqueConstraints={
        @UniqueConstraint(
                name="UQ_tbgrantuser_tbgrant_tbuser"
                ,columnNames={"tbgrantId", "tbuserId"}
        )
}
)
@Entity
public class Tbgrantuser extends AuditingFields {

    @Setter @Column(nullable = false) private String tbgrantId; // 게시판의 pk
    @Setter @Column(nullable = false) private String tbuserId; //작성자!

    protected Tbgrantuser(){}
    private Tbgrantuser(String tbgrantId, String tbuserId) {
        this.tbgrantId = tbgrantId;
        this.tbuserId = tbuserId;
    }
    public static Tbgrantuser of(String tbgrantId, String tbuserId) {
        return new Tbgrantuser(tbgrantId, tbuserId);
    }

    public TbgrantuserDto.TbgrantuserAfterCreateDto toAfterCreateDto() {
        return TbgrantuserDto.TbgrantuserAfterCreateDto.builder()
                .id(super.getId())
                .build();
    }
    public TbgrantuserDto.TbgrantuserAfterUpdateDto toAfterUpdateDto() {
        return TbgrantuserDto.TbgrantuserAfterUpdateDto.builder()
                .id(super.getId())
                .deleted(super.getDeleted())
                .tbgrantId(getTbgrantId())
                .tbuserId(getTbuserId())
                .build();
    }

}
