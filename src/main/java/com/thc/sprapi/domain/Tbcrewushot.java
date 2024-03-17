package com.thc.sprapi.domain;

import com.thc.sprapi.dto.TbcrewushotDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@ToString(callSuper = true)
@Table(indexes = {
        @Index(columnList = "createdAt")
        ,@Index(columnList = "modifiedAt")
        ,@Index(columnList = "tbcrewuserId")
        ,@Index(columnList = "process")
        ,@Index(columnList = "content")
        }
        ,uniqueConstraints={
        @UniqueConstraint(
                name="UQ_tbcrewushot_tbcrew_tbcrewuser_process"
                ,columnNames={"tbcrewuserId", "process"}
        )
}
)
@Entity
public class Tbcrewushot extends AuditingFields {

    @Setter @Column(nullable = false) private String tbcrewuserId;
    @Setter @Column(nullable = false) private String process;
    @Setter @Column(nullable = false) private String content;

    protected Tbcrewushot(){}
    private Tbcrewushot(String tbcrewuserId, String process, String content) {
        this.tbcrewuserId = tbcrewuserId;
        this.process = process;
        this.content = content;
    }
    public static Tbcrewushot of(String tbcrewuserId, String process, String content) {
        return new Tbcrewushot(tbcrewuserId, process, content);
    }

    public TbcrewushotDto.TbcrewushotAfterCreateDto toAfterCreateDto() {
        return TbcrewushotDto.TbcrewushotAfterCreateDto.builder()
                .id(super.getId())
                .build();
    }
    public TbcrewushotDto.TbcrewushotAfterUpdateDto toAfterUpdateDto() {
        return TbcrewushotDto.TbcrewushotAfterUpdateDto.builder()
                .id(super.getId())
                .deleted(super.getDeleted())
                .tbcrewuserId(getTbcrewuserId())
                .process(getProcess())
                .content(getContent())
                .build();
    }

}
