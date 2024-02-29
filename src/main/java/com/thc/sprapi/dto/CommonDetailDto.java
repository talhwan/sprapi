package com.thc.sprapi.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Schema
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommonDetailDto extends CommonGrantDto{
	@Schema(description = "id", example="id")
	private String id;
}