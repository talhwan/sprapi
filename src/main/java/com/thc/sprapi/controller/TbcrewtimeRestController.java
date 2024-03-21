package com.thc.sprapi.controller;

import com.thc.sprapi.dto.CommonAfterPagedListDto;
import com.thc.sprapi.dto.CommonDeleteListDto;
import com.thc.sprapi.dto.CommonDetailDto;
import com.thc.sprapi.dto.TbcrewtimeDto;
import com.thc.sprapi.security.PrincipalDetails;
import com.thc.sprapi.service.TbcrewtimeService;
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

@Tag(name = "3-1_3. 가맹점 출입시간 API 안내",
        description = "가맹점 출입시간 관련 기능 정의한 RestController.")
@RequestMapping("/api/tbcrewtime")
@RestController
public class TbcrewtimeRestController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final TbgrantService tbgrantService;
    private final TbcrewtimeService tbcrewtimeService;
    public TbcrewtimeRestController(TbgrantService tbgrantService, TbcrewtimeService tbcrewtimeService) {
        this.tbgrantService = tbgrantService;
        this.tbcrewtimeService = tbcrewtimeService;
    }

    @Operation(summary = "가맹점 출입시간 순서 수정",
            description = "가맹점 출입시간 순서 수정 위한 컨트롤러 (권한 확인) <br />"
                    + "@param TbcrewtimeSequenceDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<TbcrewtimeAfterUpdateDto\\> <br />"
                    + "@exception 해당 자료 없음 <br />"
    )
    @PreAuthorize("hasRole('USER')")
    @PutMapping("/sequence")
    public ResponseEntity<TbcrewtimeDto.TbcrewtimeAfterUpdateDto> sequence(@Valid @RequestBody TbcrewtimeDto.TbcrewtimeSequenceDto params, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        params.setNowGrant(tbgrantService.access("tbcrew", "update",true, principalDetails.getTbuser().getId()));
        params.setNowTbuserId(principalDetails.getTbuser().getId());
        return ResponseEntity.status(HttpStatus.OK).body(tbcrewtimeService.sequence(params));
    }

    /**/

    @Operation(summary = "가맹점 출입시간 등록",
            description = "가맹점 출입시간 등록 위한 컨트롤러 (누구나 접근 가능) <br />"
                    + "@param TbcrewtimeCreateDto <br />"
                    + "@return HttpStatus.CREATED(201) ResponseEntity\\<TbcrewtimeAfterCreateDto\\> <br />"
                    + "@exception 중복 <br />"
    )
    @PostMapping("")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<TbcrewtimeDto.TbcrewtimeAfterCreateDto> save(@Valid @RequestBody TbcrewtimeDto.TbcrewtimeCreateDto params, HttpServletRequest request, HttpServletResponse response, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        params.setNowGrant(tbgrantService.access("tbcrew", "create",true, principalDetails.getTbuser().getId()));
        params.setNowTbuserId(principalDetails.getTbuser().getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(tbcrewtimeService.create(params));
    }
    @Operation(summary = "가맹점 출입시간 수정",
            description = "가맹점 출입시간 수정 위한 컨트롤러 (누구나 접근 가능) <br />"
                    + "@param TbcrewtimeUpdateDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<TbcrewtimeAfterUpdateDto\\> <br />"
                    + "@exception 해당 자료 없음 <br />"
    )
    @PreAuthorize("hasRole('USER')")
    @PutMapping("")
    public ResponseEntity<TbcrewtimeDto.TbcrewtimeAfterUpdateDto> update(@Valid @RequestBody TbcrewtimeDto.TbcrewtimeUpdateDto params, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        params.setNowGrant(tbgrantService.access("tbcrew", "update",true, principalDetails.getTbuser().getId()));
        params.setNowTbuserId(principalDetails.getTbuser().getId());
        return ResponseEntity.status(HttpStatus.OK).body(tbcrewtimeService.update(params));
    }

    @Operation(summary = "가맹점 출입시간 삭제",
            description = "가맹점 출입시간 삭제 위한 컨트롤러 (누구나 접근 가능) <br />"
                    + "@param TbcrewtimeUpdateDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<TbcrewtimeAfterUpdateDto\\> <br />"
                    + "@exception 해당 자료 없음 <br />"
    )
    @PreAuthorize("hasRole('USER')")
    @DeleteMapping("")
    public ResponseEntity<CommonDeleteListDto> deleteList(@Valid @RequestBody CommonDeleteListDto params, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        params.setNowGrant(tbgrantService.access("tbcrew", "update",true, principalDetails.getTbuser().getId()));
        params.setNowTbuserId(principalDetails.getTbuser().getId());
        return ResponseEntity.status(HttpStatus.OK).body(tbcrewtimeService.deleteList(params));
    }

    @Operation(summary = "가맹점 출입시간 조회",
            description = "가맹점 출입시간 1개 조회 위한 컨트롤러 (모두 접근 가능) <br />"
                    + "@param id(PathVariable) <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<TbcrewtimeSelectDto\\> <br />"
                    + "@exception 정보 없음 <br />"
    )
    @PreAuthorize("permitAll()")
    @GetMapping("/get/{id}")
    public ResponseEntity<TbcrewtimeDto.TbcrewtimeSelectDto> detail(@PathVariable("id") String id, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        CommonDetailDto params = CommonDetailDto.builder().id(id).build();
        params.setNowGrant(tbgrantService.access("tbcrew", "read",true, principalDetails.getTbuser().getId()));
        return ResponseEntity.status(HttpStatus.OK).body(tbcrewtimeService.detail(params));
    }
    @Operation(summary = "가맹점 출입시간 목록 조회(검색 기능 포함)",
            description = "가맹점 출입시간 목록 조회 위한 컨트롤러 (모두 접근 가능) <br />"
                    + "@param TbcrewtimeListDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<TbcrewtimeSelectDto\\> <br />"
                    + "@exception (no Exception) <br />"
    )
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/list")
    public ResponseEntity<List<TbcrewtimeDto.TbcrewtimeSelectDto>> list(@Valid TbcrewtimeDto.TbcrewtimeListDto params, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        params.setNowGrant(tbgrantService.access("tbcrew", "read",true, principalDetails.getTbuser().getId()));
        return ResponseEntity.status(HttpStatus.OK).body(tbcrewtimeService.list(params));
    }
    @Operation(summary = "가맹점 출입시간 목록 조회 - 스크롤 (검색 기능 포함)",
            description = "가맹점 출입시간 목록 조회 - 스크롤 위한 컨트롤러 (누구나 접근 가능) <br />"
                    + "@param TbcrewtimeMoreListDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<Map<String, Object>\\> <br />"
                    + "@exception (no Exception) <br />"
    )
    @PreAuthorize("permitAll()")
    @GetMapping("/moreList")
    public ResponseEntity<List<TbcrewtimeDto.TbcrewtimeSelectDto>> moreList(@Valid TbcrewtimeDto.TbcrewtimeMoreListDto params, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        params.setNowGrant(tbgrantService.access("tbcrew", "read",true, principalDetails.getTbuser().getId()));
        return ResponseEntity.status(HttpStatus.OK).body(tbcrewtimeService.moreList(params));
    }

    @Operation(summary = "가맹점 출입시간 목록 조회 - 페이지 (검색 기능 포함)",
            description = "가맹점 출입시간 목록 조회 - 페이지 위한 컨트롤러 (누구나 접근 가능) <br />"
                    + "@param TbcrewtimePagedListDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<Map<String, Object>\\> <br />"
                    + "@exception (no Exception) <br />"
    )
    @PreAuthorize("permitAll()")
    @GetMapping("/pagedList")
    public ResponseEntity<CommonAfterPagedListDto<TbcrewtimeDto.TbcrewtimeSelectDto>> pagedList(@Valid TbcrewtimeDto.TbcrewtimePagedListDto params, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        params.setNowGrant(tbgrantService.access("tbcrew", "read",true, principalDetails.getTbuser().getId()));
        return ResponseEntity.status(HttpStatus.OK).body(tbcrewtimeService.pagedList(params));
    }

}
