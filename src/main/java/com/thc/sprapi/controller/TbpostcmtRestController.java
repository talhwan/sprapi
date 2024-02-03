package com.thc.sprapi.controller;

import com.thc.sprapi.dto.CommonAfterPagedListDto;
import com.thc.sprapi.dto.CommonDeleteListDto;
import com.thc.sprapi.dto.TbpostcmtDto;
import com.thc.sprapi.security.PrincipalDetails;
import com.thc.sprapi.service.TbpostcmtService;
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

@Tag(name = "2-1. 게시글 댓글 API 안내",
        description = "게시글 댓글 관련 기능 정의한 RestController.")
@RequestMapping("/api/tbpostcmt")
@RestController
public class TbpostcmtRestController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final TbpostcmtService tbpostcmtService;
    public TbpostcmtRestController(TbpostcmtService tbpostcmtService) {
        this.tbpostcmtService = tbpostcmtService;
    }

    @Operation(summary = "게시글 댓글 등록",
            description = "게시글 댓글 신규 등록을 위한 컨트롤러 (누구나 접근 가능) <br />"
                    + "@param TbpostcmtCreateDto <br />"
                    + "@return HttpStatus.CREATED(201) ResponseEntity\\<TbpostcmtAfterCreateDto\\> <br />"
                    + "@exception 중복 <br />"
    )
    @PostMapping("")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<TbpostcmtDto.TbpostcmtAfterCreateDto> save(@Valid @RequestBody TbpostcmtDto.TbpostcmtCreateDto params, HttpServletRequest request, HttpServletResponse response, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        params.setTbuserId(principalDetails.getTbuser().getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(tbpostcmtService.create(params));
    }
    @Operation(summary = "게시글 댓글 글 수정",
            description = "게시글 댓글 기존 글 수정을 위한 컨트롤러 (누구나 접근 가능) <br />"
                    + "@param TbpostcmtUpdateDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<TbpostcmtAfterUpdateDto\\> <br />"
                    + "@exception 해당 자료 없음 <br />"
    )
    @PutMapping("")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<TbpostcmtDto.TbpostcmtAfterUpdateDto> update(@Valid @RequestBody TbpostcmtDto.TbpostcmtUpdateDto params, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        return ResponseEntity.status(HttpStatus.OK).body(tbpostcmtService.update(params));
    }
    @Operation(summary = "게시글 댓글 글 삭제",
            description = "게시글 댓글 삭제를 위한 컨트롤러 (누구나 접근 가능) <br />"
                    + "@param TbpostcmtUpdateDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<TbpostcmtAfterUpdateDto\\> <br />"
                    + "@exception 해당 자료 없음 <br />"
    )
    @DeleteMapping("")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<CommonDeleteListDto> deleteList(@Valid @RequestBody CommonDeleteListDto params, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        params.setTbuserId(principalDetails.getTbuser().getId());
        return ResponseEntity.status(HttpStatus.OK).body(tbpostcmtService.deleteList(params));
    }

    @Operation(summary = "게시글 댓글 조회",
            description = "게시글 댓글 1개 조회를 위한 컨트롤러 (모두 접근 가능) <br />"
                    + "@param id(PathVariable) <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<TbpostcmtSelectDto\\> <br />"
                    + "@exception 정보 없음 <br />"
    )
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<TbpostcmtDto.TbpostcmtSelectDto> detail(@PathVariable("id") String id) {
        return ResponseEntity.status(HttpStatus.OK).body(tbpostcmtService.detail(id));
    }
    @Operation(summary = "게시글 댓글 목록 조회(검색 기능 포함)",
            description = "게시글 댓글 목록 조회를 위한 컨트롤러 (모두 접근 가능) <br />"
                    + "@param TbpostcmtListDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<TbpostcmtSelectDto\\> <br />"
                    + "@exception (no Exception) <br />"
    )
    @PostMapping("/list")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<TbpostcmtDto.TbpostcmtSelectDto>> list(@Valid @RequestBody TbpostcmtDto.TbpostcmtListDto params) {
        return ResponseEntity.status(HttpStatus.OK).body(tbpostcmtService.list(params));
    }
    @Operation(summary = "게시글 댓글 목록 조회 - 스크롤 (검색 기능 포함)",
            description = "게시글 댓글 목록 조회 - 스크롤 위한 컨트롤러 (누구나 접근 가능) <br />"
                    + "@param TbpostcmtMoreListDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<Map<String, Object>\\> <br />"
                    + "@exception (no Exception) <br />"
    )
    @PostMapping("/moreList")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<TbpostcmtDto.TbpostcmtSelectDto>> moreList(@Valid @RequestBody TbpostcmtDto.TbpostcmtMoreListDto params) {
        return ResponseEntity.status(HttpStatus.OK).body(tbpostcmtService.moreList(params));
    }

    @Operation(summary = "게시글 댓글 목록 조회 - 페이지 (검색 기능 포함)",
            description = "게시글 댓글 목록 조회 - 페이지 위한 컨트롤러 (누구나 접근 가능) <br />"
                    + "@param TbpostcmtPagedListDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<Map<String, Object>\\> <br />"
                    + "@exception (no Exception) <br />"
    )
    @PostMapping("/pagedList")
    @PreAuthorize("permitAll()")
    public ResponseEntity<CommonAfterPagedListDto<TbpostcmtDto.TbpostcmtSelectDto>> pagedList(@Valid @RequestBody TbpostcmtDto.TbpostcmtPagedListDto params) {
        return ResponseEntity.status(HttpStatus.OK).body(tbpostcmtService.pagedList(params));
    }

}
