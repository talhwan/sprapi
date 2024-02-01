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
public class CommonSelectDto {
	@Schema(description = "id", example="id")
	private String id;
	@Schema(description = "deleted", example="N")
	private String deleted;
	@Schema(description = "createdAt", example="2024-01-01 00:00:00.000000")
	private String createdAt;
	@Schema(description = "modifiedAt", example="2024-01-01 00:00:00.000000")
	private String modifiedAt;
}