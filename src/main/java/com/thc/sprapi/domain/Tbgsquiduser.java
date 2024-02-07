package com.thc.sprapi.domain;

import com.thc.sprapi.dto.TbgsquiduserDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@ToString(callSuper = true)
@Table(indexes = {
        @Index(columnList = "tbgsquidId")
        ,@Index(columnList = "createdAt")
        ,@Index(columnList = "modifiedAt")
        }
        ,uniqueConstraints={
        @UniqueConstraint(
                name="UQ_tbgsquiduser_tbgsquid_tbuser"
                ,columnNames={"tbgsquidId", "tbuserId"}
        )
}
)
@Entity
public class Tbgsquiduser extends AuditingFields {

    @Setter @Column(nullable = false) private String tbgsquidId;
    @Setter @Column(nullable = false) private String tbuserId;
    @Setter @Column(nullable = false) private String process;
    @Setter @Column(nullable = false) private String content;

    protected Tbgsquiduser(){}
    private Tbgsquiduser(String tbgsquidId, String tbuserId, String process, String content) {
        this.tbgsquidId = tbgsquidId;
        this.tbuserId = tbuserId;
        this.process = process;
        this.content = content;
    }
    public static Tbgsquiduser of(String tbgsquidId, String tbuserId, String process, String content) {
        return new Tbgsquiduser(tbgsquidId, tbuserId, process, content);
    }

    public TbgsquiduserDto.TbgsquiduserAfterCreateDto toAfterCreateDto() {
        return TbgsquiduserDto.TbgsquiduserAfterCreateDto.builder()
                .id(super.getId())
                .build();
    }
    public TbgsquiduserDto.TbgsquiduserAfterUpdateDto toAfterUpdateDto() {
        return TbgsquiduserDto.TbgsquiduserAfterUpdateDto.builder()
                .id(super.getId())
                .deleted(super.getDeleted())
                .tbgsquidId(getTbgsquidId())
                .tbuserId(getTbuserId())
                .process(getProcess())
                .build();
    }

}
