package com.thc.sprapi.domain;

import com.thc.sprapi.dto.TbgsquidushotDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@ToString(callSuper = true)
@Table(indexes = {
        @Index(columnList = "createdAt")
        ,@Index(columnList = "modifiedAt")
        ,@Index(columnList = "tbgsquiduserId")
        ,@Index(columnList = "level")
        ,@Index(columnList = "content")
        }
        ,uniqueConstraints={
        @UniqueConstraint(
                name="UQ_tbgsquidushot_tbgsquid_tbgsquiduser_level"
                ,columnNames={"tbgsquiduserId", "level"}
        )
}
)
@Entity
public class Tbgsquidushot extends AuditingFields {

    @Setter @Column(nullable = false) private String tbgsquiduserId;
    @Setter @Column(nullable = false) private String level;
    @Setter @Column(nullable = false) private String content;

    protected Tbgsquidushot(){}
    private Tbgsquidushot(String tbgsquiduserId, String level, String content) {
        this.tbgsquiduserId = tbgsquiduserId;
        this.level = level;
        this.content = content;
    }
    public static Tbgsquidushot of(String tbgsquiduserId, String level, String content) {
        return new Tbgsquidushot(tbgsquiduserId, level, content);
    }

    public TbgsquidushotDto.TbgsquidushotAfterCreateDto toAfterCreateDto() {
        return TbgsquidushotDto.TbgsquidushotAfterCreateDto.builder()
                .id(super.getId())
                .build();
    }
    public TbgsquidushotDto.TbgsquidushotAfterUpdateDto toAfterUpdateDto() {
        return TbgsquidushotDto.TbgsquidushotAfterUpdateDto.builder()
                .id(super.getId())
                .deleted(super.getDeleted())
                .tbgsquiduserId(getTbgsquiduserId())
                .level(getLevel())
                .content(getContent())
                .build();
    }

}
