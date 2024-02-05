package com.thc.sprapi.dto;

import com.thc.sprapi.domain.RoleType;
import com.thc.sprapi.domain.Tbpost;
import com.thc.sprapi.domain.TbuserRoleType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.List;

public class RoletypeDto {
	@Schema
	@Builder
	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class RoletypeCreateDto{
		@Schema(description = "id", example="id")
		private String id;
		@Schema(description = "typeName", example="title")
		private String typeName;

		public RoleType toEntity() {
			return RoleType.of(id, typeName);
		}
	}
	@Schema
	@Builder
	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class RoletypeSelectDto{
		@Schema(description = "id", example="id")
		private String id;
		@Schema(description = "typeName", example="title")
		private String typeName;
		/*
		@Schema(description = "listTbuserRoleType", example="")
		private List<TbuserRoleType> listTbuserRoleType;
		*/
	}
}