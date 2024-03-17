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
        ,@Index(columnList = "level")
        ,@Index(columnList = "content")
        }
        ,uniqueConstraints={
        @UniqueConstraint(
                name="UQ_tbcrewushot_tbcrew_tbcrewuser_level"
                ,columnNames={"tbcrewuserId", "level"}
        )
}
)
@Entity
public class Tbcrewushot extends AuditingFields {

    @Setter @Column(nullable = false) private String tbcrewuserId;
    @Setter @Column(nullable = false) private String level;
    @Setter @Column(nullable = false) private String content;

    protected Tbcrewushot(){}
    private Tbcrewushot(String tbcrewuserId, String level, String content) {
        this.tbcrewuserId = tbcrewuserId;
        this.level = level;
        this.content = content;
    }
    public static Tbcrewushot of(String tbcrewuserId, String level, String content) {
        return new Tbcrewushot(tbcrewuserId, level, content);
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
                .level(getLevel())
                .content(getContent())
                .build();
    }

}
