package com.thc.sprapi.domain;

import com.thc.sprapi.dto.TbgsquidDto;
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
        @Index(columnList = "title")
        ,@Index(columnList = "createdAt")
        ,@Index(columnList = "modifiedAt")
})
@Entity
public class Tbgsquid extends AuditingFields {

    @Setter @Column(nullable = false) private String title; // 제목
    @Setter @Column(nullable = false) private String process;
    @Setter @Column(nullable = false) private String startdate;
    @Setter @Column(nullable = false) private String starttime;
    @Setter @Column(nullable = false) private int goal;
    @Setter @Column(nullable = false, length = 10000) private String content; // 본문

    protected Tbgsquid(){}
    private Tbgsquid(String title, String process, String startdate, String starttime, int goal, String content) {
        this.title = title;
        this.process = process;
        this.startdate = startdate;
        this.starttime = starttime;
        this.goal = goal;
        this.content = content;
    }
    public static Tbgsquid of(String title, String startdate, String starttime, int goal, String content) {
        return new Tbgsquid(title, "0", startdate, starttime, goal, content);
    }

    public TbgsquidDto.TbgsquidAfterCreateDto toAfterCreateDto() {
        return TbgsquidDto.TbgsquidAfterCreateDto.builder()
                .id(super.getId())
                .build();
    }
    public TbgsquidDto.TbgsquidAfterUpdateDto toAfterUpdateDto() {
        return TbgsquidDto.TbgsquidAfterUpdateDto.builder()
                .id(super.getId())
                .deleted(super.getDeleted())
                .title(getTitle())
                .process(getProcess())
                .startdate(getStartdate())
                .starttime(getStarttime())
                .goal(getGoal())
                .content(getContent())
                .build();
    }

}
