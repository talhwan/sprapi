package com.thc.sprapi.controller;

import com.thc.sprapi.dto.CommonAfterPagedListDto;
import com.thc.sprapi.dto.CommonDeleteListDto;
import com.thc.sprapi.dto.CommonDetailDto;
import com.thc.sprapi.dto.TbbannerDto;
import com.thc.sprapi.security.PrincipalDetails;
import com.thc.sprapi.service.TbbannerService;
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

@Tag(name = "2-3. 배너 API 안내",
        description = "배너 관련 기능 정의한 RestController.")
@RequestMapping("/api/tbbanner")
@RestController
public class TbbannerRestController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final TbgrantService tbgrantService;
    private final TbbannerService tbbannerService;
    public TbbannerRestController(TbgrantService tbgrantService, TbbannerService tbbannerService) {
        this.tbgrantService = tbgrantService;
        this.tbbannerService = tbbannerService;
    }

    @Operation(summary = "배너 조회(추천)",
            description = "배너 1개 조회(추천) 위한 컨트롤러 (모두 접근 가능) <br />"
                    + "@param id(PathVariable) <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<TbbannerSelectDto\\> <br />"
                    + "@exception 정보 없음 <br />"
    )
    @PreAuthorize("permitAll()")
    @GetMapping("/recommend")
    public ResponseEntity<TbbannerDto.TbbannerSelectDto> recommend(@AuthenticationPrincipal PrincipalDetails principalDetails) {
        //params.setNowGrant(tbgrantService.access("tbbanner", "read",false, principalDetails.getTbuser().getId()));
        return ResponseEntity.status(HttpStatus.OK).body(tbbannerService.recommend());
    }

    @Operation(summary = "배너 순서 수정",
            description = "배너 순서 수정 위한 컨트롤러 (권한 확인) <br />"
                    + "@param TbbannerSequenceDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<TbbannerAfterUpdateDto\\> <br />"
                    + "@exception 해당 자료 없음 <br />"
    )
    @PreAuthorize("hasRole('USER')")
    @PutMapping("/sequence")
    public ResponseEntity<TbbannerDto.TbbannerAfterUpdateDto> sequence(@Valid @RequestBody TbbannerDto.TbbannerSequenceDto params, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        params.setNowGrant(tbgrantService.access("tbbanner", "update",true, principalDetails.getTbuser().getId()));
        params.setNowTbuserId(principalDetails.getTbuser().getId());
        return ResponseEntity.status(HttpStatus.OK).body(tbbannerService.sequence(params));
    }

    /**/

    @Operation(summary = "배너 등록",
            description = "배너 등록 위한 컨트롤러 (누구나 접근 가능) <br />"
                    + "@param TbbannerCreateDto <br />"
                    + "@return HttpStatus.CREATED(201) ResponseEntity\\<TbbannerAfterCreateDto\\> <br />"
                    + "@exception 중복 <br />"
    )
    @PostMapping("")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<TbbannerDto.TbbannerAfterCreateDto> save(@Valid @RequestBody TbbannerDto.TbbannerCreateDto params, HttpServletRequest request, HttpServletResponse response, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        params.setNowGrant(tbgrantService.access("tbbanner", "create",true, principalDetails.getTbuser().getId()));
        params.setNowTbuserId(principalDetails.getTbuser().getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(tbbannerService.create(params));
    }
    @Operation(summary = "배너 수정",
            description = "배너 수정 위한 컨트롤러 (누구나 접근 가능) <br />"
                    + "@param TbbannerUpdateDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<TbbannerAfterUpdateDto\\> <br />"
                    + "@exception 해당 자료 없음 <br />"
    )
    @PreAuthorize("hasRole('USER')")
    @PutMapping("")
    public ResponseEntity<TbbannerDto.TbbannerAfterUpdateDto> update(@Valid @RequestBody TbbannerDto.TbbannerUpdateDto params, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        params.setNowGrant(tbgrantService.access("tbbanner", "update",true, principalDetails.getTbuser().getId()));
        params.setNowTbuserId(principalDetails.getTbuser().getId());
        return ResponseEntity.status(HttpStatus.OK).body(tbbannerService.update(params));
    }

    @Operation(summary = "배너 삭제",
            description = "배너 삭제 위한 컨트롤러 (누구나 접근 가능) <br />"
                    + "@param TbbannerUpdateDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<TbbannerAfterUpdateDto\\> <br />"
                    + "@exception 해당 자료 없음 <br />"
    )
    @PreAuthorize("hasRole('USER')")
    @DeleteMapping("")
    public ResponseEntity<CommonDeleteListDto> deleteList(@Valid @RequestBody CommonDeleteListDto params, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        params.setNowGrant(tbgrantService.access("tbbanner", "update",true, principalDetails.getTbuser().getId()));
        params.setNowTbuserId(principalDetails.getTbuser().getId());
        return ResponseEntity.status(HttpStatus.OK).body(tbbannerService.deleteList(params));
    }

    @Operation(summary = "배너 조회",
            description = "배너 1개 조회 위한 컨트롤러 (모두 접근 가능) <br />"
                    + "@param id(PathVariable) <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<TbbannerSelectDto\\> <br />"
                    + "@exception 정보 없음 <br />"
    )
    @PreAuthorize("permitAll()")
    @GetMapping("/get/{id}")
    public ResponseEntity<TbbannerDto.TbbannerSelectDto> detail(@PathVariable("id") String id, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        CommonDetailDto params = CommonDetailDto.builder().id(id).build();
        //params.setNowGrant(tbgrantService.access("tbbanner", "read",true, principalDetails.getTbuser().getId()));
        return ResponseEntity.status(HttpStatus.OK).body(tbbannerService.detail(params));
    }
    @Operation(summary = "배너 목록 조회(검색 기능 포함)",
            description = "배너 목록 조회 위한 컨트롤러 (모두 접근 가능) <br />"
                    + "@param TbbannerListDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<TbbannerSelectDto\\> <br />"
                    + "@exception (no Exception) <br />"
    )
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/list")
    public ResponseEntity<List<TbbannerDto.TbbannerSelectDto>> list(@Valid TbbannerDto.TbbannerListDto params) {
        return ResponseEntity.status(HttpStatus.OK).body(tbbannerService.list(params));
    }
    @Operation(summary = "배너 목록 조회 - 스크롤 (검색 기능 포함)",
            description = "배너 목록 조회 - 스크롤 위한 컨트롤러 (누구나 접근 가능) <br />"
                    + "@param TbbannerMoreListDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<Map<String, Object>\\> <br />"
                    + "@exception (no Exception) <br />"
    )
    @PreAuthorize("permitAll()")
    @GetMapping("/moreList")
    public ResponseEntity<List<TbbannerDto.TbbannerSelectDto>> moreList(@Valid TbbannerDto.TbbannerMoreListDto params) {
        return ResponseEntity.status(HttpStatus.OK).body(tbbannerService.moreList(params));
    }

    @Operation(summary = "배너 목록 조회 - 페이지 (검색 기능 포함)",
            description = "배너 목록 조회 - 페이지 위한 컨트롤러 (누구나 접근 가능) <br />"
                    + "@param TbbannerPagedListDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<Map<String, Object>\\> <br />"
                    + "@exception (no Exception) <br />"
    )
    @PreAuthorize("permitAll()")
    @GetMapping("/pagedList")
    public ResponseEntity<CommonAfterPagedListDto<TbbannerDto.TbbannerSelectDto>> pagedList(@Valid TbbannerDto.TbbannerPagedListDto params) {
        return ResponseEntity.status(HttpStatus.OK).body(tbbannerService.pagedList(params));
    }

}
