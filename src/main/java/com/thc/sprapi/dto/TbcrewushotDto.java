package com.thc.sprapi.dto;

import com.thc.sprapi.domain.Tbcrewushot;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

public class TbcrewushotDto {
	@Schema
	@Builder
	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class TbcrewushotCreateDto extends CommonGrantDto{
		@Schema(description = "tbcrewuserId", example="tbcrewuserId")
		@NotNull
		@NotEmpty
		private String tbcrewuserId;
		@Schema(description = "level", example="level")
		@NotNull
		@NotEmpty
		private String level;
		@Schema(description = "content", example="point")
		@NotNull
		@NotEmpty
		private String content;

		public Tbcrewushot toEntity() {
			return Tbcrewushot.of(tbcrewuserId, level, content);
		}
	}
	@Schema
	@Builder
	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class TbcrewushotAfterCreateDto {
		@Schema(description = "id", example="length32textnumber")
		private String id;
	}

	@Schema
	@Builder
	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class TbcrewushotUpdateDto extends CommonGrantDto{
		@Schema(description = "id", example="length32textnumber")
		@NotNull
		@NotEmpty
		@Size(max=32)
		private String id;
		@Schema(description = "deleted", example="Y")
		@Size(max=1)
		private String deleted;

		@Schema(description = "tbcrewuserId", example="tbcrewuserId")
		private String tbcrewuserId;
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
	public static class TbcrewushotAfterUpdateDto {
		@Schema(description = "id", example="length32textnumber")
		private String id;
		@Schema(description = "deleted", example="Y")
		private String deleted;
		@Schema(description = "tbcrewuserId", example="tbcrewuserId")
		private String tbcrewuserId;
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
	public static class TbcrewushotSelectDto extends CommonSelectDto{
		@Schema(description = "tbcrewuserId", example="tbcrewId")
		private String tbcrewuserId;
		@Schema(description = "tbuserId", example="tbuserId")
		private String tbuserId;
		@Schema(description = "tbuserUsername", example="tbuserUsername")
		private String tbuserUsername;
		@Schema(description = "tbcrewuserNick", example="tbcrewuserNick")
		private String tbuserNick;
		@Schema(description = "tbcrewuserMpic", example="tbcrewuserMpic")
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
	public static class TbcrewushotListDto extends CommonGrantDto{
		@Schema(description = "deleted", example="N")
		private String deleted;

		@Schema(description = "tbcrewId", example="tbcrewId")
		private String tbcrewId;
		@Schema(description = "tbcrewuserId", example="tbcrewId")
		private String tbcrewuserId;
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
	public static class TbcrewushotPagedListDto extends CommonPagedListDto {
		@Schema(description = "deleted", example="N")
		private String deleted;

		@Schema(description = "tbcrewId", example="tbcrewId")
		private String tbcrewId;
		@Schema(description = "tbcrewuserId", example="tbcrewId")
		private String tbcrewuserId;
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
	public static class TbcrewushotMoreListDto extends CommonMoreListDto {
		@Schema(description = "deleted", example="N")
		private String deleted;

		@Schema(description = "tbcrewId", example="tbcrewId")
		private String tbcrewId;
		@Schema(description = "tbcrewuserId", example="tbcrewId")
		private String tbcrewuserId;
		@Schema(description = "level", example="level")
		private String level;
		@Schema(description = "content", example="content")
		private String content;

		@Schema(description = "hasgrant", example="")
		private String hasgrant;
	}
}