package com.thc.sprapi.domain;

import com.thc.sprapi.dto.TbpicDto;
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
public class Tbpic extends AuditingFields {

    @Setter @Column(nullable = false) private String tbboardId; // 게시판의 pk
    @Setter @Column(nullable = false, length = 10000) private String content;
    @Setter @Column(nullable = false) private String type;

    protected Tbpic(){}
    private Tbpic(String tbboardId, String content, String type) {
        this.tbboardId = tbboardId;
        this.content = content;
        this.type = type;
    }
    public static Tbpic of(String tbboardId, String content, String type) {
        return new Tbpic(tbboardId, content, type);
    }
    /*
    public static Tbpic of(String tbboardId) {
        return new Tbpic(tbboardId, "", "");
    }
     */

    public TbpicDto.TbpicAfterCreateDto toAfterCreateDto() {
        return TbpicDto.TbpicAfterCreateDto.builder()
                .id(super.getId())
                .build();
    }
    public TbpicDto.TbpicAfterUpdateDto toAfterUpdateDto() {
        return TbpicDto.TbpicAfterUpdateDto.builder()
                .id(super.getId())
                .deleted(super.getDeleted())
                .tbboardId(getTbboardId())
                .content(getContent())
                .type(getType())
                .build();
    }

}
