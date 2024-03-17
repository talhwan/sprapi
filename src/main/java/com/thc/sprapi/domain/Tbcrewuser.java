package com.thc.sprapi.domain;

import com.thc.sprapi.dto.TbcrewuserDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@ToString(callSuper = true)
@Table(indexes = {
        @Index(columnList = "tbcrewId")
        ,@Index(columnList = "createdAt")
        ,@Index(columnList = "modifiedAt")
        }
        ,uniqueConstraints={
        @UniqueConstraint(
                name="UQ_tbcrewuser_tbcrew_tbuser"
                ,columnNames={"tbcrewId", "tbuserId"}
        )
}
)
@Entity
public class Tbcrewuser extends AuditingFields {

    @Setter @Column(nullable = false) private String tbcrewId;
    @Setter @Column(nullable = false) private String tbuserId;
    @Setter @Column(nullable = false) private String process;
    @Setter @Column(nullable = false) private String cate;
    @Setter @Column(nullable = false) private String content;

    protected Tbcrewuser(){}
    private Tbcrewuser(String tbcrewId, String tbuserId, String process, String cate, String content) {
        this.tbcrewId = tbcrewId;
        this.tbuserId = tbuserId;
        this.process = process;
        this.cate = cate;
        this.content = content;
    }
    public static Tbcrewuser of(String tbcrewId, String tbuserId, String process, String cate, String content) {
        return new Tbcrewuser(tbcrewId, tbuserId, process, cate, content);
    }

    public TbcrewuserDto.TbcrewuserAfterCreateDto toAfterCreateDto() {
        return TbcrewuserDto.TbcrewuserAfterCreateDto.builder()
                .id(super.getId())
                .build();
    }
    public TbcrewuserDto.TbcrewuserAfterUpdateDto toAfterUpdateDto() {
        return TbcrewuserDto.TbcrewuserAfterUpdateDto.builder()
                .id(super.getId())
                .deleted(super.getDeleted())
                .tbcrewId(getTbcrewId())
                .tbuserId(getTbuserId())
                .process(getProcess())
                .cate(getCate())
                .build();
    }

}
