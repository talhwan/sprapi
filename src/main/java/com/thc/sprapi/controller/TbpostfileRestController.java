package com.thc.sprapi.controller;

import com.thc.sprapi.dto.CommonAfterPagedListDto;
import com.thc.sprapi.dto.CommonDeleteListDto;
import com.thc.sprapi.dto.CommonDetailDto;
import com.thc.sprapi.dto.TbpostfileDto;
import com.thc.sprapi.security.PrincipalDetails;
import com.thc.sprapi.service.TbpostfileService;
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

@Tag(name = "2-5_1. 게시글 파일 API 안내",
        description = "게시글 파일 관련 기능 정의한 RestController.")
@RequestMapping("/api/tbpostfile")
@RestController
public class TbpostfileRestController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final TbpostfileService tbpostfileService;
    public TbpostfileRestController(TbpostfileService tbpostfileService) {
        this.tbpostfileService = tbpostfileService;
    }

    @Operation(summary = "게시글 파일 등록",
            description = "게시글 파일 신규 등록을 위한 컨트롤러 (누구나 접근 가능) <br />"
                    + "@param TbpostfileCreateDto <br />"
                    + "@return HttpStatus.CREATED(201) ResponseEntity\\<TbpostfileAfterCreateDto\\> <br />"
                    + "@exception 중복 <br />"
    )
    @PostMapping("")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<TbpostfileDto.TbpostfileAfterCreateDto> save(@Valid @RequestBody TbpostfileDto.TbpostfileCreateDto params, HttpServletRequest request, HttpServletResponse response, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        return ResponseEntity.status(HttpStatus.CREATED).body(tbpostfileService.create(params));
    }
    @Operation(summary = "게시글 파일 글 수정",
            description = "게시글 파일 기존 글 수정을 위한 컨트롤러 (누구나 접근 가능) <br />"
                    + "@param TbpostfileUpdateDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<TbpostfileAfterUpdateDto\\> <br />"
                    + "@exception 해당 자료 없음 <br />"
    )
    @PutMapping("")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<TbpostfileDto.TbpostfileAfterUpdateDto> update(@Valid @RequestBody TbpostfileDto.TbpostfileUpdateDto params, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        return ResponseEntity.status(HttpStatus.OK).body(tbpostfileService.update(params));
    }
    @Operation(summary = "게시글 파일 글 삭제",
            description = "게시글 파일 삭제를 위한 컨트롤러 (누구나 접근 가능) <br />"
                    + "@param TbpostfileUpdateDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<TbpostfileAfterUpdateDto\\> <br />"
                    + "@exception 해당 자료 없음 <br />"
    )
    @DeleteMapping("")
    public ResponseEntity<CommonDeleteListDto> deleteList(@Valid @RequestBody CommonDeleteListDto params, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        return ResponseEntity.status(HttpStatus.OK).body(tbpostfileService.deleteList(params));
    }

    @Operation(summary = "게시글 파일 조회",
            description = "게시글 파일 1개 조회를 위한 컨트롤러 (모두 접근 가능) <br />"
                    + "@param id(PathVariable) <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<TbpostfileSelectDto\\> <br />"
                    + "@exception 정보 없음 <br />"
    )
    @GetMapping("/get/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<TbpostfileDto.TbpostfileSelectDto> detail(@PathVariable("id") String id) {
        CommonDetailDto params = CommonDetailDto.builder().id(id).build();
        return ResponseEntity.status(HttpStatus.OK).body(tbpostfileService.detail(params));
    }
    @Operation(summary = "게시글 파일 목록 조회(검색 기능 포함)",
            description = "게시글 파일 목록 조회를 위한 컨트롤러 (모두 접근 가능) <br />"
                    + "@param TbpostfileListDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<TbpostfileSelectDto\\> <br />"
                    + "@exception (no Exception) <br />"
    )
    @GetMapping("/list")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<TbpostfileDto.TbpostfileSelectDto>> list(@Valid TbpostfileDto.TbpostfileListDto params) {
        return ResponseEntity.status(HttpStatus.OK).body(tbpostfileService.list(params));
    }
    @Operation(summary = "게시글 파일 목록 조회 - 스크롤 (검색 기능 포함)",
            description = "게시글 파일 목록 조회 - 스크롤 위한 컨트롤러 (누구나 접근 가능) <br />"
                    + "@param TbpostfileMoreListDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<Map<String, Object>\\> <br />"
                    + "@exception (no Exception) <br />"
    )
    @GetMapping("/moreList")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<TbpostfileDto.TbpostfileSelectDto>> moreList(@Valid TbpostfileDto.TbpostfileMoreListDto params) {
        return ResponseEntity.status(HttpStatus.OK).body(tbpostfileService.moreList(params));
    }

    @Operation(summary = "게시글 파일 목록 조회 - 페이지 (검색 기능 포함)",
            description = "게시글 파일 목록 조회 - 페이지 위한 컨트롤러 (누구나 접근 가능) <br />"
                    + "@param TbpostfilePagedListDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<Map<String, Object>\\> <br />"
                    + "@exception (no Exception) <br />"
    )
    @GetMapping("/pagedList")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CommonAfterPagedListDto<TbpostfileDto.TbpostfileSelectDto>> pagedList(@Valid TbpostfileDto.TbpostfilePagedListDto params) {
        return ResponseEntity.status(HttpStatus.OK).body(tbpostfileService.pagedList(params));
    }

}
