package com.thc.sprapi.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import com.thc.sprapi.domain.Tbboard;

import java.util.List;

public class TbboardDto {
	
	@Schema
	@Builder
	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class TbboardCreateDto {
		@Schema(description = "title", example="title")
		@NotNull
		@NotEmpty
		@Size(max=100)
		private String title;

		@Schema(description = "content", example="content")
		@NotNull
		@NotEmpty
		@Size(max=200)
		private String content;

		@Schema(description = "pics", example="pics")
		private String[] pics;
		@Schema(description = "types", example="types")
		private String[] types;

		public Tbboard toEntity() {
			return Tbboard.of(title, content);
		}
	}
	@Schema
	@Builder
	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class TbboardAfterCreateDto {
		@Schema(description = "id", example="length32textnumber")
		private String id;
	}

	@Schema
	@Builder
	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class TbboardUpdateDto {
		@Schema(description = "id", example="length32textnumber")
		@NotNull
		@NotEmpty
		@Size(max=32)
		private String id;

		@Schema(description = "title", example="title")
		@Size(max=100)
		private String title;

		@Schema(description = "deleted", example="Y")
		@Size(max=1)
		private String deleted;

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
	public static class TbboardAfterUpdateDto {
		@Schema(description = "id", example="length32textnumber")
		private String id;
		@Schema(description = "deleted", example="Y")
		private String deleted;
		@Schema(description = "title", example="title")
		@Size(max=100)
		private String title;
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
	public static class TbboardSelectDto {

		@Schema(description = "id", example="id")
		private String id;
		@Schema(description = "title", example="title")
		private String title;
		@Schema(description = "content", example="content")
		private String content;
		@Schema(description = "deleted", example="N")
		private String deleted;
		@Schema(description = "created_at", example="2024-01-01 00:00:00.000000")
		private String created_at;
		@Schema(description = "modified_at", example="2024-01-01 00:00:00.000000")
		private String modified_at;

		@Schema(description = "pics", example="pics")
		private List<TbpicDto.TbpicSelectDto> pics;
		@Schema(description = "files", example="files")
		private List<TbpicDto.TbpicSelectDto> files;

	}

	@Schema
	@Builder
	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class TbboardListDto {
		@Schema(description = "title", example="title")
		private String title;
		@Schema(description = "deleted", example="N")
		private String deleted;
	}

	@Schema
	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class TbboardPagedListDto extends CommonPagedListDto {
		@Schema(description = "title", example="title")
		private String title;
		@Schema(description = "deleted", example="N")
		private String deleted;
	}
	@Schema
	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class TbboardMoreListDto extends CommonMoreListDto {
		@Schema(description = "title", example="title")
		private String title;
		@Schema(description = "deleted", example="N")
		private String deleted;
	}
	
	
}