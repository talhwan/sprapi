package com.thc.sprapi.dto;

import com.thc.sprapi.domain.Tbcrewtime;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

public class TbcrewtimeDto {

	@Schema
	@Builder
	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class TbcrewtimeSequenceDto extends CommonGrantDto{
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
	public static class TbcrewtimeCreateDto extends CommonGrantDto{
		@Schema(description = "tbcrewId", example="tbcrewId")
		@NotNull
		@NotEmpty
		@Size(max=100)
		private String tbcrewId;

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

		@Schema(description = "startage", example="startage")
		@NotNull
		@NotEmpty
		private Integer startage;
		@Schema(description = "finishage", example="finishage")
		@NotNull
		@NotEmpty
		private Integer finishage;

		@Schema(description = "startday", example="startday")
		@NotNull
		@NotEmpty
		private String startday;
		@Schema(description = "finishday", example="finishday")
		@NotNull
		@NotEmpty
		private String finishday;
		@Schema(description = "starttime", example="starttime")
		@NotNull
		@NotEmpty
		private String starttime;
		@Schema(description = "finishtime", example="finishtime")
		@NotNull
		@NotEmpty
		private String finishtime;

		public Tbcrewtime toEntity() {
			return Tbcrewtime.of(tbcrewId, title, sequence, cate
					, startage
					, finishage
					, startday
					, finishday
					, starttime
					, finishtime);
		}
	}
	@Schema
	@Builder
	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class TbcrewtimeAfterCreateDto {
		@Schema(description = "id", example="length32textnumber")
		private String id;
	}

	@Schema
	@Builder
	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class TbcrewtimeUpdateDto extends CommonGrantDto{
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

		@Schema(description = "title", example="title")
		@Size(max=100)
		private String title;
		@Schema(description = "sequence", example="sequence")
		private Integer sequence;
		@Schema(description = "cate", example="cate")
		@Size(max=100)
		private String cate;

		@Schema(description = "startage", example="startage")
		private Integer startage;
		@Schema(description = "finishage", example="finishage")
		private Integer finishage;

		@Schema(description = "startday", example="startday")
		private String startday;
		@Schema(description = "finishday", example="finishday")
		private String finishday;
		@Schema(description = "starttime", example="starttime")
		private String starttime;
		@Schema(description = "finishtime", example="finishtime")
		private String finishtime;

		@Schema(description = "nowTbuserId", example="nowTbuserId")
		private String nowTbuserId;

	}
	@Schema
	@Builder
	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class TbcrewtimeAfterUpdateDto {
		@Schema(description = "id", example="length32textnumber")
		private String id;
		@Schema(description = "deleted", example="Y")
		private String deleted;

		@Schema(description = "tbcrewId", example="tbcrewId")
		private String tbcrewId;

		@Schema(description = "title", example="title")
		@Size(max=100)
		private String title;
		@Schema(description = "sequence", example="sequence")
		private Integer sequence;
		@Schema(description = "cate", example="cate")
		@Size(max=100)
		private String cate;

		@Schema(description = "startage", example="startage")
		private Integer startage;
		@Schema(description = "finishage", example="finishage")
		private Integer finishage;

		@Schema(description = "startday", example="startday")
		private String startday;
		@Schema(description = "finishday", example="finishday")
		private String finishday;
		@Schema(description = "starttime", example="starttime")
		private String starttime;
		@Schema(description = "finishtime", example="finishtime")
		private String finishtime;
	}

	@Schema
	@Builder
	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class TbcrewtimeSelectDto extends CommonSelectDto{
		@Schema(description = "tbcrewId", example="tbcrewId")
		private String tbcrewId;
		@Schema(description = "title", example="title")
		private String title;
		@Schema(description = "sequence", example="sequence")
		private int sequence;
		@Schema(description = "cate", example="cate")
		private String cate;

		@Schema(description = "startage", example="startage")
		private Integer startage;
		@Schema(description = "finishage", example="finishage")
		private Integer finishage;

		@Schema(description = "startday", example="startday")
		private String startday;
		@Schema(description = "finishday", example="finishday")
		private String finishday;
		@Schema(description = "starttime", example="starttime")
		private String starttime;
		@Schema(description = "finishtime", example="finishtime")
		private String finishtime;
	}

	@Schema
	@Builder
	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class TbcrewtimeListDto extends CommonGrantDto {
		@Schema(description = "deleted", example="N")
		private String deleted;

		@Schema(description = "tbcrewId", example="tbcrewId")
		private String tbcrewId;
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
	public static class TbcrewtimePagedListDto extends CommonPagedListDto {
		@Schema(description = "deleted", example="N")
		private String deleted;

		@Schema(description = "tbcrewId", example="tbcrewId")
		private String tbcrewId;
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
	public static class TbcrewtimeMoreListDto extends CommonMoreListDto {
		@Schema(description = "deleted", example="N")
		private String deleted;

		@Schema(description = "tbcrewId", example="tbcrewId")
		private String tbcrewId;
		@Schema(description = "title", example="title")
		private String title;
		@Schema(description = "cate", example="cate")
		private String cate;
	}

}