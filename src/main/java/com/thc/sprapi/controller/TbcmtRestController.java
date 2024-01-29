package com.thc.sprapi.controller;

import com.thc.sprapi.dto.CommonAfterPagedListDto;
import com.thc.sprapi.dto.TbcmtDto;
import com.thc.sprapi.service.TbcmtService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "1. 댓글 API 안내",
        description = "댓글 관련 기능 정의한 RestController.")
@RequestMapping("/api/tbcmt")
@RestController
public class TbcmtRestController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final TbcmtService tbcmtService;
    public TbcmtRestController(TbcmtService tbcmtService) {
        this.tbcmtService = tbcmtService;
    }

    @Operation(summary = "댓글 글 등록",
            description = "댓글 신규 글 등록을 위한 컨트롤러 (누구나 접근 가능) <br />"
                    + "@param TbcmtCreateDto <br />"
                    + "@return HttpStatus.CREATED(201) ResponseEntity\\<TbcmtAfterCreateDto\\> <br />"
                    + "@exception 중복 <br />"
    )
    @PostMapping("")
    public ResponseEntity<TbcmtDto.TbcmtAfterCreateDto> save(@Valid @RequestBody TbcmtDto.TbcmtCreateDto params, HttpServletRequest request, HttpServletResponse response) {
        return ResponseEntity.status(HttpStatus.CREATED).body(tbcmtService.create(params));
    }
    @Operation(summary = "댓글 글 수정",
            description = "댓글 기존 글 수정을 위한 컨트롤러 (누구나 접근 가능) <br />"
                    + "@param TbcmtUpdateDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<TbcmtAfterUpdateDto\\> <br />"
                    + "@exception 해당 자료 없음 <br />"
    )
    @PutMapping("")
    public ResponseEntity<TbcmtDto.TbcmtAfterUpdateDto> update(@Valid @RequestBody TbcmtDto.TbcmtUpdateDto params) {
        return ResponseEntity.status(HttpStatus.OK).body(tbcmtService.update(params));
    }

    @Operation(summary = "댓글 글 정보 조회",
            description = "댓글 글 1개 정보 조회를 위한 컨트롤러 (모두 접근 가능) <br />"
                    + "@param id(PathVariable) <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<TbcmtSelectDto\\> <br />"
                    + "@exception 정보 없음 <br />"
    )
    @GetMapping("/{id}")
    public ResponseEntity<TbcmtDto.TbcmtSelectDto> detail(@PathVariable("id") String id) {
        return ResponseEntity.status(HttpStatus.OK).body(tbcmtService.detail(id));
    }
    @Operation(summary = "댓글 글 정보 목록 조회(검색 기능 포함)",
            description = "댓글 글 전체 목록 조회를 위한 컨트롤러 (모두 접근 가능) <br />"
                    + "@param (no parameter) <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<TbcmtSelectDto\\> <br />"
                    + "@exception (no Exception) <br />"
    )
    @PostMapping("/list")
    public ResponseEntity<List<TbcmtDto.TbcmtSelectDto>> list(@Valid @RequestBody TbcmtDto.TbcmtListDto params) {
        return ResponseEntity.status(HttpStatus.OK).body(tbcmtService.list(params));
    }
    @Operation(summary = "댓글 글 정보 추가조회 목록 조회(검색 기능 포함)",
            description = "댓글 추가 조회한 글 검색을 위한 컨트롤러 (누구나 접근 가능) <br />"
                    + "@param TbcmtSearchDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<Map<String, Object>\\> <br />"
                    + "@exception (no Exception) <br />"
    )
    @PostMapping("/moreList")
    public ResponseEntity<List<TbcmtDto.TbcmtSelectDto>> moreList(@Valid @RequestBody TbcmtDto.TbcmtMoreListDto params) {
        return ResponseEntity.status(HttpStatus.OK).body(tbcmtService.moreList(params));
    }

    @Operation(summary = "댓글 글 정보 페이징 처리 한 목록 조회(검색 기능 포함)",
            description = "댓글 페이징 처리 한 글 검색을 위한 컨트롤러 (누구나 접근 가능) <br />"
                    + "@param TbcmtSearchDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<Map<String, Object>\\> <br />"
                    + "@exception (no Exception) <br />"
    )
    @PostMapping("/pagedList")
    public ResponseEntity<CommonAfterPagedListDto<TbcmtDto.TbcmtSelectDto>> pagedList(@Valid @RequestBody TbcmtDto.TbcmtPagedListDto params) {
        return ResponseEntity.status(HttpStatus.OK).body(tbcmtService.pagedList(params));
    }

}
