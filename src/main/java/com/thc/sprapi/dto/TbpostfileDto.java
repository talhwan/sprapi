package com.thc.sprapi.dto;

import com.thc.sprapi.domain.Tbpostfile;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

public class TbpostfileDto {
	@Schema
	@Builder
	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class TbpostfileCreateDto {
		@Schema(description = "tbpostId", example="tbpostId")
		@NotNull
		@NotEmpty
		@Size(max=100)
		private String tbpostId;
		@Schema(description = "type", example="type")
		@NotNull
		@NotEmpty
		private String type;
		@Schema(description = "content", example="content")
		@NotNull
		@NotEmpty
		@Size(max=200)
		private String content;

		public Tbpostfile toEntity() {
			return Tbpostfile.of(tbpostId, type, content);
		}
	}
	@Schema
	@Builder
	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class TbpostfileAfterCreateDto {
		@Schema(description = "id", example="length32textnumber")
		private String id;
	}

	@Schema
	@Builder
	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class TbpostfileUpdateDto {
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
	public static class TbpostfileAfterUpdateDto {
		@Schema(description = "id", example="length32textnumber")
		private String id;
		@Schema(description = "deleted", example="Y")
		private String deleted;
		@Schema(description = "tbpostId", example="tbpostId")
		@Size(max=100)
		private String tbpostId;
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
	public static class TbpostfileSelectDto extends CommonSelectDto{
		@Schema(description = "tbpostId", example="tbpostId")
		private String tbpostId;
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
	public static class TbpostfileListDto {
		@Schema(description = "deleted", example="N")
		private String deleted;

		@Schema(description = "tbpostId", example="tbpost_id")
		private String tbpostId;
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
	public static class TbpostfilePagedListDto extends CommonPagedListDto {
		@Schema(description = "deleted", example="N")
		private String deleted;

		@Schema(description = "tbpostId", example="tbpostId")
		private String tbpostId;
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
	public static class TbpostfileMoreListDto extends CommonMoreListDto {
		@Schema(description = "deleted", example="N")
		private String deleted;

		@Schema(description = "tbpostId", example="tbpostId")
		private String tbpostId;
		@Schema(description = "type", example="type")
		private String type;
		@Schema(description = "content", example="content")
		private String content;
	}
}