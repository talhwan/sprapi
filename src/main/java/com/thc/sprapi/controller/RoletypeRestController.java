package com.thc.sprapi.controller;

import com.thc.sprapi.dto.CommonDeleteListDto;
import com.thc.sprapi.dto.RoletypeDto;
import com.thc.sprapi.security.PrincipalDetails;
import com.thc.sprapi.service.RoletypeService;
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

@Tag(name = "2. 접근권한 API 안내",
        description = "접근권한 관련 기능 정의한 RestController.")
@RequestMapping("/api/roletype")
@RestController
public class RoletypeRestController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final RoletypeService roletypeService;
    public RoletypeRestController(RoletypeService roletypeService) {
        this.roletypeService = roletypeService;
    }

    @Operation(summary = "접근권한 등록",
            description = "접근권한 등록 위한 컨트롤러 (누구나 접근 가능) <br />"
                    + "@param RoletypeCreateDto <br />"
                    + "@return HttpStatus.CREATED(201) ResponseEntity\\<RoletypeAfterCreateDto\\> <br />"
                    + "@exception 중복 <br />"
    )
    @PostMapping("")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<RoletypeDto.RoletypeSelectDto> save(@Valid @RequestBody RoletypeDto.RoletypeCreateDto params, HttpServletRequest request, HttpServletResponse response, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        return ResponseEntity.status(HttpStatus.CREATED).body(roletypeService.create(params));
    }
    @Operation(summary = "접근권한 삭제",
            description = "접근권한 삭제 위한 컨트롤러 (누구나 접근 가능) <br />"
                    + "@param RoletypeUpdateDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<RoletypeAfterUpdateDto\\> <br />"
                    + "@exception 해당 자료 없음 <br />"
    )
    @PreAuthorize("hasRole('USER')")
    @DeleteMapping("")
    public ResponseEntity<CommonDeleteListDto> deleteList(@Valid @RequestBody CommonDeleteListDto params, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        params.setTbuserId(principalDetails.getTbuser().getId());
        return ResponseEntity.status(HttpStatus.OK).body(roletypeService.deleteList(params));
    }
    @Operation(summary = "접근권한 조회",
            description = "접근권한 1개 조회 위한 컨트롤러 (모두 접근 가능) <br />"
                    + "@param id(PathVariable) <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<RoletypeSelectDto\\> <br />"
                    + "@exception 정보 없음 <br />"
    )
    //@PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<RoletypeDto.RoletypeSelectDto> detail(@PathVariable("id") String id) {
        return ResponseEntity.status(HttpStatus.OK).body(roletypeService.detail(id));
    }
    @Operation(summary = "접근권한 목록 조회(검색 기능 포함)",
            description = "접근권한 목록 조회 위한 컨트롤러 (모두 접근 가능) <br />"
                    + "@param RoletypeListDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<RoletypeSelectDto\\> <br />"
                    + "@exception (no Exception) <br />"
    )
    //@PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/list")
    public ResponseEntity<List<RoletypeDto.RoletypeSelectDto>> list() {
        return ResponseEntity.status(HttpStatus.OK).body(roletypeService.list());
    }
}
