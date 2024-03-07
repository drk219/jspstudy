package common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data      // getter setter 다 들어있는 
@Builder

public class ActionForward {
  
  private String view;
  private boolean isRedirect;  // 기본은 리다이렉트 안하겟다 (= 포워드 하겠다)

}
