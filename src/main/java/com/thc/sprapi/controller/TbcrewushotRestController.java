package com.thc.sprapi.controller;

import com.thc.sprapi.dto.CommonAfterPagedListDto;
import com.thc.sprapi.dto.CommonDeleteListDto;
import com.thc.sprapi.dto.CommonDetailDto;
import com.thc.sprapi.dto.TbcrewushotDto;
import com.thc.sprapi.security.PrincipalDetails;
import com.thc.sprapi.service.TbgrantService;
import com.thc.sprapi.service.TbcrewushotService;
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

@Tag(name = "3-1_1. 가맹점 출입시도 API 안내",
        description = "가맹점 출입시도 관련 기능 정의한 RestController.")
@RequestMapping("/api/tbcrewushot")
@RestController
public class TbcrewushotRestController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final TbgrantService tbgrantService;
    private final TbcrewushotService tbcrewushotService;
    public TbcrewushotRestController(TbgrantService tbgrantService, TbcrewushotService tbcrewushotService) {
        this.tbgrantService = tbgrantService;
        this.tbcrewushotService = tbcrewushotService;
    }

    /**/
    @Operation(summary = "가맹점 출입시도 등록",
            description = "가맹점 출입시도 신규 등록을 위한 컨트롤러 (누구나 접근 가능) <br />"
                    + "@param TbcrewushotCreateDto <br />"
                    + "@return HttpStatus.CREATED(201) ResponseEntity\\<TbcrewushotAfterCreateDto\\> <br />"
                    + "@exception 중복 <br />"
    )
    @PostMapping("")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<TbcrewushotDto.TbcrewushotAfterCreateDto> save(@Valid @RequestBody TbcrewushotDto.TbcrewushotCreateDto params, HttpServletRequest request, HttpServletResponse response, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        params.setNowGrant(tbgrantService.access("tbcrew", "create",true, principalDetails.getTbuser().getId()));
        return ResponseEntity.status(HttpStatus.CREATED).body(tbcrewushotService.create(params));
    }
    @Operation(summary = "가맹점 출입시도 수정",
            description = "가맹점 출입시도 기존 수정을 위한 컨트롤러 (누구나 접근 가능) <br />"
                    + "@param TbcrewushotUpdateDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<TbcrewushotAfterUpdateDto\\> <br />"
                    + "@exception 해당 자료 없음 <br />"
    )
    @PutMapping("")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<TbcrewushotDto.TbcrewushotAfterUpdateDto> update(@Valid @RequestBody TbcrewushotDto.TbcrewushotUpdateDto params, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        params.setNowGrant(tbgrantService.access("tbcrew", "update",true, principalDetails.getTbuser().getId()));
        return ResponseEntity.status(HttpStatus.OK).body(tbcrewushotService.update(params));
    }
    @Operation(summary = "가맹점 출입시도 삭제",
            description = "가맹점 출입시도 삭제를 위한 컨트롤러 (누구나 접근 가능) <br />"
                    + "@param TbcrewushotUpdateDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<TbcrewushotAfterUpdateDto\\> <br />"
                    + "@exception 해당 자료 없음 <br />"
    )
    @DeleteMapping("")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CommonDeleteListDto> deleteList(@Valid @RequestBody CommonDeleteListDto params, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        params.setNowGrant(tbgrantService.access("tbcrew", "update",true, principalDetails.getTbuser().getId()));
        return ResponseEntity.status(HttpStatus.OK).body(tbcrewushotService.deleteList(params));
    }

    @Operation(summary = "가맹점 출입시도 조회",
            description = "가맹점 출입시도 1개 조회를 위한 컨트롤러 (모두 접근 가능) <br />"
                    + "@param id(PathVariable) <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<TbcrewushotSelectDto\\> <br />"
                    + "@exception 정보 없음 <br />"
    )
    @GetMapping("/get/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<TbcrewushotDto.TbcrewushotSelectDto> detail(@PathVariable("id") String id, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        CommonDetailDto params = CommonDetailDto.builder().id(id).build();
        params.setNowGrant(tbgrantService.access("tbcrew", "read",true, principalDetails.getTbuser().getId()));
        return ResponseEntity.status(HttpStatus.OK).body(tbcrewushotService.detail(params));
    }
    @Operation(summary = "가맹점 출입시도 목록 조회(검색 기능 포함)",
            description = "가맹점 출입시도 목록 조회를 위한 컨트롤러 (모두 접근 가능) <br />"
                    + "@param TbcrewushotListDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<TbcrewushotSelectDto\\> <br />"
                    + "@exception (no Exception) <br />"
    )
    @GetMapping("/list")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<TbcrewushotDto.TbcrewushotSelectDto>> list(@Valid TbcrewushotDto.TbcrewushotListDto params, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        params.setNowGrant(tbgrantService.access("tbcrew", "read",true, principalDetails.getTbuser().getId()));
        return ResponseEntity.status(HttpStatus.OK).body(tbcrewushotService.list(params));
    }
    @Operation(summary = "가맹점 출입시도 목록 조회 - 스크롤 (검색 기능 포함)",
            description = "가맹점 출입시도 목록 조회 - 스크롤 위한 컨트롤러 (누구나 접근 가능) <br />"
                    + "@param TbcrewushotMoreListDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<Map<String, Object>\\> <br />"
                    + "@exception (no Exception) <br />"
    )
    @GetMapping("/moreList")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<TbcrewushotDto.TbcrewushotSelectDto>> moreList(@Valid TbcrewushotDto.TbcrewushotMoreListDto params, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        params.setNowGrant(tbgrantService.access("tbcrew", "read",true, principalDetails.getTbuser().getId()));
        return ResponseEntity.status(HttpStatus.OK).body(tbcrewushotService.moreList(params));
    }

    @Operation(summary = "가맹점 출입시도 목록 조회 - 페이지 (검색 기능 포함)",
            description = "가맹점 출입시도 목록 조회 - 페이지 위한 컨트롤러 (누구나 접근 가능) <br />"
                    + "@param TbcrewushotPagedListDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<Map<String, Object>\\> <br />"
                    + "@exception (no Exception) <br />"
    )
    @GetMapping("/pagedList")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CommonAfterPagedListDto<TbcrewushotDto.TbcrewushotSelectDto>> pagedList(@Valid TbcrewushotDto.TbcrewushotPagedListDto params, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        params.setNowGrant(tbgrantService.access("tbcrew", "read",true, principalDetails.getTbuser().getId()));
        return ResponseEntity.status(HttpStatus.OK).body(tbcrewushotService.pagedList(params));
    }

}
