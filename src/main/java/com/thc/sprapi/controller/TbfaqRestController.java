package com.thc.sprapi.controller;

import com.thc.sprapi.dto.CommonAfterPagedListDto;
import com.thc.sprapi.dto.CommonDeleteListDto;
import com.thc.sprapi.dto.CommonDetailDto;
import com.thc.sprapi.dto.TbfaqDto;
import com.thc.sprapi.security.PrincipalDetails;
import com.thc.sprapi.service.TbgrantService;
import com.thc.sprapi.service.TbfaqService;
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

@Tag(name = "2. FAQ API 안내",
        description = "FAQ 관련 기능 정의한 RestController.")
@RequestMapping("/api/tbfaq")
@RestController
public class TbfaqRestController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final TbgrantService tbgrantService;
    private final TbfaqService tbfaqService;
    public TbfaqRestController(TbgrantService tbgrantService, TbfaqService tbfaqService) {
        this.tbgrantService = tbgrantService;
        this.tbfaqService = tbfaqService;
    }

    @Operation(summary = "FAQ 순서 수정",
            description = "FAQ 순서 수정 위한 컨트롤러 (권한 확인) <br />"
                    + "@param TbfaqSequenceDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<TbfaqAfterUpdateDto\\> <br />"
                    + "@exception 해당 자료 없음 <br />"
    )
    @PreAuthorize("hasRole('USER')")
    @PutMapping("/sequence")
    public ResponseEntity<TbfaqDto.TbfaqAfterUpdateDto> sequence(@Valid @RequestBody TbfaqDto.TbfaqSequenceDto params, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        params.setNowGrant(tbgrantService.access("tbfaq", "update",true, principalDetails.getTbuser().getId()));
        params.setNowTbuserId(principalDetails.getTbuser().getId());
        return ResponseEntity.status(HttpStatus.OK).body(tbfaqService.sequence(params));
    }

    /**/

    @Operation(summary = "FAQ 등록",
            description = "FAQ 등록 위한 컨트롤러 (누구나 접근 가능) <br />"
                    + "@param TbfaqCreateDto <br />"
                    + "@return HttpStatus.CREATED(201) ResponseEntity\\<TbfaqAfterCreateDto\\> <br />"
                    + "@exception 중복 <br />"
    )
    @PostMapping("")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<TbfaqDto.TbfaqAfterCreateDto> save(@Valid @RequestBody TbfaqDto.TbfaqCreateDto params, HttpServletRequest request, HttpServletResponse response, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        params.setNowGrant(tbgrantService.access("tbfaq", "create",true, principalDetails.getTbuser().getId()));
        params.setNowTbuserId(principalDetails.getTbuser().getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(tbfaqService.create(params));
    }
    @Operation(summary = "FAQ 수정",
            description = "FAQ 수정 위한 컨트롤러 (누구나 접근 가능) <br />"
                    + "@param TbfaqUpdateDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<TbfaqAfterUpdateDto\\> <br />"
                    + "@exception 해당 자료 없음 <br />"
    )
    @PreAuthorize("hasRole('USER')")
    @PutMapping("")
    public ResponseEntity<TbfaqDto.TbfaqAfterUpdateDto> update(@Valid @RequestBody TbfaqDto.TbfaqUpdateDto params, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        params.setNowGrant(tbgrantService.access("tbfaq", "update",true, principalDetails.getTbuser().getId()));
        params.setNowTbuserId(principalDetails.getTbuser().getId());
        return ResponseEntity.status(HttpStatus.OK).body(tbfaqService.update(params));
    }

    @Operation(summary = "FAQ 삭제",
            description = "FAQ 삭제 위한 컨트롤러 (누구나 접근 가능) <br />"
                    + "@param TbfaqUpdateDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<TbfaqAfterUpdateDto\\> <br />"
                    + "@exception 해당 자료 없음 <br />"
    )
    @PreAuthorize("hasRole('USER')")
    @DeleteMapping("")
    public ResponseEntity<CommonDeleteListDto> deleteList(@Valid @RequestBody CommonDeleteListDto params, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        params.setNowGrant(tbgrantService.access("tbfaq", "update",true, principalDetails.getTbuser().getId()));
        params.setNowTbuserId(principalDetails.getTbuser().getId());
        return ResponseEntity.status(HttpStatus.OK).body(tbfaqService.deleteList(params));
    }

    @Operation(summary = "FAQ 조회",
            description = "FAQ 1개 조회 위한 컨트롤러 (모두 접근 가능) <br />"
                    + "@param id(PathVariable) <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<TbfaqSelectDto\\> <br />"
                    + "@exception 정보 없음 <br />"
    )
    @PreAuthorize("permitAll()")
    @GetMapping("/{id}")
    public ResponseEntity<TbfaqDto.TbfaqSelectDto> detail(@PathVariable("id") String id, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        CommonDetailDto params = CommonDetailDto.builder().id(id).build();
        //params.setNowGrant(tbgrantService.access("tbfaq", "read",true, principalDetails.getTbuser().getId()));
        return ResponseEntity.status(HttpStatus.OK).body(tbfaqService.detail(params));
    }
    @Operation(summary = "FAQ 목록 조회(검색 기능 포함)",
            description = "FAQ 목록 조회 위한 컨트롤러 (모두 접근 가능) <br />"
                    + "@param TbfaqListDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<TbfaqSelectDto\\> <br />"
                    + "@exception (no Exception) <br />"
    )
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/list")
    public ResponseEntity<List<TbfaqDto.TbfaqSelectDto>> list(@Valid @RequestBody TbfaqDto.TbfaqListDto params) {
        return ResponseEntity.status(HttpStatus.OK).body(tbfaqService.list(params));
    }
    @Operation(summary = "FAQ 목록 조회 - 스크롤 (검색 기능 포함)",
            description = "게시판 목록 조회 - 스크롤 위한 컨트롤러 (누구나 접근 가능) <br />"
                    + "@param TbfaqMoreListDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<Map<String, Object>\\> <br />"
                    + "@exception (no Exception) <br />"
    )
    @PreAuthorize("permitAll()")
    @PostMapping("/moreList")
    public ResponseEntity<List<TbfaqDto.TbfaqSelectDto>> moreList(@Valid @RequestBody TbfaqDto.TbfaqMoreListDto params) {
        return ResponseEntity.status(HttpStatus.OK).body(tbfaqService.moreList(params));
    }

    @Operation(summary = "FAQ 목록 조회 - 페이지 (검색 기능 포함)",
            description = "FAQ 목록 조회 - 페이지 위한 컨트롤러 (누구나 접근 가능) <br />"
                    + "@param TbfaqPagedListDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<Map<String, Object>\\> <br />"
                    + "@exception (no Exception) <br />"
    )
    @PreAuthorize("permitAll()")
    @PostMapping("/pagedList")
    public ResponseEntity<CommonAfterPagedListDto<TbfaqDto.TbfaqSelectDto>> pagedList(@Valid @RequestBody TbfaqDto.TbfaqPagedListDto params) {
        return ResponseEntity.status(HttpStatus.OK).body(tbfaqService.pagedList(params));
    }

}
