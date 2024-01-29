package com.thc.sprapi.controller;

import com.thc.sprapi.dto.CommonAfterPagedListDto;
import com.thc.sprapi.dto.TbuserDto;
import com.thc.sprapi.service.TbuserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "1. 회원 API 안내",
        description = "회원 관련 기능 정의한 RestController.")
@RequestMapping("/api/tbuser")
@RestController
public class TbuserRestController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final TbuserService tbuserService;
    public TbuserRestController(TbuserService tbuserService) {
        this.tbuserService = tbuserService;
    }

    @Operation(summary = "회원 가입",
            description = "회원 가입을 위한 컨트롤러 (누구나 접근 가능) <br />"
                    + "@param TbuserCreateDto <br />"
                    + "@return HttpStatus.CREATED(201) ResponseEntity\\<TbuserAfterCreateDto\\> <br />"
                    + "@exception 중복 <br />"
    )
    @PreAuthorize("permitAll()")
    @PostMapping("/signup")
    public ResponseEntity<TbuserDto.TbuserAfterCreateDto> signup(@Valid @RequestBody TbuserDto.TbuserCreateDto params) {
        return ResponseEntity.status(HttpStatus.CREATED).body(tbuserService.signup(params));
    }

    /*
    이 아래는 기본기능입니다!!!
     */

    /*
    @Operation(summary = "회원 정보 등록",
            description = "회원 신규 정보 등록을 위한 컨트롤러 (누구나 접근 가능) <br />"
                    + "@param TbuserCreateDto <br />"
                    + "@return HttpStatus.CREATED(201) ResponseEntity\\<TbuserAfterCreateDto\\> <br />"
                    + "@exception 중복 <br />"
    )
    @PostMapping("")
    public ResponseEntity<TbuserDto.TbuserAfterCreateDto> save(@Valid @RequestBody TbuserDto.TbuserCreateDto params) {
        return ResponseEntity.status(HttpStatus.CREATED).body(tbuserService.create(params));
    }
     */
    @Operation(summary = "회원 정보 수정",
            description = "회원 기존 정보 수정을 위한 컨트롤러 (누구나 접근 가능) <br />"
                    + "@param TbuserUpdateDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<TbuserAfterUpdateDto\\> <br />"
                    + "@exception 해당 자료 없음 <br />"
    )
    @PutMapping("")
    public ResponseEntity<TbuserDto.TbuserAfterUpdateDto> update(@Valid @RequestBody TbuserDto.TbuserUpdateDto params) {
        return ResponseEntity.status(HttpStatus.OK).body(tbuserService.update(params));
    }

    @Operation(summary = "회원 정보 조회",
            description = "회원 정보 1개 정보 조회를 위한 컨트롤러 (모두 접근 가능) <br />"
                    + "@param id(PathVariable) <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<TbuserSelectDto\\> <br />"
                    + "@exception 정보 없음 <br />"
    )
    @GetMapping("/{id}")
    public ResponseEntity<TbuserDto.TbuserSelectDto> detail(@PathVariable("id") String id) {
        return ResponseEntity.status(HttpStatus.OK).body(tbuserService.detail(id));
    }
    @Operation(summary = "회원 정보 목록 조회(검색 기능 포함)",
            description = "회원 정보 전체 목록 조회를 위한 컨트롤러 (모두 접근 가능) <br />"
                    + "@param (no parameter) <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<TbuserSelectDto\\> <br />"
                    + "@exception (no Exception) <br />"
    )
    @PostMapping("/list")
    public ResponseEntity<List<TbuserDto.TbuserSelectDto>> list(@Valid @RequestBody TbuserDto.TbuserListDto params) {
        return ResponseEntity.status(HttpStatus.OK).body(tbuserService.list(params));
    }
    @Operation(summary = "회원 정보 추가조회 목록 조회(검색 기능 포함)",
            description = "회원 추가 조회한 정보 검색을 위한 컨트롤러 (누구나 접근 가능) <br />"
                    + "@param TbuserSearchDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<Map<String, Object>\\> <br />"
                    + "@exception (no Exception) <br />"
    )
    @PostMapping("/moreList")
    public ResponseEntity<List<TbuserDto.TbuserSelectDto>> moreList(@Valid @RequestBody TbuserDto.TbuserMoreListDto params) {
        return ResponseEntity.status(HttpStatus.OK).body(tbuserService.moreList(params));
    }

    @Operation(summary = "회원 정보 페이징 처리 한 목록 조회(검색 기능 포함)",
            description = "회원 페이징 처리 한 정보 검색을 위한 컨트롤러 (누구나 접근 가능) <br />"
                    + "@param TbuserSearchDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<Map<String, Object>\\> <br />"
                    + "@exception (no Exception) <br />"
    )
    @PostMapping("/pagedList")
    public ResponseEntity<CommonAfterPagedListDto<TbuserDto.TbuserSelectDto>> pagedList(@Valid @RequestBody TbuserDto.TbuserPagedListDto params) {
        return ResponseEntity.status(HttpStatus.OK).body(tbuserService.pagedList(params));
    }

}
