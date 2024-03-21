package com.thc.sprapi.controller;

import com.thc.sprapi.dto.*;
import com.thc.sprapi.security.PrincipalDetails;
import com.thc.sprapi.service.TbgrantService;
import com.thc.sprapi.service.TbgsquidushotService;
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

@Tag(name = "4-1_1. 오징어게임 움직임 API 안내",
        description = "오징어게임 움직임 관련 기능 정의한 RestController.")
@RequestMapping("/api/tbgsquidushot")
@RestController
public class TbgsquidushotRestController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final TbgrantService tbgrantService;
    private final TbgsquidushotService tbgsquidushotService;
    public TbgsquidushotRestController(TbgrantService tbgrantService, TbgsquidushotService tbgsquidushotService) {
        this.tbgrantService = tbgrantService;
        this.tbgsquidushotService = tbgsquidushotService;
    }

    @Operation(summary = "오징어게임 움직임 참여",
            description = "오징어게임 움직임 신규 등록을 위한 컨트롤러 (누구나 접근 가능) <br />"
                    + "@param TbgsquidushotCreateDto <br />"
                    + "@return HttpStatus.CREATED(201) ResponseEntity\\<TbgsquidushotAfterCreateDto\\> <br />"
                    + "@exception 중복 <br />"
    )
    @PostMapping("/join")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<TbgsquidushotDto.TbgsquidushotAfterCreateDto> join(@Valid @RequestBody TbgsquidushotDto.TbgsquidushotCreateDto params, HttpServletRequest request, HttpServletResponse response, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        params.setNowGrant(tbgrantService.access("tbgsquid", "create",false, principalDetails.getTbuser().getId()));
        return ResponseEntity.status(HttpStatus.CREATED).body(tbgsquidushotService.join(params));
    }
    /**/
    @Operation(summary = "오징어게임 움직임 등록",
            description = "오징어게임 움직임 신규 등록을 위한 컨트롤러 (누구나 접근 가능) <br />"
                    + "@param TbgsquidushotCreateDto <br />"
                    + "@return HttpStatus.CREATED(201) ResponseEntity\\<TbgsquidushotAfterCreateDto\\> <br />"
                    + "@exception 중복 <br />"
    )
    @PostMapping("")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<TbgsquidushotDto.TbgsquidushotAfterCreateDto> save(@Valid @RequestBody TbgsquidushotDto.TbgsquidushotCreateDto params, HttpServletRequest request, HttpServletResponse response, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        params.setNowGrant(tbgrantService.access("tbgsquid", "create",true, principalDetails.getTbuser().getId()));
        return ResponseEntity.status(HttpStatus.CREATED).body(tbgsquidushotService.create(params));
    }
    @Operation(summary = "오징어게임 움직임 수정",
            description = "오징어게임 움직임 기존 수정을 위한 컨트롤러 (누구나 접근 가능) <br />"
                    + "@param TbgsquidushotUpdateDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<TbgsquidushotAfterUpdateDto\\> <br />"
                    + "@exception 해당 자료 없음 <br />"
    )
    @PutMapping("")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<TbgsquidushotDto.TbgsquidushotAfterUpdateDto> update(@Valid @RequestBody TbgsquidushotDto.TbgsquidushotUpdateDto params, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        params.setNowGrant(tbgrantService.access("tbgsquid", "update",true, principalDetails.getTbuser().getId()));
        return ResponseEntity.status(HttpStatus.OK).body(tbgsquidushotService.update(params));
    }
    @Operation(summary = "오징어게임 움직임 삭제",
            description = "오징어게임 움직임 삭제를 위한 컨트롤러 (누구나 접근 가능) <br />"
                    + "@param TbgsquidushotUpdateDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<TbgsquidushotAfterUpdateDto\\> <br />"
                    + "@exception 해당 자료 없음 <br />"
    )
    @DeleteMapping("")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CommonDeleteListDto> deleteList(@Valid @RequestBody CommonDeleteListDto params, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        params.setNowGrant(tbgrantService.access("tbgsquid", "update",true, principalDetails.getTbuser().getId()));
        return ResponseEntity.status(HttpStatus.OK).body(tbgsquidushotService.deleteList(params));
    }

    @Operation(summary = "오징어게임 움직임 조회",
            description = "오징어게임 움직임 1개 조회를 위한 컨트롤러 (모두 접근 가능) <br />"
                    + "@param id(PathVariable) <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<TbgsquidushotSelectDto\\> <br />"
                    + "@exception 정보 없음 <br />"
    )
    @GetMapping("/get/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<TbgsquidushotDto.TbgsquidushotSelectDto> detail(@PathVariable("id") String id, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        CommonDetailDto params = CommonDetailDto.builder().id(id).build();
        params.setNowGrant(tbgrantService.access("tbgsquid", "read",true, principalDetails.getTbuser().getId()));
        return ResponseEntity.status(HttpStatus.OK).body(tbgsquidushotService.detail(params));
    }
    @Operation(summary = "오징어게임 움직임 목록 조회(검색 기능 포함)",
            description = "오징어게임 움직임 목록 조회를 위한 컨트롤러 (모두 접근 가능) <br />"
                    + "@param TbgsquidushotListDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<TbgsquidushotSelectDto\\> <br />"
                    + "@exception (no Exception) <br />"
    )
    @GetMapping("/list")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<TbgsquidushotDto.TbgsquidushotSelectDto>> list(@Valid TbgsquidushotDto.TbgsquidushotListDto params, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        params.setNowGrant(tbgrantService.access("tbgsquid", "read",true, principalDetails.getTbuser().getId()));
        return ResponseEntity.status(HttpStatus.OK).body(tbgsquidushotService.list(params));
    }
    @Operation(summary = "오징어게임 움직임 목록 조회 - 스크롤 (검색 기능 포함)",
            description = "오징어게임 움직임 목록 조회 - 스크롤 위한 컨트롤러 (누구나 접근 가능) <br />"
                    + "@param TbgsquidushotMoreListDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<Map<String, Object>\\> <br />"
                    + "@exception (no Exception) <br />"
    )
    @GetMapping("/moreList")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<TbgsquidushotDto.TbgsquidushotSelectDto>> moreList(@Valid TbgsquidushotDto.TbgsquidushotMoreListDto params, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        params.setNowGrant(tbgrantService.access("tbgsquid", "read",true, principalDetails.getTbuser().getId()));
        return ResponseEntity.status(HttpStatus.OK).body(tbgsquidushotService.moreList(params));
    }

    @Operation(summary = "오징어게임 움직임 목록 조회 - 페이지 (검색 기능 포함)",
            description = "오징어게임 움직임 목록 조회 - 페이지 위한 컨트롤러 (누구나 접근 가능) <br />"
                    + "@param TbgsquidushotPagedListDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<Map<String, Object>\\> <br />"
                    + "@exception (no Exception) <br />"
    )
    @GetMapping("/pagedList")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CommonAfterPagedListDto<TbgsquidushotDto.TbgsquidushotSelectDto>> pagedList(@Valid TbgsquidushotDto.TbgsquidushotPagedListDto params, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        params.setNowGrant(tbgrantService.access("tbgsquid", "read",true, principalDetails.getTbuser().getId()));
        return ResponseEntity.status(HttpStatus.OK).body(tbgsquidushotService.pagedList(params));
    }

}
