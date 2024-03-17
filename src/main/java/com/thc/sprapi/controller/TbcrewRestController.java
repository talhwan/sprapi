package com.thc.sprapi.controller;

import com.thc.sprapi.dto.CommonAfterPagedListDto;
import com.thc.sprapi.dto.CommonDeleteListDto;
import com.thc.sprapi.dto.CommonDetailDto;
import com.thc.sprapi.dto.TbcrewDto;
import com.thc.sprapi.security.PrincipalDetails;
import com.thc.sprapi.service.TbgrantService;
import com.thc.sprapi.service.TbcrewService;
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

@Tag(name = "3-1. 가맹점 API 안내",
        description = "가맹점 관련 기능 정의한 RestController.")
@RequestMapping("/api/tbcrew")
@RestController
public class TbcrewRestController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final TbgrantService tbgrantService;
    private final TbcrewService tbcrewService;
    public TbcrewRestController(TbgrantService tbgrantService, TbcrewService tbcrewService) {
        this.tbgrantService = tbgrantService;
        this.tbcrewService = tbcrewService;
    }

    /**/
    @Operation(summary = "가맹점 등록",
            description = "가맹점 등록 위한 컨트롤러 (누구나 접근 가능) <br />"
                    + "@param TbcrewCreateDto <br />"
                    + "@return HttpStatus.CREATED(201) ResponseEntity\\<TbcrewAfterCreateDto\\> <br />"
                    + "@exception 중복 <br />"
    )
    @PostMapping("")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<TbcrewDto.TbcrewAfterCreateDto> save(@Valid @RequestBody TbcrewDto.TbcrewCreateDto params, HttpServletRequest request, HttpServletResponse response, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        params.setNowGrant(tbgrantService.access("tbcrew", "create",true, principalDetails.getTbuser().getId()));
        params.setNowTbuserId(principalDetails.getTbuser().getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(tbcrewService.create(params));
    }
    @Operation(summary = "가맹점 수정",
            description = "가맹점 수정 위한 컨트롤러 (누구나 접근 가능) <br />"
                    + "@param TbcrewUpdateDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<TbcrewAfterUpdateDto\\> <br />"
                    + "@exception 해당 자료 없음 <br />"
    )
    @PreAuthorize("hasRole('USER')")
    @PutMapping("")
    public ResponseEntity<TbcrewDto.TbcrewAfterUpdateDto> update(@Valid @RequestBody TbcrewDto.TbcrewUpdateDto params, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        params.setNowGrant(tbgrantService.access("tbcrew", "update",true, principalDetails.getTbuser().getId()));
        params.setNowTbuserId(principalDetails.getTbuser().getId());
        return ResponseEntity.status(HttpStatus.OK).body(tbcrewService.update(params));
    }

    @Operation(summary = "가맹점 삭제",
            description = "가맹점 삭제 위한 컨트롤러 (누구나 접근 가능) <br />"
                    + "@param TbcrewUpdateDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<TbcrewAfterUpdateDto\\> <br />"
                    + "@exception 해당 자료 없음 <br />"
    )
    @PreAuthorize("hasRole('USER')")
    @DeleteMapping("")
    public ResponseEntity<CommonDeleteListDto> deleteList(@Valid @RequestBody CommonDeleteListDto params, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        params.setNowGrant(tbgrantService.access("tbcrew", "update",true, principalDetails.getTbuser().getId()));
        params.setNowTbuserId(principalDetails.getTbuser().getId());
        return ResponseEntity.status(HttpStatus.OK).body(tbcrewService.deleteList(params));
    }

    @Operation(summary = "가맹점 조회",
            description = "가맹점 1개 조회 위한 컨트롤러 (모두 접근 가능) <br />"
                    + "@param id(PathVariable) <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<TbcrewSelectDto\\> <br />"
                    + "@exception 정보 없음 <br />"
    )
    @PreAuthorize("permitAll()")
    @GetMapping("/{id}")
    public ResponseEntity<TbcrewDto.TbcrewSelectDto> detail(@PathVariable("id") String id, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        CommonDetailDto params = CommonDetailDto.builder().id(id).build();
        params.setNowGrant(tbgrantService.access("tbcrew", "read",true, principalDetails.getTbuser().getId()));
        return ResponseEntity.status(HttpStatus.OK).body(tbcrewService.detail(params));
    }
    @Operation(summary = "가맹점 목록 조회(검색 기능 포함)",
            description = "가맹점 목록 조회 위한 컨트롤러 (모두 접근 가능) <br />"
                    + "@param TbcrewListDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<TbcrewSelectDto\\> <br />"
                    + "@exception (no Exception) <br />"
    )
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/list")
    public ResponseEntity<List<TbcrewDto.TbcrewSelectDto>> list(@Valid @RequestBody TbcrewDto.TbcrewListDto params, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        params.setNowGrant(tbgrantService.access("tbcrew", "read",false, principalDetails.getTbuser().getId()));
        params.setNowTbuserId(principalDetails.getTbuser().getId());
        return ResponseEntity.status(HttpStatus.OK).body(tbcrewService.list(params));
    }
    @Operation(summary = "가맹점 목록 조회 - 스크롤 (검색 기능 포함)",
            description = "게시판 목록 조회 - 스크롤 위한 컨트롤러 (누구나 접근 가능) <br />"
                    + "@param TbcrewMoreListDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<Map<String, Object>\\> <br />"
                    + "@exception (no Exception) <br />"
    )
    @PreAuthorize("permitAll()")
    @PostMapping("/moreList")
    public ResponseEntity<List<TbcrewDto.TbcrewSelectDto>> moreList(@Valid @RequestBody TbcrewDto.TbcrewMoreListDto params, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        params.setNowGrant(tbgrantService.access("tbcrew", "read",false, principalDetails.getTbuser().getId()));
        params.setNowTbuserId(principalDetails.getTbuser().getId());
        return ResponseEntity.status(HttpStatus.OK).body(tbcrewService.moreList(params));
    }

    @Operation(summary = "가맹점 목록 조회 - 페이지 (검색 기능 포함)",
            description = "가맹점 목록 조회 - 페이지 위한 컨트롤러 (누구나 접근 가능) <br />"
                    + "@param TbcrewPagedListDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<Map<String, Object>\\> <br />"
                    + "@exception (no Exception) <br />"
    )
    @PreAuthorize("permitAll()")
    @PostMapping("/pagedList")
    public ResponseEntity<CommonAfterPagedListDto<TbcrewDto.TbcrewSelectDto>> pagedList(@Valid @RequestBody TbcrewDto.TbcrewPagedListDto params, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        params.setNowGrant(tbgrantService.access("tbcrew", "read",false, principalDetails.getTbuser().getId()));
        params.setNowTbuserId(principalDetails.getTbuser().getId());
        return ResponseEntity.status(HttpStatus.OK).body(tbcrewService.pagedList(params));
    }

}
