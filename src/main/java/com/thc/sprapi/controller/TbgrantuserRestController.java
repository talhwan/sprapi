package com.thc.sprapi.controller;

import com.thc.sprapi.dto.CommonAfterPagedListDto;
import com.thc.sprapi.dto.CommonDeleteListDto;
import com.thc.sprapi.dto.TbgrantuserDto;
import com.thc.sprapi.security.PrincipalDetails;
import com.thc.sprapi.service.TbgrantuserService;
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
@RequestMapping("/api/tbgrantuser")
@RestController
public class TbgrantuserRestController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final TbgrantuserService tbgrantuserService;
    public TbgrantuserRestController(TbgrantuserService tbgrantuserService) {
        this.tbgrantuserService = tbgrantuserService;
    }

    @Operation(summary = "게시글 댓글 목록 조회 - 스크롤 (검색 기능 포함)",
            description = "게시글 댓글 목록 조회 - 스크롤 위한 컨트롤러 (누구나 접근 가능) <br />"
                    + "@param TbgrantuserMoreListDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<Map<String, Object>\\> <br />"
                    + "@exception (no Exception) <br />"
    )
    @PostMapping("/moreListByTbgrantId")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<TbgrantuserDto.TbgrantuserSelectDto>> moreListByTbgrantId(@Valid @RequestBody TbgrantuserDto.TbgrantuserMoreListDto params) {
        return ResponseEntity.status(HttpStatus.OK).body(tbgrantuserService.moreListByTbgrantId(params));
    }

    /**/

    @Operation(summary = "게시글 댓글 등록",
            description = "게시글 댓글 신규 등록을 위한 컨트롤러 (누구나 접근 가능) <br />"
                    + "@param TbgrantuserCreateDto <br />"
                    + "@return HttpStatus.CREATED(201) ResponseEntity\\<TbgrantuserAfterCreateDto\\> <br />"
                    + "@exception 중복 <br />"
    )
    @PostMapping("")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<TbgrantuserDto.TbgrantuserAfterCreateDto> save(@Valid @RequestBody TbgrantuserDto.TbgrantuserCreateDto params, HttpServletRequest request, HttpServletResponse response, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        params.setTbuserId(principalDetails.getTbuser().getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(tbgrantuserService.create(params));
    }
    @Operation(summary = "게시글 댓글 글 수정",
            description = "게시글 댓글 기존 글 수정을 위한 컨트롤러 (누구나 접근 가능) <br />"
                    + "@param TbgrantuserUpdateDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<TbgrantuserAfterUpdateDto\\> <br />"
                    + "@exception 해당 자료 없음 <br />"
    )
    @PutMapping("")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<TbgrantuserDto.TbgrantuserAfterUpdateDto> update(@Valid @RequestBody TbgrantuserDto.TbgrantuserUpdateDto params, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        return ResponseEntity.status(HttpStatus.OK).body(tbgrantuserService.update(params));
    }
    @Operation(summary = "게시글 댓글 글 삭제",
            description = "게시글 댓글 삭제를 위한 컨트롤러 (누구나 접근 가능) <br />"
                    + "@param TbgrantuserUpdateDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<TbgrantuserAfterUpdateDto\\> <br />"
                    + "@exception 해당 자료 없음 <br />"
    )
    @DeleteMapping("")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<CommonDeleteListDto> deleteList(@Valid @RequestBody CommonDeleteListDto params, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        return ResponseEntity.status(HttpStatus.OK).body(tbgrantuserService.deleteList(params));
    }

    @Operation(summary = "게시글 댓글 조회",
            description = "게시글 댓글 1개 조회를 위한 컨트롤러 (모두 접근 가능) <br />"
                    + "@param id(PathVariable) <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<TbgrantuserSelectDto\\> <br />"
                    + "@exception 정보 없음 <br />"
    )
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<TbgrantuserDto.TbgrantuserSelectDto> detail(@PathVariable("id") String id) {
        return ResponseEntity.status(HttpStatus.OK).body(tbgrantuserService.detail(id));
    }
    @Operation(summary = "게시글 댓글 목록 조회(검색 기능 포함)",
            description = "게시글 댓글 목록 조회를 위한 컨트롤러 (모두 접근 가능) <br />"
                    + "@param TbgrantuserListDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<TbgrantuserSelectDto\\> <br />"
                    + "@exception (no Exception) <br />"
    )
    @PostMapping("/list")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<TbgrantuserDto.TbgrantuserSelectDto>> list(@Valid @RequestBody TbgrantuserDto.TbgrantuserListDto params) {
        return ResponseEntity.status(HttpStatus.OK).body(tbgrantuserService.list(params));
    }
    @Operation(summary = "게시글 댓글 목록 조회 - 스크롤 (검색 기능 포함)",
            description = "게시글 댓글 목록 조회 - 스크롤 위한 컨트롤러 (누구나 접근 가능) <br />"
                    + "@param TbgrantuserMoreListDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<Map<String, Object>\\> <br />"
                    + "@exception (no Exception) <br />"
    )
    @PostMapping("/moreList")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<TbgrantuserDto.TbgrantuserSelectDto>> moreList(@Valid @RequestBody TbgrantuserDto.TbgrantuserMoreListDto params) {
        return ResponseEntity.status(HttpStatus.OK).body(tbgrantuserService.moreList(params));
    }

    @Operation(summary = "게시글 댓글 목록 조회 - 페이지 (검색 기능 포함)",
            description = "게시글 댓글 목록 조회 - 페이지 위한 컨트롤러 (누구나 접근 가능) <br />"
                    + "@param TbgrantuserPagedListDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<Map<String, Object>\\> <br />"
                    + "@exception (no Exception) <br />"
    )
    @PostMapping("/pagedList")
    @PreAuthorize("permitAll()")
    public ResponseEntity<CommonAfterPagedListDto<TbgrantuserDto.TbgrantuserSelectDto>> pagedList(@Valid @RequestBody TbgrantuserDto.TbgrantuserPagedListDto params) {
        return ResponseEntity.status(HttpStatus.OK).body(tbgrantuserService.pagedList(params));
    }

}
