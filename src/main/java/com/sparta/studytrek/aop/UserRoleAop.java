package com.sparta.studytrek.aop;

import com.sparta.studytrek.common.exception.CustomException;
import com.sparta.studytrek.common.exception.ErrorCode;
import com.sparta.studytrek.domain.auth.entity.UserRoleEnum;
import com.sparta.studytrek.jwt.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Component
@RequiredArgsConstructor
public class UserRoleAop {

    private final JwtUtil jwtUtil;

    public String getUserRole() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        String token = jwtUtil.getJwtFromHeader(request);
        return jwtUtil.getUserRoleFromToken(token);
    }

    @Before("@annotation(RecruitmentRoleCheck)")
    public void recruitmentUserCheck() {
        String userRole = getUserRole();
        if (!UserRoleEnum.BOOTCAMP.name().equals(userRole) && !UserRoleEnum.ADMIN.name().equals(userRole)) {
            throw new CustomException(ErrorCode.NOT_AUTHENTICATED_USER);
        }
    }

    @Before("@annotation(AdminRoleCheck)")
    public void adminUserCheck() {
        String userRole = getUserRole();
        if (!UserRoleEnum.ADMIN.name().equals(userRole)) {
            throw new CustomException(ErrorCode.NOT_AUTHENTICATED_ADMIN);
        }
    }

    @Before("@annotation(ReviewRoleCheck) || @annotation(StudyRoleCheck)")
    public void reviewAndStudyUserCheck() {
        String userRole = getUserRole();
        if (!UserRoleEnum.USER.name().equals(userRole) && !UserRoleEnum.ADMIN.name().equals(userRole)) {
            throw new CustomException(ErrorCode.NOT_AUTHENTICATED_BOOTCAMP);
        }
    }
}
