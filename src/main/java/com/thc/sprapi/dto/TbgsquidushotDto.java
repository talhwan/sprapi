package com.thc.sprapi.dto;

import com.thc.sprapi.domain.Tbgsquidushot;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

public class TbgsquidushotDto {
	@Schema
	@Builder
	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class TbgsquidushotCreateDto extends CommonGrantDto{
		@Schema(description = "tbgsquiduserId", example="tbgsquiduserId")
		@NotNull
		@NotEmpty
		private String tbgsquiduserId;
		@Schema(description = "level", example="level")
		@NotNull
		@NotEmpty
		private String level;
		@Schema(description = "content", example="point")
		@NotNull
		@NotEmpty
		private String content;

		public Tbgsquidushot toEntity() {
			return Tbgsquidushot.of(tbgsquiduserId, level, content);
		}
	}
	@Schema
	@Builder
	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class TbgsquidushotAfterCreateDto {
		@Schema(description = "id", example="length32textnumber")
		private String id;
	}

	@Schema
	@Builder
	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class TbgsquidushotUpdateDto extends CommonGrantDto{
		@Schema(description = "id", example="length32textnumber")
		@NotNull
		@NotEmpty
		@Size(max=32)
		private String id;
		@Schema(description = "deleted", example="Y")
		@Size(max=1)
		private String deleted;

		@Schema(description = "tbgsquiduserId", example="tbgsquiduserId")
		private String tbgsquiduserId;
		@Schema(description = "level", example="level")
		private String level;
		@Schema(description = "content", example="content")
		private String content;
	}
	@Schema
	@Builder
	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class TbgsquidushotAfterUpdateDto {
		@Schema(description = "id", example="length32textnumber")
		private String id;
		@Schema(description = "deleted", example="Y")
		private String deleted;
		@Schema(description = "tbgsquiduserId", example="tbgsquiduserId")
		private String tbgsquiduserId;
		@Schema(description = "level", example="level")
		private String level;
		@Schema(description = "content", example="content")
		private String content;
	}

	@Schema
	@Builder
	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class TbgsquidushotSelectDto extends CommonSelectDto{
		@Schema(description = "tbgsquiduserId", example="tbgsquidId")
		private String tbgsquiduserId;
		@Schema(description = "tbuserId", example="tbuserId")
		private String tbuserId;
		@Schema(description = "tbuserUsername", example="tbuserUsername")
		private String tbuserUsername;
		@Schema(description = "tbgsquiduserNick", example="tbgsquiduserNick")
		private String tbuserNick;
		@Schema(description = "tbgsquiduserMpic", example="tbgsquiduserMpic")
		private String tbuserMpic;

		@Schema(description = "level", example="level")
		private String level;
		@Schema(description = "content", example="content")
		private String content;
		@Schema(description = "hasgrant", example="hasgrant")
		private String hasgrant;
	}

	@Schema
	@Builder
	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class TbgsquidushotListDto extends CommonGrantDto{
		@Schema(description = "deleted", example="N")
		private String deleted;

		@Schema(description = "tbgsquidId", example="tbgsquidId")
		private String tbgsquidId;
		@Schema(description = "tbgsquiduserId", example="tbgsquidId")
		private String tbgsquiduserId;
		@Schema(description = "level", example="level")
		private String level;
		@Schema(description = "content", example="content")
		private String content;
	}

	@Schema
	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class TbgsquidushotPagedListDto extends CommonPagedListDto {
		@Schema(description = "deleted", example="N")
		private String deleted;

		@Schema(description = "tbgsquidId", example="tbgsquidId")
		private String tbgsquidId;
		@Schema(description = "tbgsquiduserId", example="tbgsquidId")
		private String tbgsquiduserId;
		@Schema(description = "level", example="level")
		private String level;
		@Schema(description = "content", example="content")
		private String content;
	}
	@Schema
	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class TbgsquidushotMoreListDto extends CommonMoreListDto {
		@Schema(description = "deleted", example="N")
		private String deleted;

		@Schema(description = "tbgsquidId", example="tbgsquidId")
		private String tbgsquidId;
		@Schema(description = "tbgsquiduserId", example="tbgsquidId")
		private String tbgsquiduserId;
		@Schema(description = "level", example="level")
		private String level;
		@Schema(description = "content", example="content")
		private String content;

		@Schema(description = "hasgrant", example="")
		private String hasgrant;
	}
}