package com.thc.sprapi.dto;

import com.thc.sprapi.domain.Tbgrantpart;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

public class TbgrantpartDto {

	public static String[][] types = {
		{"tbgrant","접근권한"}
		,{"tbuser", "사용자"}

		,{"tbnotice", "공지사항"}
		,{"tbfaq", "FAQ"}
		,{"tbbanner", "배너"}
		,{"tbpopup", "팝업"}
		,{"tbpost", "게시글"}
			
		,{"tbcrew", "가맹점"}

		,{"tbgsquid", "오징어게임"}
	};

	@Schema
	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class TbgrantpartToggleDto extends TbgrantpartCreateDto{
		@Schema(description = "way", example="way")
		@NotNull
		@NotEmpty
		private boolean way;
		public Tbgrantpart toEntity() {
			return Tbgrantpart.of(super.tbgrantId, super.type, super.content);
		}
	}
	@Schema
	@Builder
	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class TbgrantpartCreateDto extends CommonGrantDto{
		@Schema(description = "tbgrantId", example="tbgrantId")
		@NotNull
		@NotEmpty
		@Size(max=100)
		private String tbgrantId;
		@Schema(description = "type", example="type")
		@NotNull
		@NotEmpty
		private String type;
		@Schema(description = "content", example="content")
		@NotNull
		@NotEmpty
		@Size(max=200)
		private String content;

		public Tbgrantpart toEntity() {
			return Tbgrantpart.of(tbgrantId, type, content);
		}
	}
	@Schema
	@Builder
	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class TbgrantpartAfterCreateDto {
		@Schema(description = "id", example="length32textnumber")
		private String id;
	}

	@Schema
	@Builder
	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class TbgrantpartUpdateDto extends CommonGrantDto{
		@Schema(description = "id", example="length32textnumber")
		@NotNull
		@NotEmpty
		@Size(max=32)
		private String id;
		@Schema(description = "deleted", example="Y")
		@Size(max=1)
		private String deleted;

		@Schema(description = "tbgrantId", example="tbgrantId")
		@Size(max=100)
		private String tbgrantId;
		@Schema(description = "type", example="type")
		private String type;
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
	public static class TbgrantpartAfterUpdateDto {
		@Schema(description = "id", example="length32textnumber")
		private String id;
		@Schema(description = "deleted", example="Y")
		private String deleted;
		@Schema(description = "tbgrantId", example="tbgrantId")
		@Size(max=100)
		private String tbgrantId;
		@Schema(description = "type", example="type")
		private String type;
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
	public static class TbgrantpartSelectDto extends CommonSelectDto{
		@Schema(description = "tbgrantId", example="tbgrantId")
		private String tbgrantId;
		@Schema(description = "type", example="type")
		private String type;
		@Schema(description = "content", example="content")
		private String content;
	}

	@Schema
	@Builder
	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class TbgrantpartListDto extends CommonGrantDto{
		@Schema(description = "deleted", example="N")
		private String deleted;

		@Schema(description = "tbgrantId", example="tbgrant_id")
		private String tbgrantId;
		@Schema(description = "type", example="type")
		private String type;
		@Schema(description = "content", example="content")
		private String content;
	}

	@Schema
	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class TbgrantpartPagedListDto extends CommonPagedListDto {
		@Schema(description = "deleted", example="N")
		private String deleted;

		@Schema(description = "tbgrantId", example="tbgrantId")
		private String tbgrantId;
		@Schema(description = "type", example="type")
		private String type;
		@Schema(description = "content", example="content")
		private String content;
	}
	@Schema
	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class TbgrantpartMoreListDto extends CommonMoreListDto {
		@Schema(description = "deleted", example="N")
		private String deleted;

		@Schema(description = "tbgrantId", example="tbgrantId")
		private String tbgrantId;
		@Schema(description = "type", example="type")
		private String type;
		@Schema(description = "content", example="content")
		private String content;
	}
}