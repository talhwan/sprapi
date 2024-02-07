package com.thc.sprapi.dto;

import com.thc.sprapi.domain.Tbgsquid;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;

public class TbgsquidDto {
	/**/
	@Schema
	@Builder
	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class TbgsquidCreateDto extends CommonGrantDto{
		@Schema(description = "title", example="title")
		@NotNull
		@NotEmpty
		@Size(max=100)
		private String title;
		/*@Schema(description = "process", example="process")
		@NotNull
		@NotEmpty
		@Size(max=100)
		private String process;*/
		@Schema(description = "startdate", example="1970-01-01")
		@NotNull
		@NotEmpty
		private String startdate;
		@Schema(description = "starttime", example="09:00")
		@NotNull
		@NotEmpty
		private String starttime;
		@Schema(description = "goal", example="100")
		@NotNull
		@NotEmpty
		private int goal;
		@Schema(description = "content", example="content")
		@NotNull
		@NotEmpty
		@Size(max=10000)
		private String content;

		public Tbgsquid toEntity() {
			return Tbgsquid.of(title, startdate, starttime, goal, content);
		}
	}
	@Schema
	@Builder
	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class TbgsquidAfterCreateDto {
		@Schema(description = "id", example="length32textnumber")
		private String id;
	}

	@Schema
	@Builder
	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class TbgsquidUpdateDto extends CommonGrantDto{
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
		@Schema(description = "process", example="process")
		private String process;
		@Schema(description = "startdate", example="1970-01-01")
		private String startdate;
		@Schema(description = "starttime", example="09:00")
		private String starttime;
		@Schema(description = "goal", example="100")
		private String goal;
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
	public static class TbgsquidAfterUpdateDto {
		@Schema(description = "id", example="length32textnumber")
		private String id;
		@Schema(description = "deleted", example="Y")
		private String deleted;

		@Schema(description = "title", example="title")
		@Size(max=100)
		private String title;
		@Schema(description = "process", example="process")
		private String process;
		@Schema(description = "startdate", example="1970-01-01")
		private String startdate;
		@Schema(description = "starttime", example="09:00")
		private String starttime;
		@Schema(description = "goal", example="100")
		private int goal;
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
	public static class TbgsquidSelectDto extends CommonSelectDto{
		@Schema(description = "title", example="title")
		private String title;
		@Schema(description = "process", example="process")
		private String process;
		@Schema(description = "startdate", example="1970-01-01")
		private String startdate;
		@Schema(description = "starttime", example="09:00")
		private String starttime;
		@Schema(description = "goal", example="100")
		private int goal;
		@Schema(description = "content", example="content")
		private String content;

	}

	@Schema
	@Builder
	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class TbgsquidListDto extends CommonGrantDto{
		@Schema(description = "deleted", example="N")
		private String deleted;

		@Schema(description = "title", example="title")
		private String title;
		@Schema(description = "process", example="process")
		private String process;
		@Schema(description = "startdate", example="1970-01-01")
		private String startdate;

	}

	@Schema
	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class TbgsquidPagedListDto extends CommonPagedListDto {
		@Schema(description = "deleted", example="N")
		private String deleted;

		@Schema(description = "title", example="title")
		private String title;
		@Schema(description = "process", example="process")
		private String process;
		@Schema(description = "startdate", example="1970-01-01")
		private String startdate;
	}
	@Schema
	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class TbgsquidMoreListDto extends CommonMoreListDto {
		@Schema(description = "deleted", example="N")
		private String deleted;

		@Schema(description = "title", example="title")
		private String title;
		@Schema(description = "process", example="process")
		private String process;
		@Schema(description = "startdate", example="1970-01-01")
		private String startdate;
	}

}