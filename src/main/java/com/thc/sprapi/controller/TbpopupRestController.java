package com.thc.sprapi.controller;

import com.thc.sprapi.dto.CommonAfterPagedListDto;
import com.thc.sprapi.dto.CommonDeleteListDto;
import com.thc.sprapi.dto.CommonDetailDto;
import com.thc.sprapi.dto.TbpopupDto;
import com.thc.sprapi.security.PrincipalDetails;
import com.thc.sprapi.service.TbpopupService;
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

@Tag(name = "2. 팝업 API 안내",
        description = "팝업 관련 기능 정의한 RestController.")
@RequestMapping("/api/tbpopup")
@RestController
public class TbpopupRestController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final TbgrantService tbgrantService;
    private final TbpopupService tbpopupService;
    public TbpopupRestController(TbgrantService tbgrantService, TbpopupService tbpopupService) {
        this.tbgrantService = tbgrantService;
        this.tbpopupService = tbpopupService;
    }

    @Operation(summary = "팝업 순서 수정",
            description = "팝업 순서 수정 위한 컨트롤러 (권한 확인) <br />"
                    + "@param TbpopupSequenceDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<TbpopupAfterUpdateDto\\> <br />"
                    + "@exception 해당 자료 없음 <br />"
    )
    @PreAuthorize("hasRole('USER')")
    @PutMapping("/sequence")
    public ResponseEntity<TbpopupDto.TbpopupAfterUpdateDto> sequence(@Valid @RequestBody TbpopupDto.TbpopupSequenceDto params, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        params.setNowGrant(tbgrantService.access("tbpopup", "update",true, principalDetails.getTbuser().getId()));
        params.setNowTbuserId(principalDetails.getTbuser().getId());
        return ResponseEntity.status(HttpStatus.OK).body(tbpopupService.sequence(params));
    }

    /**/

    @Operation(summary = "팝업 등록",
            description = "팝업 등록 위한 컨트롤러 (누구나 접근 가능) <br />"
                    + "@param TbpopupCreateDto <br />"
                    + "@return HttpStatus.CREATED(201) ResponseEntity\\<TbpopupAfterCreateDto\\> <br />"
                    + "@exception 중복 <br />"
    )
    @PostMapping("")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<TbpopupDto.TbpopupAfterCreateDto> save(@Valid @RequestBody TbpopupDto.TbpopupCreateDto params, HttpServletRequest request, HttpServletResponse response, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        params.setNowGrant(tbgrantService.access("tbpopup", "create",true, principalDetails.getTbuser().getId()));
        params.setNowTbuserId(principalDetails.getTbuser().getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(tbpopupService.create(params));
    }
    @Operation(summary = "팝업 수정",
            description = "팝업 수정 위한 컨트롤러 (누구나 접근 가능) <br />"
                    + "@param TbpopupUpdateDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<TbpopupAfterUpdateDto\\> <br />"
                    + "@exception 해당 자료 없음 <br />"
    )
    @PreAuthorize("hasRole('USER')")
    @PutMapping("")
    public ResponseEntity<TbpopupDto.TbpopupAfterUpdateDto> update(@Valid @RequestBody TbpopupDto.TbpopupUpdateDto params, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        params.setNowGrant(tbgrantService.access("tbpopup", "update",true, principalDetails.getTbuser().getId()));
        params.setNowTbuserId(principalDetails.getTbuser().getId());
        return ResponseEntity.status(HttpStatus.OK).body(tbpopupService.update(params));
    }

    @Operation(summary = "팝업 삭제",
            description = "팝업 삭제 위한 컨트롤러 (누구나 접근 가능) <br />"
                    + "@param TbpopupUpdateDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<TbpopupAfterUpdateDto\\> <br />"
                    + "@exception 해당 자료 없음 <br />"
    )
    @PreAuthorize("hasRole('USER')")
    @DeleteMapping("")
    public ResponseEntity<CommonDeleteListDto> deleteList(@Valid @RequestBody CommonDeleteListDto params, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        params.setNowGrant(tbgrantService.access("tbpopup", "update",true, principalDetails.getTbuser().getId()));
        params.setNowTbuserId(principalDetails.getTbuser().getId());
        return ResponseEntity.status(HttpStatus.OK).body(tbpopupService.deleteList(params));
    }

    @Operation(summary = "팝업 조회",
            description = "팝업 1개 조회 위한 컨트롤러 (모두 접근 가능) <br />"
                    + "@param id(PathVariable) <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<TbpopupSelectDto\\> <br />"
                    + "@exception 정보 없음 <br />"
    )
    @PreAuthorize("permitAll()")
    @GetMapping("/{id}")
    public ResponseEntity<TbpopupDto.TbpopupSelectDto> detail(@PathVariable("id") String id, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        CommonDetailDto params = CommonDetailDto.builder().id(id).build();
        //params.setNowGrant(tbgrantService.access("tbpopup", "read",true, principalDetails.getTbuser().getId()));
        return ResponseEntity.status(HttpStatus.OK).body(tbpopupService.detail(params));
    }
    @Operation(summary = "팝업 목록 조회(검색 기능 포함)",
            description = "팝업 목록 조회 위한 컨트롤러 (모두 접근 가능) <br />"
                    + "@param TbpopupListDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<TbpopupSelectDto\\> <br />"
                    + "@exception (no Exception) <br />"
    )
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/list")
    public ResponseEntity<List<TbpopupDto.TbpopupSelectDto>> list(@Valid @RequestBody TbpopupDto.TbpopupListDto params) {
        return ResponseEntity.status(HttpStatus.OK).body(tbpopupService.list(params));
    }
    @Operation(summary = "팝업 목록 조회 - 스크롤 (검색 기능 포함)",
            description = "팝업 목록 조회 - 스크롤 위한 컨트롤러 (누구나 접근 가능) <br />"
                    + "@param TbpopupMoreListDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<Map<String, Object>\\> <br />"
                    + "@exception (no Exception) <br />"
    )
    @PreAuthorize("permitAll()")
    @PostMapping("/moreList")
    public ResponseEntity<List<TbpopupDto.TbpopupSelectDto>> moreList(@Valid @RequestBody TbpopupDto.TbpopupMoreListDto params) {
        return ResponseEntity.status(HttpStatus.OK).body(tbpopupService.moreList(params));
    }

    @Operation(summary = "팝업 목록 조회 - 페이지 (검색 기능 포함)",
            description = "팝업 목록 조회 - 페이지 위한 컨트롤러 (누구나 접근 가능) <br />"
                    + "@param TbpopupPagedListDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<Map<String, Object>\\> <br />"
                    + "@exception (no Exception) <br />"
    )
    @PreAuthorize("permitAll()")
    @PostMapping("/pagedList")
    public ResponseEntity<CommonAfterPagedListDto<TbpopupDto.TbpopupSelectDto>> pagedList(@Valid @RequestBody TbpopupDto.TbpopupPagedListDto params) {
        return ResponseEntity.status(HttpStatus.OK).body(tbpopupService.pagedList(params));
    }

}
