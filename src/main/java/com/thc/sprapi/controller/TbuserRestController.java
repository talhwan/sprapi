package com.thc.sprapi.controller;

import com.thc.sprapi.dto.*;
import com.thc.sprapi.security.JwtTokenDto;
import com.thc.sprapi.security.PrincipalDetails;
import com.thc.sprapi.service.TbgrantService;
import com.thc.sprapi.service.TbuserService;
import com.thc.sprapi.util.FileUpload;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Tag(name = "1-2. 회원 API 안내",
        description = "회원 관련 기능 정의한 RestController.")
@RequestMapping("/api/tbuser")
@RestController
public class TbuserRestController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final TbgrantService tbgrantService;
    private final TbuserService tbuserService;
    public TbuserRestController(TbgrantService tbgrantService, TbuserService tbuserService) {
        this.tbgrantService = tbgrantService;
        this.tbuserService = tbuserService;
    }

    //TO-DO
    // /qrcode 요청 만들고 리턴은 {code:""}

    @Operation(summary = "본인인증 토큰 발급",
            description = "본인인증 토큰 발급 위한 컨트롤러 (누구나 접근 가능) <br />"
                    + "@param String token <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<JwtTokenDto\\> <br />"
                    + "@exception 중복 <br />"
    )
    @PreAuthorize("permitAll()")
    @PostMapping("/okcert")
    public ResponseEntity<TbuserDto.TbuserOkcertTokenDto> okcert(HttpServletRequest request) throws Exception {
        logger.info("okcert");
        return ResponseEntity.status(HttpStatus.OK).body(
                tbuserService.okcert(FileUpload.rootPath(request))
        );
    }
    @Operation(summary = "회원 NAVER 로그인",
            description = "회원 NAVER 로그인 위한 컨트롤러 (누구나 접근 가능) <br />"
                    + "@param String token <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<JwtTokenDto\\> <br />"
                    + "@exception 중복 <br />"
    )
    @PreAuthorize("permitAll()")
    @PostMapping("/naver")
    public ResponseEntity<JwtTokenDto> naver(@Valid @RequestBody String token) {
        logger.info("token : " + token);
        return ResponseEntity.status(HttpStatus.CREATED).body(tbuserService.naver(token));
    }
    @Operation(summary = "회원 가입",
            description = "회원 가입 위한 컨트롤러 (누구나 접근 가능) <br />"
                    + "@param TbuserCreateDto <br />"
                    + "@return HttpStatus.CREATED(201) ResponseEntity\\<TbuserAfterCreateDto\\> <br />"
                    + "@exception 중복 <br />"
    )
    @PreAuthorize("permitAll()")
    @PostMapping("/signup")
    public ResponseEntity<TbuserDto.TbuserAfterCreateDto> signup(@Valid @RequestBody TbuserDto.TbuserCreateDto params) {
        return ResponseEntity.status(HttpStatus.CREATED).body(tbuserService.signup(params));
    }
    @Operation(summary = "회원 로그아웃",
            description = "회원 로그아웃 위한 컨트롤러 (누구나 접근 가능) <br />"
                    + "@param TbuserCreateDto <br />"
                    + "@return HttpStatus.CREATED(201) ResponseEntity\\<TbuserAfterCreateDto\\> <br />"
                    + "@exception 중복 <br />"
    )
    @PreAuthorize("hasRole('USER')")
    @PostMapping("/logout")
    public ResponseEntity<TbuserDto.TbuserAfterUpdateDto> logout(@AuthenticationPrincipal PrincipalDetails principalDetails) {
        return ResponseEntity.status(HttpStatus.CREATED).body(tbuserService.logout(TbuserDto.TbuserUpdateDto.builder().id(principalDetails.getTbuser().getId()).build()));
    }

    /*
    이 아래는 기본기능입니다!!!
     */

    @Operation(summary = "회원 등록",
            description = "회원 신규 등록 위한 컨트롤러 (누구나 접근 가능) <br />"
                    + "@param TbuserCreateDto <br />"
                    + "@return HttpStatus.CREATED(201) ResponseEntity\\<TbuserAfterCreateDto\\> <br />"
                    + "@exception 중복 <br />"
    )
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("")
    public ResponseEntity<TbuserDto.TbuserAfterCreateDto> save(@Valid @RequestBody TbuserDto.TbuserCreateDto params, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        params.setNowGrant(tbgrantService.access("tbuser", "create",false, principalDetails.getTbuser().getId()));
        return ResponseEntity.status(HttpStatus.CREATED).body(tbuserService.create(params));
    }

    @Operation(summary = "회원 정보 수정",
            description = "회원 정보 수정 위한 컨트롤러 (누구나 접근 가능) <br />"
                    + "@param TbuserUpdateDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<TbuserAfterUpdateDto\\> <br />"
                    + "@exception 해당 자료 없음 <br />"
    )
    @PreAuthorize("hasRole('USER')")
    @PutMapping("")
    public ResponseEntity<TbuserDto.TbuserAfterUpdateDto> update(@Valid @RequestBody TbuserDto.TbuserUpdateDto params, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        params.setNowGrant(tbgrantService.access("tbuser", "update",false, principalDetails.getTbuser().getId()));
        return ResponseEntity.status(HttpStatus.OK).body(tbuserService.update(params));
    }

    @Operation(summary = "회원 일괄 삭제",
            description = "회원 일괄 삭제 위한 컨트롤러 <br />"
                    + "@param CommonDeleteListDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<CommonDeleteListDto\\> <br />"
                    + "@exception 해당 자료 없음 <br />"
    )
    @PreAuthorize("hasRole('USER')")
    @DeleteMapping("")
    public ResponseEntity<CommonDeleteListDto> deleteList(@Valid @RequestBody CommonDeleteListDto params, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        params.setNowGrant(tbgrantService.access("tbuser", "update",true, principalDetails.getTbuser().getId()));
        return ResponseEntity.status(HttpStatus.OK).body(tbuserService.deleteList(params));
    }

    @Operation(summary = "회원 정보 조회",
            description = "회원 정보 1개 정보 조회를 위한 컨트롤러 (모두 접근 가능) <br />"
                    + "@param id(PathVariable) <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<TbuserSelectDto\\> <br />"
                    + "@exception 정보 없음 <br />"
    )
    @PreAuthorize("permitAll()")
    @GetMapping("/get/{id}")
    public ResponseEntity<TbuserDto.TbuserSelectDto> detail(@PathVariable("id") String id, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        CommonDetailDto params = CommonDetailDto.builder().id(id).build();
        return ResponseEntity.status(HttpStatus.OK).body(tbuserService.detail(params));
    }
    @Operation(summary = "회원 정보 목록 조회(검색 기능 포함)",
            description = "회원 정보 전체 목록 조회를 위한 컨트롤러 (모두 접근 가능) <br />"
                    + "@param (no parameter) <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<TbuserSelectDto\\> <br />"
                    + "@exception (no Exception) <br />"
    )
    @GetMapping("/list")
    public ResponseEntity<List<TbuserDto.TbuserSelectDto>> list(@Valid TbuserDto.TbuserListDto params, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        return ResponseEntity.status(HttpStatus.OK).body(tbuserService.list(params));
    }
    @Operation(summary = "회원 정보 추가조회 목록 조회(검색 기능 포함)",
            description = "회원 추가 조회한 정보 검색을 위한 컨트롤러 (누구나 접근 가능) <br />"
                    + "@param TbuserSearchDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<Map<String, Object>\\> <br />"
                    + "@exception (no Exception) <br />"
    )
    @GetMapping("/moreList")
    public ResponseEntity<List<TbuserDto.TbuserSelectDto>> moreList(@Valid TbuserDto.TbuserMoreListDto params, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        return ResponseEntity.status(HttpStatus.OK).body(tbuserService.moreList(params));
    }

    @Operation(summary = "회원 정보 페이징 처리 한 목록 조회(검색 기능 포함)",
            description = "회원 페이징 처리 한 정보 검색을 위한 컨트롤러 (누구나 접근 가능) <br />"
                    + "@param TbuserSearchDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<Map<String, Object>\\> <br />"
                    + "@exception (no Exception) <br />"
    )
    @GetMapping("/pagedList")
    public ResponseEntity<CommonAfterPagedListDto<TbuserDto.TbuserSelectDto>> pagedList(@Valid TbuserDto.TbuserPagedListDto params, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        return ResponseEntity.status(HttpStatus.OK).body(tbuserService.pagedList(params));
    }

}
