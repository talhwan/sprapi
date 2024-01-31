package com.thc.sprapi.controller;

import com.thc.sprapi.dto.CommonAfterPagedListDto;
import com.thc.sprapi.dto.TbboardDto;
import com.thc.sprapi.dto.TbpicDto;
import com.thc.sprapi.security.PrincipalDetails;
import com.thc.sprapi.service.TbboardService;
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
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "1. 게시판 API 안내",
        description = "게시판 관련 기능 정의한 RestController.")
@RequestMapping("/api/tbboard")
@RestController
public class TbboardRestController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final TbboardService tbboardService;
    public TbboardRestController(TbboardService tbboardService) {
        this.tbboardService = tbboardService;
    }

    @Operation(summary = "게시판 글 등록",
            description = "게시판 신규 글 등록을 위한 컨트롤러 (누구나 접근 가능) <br />"
                    + "@param TbboardCreateDto <br />"
                    + "@return HttpStatus.CREATED(201) ResponseEntity\\<TbboardAfterCreateDto\\> <br />"
                    + "@exception 중복 <br />"
    )
    @PostMapping("")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<TbboardDto.TbboardAfterCreateDto> save(@Valid @RequestBody TbboardDto.TbboardCreateDto params, HttpServletRequest request, HttpServletResponse response, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        params.setTbuserId(principalDetails.getTbuser().getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(tbboardService.create(params));
    }
    @Operation(summary = "게시판 글 수정",
            description = "게시판 기존 글 수정을 위한 컨트롤러 (누구나 접근 가능) <br />"
                    + "@param TbboardUpdateDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<TbboardAfterUpdateDto\\> <br />"
                    + "@exception 해당 자료 없음 <br />"
    )
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("")
    public ResponseEntity<TbboardDto.TbboardAfterUpdateDto> update(@Valid @RequestBody TbboardDto.TbboardUpdateDto params) {
        return ResponseEntity.status(HttpStatus.OK).body(tbboardService.update(params));
    }

    @Operation(summary = "게시판 글 삭제",
            description = "게시판 글 삭제를 위한 컨트롤러 (누구나 접근 가능) <br />"
                    + "@param TbboardUpdateDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<TbboardAfterUpdateDto\\> <br />"
                    + "@exception 해당 자료 없음 <br />"
    )
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("")
    public ResponseEntity<TbboardDto.TbboardAfterUpdateDto> delete(@Valid @RequestBody TbboardDto.TbboardUpdateDto params) {
        return ResponseEntity.status(HttpStatus.OK).body(tbboardService.delete(params));
    }

    @Operation(summary = "게시판 글 정보 조회",
            description = "게시판 글 1개 정보 조회를 위한 컨트롤러 (모두 접근 가능) <br />"
                    + "@param id(PathVariable) <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<TbboardSelectDto\\> <br />"
                    + "@exception 정보 없음 <br />"
    )
    @PreAuthorize("permitAll()")
    @GetMapping("/{id}")
    public ResponseEntity<TbboardDto.TbboardSelectDto> detail(@PathVariable("id") String id) {
        return ResponseEntity.status(HttpStatus.OK).body(tbboardService.detail(id));
    }
    @Operation(summary = "게시판 글 정보 목록 조회(검색 기능 포함)",
            description = "게시판 글 전체 목록 조회를 위한 컨트롤러 (모두 접근 가능) <br />"
                    + "@param (no parameter) <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<TbboardSelectDto\\> <br />"
                    + "@exception (no Exception) <br />"
    )
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/list")
    public ResponseEntity<List<TbboardDto.TbboardSelectDto>> list(@Valid @RequestBody TbboardDto.TbboardListDto params) {
        return ResponseEntity.status(HttpStatus.OK).body(tbboardService.list(params));
    }
    @Operation(summary = "게시판 글 정보 추가조회 목록 조회(검색 기능 포함)",
            description = "게시판 추가 조회한 글 검색을 위한 컨트롤러 (누구나 접근 가능) <br />"
                    + "@param TbboardSearchDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<Map<String, Object>\\> <br />"
                    + "@exception (no Exception) <br />"
    )
    @PreAuthorize("permitAll()")
    @PostMapping("/moreList")
    public ResponseEntity<List<TbboardDto.TbboardSelectDto>> moreList(@Valid @RequestBody TbboardDto.TbboardMoreListDto params) {
        return ResponseEntity.status(HttpStatus.OK).body(tbboardService.moreList(params));
    }

    @Operation(summary = "게시판 글 정보 페이징 처리 한 목록 조회(검색 기능 포함)",
            description = "게시판 페이징 처리 한 글 검색을 위한 컨트롤러 (누구나 접근 가능) <br />"
                    + "@param TbboardSearchDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<Map<String, Object>\\> <br />"
                    + "@exception (no Exception) <br />"
    )
    @PreAuthorize("permitAll()")
    @PostMapping("/pagedList")
    public ResponseEntity<CommonAfterPagedListDto<TbboardDto.TbboardSelectDto>> pagedList(@Valid @RequestBody TbboardDto.TbboardPagedListDto params) {
        return ResponseEntity.status(HttpStatus.OK).body(tbboardService.pagedList(params));
    }

}
