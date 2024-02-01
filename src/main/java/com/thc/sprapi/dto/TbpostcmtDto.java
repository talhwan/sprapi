package com.thc.sprapi.dto;

import com.thc.sprapi.domain.Tbpostcmt;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

public class TbpostcmtDto {
	@Schema
	@Builder
	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class TbpostcmtCreateDto {
		@Schema(description = "tbpostId", example="tbpostId")
		@NotNull
		@NotEmpty
		@Size(max=100)
		private String tbpostId;
		@Schema(description = "tbuserId", example="tbuserId")
		@NotNull
		@NotEmpty
		private String tbuserId;
		@Schema(description = "content", example="content")
		@NotNull
		@NotEmpty
		@Size(max=200)
		private String content;

		public Tbpostcmt toEntity() {
			return Tbpostcmt.of(tbpostId, tbuserId, content);
		}
	}
	@Schema
	@Builder
	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class TbpostcmtAfterCreateDto {
		@Schema(description = "id", example="length32textnumber")
		private String id;
	}

	@Schema
	@Builder
	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class TbpostcmtUpdateDto {
		@Schema(description = "id", example="length32textnumber")
		@NotNull
		@NotEmpty
		@Size(max=32)
		private String id;
		@Schema(description = "deleted", example="Y")
		@Size(max=1)
		private String deleted;

		@Schema(description = "tbpostId", example="tbpostId")
		@Size(max=100)
		private String tbpostId;
		@Schema(description = "tbuserId", example="tbuserId")
		@Size(max=100)
		private String tbuserId;
		@Schema(description = "content", example="content")
		@Size(max=200)
		private String content;
	}
	@Schema
	@Builder
	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class TbpostcmtAfterUpdateDto {
		@Schema(description = "id", example="length32textnumber")
		private String id;
		@Schema(description = "deleted", example="Y")
		private String deleted;
		@Schema(description = "tbpostId", example="tbpostId")
		private String tbpostId;
		@Schema(description = "tbuserId", example="tbuserId")
		private String tbuserId;
		@Schema(description = "content", example="content")
		private String content;
	}

	@Schema
	@Builder
	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class TbpostcmtSelectDto extends CommonSelectDto{
		@Schema(description = "tbpostId", example="tbpostId")
		private String tbpostId;
		@Schema(description = "tbuserId", example="tbpostId")
		private String tbuserId;
		@Schema(description = "tbuserNick", example="tbuserNick")
		private String tbuserNick;
		@Schema(description = "tbuserMpic", example="tbuserMpic")
		private String tbuserMpic;
		@Schema(description = "content", example="content")
		private String content;
	}

	@Schema
	@Builder
	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class TbpostcmtListDto {
		@Schema(description = "deleted", example="N")
		private String deleted;

		@Schema(description = "tbpostId", example="tbpostId")
		private String tbpostId;
		@Schema(description = "tbuserId", example="tbpostId")
		private String tbuserId;
		@Schema(description = "content", example="content")
		private String content;
	}

	@Schema
	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class TbpostcmtPagedListDto extends CommonPagedListDto {
		@Schema(description = "deleted", example="N")
		private String deleted;

		@Schema(description = "tbpostId", example="tbpostId")
		private String tbpostId;
		@Schema(description = "tbuserId", example="tbpostId")
		private String tbuserId;
		@Schema(description = "content", example="content")
		private String content;
	}
	@Schema
	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class TbpostcmtMoreListDto extends CommonMoreListDto {
		@Schema(description = "deleted", example="N")
		private String deleted;

		@Schema(description = "tbpostId", example="tbpostId")
		private String tbpostId;
		@Schema(description = "tbuserId", example="tbpostId")
		private String tbuserId;
		@Schema(description = "content", example="content")
		private String content;
	}
}