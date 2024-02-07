package com.thc.sprapi.dto;

import com.thc.sprapi.domain.Tbgsquiduser;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

public class TbgsquiduserDto {
	@Schema
	@Builder
	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class TbgsquiduserCreateDto extends CommonGrantDto{
		@Schema(description = "tbgsquidId", example="tbgsquidId")
		@NotNull
		@NotEmpty
		@Size(max=100)
		private String tbgsquidId;
		@Schema(description = "tbuserId", example="tbuserId")
		@NotNull
		@NotEmpty
		private String tbuserId;
		public Tbgsquiduser toEntity() {
			return Tbgsquiduser.of(tbgsquidId, tbuserId, "0", "0");
		}
	}
	@Schema
	@Builder
	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class TbgsquiduserAfterCreateDto {
		@Schema(description = "id", example="length32textnumber")
		private String id;
	}

	@Schema
	@Builder
	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class TbgsquiduserUpdateDto extends CommonGrantDto{
		@Schema(description = "id", example="length32textnumber")
		@NotNull
		@NotEmpty
		@Size(max=32)
		private String id;
		@Schema(description = "deleted", example="Y")
		@Size(max=1)
		private String deleted;

		@Schema(description = "tbgsquidId", example="tbgsquidId")
		private String tbgsquidId;
		@Schema(description = "tbuserId", example="tbuserId")
		private String tbuserId;
		@Schema(description = "process", example="process")
		private String process;
		@Schema(description = "content", example="content")
		private String content;
	}
	@Schema
	@Builder
	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class TbgsquiduserAfterUpdateDto {
		@Schema(description = "id", example="length32textnumber")
		private String id;
		@Schema(description = "deleted", example="Y")
		private String deleted;
		@Schema(description = "tbgsquidId", example="tbgsquidId")
		private String tbgsquidId;
		@Schema(description = "tbuserId", example="tbuserId")
		private String tbuserId;
		@Schema(description = "process", example="process")
		private String process;
	}

	@Schema
	@Builder
	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class TbgsquiduserSelectDto extends CommonSelectDto{
		@Schema(description = "tbgsquidId", example="tbgsquidId")
		private String tbgsquidId;
		@Schema(description = "tbuserId", example="tbgsquidId")
		private String tbuserId;
		@Schema(description = "process", example="process")
		private String process;
		@Schema(description = "content", example="content")
		private String content;
		@Schema(description = "alive", example="alive")
		private String alive;
		@Schema(description = "tbgsquidTitle", example="tbgsquidTitle")
		private String tbgsquidTitle;
		@Schema(description = "tbgsquidProcess", example="tbgsquidProcess")
		private String tbgsquidProcess;
		@Schema(description = "tbgsquidGoal", example="tbgsquidGoal")
		private String tbgsquidGoal;
		@Schema(description = "tbuserUsername", example="tbuserUsername")
		private String tbuserUsername;
		@Schema(description = "tbuserNick", example="tbuserNick")
		private String tbuserNick;
		@Schema(description = "tbuserMpic", example="tbuserMpic")
		private String tbuserMpic;

		@Schema(description = "hasgrant", example="hasgrant")
		private String hasgrant;
	}

	@Schema
	@Builder
	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class TbgsquiduserListDto extends CommonGrantDto{
		@Schema(description = "deleted", example="N")
		private String deleted;

		@Schema(description = "tbgsquidId", example="tbgsquidId")
		private String tbgsquidId;
		@Schema(description = "tbuserId", example="tbgsquidId")
		private String tbuserId;
		@Schema(description = "process", example="process")
		private String process;
	}

	@Schema
	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class TbgsquiduserPagedListDto extends CommonPagedListDto {
		@Schema(description = "deleted", example="N")
		private String deleted;

		@Schema(description = "tbgsquidId", example="tbgsquidId")
		private String tbgsquidId;
		@Schema(description = "tbuserId", example="tbgsquidId")
		private String tbuserId;
		@Schema(description = "process", example="process")
		private String process;
	}
	@Schema
	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class TbgsquiduserMoreListDto extends CommonMoreListDto {
		@Schema(description = "deleted", example="N")
		private String deleted;

		@Schema(description = "tbgsquidId", example="tbgsquidId")
		private String tbgsquidId;
		@Schema(description = "tbuserId", example="tbgsquidId")
		private String tbuserId;
		@Schema(description = "process", example="process")
		private String process;

		@Schema(description = "hasgrant", example="")
		private String hasgrant;
		@Schema(description = "tbuserUsername", example="")
		private String tbuserUsername;
		@Schema(description = "tbuserNick", example="")
		private String tbuserNick;
	}
}