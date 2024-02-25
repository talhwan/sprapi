package com.thc.sprapi.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.thc.sprapi.domain.Tbuser;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Pattern;
import lombok.*;

public class TbuserDto {

	@Builder
	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class TbuserOkcertTokenDto {
		private String RSLT_CD;
		private String RSLT_MSG;
		private String CP_CD;
		private String POPUP_URL;
		private String MDL_TKN;
	}

	@Builder
	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class TbuserSnsDto {
		private String username;
		private String password;
	}

	@Schema
	@Builder
	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class TbuserLoginDto {

		@Schema(description = "username", example="xxx@xxxx.com")
		@NotNull
		@NotEmpty
		//@Email
		@Size(max=191)
		private String username;

		@Schema(description = "min 8, 1 upper case, 1 number", example="testPass1!")
		//@Pattern(regexp="^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[$@$!%*?&])[A-Za-z\\d$@$!%*?&]{8,}")
		@NotNull
		@NotEmpty
		@Size(min=8, max=191)
		private String password;

		public Tbuser toEntity() {
			return Tbuser.of(username, password, "", "", "0");
		}
	}

	/**/

	@Schema
	@Builder
	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class TbuserCreateDto {
		@Schema(description = "username", example="이메일 주소")
		@NotNull
		@Email
		@NotEmpty
		@Size(max=100)
		private String username;

		@Schema(description = "password", example="비밀번호")
		@NotNull
		@NotEmpty
		@Size(max=50)
		private String password;

		@Schema(description = "nick", example="닉네임")
		@Size(max=50)
		private String nick;

		@Schema(description = "sfrom", example="가입 경로")
		@Size(max=50)
		private String sfrom;

		@Schema(description = "process", example="진행 단계")
		@Size(max=50)
		private String process;

		public Tbuser toEntity() {
			return Tbuser.of(username, password, nick, sfrom, process);
		}
	}
	@Schema
	@Builder
	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class TbuserAfterCreateDto {
		@Schema(description = "id", example="length32textnumber")
		private String id;
	}

	@Schema
	@Builder
	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class TbuserUpdateDto {
		@Schema(description = "id", example="length32textnumber")
		@NotNull
		@NotEmpty
		@Size(max=32)
		private String id;
		@Schema(description = "삭제 여부", example="Y")
		@Size(max=1)
		private String deleted;

		@Schema(description = "비밀번호", example="abcd1234!")
		@Size(max=20)
		private String password;
		@Schema(description = "닉네임", example="1234")
		@Size(max=20)
		private String nick;
		@Schema(description = "가입경로", example="1234")
		@Size(max=20)
		private String sfrom;
		@Schema(description = "단계", example="")
		@Size(max=20)
		private String process;
		@Schema(description = "이름", example="1234")
		@Size(max=20)
		private String name;
		@Schema(description = "전화번호", example="1234")
		@Size(max=20)
		private String phone;
		@Schema(description = "대표사진", example="~~~~.png")
		@Size(max=200)
		private String mpic;
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
	public static class TbuserAfterUpdateDto {
		@Schema(description = "id", example="length32textnumber")
		private String id;
		@Schema(description = "deleted", example="Y")
		private String deleted;
	}

	@Schema
	@Builder
	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class TbuserSelectDto {
		@Schema(description = "id", example="id")
		private String id;
		@Schema(description = "삭제 여부", example="Y")
		private String deleted;
		@Schema(description = "createdAt", example="2024-01-01 00:00:00.000000")
		private String createdAt;
		@Schema(description = "modifiedAt", example="2024-01-01 00:00:00.000000")
		private String modifiedAt;

		@Schema(description = "E-mail(id)", example="id")
		private String username;
		@Schema(description = "비밀번호", example="abcd1234!")
		private String password;
		@Schema(description = "닉네임", example="1234")
		private String nick;
		@Schema(description = "가입경로", example="")
		private String sfrom;
		@Schema(description = "단계", example="")
		private String process;
		@Schema(description = "이름", example="1234")
		private String name;
		@Schema(description = "전화번호", example="1234")
		private String phone;
		@Schema(description = "대표사진", example="~~~~.png")
		private String mpic;
		@Schema(description = "content", example="")
		private String content;
	}

	@Schema
	@Builder
	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class TbuserListDto {
		@Schema(description = "삭제 여부", example="Y")
		private String deleted;

		@Schema(description = "E-mail(id)", example="id")
		private String username;
		@Schema(description = "닉네임", example="1234")
		private String nick;
		@Schema(description = "이름", example="1234")
		private String name;
		@Schema(description = "단계", example="")
		private String process;
		@Schema(description = "전화번호", example="1234")
		private String phone;
	}

	@Schema
	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class TbuserPagedListDto extends CommonPagedListDto {
		@Schema(description = "삭제 여부", example="Y")
		private String deleted;

		@Schema(description = "E-mail(id)", example="id")
		private String username;
		@Schema(description = "닉네임", example="1234")
		private String nick;
		@Schema(description = "이름", example="1234")
		private String name;
		@Schema(description = "단계", example="")
		private String process;
		@Schema(description = "전화번호", example="1234")
		private String phone;
	}
	@Schema
	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class TbuserMoreListDto extends CommonMoreListDto {
		@Schema(description = "삭제 여부", example="Y")
		private String deleted;

		@Schema(description = "E-mail(id)", example="id")
		private String username;
		@Schema(description = "닉네임", example="1234")
		private String nick;
		@Schema(description = "이름", example="1234")
		private String name;
		@Schema(description = "단계", example="")
		private String process;
		@Schema(description = "전화번호", example="1234")
		private String phone;
	}
	
	
}