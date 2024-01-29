package com.thc.sprapi.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.thc.sprapi.dto.TbuserDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
//@ToString(callSuper = true)
@ToString(exclude = {"tbuserRoleType"}, callSuper = true)
@Table(
        indexes = {
        @Index(name = "IDX_tbuser_createdAt", columnList = "createdAt")
        ,@Index(name = "IDX_tbuser_modifiedAt", columnList = "modifiedAt")
        ,@Index(name = "IDX_tbuser_process", columnList = "process")
        }
        , uniqueConstraints= {
            @UniqueConstraint(name = "UQ_tbuser_nick", columnNames = {"nick"})
        }
        )
@Entity
public class Tbuser extends AuditingFields {

    @Setter @Column(nullable = false) private String username;
    @JsonIgnore @Setter @Column(nullable = false) private String password;
    @Setter @Column(nullable = false) private String nick;
    @Setter @Column(nullable = false) private String sfrom;
    @Setter @Column(nullable = false) private String process;
    @Setter @Column(nullable = true) private String name;
    @Setter @Column(nullable = true) private String phone;
    @Setter @Column(nullable = true) private String mpic;
    @Setter @Column(nullable = true, length = 10000) private String content;

    //fetch 타입 바꾸고, toString 순환 참조 수정
    @OneToMany(mappedBy = "tbuser", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private List<TbuserRoleType> tbuserRoleType = new ArrayList<>();

    public List<TbuserRoleType> getRoleList(){
        if(!this.tbuserRoleType.isEmpty()){
            return tbuserRoleType;
        }
        return new ArrayList<>();
    }

    protected Tbuser(){}
    private Tbuser(String username, String password, String nick, String sfrom, String process) {
        this.username = username;
        this.password = password;
        this.nick = nick;
        this.sfrom = sfrom;
        this.process = process;
    }
    public static Tbuser of(String username, String password, String nick, String sfrom, String process) {
        return new Tbuser(username, password, nick, sfrom, process);
    }
    public static Tbuser of(String username, String password) {
        return new Tbuser(username, password, "", "", "0");
    }

    public TbuserDto.TbuserAfterCreateDto toAfterCreateDto() {
        return TbuserDto.TbuserAfterCreateDto.builder()
                .id(super.getId())
                .build();
    }
    public TbuserDto.TbuserAfterUpdateDto toAfterUpdateDto() {
        return TbuserDto.TbuserAfterUpdateDto.builder()
                .id(super.getId())
                .deleted(super.getDeleted())
                .build();
    }

}
