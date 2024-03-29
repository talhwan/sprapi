package com.thc.sprapi.controller;

import com.thc.sprapi.dto.*;
import com.thc.sprapi.security.PrincipalDetails;
import com.thc.sprapi.service.TbgrantService;
import com.thc.sprapi.service.TbgrantpartService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "1-1_1. 접근권한 기능 API 안내",
        description = "접근권한 기능 관련 기능 정의한 RestController.")
@RequestMapping("/api/tbgrantpart")
@RestController
public class TbgrantpartRestController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final TbgrantService tbgrantService;
    private final TbgrantpartService tbgrantpartService;
    public TbgrantpartRestController(TbgrantService tbgrantService, TbgrantpartService tbgrantpartService) {
        this.tbgrantService = tbgrantService;
        this.tbgrantpartService = tbgrantpartService;
    }

    @Operation(summary = "접근권한 기능 토글",
            description = "접근권한 기능 토글 위한 컨트롤러 (누구나 접근 가능) <br />"
                    + "@param TbgrantpartCreateDto <br />"
                    + "@return HttpStatus.CREATED(201) ResponseEntity\\<TbgrantpartAfterCreateDto\\> <br />"
                    + "@exception 중복 <br />"
    )
    @PostMapping("/toggle")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<TbgrantpartDto.TbgrantpartAfterCreateDto> save(@Valid @RequestBody TbgrantpartDto.TbgrantpartToggleDto params, HttpServletRequest request, HttpServletResponse response, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        params.setNowGrant(tbgrantService.access("tbgrant", "update",true, principalDetails.getTbuser().getId()));
        return ResponseEntity.status(HttpStatus.CREATED).body(tbgrantpartService.toggle(params));
    }
    @Operation(summary = "접근권한 기능 등록",
            description = "접근권한 기능 신규 등록을 위한 컨트롤러 (누구나 접근 가능) <br />"
                    + "@param TbgrantpartCreateDto <br />"
                    + "@return HttpStatus.CREATED(201) ResponseEntity\\<TbgrantpartAfterCreateDto\\> <br />"
                    + "@exception 중복 <br />"
    )
    @PostMapping("")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<TbgrantpartDto.TbgrantpartAfterCreateDto> save(@Valid @RequestBody TbgrantpartDto.TbgrantpartCreateDto params, HttpServletRequest request, HttpServletResponse response, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        params.setNowGrant(tbgrantService.access("tbgrant", "create",true, principalDetails.getTbuser().getId()));
        return ResponseEntity.status(HttpStatus.CREATED).body(tbgrantpartService.create(params));
    }
    @Operation(summary = "접근권한 기능 글 수정",
            description = "접근권한 기능 기존 글 수정을 위한 컨트롤러 (누구나 접근 가능) <br />"
                    + "@param TbgrantpartUpdateDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<TbgrantpartAfterUpdateDto\\> <br />"
                    + "@exception 해당 자료 없음 <br />"
    )
    @PutMapping("")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<TbgrantpartDto.TbgrantpartAfterUpdateDto> update(@Valid @RequestBody TbgrantpartDto.TbgrantpartUpdateDto params, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        params.setNowGrant(tbgrantService.access("tbgrant", "update",true, principalDetails.getTbuser().getId()));
        return ResponseEntity.status(HttpStatus.OK).body(tbgrantpartService.update(params));
    }
    @Operation(summary = "접근권한 기능 글 삭제",
            description = "접근권한 기능 삭제를 위한 컨트롤러 (누구나 접근 가능) <br />"
                    + "@param TbgrantpartUpdateDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<TbgrantpartAfterUpdateDto\\> <br />"
                    + "@exception 해당 자료 없음 <br />"
    )
    @DeleteMapping("")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<CommonDeleteListDto> deleteList(@Valid @RequestBody CommonDeleteListDto params, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        params.setNowGrant(tbgrantService.access("tbgrant", "update",true, principalDetails.getTbuser().getId()));
        return ResponseEntity.status(HttpStatus.OK).body(tbgrantpartService.deleteList(params));
    }

    @Operation(summary = "접근권한 기능 조회",
            description = "접근권한 기능 1개 조회를 위한 컨트롤러 (모두 접근 가능) <br />"
                    + "@param id(PathVariable) <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<TbgrantpartSelectDto\\> <br />"
                    + "@exception 정보 없음 <br />"
    )
    @GetMapping("/get/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<TbgrantpartDto.TbgrantpartSelectDto> detail(@PathVariable("id") String id, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        CommonDetailDto params = CommonDetailDto.builder().id(id).build();
        params.setNowGrant(tbgrantService.access("tbgrant", "read",true, principalDetails.getTbuser().getId()));
        return ResponseEntity.status(HttpStatus.OK).body(tbgrantpartService.detail(params));
    }
    @Operation(summary = "접근권한 기능 목록 조회(검색 기능 포함)",
            description = "접근권한 기능 목록 조회를 위한 컨트롤러 (모두 접근 가능) <br />"
                    + "@param TbgrantpartListDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<TbgrantpartSelectDto\\> <br />"
                    + "@exception (no Exception) <br />"
    )
    @GetMapping("/list")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<TbgrantpartDto.TbgrantpartSelectDto>> list(@Valid TbgrantpartDto.TbgrantpartListDto params, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        params.setNowGrant(tbgrantService.access("tbgrant", "read",true, principalDetails.getTbuser().getId()));
        return ResponseEntity.status(HttpStatus.OK).body(tbgrantpartService.list(params));
    }
    @Operation(summary = "접근권한 기능 목록 조회 - 스크롤 (검색 기능 포함)",
            description = "접근권한 기능 목록 조회 - 스크롤 위한 컨트롤러 (누구나 접근 가능) <br />"
                    + "@param TbgrantpartMoreListDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<Map<String, Object>\\> <br />"
                    + "@exception (no Exception) <br />"
    )
    @GetMapping("/moreList")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<TbgrantpartDto.TbgrantpartSelectDto>> moreList(@Valid TbgrantpartDto.TbgrantpartMoreListDto params, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        params.setNowGrant(tbgrantService.access("tbgrant", "read",true, principalDetails.getTbuser().getId()));
        return ResponseEntity.status(HttpStatus.OK).body(tbgrantpartService.moreList(params));
    }

    @Operation(summary = "접근권한 기능 목록 조회 - 페이지 (검색 기능 포함)",
            description = "접근권한 기능 목록 조회 - 페이지 위한 컨트롤러 (누구나 접근 가능) <br />"
                    + "@param TbgrantpartPagedListDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<Map<String, Object>\\> <br />"
                    + "@exception (no Exception) <br />"
    )
    @GetMapping("/pagedList")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CommonAfterPagedListDto<TbgrantpartDto.TbgrantpartSelectDto>> pagedList(@Valid TbgrantpartDto.TbgrantpartPagedListDto params, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        params.setNowGrant(tbgrantService.access("tbgrant", "read",true, principalDetails.getTbuser().getId()));
        return ResponseEntity.status(HttpStatus.OK).body(tbgrantpartService.pagedList(params));
    }

}
