package com.thc.sprapi.dto;

import com.thc.sprapi.domain.Tbpic;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

public class TbpicDto {
	
	@Schema
	@Builder
	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class TbpicCreateDto {
		@Schema(description = "tbboardId", example="tbboardId")
		@NotNull
		@NotEmpty
		@Size(max=100)
		private String tbboardId;

		@Schema(description = "content", example="content")
		@NotNull
		@NotEmpty
		@Size(max=200)
		private String content;

		@Schema(description = "type", example="type")
		@NotNull
		@NotEmpty
		private String type;

		public Tbpic toEntity() {
			return Tbpic.of(tbboardId, content, type);
		}
	}
	@Schema
	@Builder
	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class TbpicAfterCreateDto {
		@Schema(description = "id", example="length32textnumber")
		private String id;
	}

	@Schema
	@Builder
	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class TbpicUpdateDto {
		@Schema(description = "id", example="length32textnumber")
		@NotNull
		@NotEmpty
		@Size(max=32)
		private String id;

		@Schema(description = "tbboardId", example="tbboardId")
		@Size(max=100)
		private String tbboardId;

		@Schema(description = "deleted", example="Y")
		@Size(max=1)
		private String deleted;

		@Schema(description = "content", example="content")
		@Size(max=200)
		private String content;

		@Schema(description = "type", example="type")
		private String type;
	}
	@Schema
	@Builder
	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class TbpicAfterUpdateDto {
		@Schema(description = "id", example="length32textnumber")
		private String id;
		@Schema(description = "deleted", example="Y")
		private String deleted;
		@Schema(description = "tbboardId", example="tbboardId")
		@Size(max=100)
		private String tbboardId;
		@Schema(description = "content", example="content")
		@Size(max=200)
		private String content;
		@Schema(description = "type", example="type")
		private String type;
	}

	@Schema
	@Builder
	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class TbpicSelectDto {

		@Schema(description = "id", example="id")
		private String id;
		@Schema(description = "tbboard_id", example="tbboard_id")
		private String tbboard_id;
		@Schema(description = "content", example="content")
		private String content;
		@Schema(description = "type", example="type")
		private String type;
		@Schema(description = "deleted", example="N")
		private String deleted;
		@Schema(description = "created_at", example="2024-01-01 00:00:00.000000")
		private String created_at;
		@Schema(description = "modified_at", example="2024-01-01 00:00:00.000000")
		private String modified_at;

	}

	@Schema
	@Builder
	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class TbpicListDto {
		@Schema(description = "tbboard_id", example="tbboardId")
		private String tbboard_id;
		@Schema(description = "content", example="content")
		private String content;
		@Schema(description = "type", example="type")
		private String type;
		@Schema(description = "deleted", example="N")
		private String deleted;
	}

	@Schema
	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class TbpicPagedListDto extends CommonPagedListDto {
		@Schema(description = "tbboard_id", example="tbboard_id")
		private String tbboard_id;
		@Schema(description = "content", example="content")
		private String content;
		@Schema(description = "type", example="type")
		private String type;
		@Schema(description = "deleted", example="N")
		private String deleted;
	}
	@Schema
	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class TbpicMoreListDto extends CommonMoreListDto {
		@Schema(description = "tbboard_id", example="tbboard_id")
		private String tbboard_id;
		@Schema(description = "content", example="content")
		private String content;
		@Schema(description = "type", example="type")
		private String type;
		@Schema(description = "deleted", example="N")
		private String deleted;
	}
	
	
}