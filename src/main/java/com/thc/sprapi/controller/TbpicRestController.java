package com.thc.sprapi.controller;

import com.thc.sprapi.dto.CommonAfterPagedListDto;
import com.thc.sprapi.dto.TbpicDto;
import com.thc.sprapi.service.TbpicService;
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

@Tag(name = "1. 게시글 이미지 API 안내",
        description = "게시글 이미지 관련 기능 정의한 RestController.")
@RequestMapping("/api/tbpic")
@RestController
public class TbpicRestController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final TbpicService tbpicService;
    public TbpicRestController(TbpicService tbpicService) {
        this.tbpicService = tbpicService;
    }

    @Operation(summary = "게시글 이미지 글 등록",
            description = "게시글 이미지 신규 글 등록을 위한 컨트롤러 (누구나 접근 가능) <br />"
                    + "@param TbpicCreateDto <br />"
                    + "@return HttpStatus.CREATED(201) ResponseEntity\\<TbpicAfterCreateDto\\> <br />"
                    + "@exception 중복 <br />"
    )
    @PostMapping("")
    public ResponseEntity<TbpicDto.TbpicAfterCreateDto> save(@Valid @RequestBody TbpicDto.TbpicCreateDto params, HttpServletRequest request, HttpServletResponse response) {
        return ResponseEntity.status(HttpStatus.CREATED).body(tbpicService.create(params));
    }
    @Operation(summary = "게시글 이미지 글 수정",
            description = "게시글 이미지 기존 글 수정을 위한 컨트롤러 (누구나 접근 가능) <br />"
                    + "@param TbpicUpdateDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<TbpicAfterUpdateDto\\> <br />"
                    + "@exception 해당 자료 없음 <br />"
    )
    @PutMapping("")
    public ResponseEntity<TbpicDto.TbpicAfterUpdateDto> update(@Valid @RequestBody TbpicDto.TbpicUpdateDto params) {
        return ResponseEntity.status(HttpStatus.OK).body(tbpicService.update(params));
    }
    @Operation(summary = "게시글 이미지 글 삭제",
            description = "게시글 이미지 삭제를 위한 컨트롤러 (누구나 접근 가능) <br />"
                    + "@param TbpicUpdateDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<TbpicAfterUpdateDto\\> <br />"
                    + "@exception 해당 자료 없음 <br />"
    )
    @DeleteMapping("")
    public ResponseEntity<TbpicDto.TbpicAfterUpdateDto> delete(@Valid @RequestBody TbpicDto.TbpicUpdateDto params) {
        return ResponseEntity.status(HttpStatus.OK).body(tbpicService.delete(params));
    }

    @Operation(summary = "게시글 이미지 글 정보 조회",
            description = "게시글 이미지 글 1개 정보 조회를 위한 컨트롤러 (모두 접근 가능) <br />"
                    + "@param id(PathVariable) <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<TbpicSelectDto\\> <br />"
                    + "@exception 정보 없음 <br />"
    )
    @GetMapping("/{id}")
    public ResponseEntity<TbpicDto.TbpicSelectDto> detail(@PathVariable("id") String id) {
        return ResponseEntity.status(HttpStatus.OK).body(tbpicService.detail(id));
    }
    @Operation(summary = "게시글 이미지 글 정보 목록 조회(검색 기능 포함)",
            description = "게시글 이미지 글 전체 목록 조회를 위한 컨트롤러 (모두 접근 가능) <br />"
                    + "@param (no parameter) <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<TbpicSelectDto\\> <br />"
                    + "@exception (no Exception) <br />"
    )
    @PostMapping("/list")
    public ResponseEntity<List<TbpicDto.TbpicSelectDto>> list(@Valid @RequestBody TbpicDto.TbpicListDto params) {
        return ResponseEntity.status(HttpStatus.OK).body(tbpicService.list(params));
    }
    @Operation(summary = "게시글 이미지 글 정보 추가조회 목록 조회(검색 기능 포함)",
            description = "게시글 이미지 추가 조회한 글 검색을 위한 컨트롤러 (누구나 접근 가능) <br />"
                    + "@param TbpicSearchDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<Map<String, Object>\\> <br />"
                    + "@exception (no Exception) <br />"
    )
    @PostMapping("/moreList")
    public ResponseEntity<List<TbpicDto.TbpicSelectDto>> moreList(@Valid @RequestBody TbpicDto.TbpicMoreListDto params) {
        return ResponseEntity.status(HttpStatus.OK).body(tbpicService.moreList(params));
    }

    @Operation(summary = "게시글 이미지 글 정보 페이징 처리 한 목록 조회(검색 기능 포함)",
            description = "게시글 이미지 페이징 처리 한 글 검색을 위한 컨트롤러 (누구나 접근 가능) <br />"
                    + "@param TbpicSearchDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<Map<String, Object>\\> <br />"
                    + "@exception (no Exception) <br />"
    )
    @PostMapping("/pagedList")
    public ResponseEntity<CommonAfterPagedListDto<TbpicDto.TbpicSelectDto>> pagedList(@Valid @RequestBody TbpicDto.TbpicPagedListDto params) {
        return ResponseEntity.status(HttpStatus.OK).body(tbpicService.pagedList(params));
    }

}
