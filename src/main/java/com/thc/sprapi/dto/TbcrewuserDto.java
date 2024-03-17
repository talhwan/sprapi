package com.thc.sprapi.dto;

import com.thc.sprapi.domain.Tbcrewuser;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

public class TbcrewuserDto {
	@Schema
	@Builder
	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class TbcrewuserCreateDto extends CommonGrantDto{
		@Schema(description = "tbcrewId", example="tbcrewId")
		@NotNull
		@NotEmpty
		@Size(max=100)
		private String tbcrewId;
		@Schema(description = "tbuserId", example="tbuserId")
		@NotNull
		@NotEmpty
		private String tbuserId;
		@Schema(description = "process", example="process")
		@NotNull
		@NotEmpty
		private String process;
		@Schema(description = "cate", example="cate")
		@NotNull
		@NotEmpty
		private String cate;

		public Tbcrewuser toEntity() {
			return Tbcrewuser.of(tbcrewId, tbuserId, process, cate, "");
		}
	}
	@Schema
	@Builder
	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class TbcrewuserAfterCreateDto {
		@Schema(description = "id", example="length32textnumber")
		private String id;
	}

	@Schema
	@Builder
	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class TbcrewuserUpdateDto extends CommonGrantDto{
		@Schema(description = "id", example="length32textnumber")
		@NotNull
		@NotEmpty
		@Size(max=32)
		private String id;
		@Schema(description = "deleted", example="Y")
		@Size(max=1)
		private String deleted;

		@Schema(description = "tbcrewId", example="tbcrewId")
		private String tbcrewId;
		@Schema(description = "tbuserId", example="tbuserId")
		private String tbuserId;
		@Schema(description = "process", example="process")
		private String process;
		@Schema(description = "cate", example="cate")
		private String cate;
		@Schema(description = "content", example="content")
		private String content;
	}
	@Schema
	@Builder
	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class TbcrewuserAfterUpdateDto {
		@Schema(description = "id", example="length32textnumber")
		private String id;
		@Schema(description = "deleted", example="Y")
		private String deleted;
		@Schema(description = "tbcrewId", example="tbcrewId")
		private String tbcrewId;
		@Schema(description = "tbuserId", example="tbuserId")
		private String tbuserId;
		@Schema(description = "process", example="process")
		private String process;
		@Schema(description = "cate", example="cate")
		private String cate;
	}

	@Schema
	@Builder
	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class TbcrewuserSelectDto extends CommonSelectDto{
		@Schema(description = "tbcrewId", example="tbcrewId")
		private String tbcrewId;
		@Schema(description = "tbuserId", example="tbcrewId")
		private String tbuserId;
		@Schema(description = "process", example="process")
		private String process;
		@Schema(description = "cate", example="cate")
		private String cate;
		@Schema(description = "content", example="content")
		private String content;
		@Schema(description = "tbcrewTitle", example="tbcrewTitle")
		private String tbcrewTitle;
		@Schema(description = "tbcrewProcess", example="tbcrewProcess")
		private String tbcrewProcess;
		@Schema(description = "tbuserUsername", example="tbuserUsername")
		private String tbuserUsername;
		@Schema(description = "tbuserNick", example="tbuserNick")
		private String tbuserNick;
		@Schema(description = "tbuserName", example="tbuserName")
		private String tbuserName;
		@Schema(description = "tbuserPhone", example="tbuserPhone")
		private String tbuserPhone;
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
	public static class TbcrewuserListDto extends CommonGrantDto{
		@Schema(description = "deleted", example="N")
		private String deleted;

		@Schema(description = "tbcrewId", example="tbcrewId")
		private String tbcrewId;
		@Schema(description = "tbuserId", example="tbcrewId")
		private String tbuserId;
		@Schema(description = "process", example="process")
		private String process;
		@Schema(description = "cate", example="process")
		private String cate;
	}

	@Schema
	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class TbcrewuserPagedListDto extends CommonPagedListDto {
		@Schema(description = "deleted", example="N")
		private String deleted;

		@Schema(description = "tbcrewId", example="tbcrewId")
		private String tbcrewId;
		@Schema(description = "tbuserId", example="tbcrewId")
		private String tbuserId;
		@Schema(description = "process", example="process")
		private String process;
		@Schema(description = "cate", example="process")
		private String cate;
	}
	@Schema
	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class TbcrewuserMoreListDto extends CommonMoreListDto {
		@Schema(description = "deleted", example="N")
		private String deleted;

		@Schema(description = "tbcrewId", example="tbcrewId")
		private String tbcrewId;
		@Schema(description = "tbuserId", example="tbcrewId")
		private String tbuserId;
		@Schema(description = "process", example="process")
		private String process;
		@Schema(description = "cate", example="process")
		private String cate;

		@Schema(description = "hasgrant", example="")
		private String hasgrant;
		@Schema(description = "tbuserUsername", example="")
		private String tbuserUsername;
		@Schema(description = "tbuserNick", example="")
		private String tbuserNick;
	}
}