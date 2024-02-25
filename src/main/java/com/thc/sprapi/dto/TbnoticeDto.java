package com.thc.sprapi.dto;

import com.thc.sprapi.domain.Tbnotice;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

public class TbnoticeDto {
	
	@Schema
	@Builder
	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class TbnoticeCreateDto extends CommonGrantDto{
		@Schema(description = "title", example="title")
		@NotNull
		@NotEmpty
		@Size(max=100)
		private String title;
		@Schema(description = "cate", example="cate")
		@NotNull
		@NotEmpty
		@Size(max=100)
		private String cate;
		@Schema(description = "mpic", example="mpic")
		@NotNull
		@NotEmpty
		@Size(max=200)
		private String mpic;
		@Schema(description = "content", example="content")
		@NotNull
		@NotEmpty
		private String content;

		public Tbnotice toEntity() {
			return Tbnotice.of(title, cate, mpic, content);
		}
	}
	@Schema
	@Builder
	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class TbnoticeAfterCreateDto {
		@Schema(description = "id", example="length32textnumber")
		private String id;
	}

	@Schema
	@Builder
	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class TbnoticeUpdateDto extends CommonGrantDto{
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
		@Schema(description = "cate", example="cate")
		@Size(max=100)
		private String cate;
		@Schema(description = "mpic", example="mpic")
		@Size(max=200)
		private String mpic;
		@Schema(description = "content", example="content")
		private String content;

		@Schema(description = "nowTbuserId", example="nowTbuserId")
		private String nowTbuserId;

	}
	@Schema
	@Builder
	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class TbnoticeAfterUpdateDto {
		@Schema(description = "id", example="length32textnumber")
		private String id;
		@Schema(description = "deleted", example="Y")
		private String deleted;

		@Schema(description = "title", example="title")
		@Size(max=100)
		private String title;
		@Schema(description = "cate", example="cate")
		@Size(max=100)
		private String cate;
		@Schema(description = "mpic", example="mpic")
		@Size(max=200)
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
	public static class TbnoticeSelectDto extends CommonSelectDto{
		@Schema(description = "title", example="title")
		private String title;
		@Schema(description = "cate", example="cate")
		private String cate;
		@Schema(description = "mpic", example="mpic")
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
	public static class TbnoticeListDto extends CommonGrantDto {
		@Schema(description = "deleted", example="N")
		private String deleted;

		@Schema(description = "title", example="title")
		private String title;
		@Schema(description = "cate", example="cate")
		private String cate;
	}

	@Schema
	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class TbnoticePagedListDto extends CommonPagedListDto {
		@Schema(description = "deleted", example="N")
		private String deleted;

		@Schema(description = "title", example="title")
		private String title;
		@Schema(description = "cate", example="cate")
		private String cate;
	}
	@Schema
	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class TbnoticeMoreListDto extends CommonMoreListDto {
		@Schema(description = "deleted", example="N")
		private String deleted;

		@Schema(description = "title", example="title")
		private String title;
		@Schema(description = "cate", example="cate")
		private String cate;
	}

}