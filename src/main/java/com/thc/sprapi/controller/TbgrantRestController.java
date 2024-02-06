package com.thc.sprapi.controller;

import com.thc.sprapi.dto.CommonAfterPagedListDto;
import com.thc.sprapi.dto.CommonDeleteListDto;
import com.thc.sprapi.dto.TbgrantDto;
import com.thc.sprapi.security.PrincipalDetails;
import com.thc.sprapi.service.TbgrantService;
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

@Tag(name = "0. 접근권한 API 안내",
        description = "접근권한 관련 기능 정의한 RestController.")
@RequestMapping("/api/tbgrant")
@RestController
public class TbgrantRestController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final TbgrantService tbgrantService;
    public TbgrantRestController(TbgrantService tbgrantService) {
        this.tbgrantService = tbgrantService;
    }

    @Operation(summary = "접근권한 등록",
            description = "접근권한 등록 위한 컨트롤러 (누구나 접근 가능) <br />"
                    + "@param TbgrantCreateDto <br />"
                    + "@return HttpStatus.CREATED(201) ResponseEntity\\<TbgrantAfterCreateDto\\> <br />"
                    + "@exception 중복 <br />"
    )
    @PostMapping("")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<TbgrantDto.TbgrantAfterCreateDto> save(@Valid @RequestBody TbgrantDto.TbgrantCreateDto params, HttpServletRequest request, HttpServletResponse response, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        tbgrantService.access(new TbgrantDto.TbgrantAccessDto("tbgrant", "create",true, principalDetails.getTbuser().getId()));
        params.setNowTbuserId(principalDetails.getTbuser().getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(tbgrantService.create(params));
    }
    @Operation(summary = "접근권한 수정",
            description = "접근권한 수정 위한 컨트롤러 (누구나 접근 가능) <br />"
                    + "@param TbgrantUpdateDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<TbgrantAfterUpdateDto\\> <br />"
                    + "@exception 해당 자료 없음 <br />"
    )
    @PreAuthorize("hasRole('USER')")
    @PutMapping("")
    public ResponseEntity<TbgrantDto.TbgrantAfterUpdateDto> update(@Valid @RequestBody TbgrantDto.TbgrantUpdateDto params, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        tbgrantService.access(new TbgrantDto.TbgrantAccessDto("tbgrant", "update",true, principalDetails.getTbuser().getId()));
        params.setNowTbuserId(principalDetails.getTbuser().getId());
        return ResponseEntity.status(HttpStatus.OK).body(tbgrantService.update(params));
    }

    @Operation(summary = "접근권한 삭제",
            description = "접근권한 삭제 위한 컨트롤러 (누구나 접근 가능) <br />"
                    + "@param TbgrantUpdateDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<TbgrantAfterUpdateDto\\> <br />"
                    + "@exception 해당 자료 없음 <br />"
    )
    @PreAuthorize("hasRole('USER')")
    @DeleteMapping("")
    public ResponseEntity<CommonDeleteListDto> deleteList(@Valid @RequestBody CommonDeleteListDto params, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        tbgrantService.access(new TbgrantDto.TbgrantAccessDto("tbgrant", "update",true, principalDetails.getTbuser().getId()));
        params.setNowTbuserId(principalDetails.getTbuser().getId());
        return ResponseEntity.status(HttpStatus.OK).body(tbgrantService.deleteList(params));
    }

    @Operation(summary = "접근권한 조회",
            description = "접근권한 1개 조회 위한 컨트롤러 (모두 접근 가능) <br />"
                    + "@param id(PathVariable) <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<TbgrantSelectDto\\> <br />"
                    + "@exception 정보 없음 <br />"
    )
    @PreAuthorize("permitAll()")
    @GetMapping("/{id}")
    public ResponseEntity<TbgrantDto.TbgrantSelectDto> detail(@PathVariable("id") String id, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        tbgrantService.access(new TbgrantDto.TbgrantAccessDto("tbgrant", "read",true, principalDetails.getTbuser().getId()));
        return ResponseEntity.status(HttpStatus.OK).body(tbgrantService.detail(id));
    }
    @Operation(summary = "접근권한 목록 조회(검색 기능 포함)",
            description = "접근권한 목록 조회 위한 컨트롤러 (모두 접근 가능) <br />"
                    + "@param TbgrantListDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<TbgrantSelectDto\\> <br />"
                    + "@exception (no Exception) <br />"
    )
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/list")
    public ResponseEntity<List<TbgrantDto.TbgrantSelectDto>> list(@Valid @RequestBody TbgrantDto.TbgrantListDto params, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        tbgrantService.access(new TbgrantDto.TbgrantAccessDto("tbgrant", "read",true, principalDetails.getTbuser().getId()));
        params.setNowTbuserId(principalDetails.getTbuser().getId());
        return ResponseEntity.status(HttpStatus.OK).body(tbgrantService.list(params));
    }
    @Operation(summary = "접근권한 목록 조회 - 스크롤 (검색 기능 포함)",
            description = "게시판 목록 조회 - 스크롤 위한 컨트롤러 (누구나 접근 가능) <br />"
                    + "@param TbgrantMoreListDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<Map<String, Object>\\> <br />"
                    + "@exception (no Exception) <br />"
    )
    @PreAuthorize("permitAll()")
    @PostMapping("/moreList")
    public ResponseEntity<List<TbgrantDto.TbgrantSelectDto>> moreList(@Valid @RequestBody TbgrantDto.TbgrantMoreListDto params, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        tbgrantService.access(new TbgrantDto.TbgrantAccessDto("tbgrant", "read",true, principalDetails.getTbuser().getId()));
        params.setNowTbuserId(principalDetails.getTbuser().getId());
        return ResponseEntity.status(HttpStatus.OK).body(tbgrantService.moreList(params));
    }

    @Operation(summary = "접근권한 목록 조회 - 페이지 (검색 기능 포함)",
            description = "접근권한 목록 조회 - 페이지 위한 컨트롤러 (누구나 접근 가능) <br />"
                    + "@param TbgrantPagedListDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<Map<String, Object>\\> <br />"
                    + "@exception (no Exception) <br />"
    )
    @PreAuthorize("permitAll()")
    @PostMapping("/pagedList")
    public ResponseEntity<CommonAfterPagedListDto<TbgrantDto.TbgrantSelectDto>> pagedList(@Valid @RequestBody TbgrantDto.TbgrantPagedListDto params, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        tbgrantService.access(new TbgrantDto.TbgrantAccessDto("tbgrant", "read",true, principalDetails.getTbuser().getId()));
        params.setNowTbuserId(principalDetails.getTbuser().getId());
        return ResponseEntity.status(HttpStatus.OK).body(tbgrantService.pagedList(params));
    }

}
