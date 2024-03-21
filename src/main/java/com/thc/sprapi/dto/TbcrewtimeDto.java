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
		@Schema(description = "process", example="process")
		@NotNull
		@NotEmpty
		private String process;

		@Schema(description = "startage", example="startage")
		@NotNull
		@NotEmpty
		private Integer startage;
		@Schema(description = "finishage", example="finishage")
		@NotNull
		@NotEmpty
		private Integer finishage;

		@Schema(description = "day0", example="day0")
		@NotNull
		@NotEmpty
		private String day0;
		@Schema(description = "day1", example="day1")
		@NotNull
		@NotEmpty
		private String day1;
		@Schema(description = "day2", example="day2")
		@NotNull
		@NotEmpty
		private String day2;
		@Schema(description = "day3", example="day3")
		@NotNull
		@NotEmpty
		private String day3;
		@Schema(description = "day4", example="day4")
		@NotNull
		@NotEmpty
		private String day4;
		@Schema(description = "day5", example="day5")
		@NotNull
		@NotEmpty
		private String day5;
		@Schema(description = "day6", example="day6")
		@NotNull
		@NotEmpty
		private String day6;
		@Schema(description = "starttime", example="starttime")
		@NotNull
		@NotEmpty
		private String starttime;
		@Schema(description = "finishtime", example="finishtime")
		@NotNull
		@NotEmpty
		private String finishtime;

		public Tbcrewtime toEntity() {
			return Tbcrewtime.of(tbcrewId, title, sequence, cate, process
					, startage
					, finishage
					, day0
					, day1
					, day2
					, day3
					, day4
					, day5
					, day6
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
		@Schema(description = "process", example="process")
		@Size(max=100)
		private String process;

		@Schema(description = "startage", example="startage")
		private Integer startage;
		@Schema(description = "finishage", example="finishage")
		private Integer finishage;

		@Schema(description = "day0", example="day0")
		private String day0;
		@Schema(description = "day1", example="day1")
		private String day1;
		@Schema(description = "day2", example="day2")
		private String day2;
		@Schema(description = "day3", example="day3")
		private String day3;
		@Schema(description = "day4", example="day4")
		private String day4;
		@Schema(description = "day5", example="day5")
		private String day5;
		@Schema(description = "day6", example="day6")
		private String day6;
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
		@Schema(description = "process", example="process")
		private String process;

		@Schema(description = "startage", example="startage")
		private Integer startage;
		@Schema(description = "finishage", example="finishage")
		private Integer finishage;

		@Schema(description = "day0", example="day0")
		private String day0;
		@Schema(description = "day1", example="day1")
		private String day1;
		@Schema(description = "day2", example="day2")
		private String day2;
		@Schema(description = "day3", example="day3")
		private String day3;
		@Schema(description = "day4", example="day4")
		private String day4;
		@Schema(description = "day5", example="day5")
		private String day5;
		@Schema(description = "day6", example="day6")
		private String day6;
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
		@Schema(description = "process", example="process")
		private String process;

		@Schema(description = "tbcrewTitle", example="tbcrewTitle")
		private String tbcrewTitle;

		@Schema(description = "startage", example="startage")
		private Integer startage;
		@Schema(description = "finishage", example="finishage")
		private Integer finishage;

		@Schema(description = "day0", example="day0")
		private String day0;
		@Schema(description = "day1", example="day1")
		private String day1;
		@Schema(description = "day2", example="day2")
		private String day2;
		@Schema(description = "day3", example="day3")
		private String day3;
		@Schema(description = "day4", example="day4")
		private String day4;
		@Schema(description = "day5", example="day5")
		private String day5;
		@Schema(description = "day6", example="day6")
		private String day6;
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
		@Schema(description = "process", example="process")
		private String process;
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
		@Schema(description = "process", example="process")
		private String process;
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
		@Schema(description = "process", example="process")
		private String process;
	}

}