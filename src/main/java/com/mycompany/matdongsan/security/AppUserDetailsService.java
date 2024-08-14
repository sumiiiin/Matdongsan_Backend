package com.mycompany.matdongsan.security;

// 필요한 클래스 및 인터페이스들을 import
import java.util.ArrayList; // ArrayList 클래스를 사용하기 위해 import
import java.util.List; // List 인터페이스를 사용하기 위해 import

import org.springframework.beans.factory.annotation.Autowired; // 의존성 주입을 위해 import
import org.springframework.security.core.GrantedAuthority; // 권한 관련 인터페이스 import
import org.springframework.security.core.userdetails.UserDetails; // 사용자 세부 정보를 나타내는 인터페이스 import
import org.springframework.security.core.userdetails.UserDetailsService; // 사용자 세부 정보를 로드하기 위한 인터페이스 import
import org.springframework.security.core.userdetails.UsernameNotFoundException; // 사용자 이름을 찾지 못했을 때의 예외 import
import org.springframework.stereotype.Service; // 서비스 어노테이션을 사용하기 위해 import

import com.mycompany.matdongsan.dao.UserCommonDataDao; // 사용자 공통 데이터 접근 객체를 사용하기 위해 import
import com.mycompany.matdongsan.dto.UserCommonData; // 사용자 공통 데이터를 나타내는 클래스 import

// AppUserDetailsService 클래스는 UserDetailsService 인터페이스를 구현
@Service // 이 클래스가 서비스 계층의 컴포넌트임을 나타냄
public class AppUserDetailsService implements UserDetailsService {
	// UserCommonDataDao를 자동으로 주입받기 위해 @Autowired 사용
	@Autowired
	private UserCommonDataDao userCommonDataDao;

	// 사용자의 이름으로 사용자 세부 정보를 로드하는 메서드
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// 데이터베이스에서 사용자 이름으로 사용자 공통 데이터를 가져옴
		UserCommonData userCommonData = userCommonDataDao.selectByUnumber(username);
		
		// 사용자가 존재하지 않으면 UsernameNotFoundException을 던짐
		if (userCommonData == null) {
			throw new UsernameNotFoundException(username);
		}

		// 사용자의 권한 목록을 저장할 리스트 생성
		List<GrantedAuthority> authorities = new ArrayList<>();
		// 권한을 추가할 수 있지만 현재는 주석 처리됨
//      authorities.add(new SimpleGrantedAuthority(member.getMrole()));

		// AppUserDetails 객체를 생성하여 사용자 공통 데이터와 권한을 설정
		AppUserDetails userDetails = new AppUserDetails(userCommonData, authorities);
		// 생성된 AppUserDetails 객체를 반환
		return userDetails;
	}
}
