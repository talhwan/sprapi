package com.thc.sprapi.controller;

import com.thc.sprapi.dto.CommonAfterPagedListDto;
import com.thc.sprapi.dto.CommonDeleteListDto;
import com.thc.sprapi.dto.TbgrantDto;
import com.thc.sprapi.dto.TbnoticeDto;
import com.thc.sprapi.security.PrincipalDetails;
import com.thc.sprapi.service.TbgrantService;
import com.thc.sprapi.service.TbnoticeService;
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
@RequestMapping("/api/tbnotice")
@RestController
public class TbnoticeRestController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final TbgrantService tbgrantService;
    private final TbnoticeService tbnoticeService;
    public TbnoticeRestController(TbgrantService tbgrantService, TbnoticeService tbnoticeService) {
        this.tbgrantService = tbgrantService;
        this.tbnoticeService = tbnoticeService;
    }

    @Operation(summary = "게시글 등록",
            description = "게시글 등록 위한 컨트롤러 (누구나 접근 가능) <br />"
                    + "@param TbnoticeCreateDto <br />"
                    + "@return HttpStatus.CREATED(201) ResponseEntity\\<TbnoticeAfterCreateDto\\> <br />"
                    + "@exception 중복 <br />"
    )
    @PostMapping("")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<TbnoticeDto.TbnoticeAfterCreateDto> save(@Valid @RequestBody TbnoticeDto.TbnoticeCreateDto params, HttpServletRequest request, HttpServletResponse response, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        params.setNowGrant(tbgrantService.access(new TbgrantDto.TbgrantAccessDto("tbnotice", "create",false, principalDetails.getTbuser().getId())));
        params.setNowTbuserId(principalDetails.getTbuser().getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(tbnoticeService.create(params));
    }
    @Operation(summary = "게시글 수정",
            description = "게시글 수정 위한 컨트롤러 (누구나 접근 가능) <br />"
                    + "@param TbnoticeUpdateDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<TbnoticeAfterUpdateDto\\> <br />"
                    + "@exception 해당 자료 없음 <br />"
    )
    @PreAuthorize("hasRole('USER')")
    @PutMapping("")
    public ResponseEntity<TbnoticeDto.TbnoticeAfterUpdateDto> update(@Valid @RequestBody TbnoticeDto.TbnoticeUpdateDto params, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        params.setNowGrant(tbgrantService.access(new TbgrantDto.TbgrantAccessDto("tbnotice", "update",false, principalDetails.getTbuser().getId())));
        params.setNowTbuserId(principalDetails.getTbuser().getId());
        return ResponseEntity.status(HttpStatus.OK).body(tbnoticeService.update(params));
    }

    @Operation(summary = "게시글 삭제",
            description = "게시글 삭제 위한 컨트롤러 (누구나 접근 가능) <br />"
                    + "@param TbnoticeUpdateDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<TbnoticeAfterUpdateDto\\> <br />"
                    + "@exception 해당 자료 없음 <br />"
    )
    @PreAuthorize("hasRole('USER')")
    @DeleteMapping("")
    public ResponseEntity<CommonDeleteListDto> deleteList(@Valid @RequestBody CommonDeleteListDto params, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        params.setNowGrant(tbgrantService.access(new TbgrantDto.TbgrantAccessDto("tbnotice", "update",false, principalDetails.getTbuser().getId())));
        params.setNowTbuserId(principalDetails.getTbuser().getId());
        return ResponseEntity.status(HttpStatus.OK).body(tbnoticeService.deleteList(params));
    }

    @Operation(summary = "게시글 조회",
            description = "게시글 1개 조회 위한 컨트롤러 (모두 접근 가능) <br />"
                    + "@param id(PathVariable) <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<TbnoticeSelectDto\\> <br />"
                    + "@exception 정보 없음 <br />"
    )
    @PreAuthorize("permitAll()")
    @GetMapping("/{id}")
    public ResponseEntity<TbnoticeDto.TbnoticeSelectDto> detail(@PathVariable("id") String id, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        return ResponseEntity.status(HttpStatus.OK).body(tbnoticeService.detail(id));
    }
    @Operation(summary = "게시글 목록 조회(검색 기능 포함)",
            description = "게시글 목록 조회 위한 컨트롤러 (모두 접근 가능) <br />"
                    + "@param TbnoticeListDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<TbnoticeSelectDto\\> <br />"
                    + "@exception (no Exception) <br />"
    )
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/list")
    public ResponseEntity<List<TbnoticeDto.TbnoticeSelectDto>> list(@Valid @RequestBody TbnoticeDto.TbnoticeListDto params) {
        return ResponseEntity.status(HttpStatus.OK).body(tbnoticeService.list(params));
    }
    @Operation(summary = "게시글 목록 조회 - 스크롤 (검색 기능 포함)",
            description = "게시판 목록 조회 - 스크롤 위한 컨트롤러 (누구나 접근 가능) <br />"
                    + "@param TbnoticeMoreListDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<Map<String, Object>\\> <br />"
                    + "@exception (no Exception) <br />"
    )
    @PreAuthorize("permitAll()")
    @PostMapping("/moreList")
    public ResponseEntity<List<TbnoticeDto.TbnoticeSelectDto>> moreList(@Valid @RequestBody TbnoticeDto.TbnoticeMoreListDto params) {
        return ResponseEntity.status(HttpStatus.OK).body(tbnoticeService.moreList(params));
    }

    @Operation(summary = "게시글 목록 조회 - 페이지 (검색 기능 포함)",
            description = "게시글 목록 조회 - 페이지 위한 컨트롤러 (누구나 접근 가능) <br />"
                    + "@param TbnoticePagedListDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<Map<String, Object>\\> <br />"
                    + "@exception (no Exception) <br />"
    )
    @PreAuthorize("permitAll()")
    @PostMapping("/pagedList")
    public ResponseEntity<CommonAfterPagedListDto<TbnoticeDto.TbnoticeSelectDto>> pagedList(@Valid @RequestBody TbnoticeDto.TbnoticePagedListDto params) {
        return ResponseEntity.status(HttpStatus.OK).body(tbnoticeService.pagedList(params));
    }

}
