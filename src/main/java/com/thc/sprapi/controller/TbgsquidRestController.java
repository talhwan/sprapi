package com.thc.sprapi.controller;

import com.thc.sprapi.dto.*;
import com.thc.sprapi.security.PrincipalDetails;
import com.thc.sprapi.service.TbgrantService;
import com.thc.sprapi.service.TbgsquidService;
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

@Tag(name = "4-1. 오징어게임 API 안내",
        description = "오징어게임 관련 기능 정의한 RestController.")
@RequestMapping("/api/tbgsquid")
@RestController
public class TbgsquidRestController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final TbgrantService tbgrantService;
    private final TbgsquidService tbgsquidService;
    public TbgsquidRestController(TbgrantService tbgrantService, TbgsquidService tbgsquidService) {
        this.tbgrantService = tbgrantService;
        this.tbgsquidService = tbgsquidService;
    }

    @Operation(summary = "오징어게임 수정",
            description = "오징어게임 수정 위한 컨트롤러 (누구나 접근 가능) <br />"
                    + "@param TbgsquidUpdateDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<TbgsquidAfterUpdateDto\\> <br />"
                    + "@exception 해당 자료 없음 <br />"
    )
    @PreAuthorize("hasRole('USER')")
    @PutMapping("/next")
    public ResponseEntity<TbgsquidDto.TbgsquidAfterUpdateDto> next(@Valid @RequestBody TbgsquidDto.TbgsquidUpdateDto params, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        params.setNowGrant(tbgrantService.access("tbgsquid", "update",true, principalDetails.getTbuser().getId()));
        params.setNowTbuserId(principalDetails.getTbuser().getId());
        return ResponseEntity.status(HttpStatus.OK).body(tbgsquidService.next(params));
    }
    /**/
    @Operation(summary = "오징어게임 등록",
            description = "오징어게임 등록 위한 컨트롤러 (누구나 접근 가능) <br />"
                    + "@param TbgsquidCreateDto <br />"
                    + "@return HttpStatus.CREATED(201) ResponseEntity\\<TbgsquidAfterCreateDto\\> <br />"
                    + "@exception 중복 <br />"
    )
    @PostMapping("")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<TbgsquidDto.TbgsquidAfterCreateDto> save(@Valid @RequestBody TbgsquidDto.TbgsquidCreateDto params, HttpServletRequest request, HttpServletResponse response, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        params.setNowGrant(tbgrantService.access("tbgsquid", "create",true, principalDetails.getTbuser().getId()));
        params.setNowTbuserId(principalDetails.getTbuser().getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(tbgsquidService.create(params));
    }
    @Operation(summary = "오징어게임 수정",
            description = "오징어게임 수정 위한 컨트롤러 (누구나 접근 가능) <br />"
                    + "@param TbgsquidUpdateDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<TbgsquidAfterUpdateDto\\> <br />"
                    + "@exception 해당 자료 없음 <br />"
    )
    @PreAuthorize("hasRole('USER')")
    @PutMapping("")
    public ResponseEntity<TbgsquidDto.TbgsquidAfterUpdateDto> update(@Valid @RequestBody TbgsquidDto.TbgsquidUpdateDto params, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        params.setNowGrant(tbgrantService.access("tbgsquid", "update",true, principalDetails.getTbuser().getId()));
        params.setNowTbuserId(principalDetails.getTbuser().getId());
        return ResponseEntity.status(HttpStatus.OK).body(tbgsquidService.update(params));
    }

    @Operation(summary = "오징어게임 삭제",
            description = "오징어게임 삭제 위한 컨트롤러 (누구나 접근 가능) <br />"
                    + "@param TbgsquidUpdateDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<TbgsquidAfterUpdateDto\\> <br />"
                    + "@exception 해당 자료 없음 <br />"
    )
    @PreAuthorize("hasRole('USER')")
    @DeleteMapping("")
    public ResponseEntity<CommonDeleteListDto> deleteList(@Valid @RequestBody CommonDeleteListDto params, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        params.setNowGrant(tbgrantService.access("tbgsquid", "update",true, principalDetails.getTbuser().getId()));
        params.setNowTbuserId(principalDetails.getTbuser().getId());
        return ResponseEntity.status(HttpStatus.OK).body(tbgsquidService.deleteList(params));
    }

    @Operation(summary = "오징어게임 조회",
            description = "오징어게임 1개 조회 위한 컨트롤러 (모두 접근 가능) <br />"
                    + "@param id(PathVariable) <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<TbgsquidSelectDto\\> <br />"
                    + "@exception 정보 없음 <br />"
    )
    @PreAuthorize("permitAll()")
    @GetMapping("/{id}")
    public ResponseEntity<TbgsquidDto.TbgsquidSelectDto> detail(@PathVariable("id") String id, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        CommonDetailDto params = CommonDetailDto.builder().id(id).build();
        params.setNowGrant(tbgrantService.access("tbgsquid", "read",true, principalDetails.getTbuser().getId()));
        return ResponseEntity.status(HttpStatus.OK).body(tbgsquidService.detail(params));
    }
    @Operation(summary = "오징어게임 목록 조회(검색 기능 포함)",
            description = "오징어게임 목록 조회 위한 컨트롤러 (모두 접근 가능) <br />"
                    + "@param TbgsquidListDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<TbgsquidSelectDto\\> <br />"
                    + "@exception (no Exception) <br />"
    )
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/list")
    public ResponseEntity<List<TbgsquidDto.TbgsquidSelectDto>> list(@Valid @RequestBody TbgsquidDto.TbgsquidListDto params, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        params.setNowGrant(tbgrantService.access("tbgsquid", "read",false, principalDetails.getTbuser().getId()));
        params.setNowTbuserId(principalDetails.getTbuser().getId());
        return ResponseEntity.status(HttpStatus.OK).body(tbgsquidService.list(params));
    }
    @Operation(summary = "오징어게임 목록 조회 - 스크롤 (검색 기능 포함)",
            description = "게시판 목록 조회 - 스크롤 위한 컨트롤러 (누구나 접근 가능) <br />"
                    + "@param TbgsquidMoreListDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<Map<String, Object>\\> <br />"
                    + "@exception (no Exception) <br />"
    )
    @PreAuthorize("permitAll()")
    @PostMapping("/moreList")
    public ResponseEntity<List<TbgsquidDto.TbgsquidSelectDto>> moreList(@Valid @RequestBody TbgsquidDto.TbgsquidMoreListDto params, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        params.setNowGrant(tbgrantService.access("tbgsquid", "read",false, principalDetails.getTbuser().getId()));
        params.setNowTbuserId(principalDetails.getTbuser().getId());
        return ResponseEntity.status(HttpStatus.OK).body(tbgsquidService.moreList(params));
    }

    @Operation(summary = "오징어게임 목록 조회 - 페이지 (검색 기능 포함)",
            description = "오징어게임 목록 조회 - 페이지 위한 컨트롤러 (누구나 접근 가능) <br />"
                    + "@param TbgsquidPagedListDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<Map<String, Object>\\> <br />"
                    + "@exception (no Exception) <br />"
    )
    @PreAuthorize("permitAll()")
    @PostMapping("/pagedList")
    public ResponseEntity<CommonAfterPagedListDto<TbgsquidDto.TbgsquidSelectDto>> pagedList(@Valid @RequestBody TbgsquidDto.TbgsquidPagedListDto params, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        params.setNowGrant(tbgrantService.access("tbgsquid", "read",false, principalDetails.getTbuser().getId()));
        params.setNowTbuserId(principalDetails.getTbuser().getId());
        return ResponseEntity.status(HttpStatus.OK).body(tbgsquidService.pagedList(params));
    }

}
