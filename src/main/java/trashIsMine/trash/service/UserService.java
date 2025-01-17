package trashIsMine.trash.service;

import java.util.Collections;
import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import trashIsMine.trash.domain.Authority;
import trashIsMine.trash.domain.User;
import trashIsMine.trash.dto.UserDto;
import trashIsMine.trash.exception.DuplicateMemberException;
import trashIsMine.trash.exception.NotFoundMemberException;
import trashIsMine.trash.repository.UserRepository;
import trashIsMine.trash.util.SecurityUtil;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public UserDto signup(UserDto userDto) {
        if (userRepository.findOneWithAuthoritiesByUsername(userDto.getUsername()).orElse(null) != null) {
            throw new DuplicateMemberException("이미 가입되어 있는 유저입니다.");
        }

        Authority authority = Authority.builder()
                .authorityName("ROLE_USER")
                .build();

        User user = User.builder()
                .username(userDto.getUsername())
                .password(passwordEncoder.encode(userDto.getPassword()))
                .name(userDto.getName())
                .email(userDto.getEmail())
                .birth(userDto.getBirth())
                .introduction(userDto.getIntroduction())
                .gender(userDto.getGender())
                .authorities(Collections.singleton(authority))
                .activated(true)
                .build();

        return UserDto.from(userRepository.save(user));
    }

    @Transactional(readOnly = true)
    public UserDto getUserWithAuthorities(String username) {
        return UserDto.from(userRepository.findOneWithAuthoritiesByUsername(username).orElse(null));
    }

    @Transactional(readOnly = true)
    public UserDto getMyUserWithAuthorities() {
        return UserDto.from(
                SecurityUtil.getCurrentUsername()
                        .flatMap(userRepository::findOneWithAuthoritiesByUsername)
                        .orElseThrow(() -> new NotFoundMemberException("Member not found"))
        );
    }

    public boolean userExists(String username) {
        return userRepository.findByUsername(username).isPresent();
    }

    public Long findUserIdByUsername(String username) {
        Optional<User> user = userRepository.findByUsername(username);
        return user.map(User::getId).orElse(null); // 사용자 존재 시 ID 반환, 존재하지 않으면 null
    }
    // 사용자 삭제
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
    // 사용자 ID로 사용자 조회
    public User findUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }
}