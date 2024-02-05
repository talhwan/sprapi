package com.thc.sprapi.dto;

import com.thc.sprapi.domain.Tbgrant;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;

public class TbgrantDto {
	
	@Schema
	@Builder
	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class TbgrantCreateDto extends CommonGrantDto{
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

		@Schema(description = "content", example="content")
		@NotNull
		@NotEmpty
		@Size(max=10000)
		private String content;

		public Tbgrant toEntity() {
			return Tbgrant.of(title, cate, content);
		}
	}
	@Schema
	@Builder
	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class TbgrantAfterCreateDto {
		@Schema(description = "id", example="length32textnumber")
		private String id;
	}

	@Schema
	@Builder
	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class TbgrantUpdateDto extends CommonGrantDto{
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
	public static class TbgrantAfterUpdateDto {
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
	public static class TbgrantSelectDto extends CommonSelectDto{
		@Schema(description = "title", example="title")
		private String title;
		@Schema(description = "cate", example="cate")
		private String cate;
		@Schema(description = "content", example="content")
		private String content;

		@Schema(description = "tbuserId", example="")
		private String tbuserId;
		@Schema(description = "tbuserNick", example="")
		private String tbuserNick;
		@Schema(description = "tbuserMpic", example="")
		private String tbuserMpic;

		private String[][] types = TbgrantpartDto.types;
		@Schema(description = "tbgrantparts", example="tbgrantparts")
		private List<TbgrantpartDto.TbgrantpartSelectDto> tbgrantparts;
/*
		@Schema(description = "pics", example="pics")
		private List<TbgrantfileDto.TbgrantfileSelectDto> pics;
		@Schema(description = "files", example="files")
		private List<TbgrantfileDto.TbgrantfileSelectDto> files;

 */

	}

	@Schema
	@Builder
	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class TbgrantListDto extends CommonGrantDto{
		@Schema(description = "deleted", example="N")
		private String deleted;

		@Schema(description = "title", example="title")
		private String title;
		@Schema(description = "cate", example="cate")
		private String cate;

		@Schema(description = "tbuserId", example="")
		private String tbuserId;
		@Schema(description = "tbuserUsername", example="")
		private String tbuserUsername;
		@Schema(description = "tbuserNick", example="")
		private String tbuserNick;
	}

	@Schema
	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class TbgrantPagedListDto extends CommonPagedListDto {
		@Schema(description = "deleted", example="N")
		private String deleted;

		@Schema(description = "title", example="title")
		private String title;
		@Schema(description = "cate", example="cate")
		private String cate;

		@Schema(description = "tbuserId", example="")
		private String tbuserId;
		@Schema(description = "tbuserUsername", example="")
		private String tbuserUsername;
		@Schema(description = "tbuserNick", example="")
		private String tbuserNick;
	}
	@Schema
	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class TbgrantMoreListDto extends CommonMoreListDto {
		@Schema(description = "deleted", example="N")
		private String deleted;

		@Schema(description = "title", example="title")
		private String title;
		@Schema(description = "cate", example="cate")
		private String cate;

		@Schema(description = "tbuserId", example="")
		private String tbuserId;
		@Schema(description = "tbuserUsername", example="")
		private String tbuserUsername;
		@Schema(description = "tbuserNick", example="")
		private String tbuserNick;
	}

}