package com.gdu.prj.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ActionForward {
  
  private String view;         // 포워드로 이동시 다음 나타날 화면 (to where?)
  private boolean isRedirect;  // 포워드(false) or 리다이렉트(true) (how?)
  
}
