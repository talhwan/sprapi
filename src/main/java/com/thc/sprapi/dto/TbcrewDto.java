package com.thc.sprapi.dto;

import com.thc.sprapi.domain.Tbcrew;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

public class TbcrewDto {
	/**/
	@Schema
	@Builder
	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class TbcrewCreateDto extends CommonGrantDto{
		@Schema(description = "title", example="title")
		@NotNull
		@NotEmpty
		@Size(max=100)
		private String title;
		@Schema(description = "code", example="code")
		@NotNull
		@NotEmpty
		@Size(max=100)
		private String code;
		@Schema(description = "process", example="process")
		@NotNull
		@NotEmpty
		@Size(max=100)
		private String process;
		@Schema(description = "cate", example="1970-01-01")
		@NotNull
		@NotEmpty
		private String cate;
		@Schema(description = "mpic", example="09:00")
		@NotNull
		@NotEmpty
		private String mpic;
		@Schema(description = "content", example="content")
		@NotNull
		@NotEmpty
		@Size(max=10000)
		private String content;

		public Tbcrew toEntity() {
			return Tbcrew.of(title, code, process, cate, mpic, content);
		}
	}
	@Schema
	@Builder
	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class TbcrewAfterCreateDto {
		@Schema(description = "id", example="length32textnumber")
		private String id;
	}

	@Schema
	@Builder
	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class TbcrewUpdateDto extends CommonGrantDto{
		@Schema(description = "id", example="length32textnumber")
		@NotNull
		@NotEmpty
		@Size(max=32)
		private String id;
		@Schema(description = "deleted", example="Y")
		@Size(max=1)
		private String deleted;

		@Schema(description = "title", example="title")
		@Size(max=100)
		private String title;
		@Schema(description = "code", example="code")
		@Size(max=100)
		private String code;
		@Schema(description = "process", example="process")
		private String process;
		@Schema(description = "cate", example="1970-01-01")
		private String cate;
		@Schema(description = "mpic", example="09:00")
		private String mpic;
		@Schema(description = "content", example="content")
		@Size(max=10000)
		private String content;
	}
	@Schema
	@Builder
	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class TbcrewAfterUpdateDto {
		@Schema(description = "id", example="length32textnumber")
		private String id;
		@Schema(description = "deleted", example="Y")
		private String deleted;

		@Schema(description = "title", example="title")
		private String title;
		@Schema(description = "code", example="code")
		private String code;
		@Schema(description = "process", example="process")
		private String process;
		@Schema(description = "cate", example="1970-01-01")
		private String cate;
		@Schema(description = "mpic", example="09:00")
		private String mpic;
		@Schema(description = "content", example="content")
		@Size(max=10000)
		private String content;
	}

	@Schema
	@Builder
	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class TbcrewSelectDto extends CommonSelectDto{
		@Schema(description = "title", example="title")
		private String title;
		@Schema(description = "code", example="code")
		private String code;
		@Schema(description = "process", example="process")
		private String process;
		@Schema(description = "cate", example="1970-01-01")
		private String cate;
		@Schema(description = "mpic", example="09:00")
		private String mpic;
		@Schema(description = "content", example="content")
		private String content;

	}

	@Schema
	@Builder
	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class TbcrewListDto extends CommonGrantDto{
		@Schema(description = "deleted", example="N")
		private String deleted;

		@Schema(description = "title", example="title")
		private String title;
		@Schema(description = "process", example="process")
		private String process;
		@Schema(description = "cate", example="1970-01-01")
		private String cate;

	}

	@Schema
	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class TbcrewPagedListDto extends CommonPagedListDto {
		@Schema(description = "deleted", example="N")
		private String deleted;

		@Schema(description = "title", example="title")
		private String title;
		@Schema(description = "process", example="process")
		private String process;
		@Schema(description = "cate", example="1970-01-01")
		private String cate;
	}
	@Schema
	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class TbcrewMoreListDto extends CommonMoreListDto {
		@Schema(description = "deleted", example="N")
		private String deleted;

		@Schema(description = "title", example="title")
		private String title;
		@Schema(description = "process", example="process")
		private String process;
		@Schema(description = "cate", example="1970-01-01")
		private String cate;
	}

}