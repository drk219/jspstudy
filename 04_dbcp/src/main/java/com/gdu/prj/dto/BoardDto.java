package com.gdu.prj.dto;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder

// DTO : 데이터베이스나 다른 소스로부터 데이터를 얻거나 해당 데이터를 저장할 때 사용
public class BoardDto {
  
  private int board_no;       // 게시글 번호
  private String title;       // 제목
  private String contents;    // 내용
  private Date modified_at;   // 수정일
  private Date created_at;    // 작성일 

}
