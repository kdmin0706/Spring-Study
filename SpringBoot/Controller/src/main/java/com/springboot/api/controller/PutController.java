package com.springboot.api.controller;

import com.springboot.api.dto.MemberDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/put-api")
public class PutController {

    @PutMapping(value = "/member")
    public String putMember(@RequestBody Map<String, Object> putData) {
        StringBuilder sb = new StringBuilder();

        putData.forEach((key, value) -> sb.append(key)
                .append(" ").append(value).append("\n"));

        return sb.toString();
    }

    @PutMapping(value = "/member1")
    public String putMemberDto1(@RequestBody MemberDto memberDto) {
        return memberDto.toString();
    }

    @PutMapping(value = "/member2")
    public MemberDto putMemberDto2(@RequestBody MemberDto memberDto) {
        return memberDto;
    }

    @PutMapping(value = "/member3")
    public ResponseEntity<MemberDto> putMemberDto3(@RequestBody MemberDto memberDto) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(memberDto);
    }
}
