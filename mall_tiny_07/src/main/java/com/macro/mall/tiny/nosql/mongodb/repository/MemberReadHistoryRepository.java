package com.macro.mall.tiny.nosql.mongodb.repository;

import com.macro.mall.tiny.nosql.mongodb.document.MemberReadHistory;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * 会员商品浏览历史Repository
 *
 * @author ovo
 */
public interface MemberReadHistoryRepository extends MongoRepository<MemberReadHistory, String> {
  
  /**
   * 根据memberId 查询 这个用户按照时间倒序浏览过的商品信息
   *
   * @param memberId memberId 会员id
   * @return
   */
  List<MemberReadHistory> findByMemberIdOrderByCreateTimeDesc(Long memberId);
  
  
}

