package com.thc.sprapi.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Schema
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommonMoreListDto {
	@Schema(description = "검색 기준일(시작)", example="1970-01-01")
	private String sdate;
	@Schema(description = "검색 기준일(종료)", example="1970-01-01")
	private String fdate;

	@NotNull
	@NotEmpty
	@Schema(description = "한번에 볼 갯수", example="10")
	private int permore;
	@Schema(description = "검색 기준일시", example="1970-01-01 00:00:00.000000")
	private String cdatetime;
	@Schema(description = "조회 방향", example="recent,before")
	private String cway;

	public void afterBuild(){
		if(permore <= 0){
			permore = 10;
		}
		if(cdatetime == null || "".equals(cdatetime)){
			cdatetime = "9999-12-31 23:59:59.999999";
			cway = "before";
		}
	}
}