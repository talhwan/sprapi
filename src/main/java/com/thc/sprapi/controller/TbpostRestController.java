package com.thc.sprapi.controller;

import com.thc.sprapi.dto.*;
import com.thc.sprapi.security.PrincipalDetails;
import com.thc.sprapi.service.TbgrantService;
import com.thc.sprapi.service.TbpostService;
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

@Tag(name = "2. 게시글 API 안내",
        description = "게시글 관련 기능 정의한 RestController.")
@RequestMapping("/api/tbpost")
@RestController
public class TbpostRestController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final TbgrantService tbgrantService;
    private final TbpostService tbpostService;
    public TbpostRestController(TbgrantService tbgrantService, TbpostService tbpostService) {
        this.tbgrantService = tbgrantService;
        this.tbpostService = tbpostService;
    }

    @Operation(summary = "게시글 등록",
            description = "게시글 등록 위한 컨트롤러 (누구나 접근 가능) <br />"
                    + "@param TbpostCreateDto <br />"
                    + "@return HttpStatus.CREATED(201) ResponseEntity\\<TbpostAfterCreateDto\\> <br />"
                    + "@exception 중복 <br />"
    )
    @PostMapping("")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<TbpostDto.TbpostAfterCreateDto> save(@Valid @RequestBody TbpostDto.TbpostCreateDto params, HttpServletRequest request, HttpServletResponse response, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        params.setNowGrant(tbgrantService.access("tbpost", "create",false, principalDetails.getTbuser().getId()));
        params.setNowTbuserId(principalDetails.getTbuser().getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(tbpostService.create(params));
    }
    @Operation(summary = "게시글 수정",
            description = "게시글 수정 위한 컨트롤러 (누구나 접근 가능) <br />"
                    + "@param TbpostUpdateDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<TbpostAfterUpdateDto\\> <br />"
                    + "@exception 해당 자료 없음 <br />"
    )
    @PreAuthorize("hasRole('USER')")
    @PutMapping("")
    public ResponseEntity<TbpostDto.TbpostAfterUpdateDto> update(@Valid @RequestBody TbpostDto.TbpostUpdateDto params, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        params.setNowGrant(tbgrantService.access("tbpost", "update",false, principalDetails.getTbuser().getId()));
        params.setNowTbuserId(principalDetails.getTbuser().getId());
        return ResponseEntity.status(HttpStatus.OK).body(tbpostService.update(params));
    }

    @Operation(summary = "게시글 삭제",
            description = "게시글 삭제 위한 컨트롤러 (누구나 접근 가능) <br />"
                    + "@param TbpostUpdateDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<TbpostAfterUpdateDto\\> <br />"
                    + "@exception 해당 자료 없음 <br />"
    )
    @PreAuthorize("hasRole('USER')")
    @DeleteMapping("")
    public ResponseEntity<CommonDeleteListDto> deleteList(@Valid @RequestBody CommonDeleteListDto params, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        params.setNowGrant(tbgrantService.access("tbpost", "update",false, principalDetails.getTbuser().getId()));
        params.setNowTbuserId(principalDetails.getTbuser().getId());
        return ResponseEntity.status(HttpStatus.OK).body(tbpostService.deleteList(params));
    }

    @Operation(summary = "게시글 조회",
            description = "게시글 1개 조회 위한 컨트롤러 (모두 접근 가능) <br />"
                    + "@param id(PathVariable) <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<TbpostSelectDto\\> <br />"
                    + "@exception 정보 없음 <br />"
    )
    @PreAuthorize("permitAll()")
    @GetMapping("/{id}")
    public ResponseEntity<TbpostDto.TbpostSelectDto> detail(@PathVariable("id") String id, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        CommonDetailDto params = CommonDetailDto.builder().id(id).build();
        return ResponseEntity.status(HttpStatus.OK).body(tbpostService.detail(params));
    }
    @Operation(summary = "게시글 목록 조회(검색 기능 포함)",
            description = "게시글 목록 조회 위한 컨트롤러 (모두 접근 가능) <br />"
                    + "@param TbpostListDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<TbpostSelectDto\\> <br />"
                    + "@exception (no Exception) <br />"
    )
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/list")
    public ResponseEntity<List<TbpostDto.TbpostSelectDto>> list(@Valid @RequestBody TbpostDto.TbpostListDto params) {
        return ResponseEntity.status(HttpStatus.OK).body(tbpostService.list(params));
    }
    @Operation(summary = "게시글 목록 조회 - 스크롤 (검색 기능 포함)",
            description = "게시판 목록 조회 - 스크롤 위한 컨트롤러 (누구나 접근 가능) <br />"
                    + "@param TbpostMoreListDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<Map<String, Object>\\> <br />"
                    + "@exception (no Exception) <br />"
    )
    @PreAuthorize("permitAll()")
    @PostMapping("/moreList")
    public ResponseEntity<List<TbpostDto.TbpostSelectDto>> moreList(@Valid @RequestBody TbpostDto.TbpostMoreListDto params) {
        return ResponseEntity.status(HttpStatus.OK).body(tbpostService.moreList(params));
    }

    @Operation(summary = "게시글 목록 조회 - 페이지 (검색 기능 포함)",
            description = "게시글 목록 조회 - 페이지 위한 컨트롤러 (누구나 접근 가능) <br />"
                    + "@param TbpostPagedListDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<Map<String, Object>\\> <br />"
                    + "@exception (no Exception) <br />"
    )
    @PreAuthorize("permitAll()")
    @PostMapping("/pagedList")
    public ResponseEntity<CommonAfterPagedListDto<TbpostDto.TbpostSelectDto>> pagedList(@Valid @RequestBody TbpostDto.TbpostPagedListDto params) {
        return ResponseEntity.status(HttpStatus.OK).body(tbpostService.pagedList(params));
    }

}
