package com.thc.sprapi.controller;

import com.thc.sprapi.dto.CommonAfterPagedListDto;
import com.thc.sprapi.dto.CommonDeleteListDto;
import com.thc.sprapi.dto.CommonDetailDto;
import com.thc.sprapi.dto.TbcrewuserDto;
import com.thc.sprapi.security.PrincipalDetails;
import com.thc.sprapi.service.TbgrantService;
import com.thc.sprapi.service.TbcrewuserService;
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

@Tag(name = "4-1_2. 가맹점 출입자 API 안내",
        description = "가맹점 출입자 관련 기능 정의한 RestController.")
@RequestMapping("/api/tbcrewuser")
@RestController
public class TbcrewuserRestController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final TbgrantService tbgrantService;
    private final TbcrewuserService tbcrewuserService;
    public TbcrewuserRestController(TbgrantService tbgrantService, TbcrewuserService tbcrewuserService) {
        this.tbgrantService = tbgrantService;
        this.tbcrewuserService = tbcrewuserService;
    }

    @Operation(summary = "가맹점 출입자 참여",
            description = "가맹점 출입자 참여 위한 컨트롤러 (누구나 접근 가능) <br />"
                    + "@param TbcrewuserCreateDto <br />"
                    + "@return HttpStatus.CREATED(201) ResponseEntity\\<TbcrewuserAfterCreateDto\\> <br />"
                    + "@exception 중복 <br />"
    )
    @PostMapping("/join")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<TbcrewuserDto.TbcrewuserAfterCreateDto> join(@Valid @RequestBody TbcrewuserDto.TbcrewuserCreateDto params, HttpServletRequest request, HttpServletResponse response, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        params.setNowGrant(tbgrantService.access("tbcrew", "create",false, principalDetails.getTbuser().getId()));
        params.setTbuserId(principalDetails.getTbuser().getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(tbcrewuserService.join(params));
    }
    /*
     */

    @Operation(summary = "가맹점 출입자 등록",
            description = "가맹점 출입자 신규 등록을 위한 컨트롤러 (누구나 접근 가능) <br />"
                    + "@param TbcrewuserCreateDto <br />"
                    + "@return HttpStatus.CREATED(201) ResponseEntity\\<TbcrewuserAfterCreateDto\\> <br />"
                    + "@exception 중복 <br />"
    )
    @PostMapping("")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<TbcrewuserDto.TbcrewuserAfterCreateDto> save(@Valid @RequestBody TbcrewuserDto.TbcrewuserCreateDto params, HttpServletRequest request, HttpServletResponse response, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        params.setNowGrant(tbgrantService.access("tbcrew", "create",true, principalDetails.getTbuser().getId()));
        params.setTbuserId(principalDetails.getTbuser().getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(tbcrewuserService.create(params));
    }
    @Operation(summary = "가맹점 출입자 수정",
            description = "가맹점 출입자 기존 수정을 위한 컨트롤러 (누구나 접근 가능) <br />"
                    + "@param TbcrewuserUpdateDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<TbcrewuserAfterUpdateDto\\> <br />"
                    + "@exception 해당 자료 없음 <br />"
    )
    @PutMapping("")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<TbcrewuserDto.TbcrewuserAfterUpdateDto> update(@Valid @RequestBody TbcrewuserDto.TbcrewuserUpdateDto params, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        params.setNowGrant(tbgrantService.access("tbcrew", "update",true, principalDetails.getTbuser().getId()));
        return ResponseEntity.status(HttpStatus.OK).body(tbcrewuserService.update(params));
    }
    @Operation(summary = "가맹점 출입자 삭제",
            description = "가맹점 출입자 삭제를 위한 컨트롤러 (누구나 접근 가능) <br />"
                    + "@param TbcrewuserUpdateDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<TbcrewuserAfterUpdateDto\\> <br />"
                    + "@exception 해당 자료 없음 <br />"
    )
    @DeleteMapping("")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<CommonDeleteListDto> deleteList(@Valid @RequestBody CommonDeleteListDto params, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        params.setNowGrant(tbgrantService.access("tbcrew", "update",true, principalDetails.getTbuser().getId()));
        return ResponseEntity.status(HttpStatus.OK).body(tbcrewuserService.deleteList(params));
    }

    @Operation(summary = "가맹점 출입자 조회",
            description = "가맹점 출입자 1개 조회를 위한 컨트롤러 (모두 접근 가능) <br />"
                    + "@param id(PathVariable) <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<TbcrewuserSelectDto\\> <br />"
                    + "@exception 정보 없음 <br />"
    )
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<TbcrewuserDto.TbcrewuserSelectDto> detail(@PathVariable("id") String id, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        CommonDetailDto params = CommonDetailDto.builder().id(id).build();
        params.setNowGrant(tbgrantService.access("tbcrew", "read",false, principalDetails.getTbuser().getId()));
        return ResponseEntity.status(HttpStatus.OK).body(tbcrewuserService.detail(params));
    }
    @Operation(summary = "가맹점 출입자 목록 조회(검색 기능 포함)",
            description = "가맹점 출입자 목록 조회를 위한 컨트롤러 (모두 접근 가능) <br />"
                    + "@param TbcrewuserListDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<TbcrewuserSelectDto\\> <br />"
                    + "@exception (no Exception) <br />"
    )
    @PostMapping("/list")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<TbcrewuserDto.TbcrewuserSelectDto>> list(@Valid @RequestBody TbcrewuserDto.TbcrewuserListDto params, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        params.setNowGrant(tbgrantService.access("tbcrew", "read",false, principalDetails.getTbuser().getId()));
        return ResponseEntity.status(HttpStatus.OK).body(tbcrewuserService.list(params));
    }
    @Operation(summary = "가맹점 출입자 목록 조회 - 스크롤 (검색 기능 포함)",
            description = "가맹점 출입자 목록 조회 - 스크롤 위한 컨트롤러 (누구나 접근 가능) <br />"
                    + "@param TbcrewuserMoreListDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<Map<String, Object>\\> <br />"
                    + "@exception (no Exception) <br />"
    )
    @PostMapping("/moreList")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<TbcrewuserDto.TbcrewuserSelectDto>> moreList(@Valid @RequestBody TbcrewuserDto.TbcrewuserMoreListDto params, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        params.setNowGrant(tbgrantService.access("tbcrew", "read",false, principalDetails.getTbuser().getId()));
        return ResponseEntity.status(HttpStatus.OK).body(tbcrewuserService.moreList(params));
    }

    @Operation(summary = "가맹점 출입자 목록 조회 - 페이지 (검색 기능 포함)",
            description = "가맹점 출입자 목록 조회 - 페이지 위한 컨트롤러 (누구나 접근 가능) <br />"
                    + "@param TbcrewuserPagedListDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<Map<String, Object>\\> <br />"
                    + "@exception (no Exception) <br />"
    )
    @PostMapping("/pagedList")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CommonAfterPagedListDto<TbcrewuserDto.TbcrewuserSelectDto>> pagedList(@Valid @RequestBody TbcrewuserDto.TbcrewuserPagedListDto params, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        params.setNowGrant(tbgrantService.access("tbcrew", "read",false, principalDetails.getTbuser().getId()));
        return ResponseEntity.status(HttpStatus.OK).body(tbcrewuserService.pagedList(params));
    }

}
