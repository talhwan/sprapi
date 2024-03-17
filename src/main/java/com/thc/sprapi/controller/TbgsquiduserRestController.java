package com.thc.sprapi.controller;

import com.thc.sprapi.dto.*;
import com.thc.sprapi.security.PrincipalDetails;
import com.thc.sprapi.service.TbgrantService;
import com.thc.sprapi.service.TbgsquidService;
import com.thc.sprapi.service.TbgsquiduserService;
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

@Tag(name = "4-1_2. 오징어게임 사용자 API 안내",
        description = "오징어게임 사용자 관련 기능 정의한 RestController.")
@RequestMapping("/api/tbgsquiduser")
@RestController
public class TbgsquiduserRestController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final TbgrantService tbgrantService;
    private final TbgsquiduserService tbgsquiduserService;
    public TbgsquiduserRestController(TbgrantService tbgrantService, TbgsquiduserService tbgsquiduserService) {
        this.tbgrantService = tbgrantService;
        this.tbgsquiduserService = tbgsquiduserService;
    }

    @Operation(summary = "오징어게임 사용자 참여",
            description = "오징어게임 사용자 참여 위한 컨트롤러 (누구나 접근 가능) <br />"
                    + "@param TbgsquiduserCreateDto <br />"
                    + "@return HttpStatus.CREATED(201) ResponseEntity\\<TbgsquiduserAfterCreateDto\\> <br />"
                    + "@exception 중복 <br />"
    )
    @PostMapping("/join")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<TbgsquiduserDto.TbgsquiduserAfterCreateDto> join(@Valid @RequestBody TbgsquiduserDto.TbgsquiduserCreateDto params, HttpServletRequest request, HttpServletResponse response, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        params.setNowGrant(tbgrantService.access("tbgsquid", "create",false, principalDetails.getTbuser().getId()));
        params.setTbuserId(principalDetails.getTbuser().getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(tbgsquiduserService.join(params));
    }
    /*
     */

    @Operation(summary = "오징어게임 사용자 등록",
            description = "오징어게임 사용자 신규 등록을 위한 컨트롤러 (누구나 접근 가능) <br />"
                    + "@param TbgsquiduserCreateDto <br />"
                    + "@return HttpStatus.CREATED(201) ResponseEntity\\<TbgsquiduserAfterCreateDto\\> <br />"
                    + "@exception 중복 <br />"
    )
    @PostMapping("")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<TbgsquiduserDto.TbgsquiduserAfterCreateDto> save(@Valid @RequestBody TbgsquiduserDto.TbgsquiduserCreateDto params, HttpServletRequest request, HttpServletResponse response, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        params.setNowGrant(tbgrantService.access("tbgsquid", "create",true, principalDetails.getTbuser().getId()));
        params.setTbuserId(principalDetails.getTbuser().getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(tbgsquiduserService.create(params));
    }
    @Operation(summary = "오징어게임 사용자 수정",
            description = "오징어게임 사용자 기존 수정을 위한 컨트롤러 (누구나 접근 가능) <br />"
                    + "@param TbgsquiduserUpdateDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<TbgsquiduserAfterUpdateDto\\> <br />"
                    + "@exception 해당 자료 없음 <br />"
    )
    @PutMapping("")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<TbgsquiduserDto.TbgsquiduserAfterUpdateDto> update(@Valid @RequestBody TbgsquiduserDto.TbgsquiduserUpdateDto params, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        params.setNowGrant(tbgrantService.access("tbgsquid", "update",true, principalDetails.getTbuser().getId()));
        return ResponseEntity.status(HttpStatus.OK).body(tbgsquiduserService.update(params));
    }
    @Operation(summary = "오징어게임 사용자 삭제",
            description = "오징어게임 사용자 삭제를 위한 컨트롤러 (누구나 접근 가능) <br />"
                    + "@param TbgsquiduserUpdateDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<TbgsquiduserAfterUpdateDto\\> <br />"
                    + "@exception 해당 자료 없음 <br />"
    )
    @DeleteMapping("")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<CommonDeleteListDto> deleteList(@Valid @RequestBody CommonDeleteListDto params, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        params.setNowGrant(tbgrantService.access("tbgsquid", "update",true, principalDetails.getTbuser().getId()));
        return ResponseEntity.status(HttpStatus.OK).body(tbgsquiduserService.deleteList(params));
    }

    @Operation(summary = "오징어게임 사용자 조회",
            description = "오징어게임 사용자 1개 조회를 위한 컨트롤러 (모두 접근 가능) <br />"
                    + "@param id(PathVariable) <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<TbgsquiduserSelectDto\\> <br />"
                    + "@exception 정보 없음 <br />"
    )
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<TbgsquiduserDto.TbgsquiduserSelectDto> detail(@PathVariable("id") String id, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        CommonDetailDto params = CommonDetailDto.builder().id(id).build();
        params.setNowGrant(tbgrantService.access("tbgsquid", "read",false, principalDetails.getTbuser().getId()));
        return ResponseEntity.status(HttpStatus.OK).body(tbgsquiduserService.detail(params));
    }
    @Operation(summary = "오징어게임 사용자 목록 조회(검색 기능 포함)",
            description = "오징어게임 사용자 목록 조회를 위한 컨트롤러 (모두 접근 가능) <br />"
                    + "@param TbgsquiduserListDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<TbgsquiduserSelectDto\\> <br />"
                    + "@exception (no Exception) <br />"
    )
    @PostMapping("/list")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<TbgsquiduserDto.TbgsquiduserSelectDto>> list(@Valid @RequestBody TbgsquiduserDto.TbgsquiduserListDto params, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        params.setNowGrant(tbgrantService.access("tbgsquid", "read",false, principalDetails.getTbuser().getId()));
        return ResponseEntity.status(HttpStatus.OK).body(tbgsquiduserService.list(params));
    }
    @Operation(summary = "오징어게임 사용자 목록 조회 - 스크롤 (검색 기능 포함)",
            description = "오징어게임 사용자 목록 조회 - 스크롤 위한 컨트롤러 (누구나 접근 가능) <br />"
                    + "@param TbgsquiduserMoreListDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<Map<String, Object>\\> <br />"
                    + "@exception (no Exception) <br />"
    )
    @PostMapping("/moreList")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<TbgsquiduserDto.TbgsquiduserSelectDto>> moreList(@Valid @RequestBody TbgsquiduserDto.TbgsquiduserMoreListDto params, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        params.setNowGrant(tbgrantService.access("tbgsquid", "read",false, principalDetails.getTbuser().getId()));
        return ResponseEntity.status(HttpStatus.OK).body(tbgsquiduserService.moreList(params));
    }

    @Operation(summary = "오징어게임 사용자 목록 조회 - 페이지 (검색 기능 포함)",
            description = "오징어게임 사용자 목록 조회 - 페이지 위한 컨트롤러 (누구나 접근 가능) <br />"
                    + "@param TbgsquiduserPagedListDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<Map<String, Object>\\> <br />"
                    + "@exception (no Exception) <br />"
    )
    @PostMapping("/pagedList")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CommonAfterPagedListDto<TbgsquiduserDto.TbgsquiduserSelectDto>> pagedList(@Valid @RequestBody TbgsquiduserDto.TbgsquiduserPagedListDto params, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        params.setNowGrant(tbgrantService.access("tbgsquid", "read",false, principalDetails.getTbuser().getId()));
        return ResponseEntity.status(HttpStatus.OK).body(tbgsquiduserService.pagedList(params));
    }

}
