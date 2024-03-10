package com.thc.sprapi.dto;

import com.thc.sprapi.domain.Tbpopup;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

public class TbpopupDto {

	@Schema
	@Builder
	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class TbpopupSequenceDto extends CommonGrantDto{
		@Schema(description = "id", example="length32textnumber")
		@NotNull
		@NotEmpty
		@Size(max=32)
		private String id;

		@Schema(description = "way", example="way")
		private String way;

		@Schema(description = "nowTbuserId", example="nowTbuserId")
		private String nowTbuserId;

	}

	/**/

	@Schema
	@Builder
	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class TbpopupCreateDto extends CommonGrantDto{
		@Schema(description = "title", example="title")
		@NotNull
		@NotEmpty
		@Size(max=100)
		private String title;
		@Schema(description = "sequence", example="sequence")
		private Integer sequence;
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
		@Schema(description = "url", example="url")
		@NotNull
		@NotEmpty
		@Size(max=200)
		private String url;
		@Schema(description = "content", example="content")
		@NotNull
		@NotEmpty
		private String content;

		public Tbpopup toEntity() {
			return Tbpopup.of(title, sequence, cate, mpic, url, content);
		}
	}
	@Schema
	@Builder
	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class TbpopupAfterCreateDto {
		@Schema(description = "id", example="length32textnumber")
		private String id;
	}

	@Schema
	@Builder
	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class TbpopupUpdateDto extends CommonGrantDto{
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
		@Schema(description = "sequence", example="sequence")
		private Integer sequence;
		@Schema(description = "cate", example="cate")
		@Size(max=100)
		private String cate;
		@Schema(description = "mpic", example="mpic")
		private String mpic;
		@Schema(description = "url", example="url")
		private String url;
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
	public static class TbpopupAfterUpdateDto {
		@Schema(description = "id", example="length32textnumber")
		private String id;
		@Schema(description = "deleted", example="Y")
		private String deleted;

		@Schema(description = "title", example="title")
		@Size(max=100)
		private String title;
		@Schema(description = "sequence", example="sequence")
		private Integer sequence;
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
	public static class TbpopupSelectDto extends CommonSelectDto{
		@Schema(description = "title", example="title")
		private String title;
		@Schema(description = "sequence", example="sequence")
		private int sequence;
		@Schema(description = "cate", example="cate")
		private String cate;
		@Schema(description = "mpic", example="mpic")
		private String mpic;
		@Schema(description = "url", example="url")
		private String url;
		@Schema(description = "content", example="content")
		private String content;
	}

	@Schema
	@Builder
	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class TbpopupListDto extends CommonGrantDto {
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
	public static class TbpopupPagedListDto extends CommonPagedListDto {
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
	public static class TbpopupMoreListDto extends CommonMoreListDto {
		@Schema(description = "deleted", example="N")
		private String deleted;

		@Schema(description = "title", example="title")
		private String title;
		@Schema(description = "cate", example="cate")
		private String cate;
	}

}