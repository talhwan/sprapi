package com.thc.sprapi.dto;

import com.thc.sprapi.domain.Tbgrantpart;
import com.thc.sprapi.domain.Tbgrantuser;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

public class TbgrantuserDto {

	@Schema
	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class TbgrantuserToggleDto extends TbgrantuserDto.TbgrantuserCreateDto {
		@Schema(description = "way", example="way")
		@NotNull
		@NotEmpty
		private boolean way;
		public Tbgrantuser toEntity() {
			return Tbgrantuser.of(super.tbgrantId, super.tbuserId);
		}
	}

	@Schema
	@Builder
	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class TbgrantuserCreateDto extends CommonGrantDto{
		@Schema(description = "tbgrantId", example="tbgrantId")
		@NotNull
		@NotEmpty
		@Size(max=100)
		private String tbgrantId;
		@Schema(description = "tbuserId", example="tbuserId")
		@NotNull
		@NotEmpty
		private String tbuserId;

		public Tbgrantuser toEntity() {
			return Tbgrantuser.of(tbgrantId, tbuserId);
		}
	}
	@Schema
	@Builder
	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class TbgrantuserAfterCreateDto {
		@Schema(description = "id", example="length32textnumber")
		private String id;
	}

	@Schema
	@Builder
	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class TbgrantuserUpdateDto extends CommonGrantDto{
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
		@Schema(description = "tbuserId", example="tbuserId")
		@Size(max=100)
		private String tbuserId;
	}
	@Schema
	@Builder
	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class TbgrantuserAfterUpdateDto {
		@Schema(description = "id", example="length32textnumber")
		private String id;
		@Schema(description = "deleted", example="Y")
		private String deleted;
		@Schema(description = "tbgrantId", example="tbgrantId")
		private String tbgrantId;
		@Schema(description = "tbuserId", example="tbuserId")
		private String tbuserId;
	}

	@Schema
	@Builder
	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class TbgrantuserSelectDto extends CommonSelectDto{
		@Schema(description = "tbgrantId", example="tbgrantId")
		private String tbgrantId;
		@Schema(description = "tbuserId", example="tbgrantId")
		private String tbuserId;
		@Schema(description = "tbuserUsername", example="tbuserUsername")
		private String tbuserUsername;
		@Schema(description = "tbuserNick", example="tbuserNick")
		private String tbuserNick;
		@Schema(description = "tbuserMpic", example="tbuserMpic")
		private String tbuserMpic;

		@Schema(description = "hasgrant", example="hasgrant")
		private String hasgrant;
	}

	@Schema
	@Builder
	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class TbgrantuserListDto extends CommonGrantDto{
		@Schema(description = "deleted", example="N")
		private String deleted;

		@Schema(description = "tbgrantId", example="tbgrantId")
		private String tbgrantId;
		@Schema(description = "tbuserId", example="tbgrantId")
		private String tbuserId;
	}

	@Schema
	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class TbgrantuserPagedListDto extends CommonPagedListDto {
		@Schema(description = "deleted", example="N")
		private String deleted;

		@Schema(description = "tbgrantId", example="tbgrantId")
		private String tbgrantId;
		@Schema(description = "tbuserId", example="tbgrantId")
		private String tbuserId;
	}
	@Schema
	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class TbgrantuserMoreListDto extends CommonMoreListDto {
		@Schema(description = "deleted", example="N")
		private String deleted;

		@Schema(description = "tbgrantId", example="tbgrantId")
		private String tbgrantId;
		@Schema(description = "tbuserId", example="tbgrantId")
		private String tbuserId;

		@Schema(description = "hasgrant", example="")
		private String hasgrant;
		@Schema(description = "tbuserUsername", example="")
		private String tbuserUsername;
		@Schema(description = "tbuserNick", example="")
		private String tbuserNick;
	}
}