package com.mycompany.matdongsan.security;

// 필요한 클래스 및 인터페이스들을 import
import java.util.List; // List 컬렉션을 사용하기 위해 import
import org.springframework.security.core.GrantedAuthority; // 권한 관련 인터페이스 import
import org.springframework.security.core.userdetails.User; // Spring Security의 User 클래스를 import
import com.mycompany.matdongsan.dto.UserCommonData; // 사용자 공통 데이터를 담고 있는 클래스 import

// AppUserDetails 클래스는 Spring Security의 User 클래스를 확장 (extends)하여 추가적인 사용자 정보를 포함
public class AppUserDetails extends User {
   
   // UserCommonData 객체를 저장할 변수 선언
   private UserCommonData userEmail;

   // 생성자 정의, UserCommonData와 권한 목록을 매개변수로 받음
   public AppUserDetails(UserCommonData userEmail, List<GrantedAuthority> authorities) {
      // 부모 클래스(User)의 생성자를 호출하여 필요한 정보들을 전달
      // userEmail 객체에서 이메일, 비밀번호, 계정 활성화 상태 등을 가져와 설정
      super(userEmail.getUemail(), userEmail.getUpassword(), userEmail.isUremoved(), 
            true, // 계정 만료 여부 (true: 만료되지 않음)
            true, // 자격 증명 만료 여부 (true: 만료되지 않음)
            true, // 계정 잠김 여부 (true: 잠기지 않음)
            authorities); // 사용자의 권한 목록
      // userEmail 필드를 초기화
      this.userEmail = userEmail;
   }

   // userEmail 필드를 반환하는 getter 메서드
   public UserCommonData getUser() {
      return userEmail;
   }
}
