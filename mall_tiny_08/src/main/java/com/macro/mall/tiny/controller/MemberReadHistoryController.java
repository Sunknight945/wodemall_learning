package com.macro.mall.tiny.controller;

import com.macro.mall.tiny.common.api.CommonResult;
import com.macro.mall.tiny.nosql.mongodb.document.MemberReadHistory;
import com.macro.mall.tiny.service.MemberReadHistoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 会员商品浏览记录管理Controller
 *
 * @author ovo
 */
@RestController
@Api(tags = "MemberReadHistoryController", description = "会员商品浏览记录管理")
@RequestMapping("/member/readHistory")
public class MemberReadHistoryController {
  @Autowired
  private MemberReadHistoryService memberReadHistoryService;
  
  @ApiOperation("创建浏览记录")
  @PostMapping("/create")
  public CommonResult create(MemberReadHistory memberReadHistory) {
    int count = memberReadHistoryService.create(memberReadHistory);
    if (count > 0) {
      return CommonResult.success(count);
    } else {
      return CommonResult.failed();
    }
  }
  
  @ApiOperation("删除浏览记录")
  @DeleteMapping("/delete")
  public CommonResult delete(@RequestParam("ids") List<String> ids) {
    int count = memberReadHistoryService.delete(ids);
    if (count > 0) {
      return CommonResult.success(count);
    } else {
      return CommonResult.failed();
    }
  }
  
  @ApiOperation("展示浏览记录")
  @GetMapping("/list")
  public CommonResult<List<MemberReadHistory>> list(Long memberId) {
    List<MemberReadHistory> memberReadHistoryList = memberReadHistoryService.list(memberId);
    return CommonResult.success(memberReadHistoryList);
  }
}
