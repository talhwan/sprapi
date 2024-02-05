package com.thc.sprapi.domain;

import java.util.ArrayList;
import java.util.List;

import com.thc.sprapi.dto.RoletypeDto;
import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "roleType")
@Entity
public class RoleType{

	@Id
	@Column(length = 32, columnDefinition = "CHAR(32)")
	private String id;

	@Column(length = 191, nullable = false, unique = true)
	private String typeName;
	
	@OneToMany(mappedBy = "roleType", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
	private List<TbuserRoleType> tbuserRoleType = new ArrayList<>();

	public static RoleType of(String id, String typeName) {
		return new RoleType(id, typeName, null);
	}

	public RoletypeDto.RoletypeSelectDto toRoletypeSelectDto() {
		return RoletypeDto.RoletypeSelectDto.builder()
				.id(id)
				.typeName(typeName)
				.build();
	}

}