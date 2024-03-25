package dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class StudentDto {

  private int studentNo;
  private String name; 
  private String kor; 
  private String eng; 
  private String math;
  private double average;
  private String grade;
  
}
