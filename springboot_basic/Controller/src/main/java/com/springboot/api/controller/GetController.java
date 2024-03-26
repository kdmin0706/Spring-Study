package com.springboot.api.controller;

import com.springboot.api.dto.MemberDto;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/get-api")
public class GetController {

    private final Logger logger = LoggerFactory.getLogger(GetController.class);

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String getHello() {
        logger.info("getHello 메서드가 호출되었습니다.");
        return "Hello World";
    }

    @GetMapping(value = "name")
    public String getName() {
        return "name";
    }

    @GetMapping(value = "/variable1/{variable}")
    public String getVariable1(@PathVariable String variable) {
        logger.info("@PathVariable을 통해 들어온 값 : {}", variable);
        return variable;
    }

    @GetMapping(value = "/variable2/{variable}")
    public String getVariable2(@PathVariable("variable") String var) {
        return var;
    }

    @GetMapping("/request1")
    @ApiOperation(value = "GET 메서드 예제", notes = "@RequestParam을 활용한 GET-METHOD")
    public String getRequestParam1(@ApiParam(value = "이름", required = true) @RequestParam String name,
                                   @ApiParam(value = "이메일", required = true) @RequestParam String email) {
        return name + " " + email;
    }

    @GetMapping("/request2")
    public String getRequestParam2(@RequestParam Map<String, String> param) {
        StringBuilder sb = new StringBuilder();

        param.forEach((key, value) -> sb.append(key)
                .append(" ").append(value).append("\n"));

        return sb.toString();
    }

    @GetMapping(value = "/request3")
    public String getRequestParam3(MemberDto memberDto) {
        return memberDto.toString();
    }
}
