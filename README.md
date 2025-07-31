# 소개

- SSO(Single Sign-On) 시스템에 등록 하기 위한 리소스 서버 템플릿입니다.
# 인증

- 본 리소스 서버는 **인증 서버**로부터 전달받은 **JWT** Access Token을 검증하여 사용자를 인증합니다.
- Access Token 만료 시, Refresh Token으로 새로운 Access Token, Refresh Token을 발급 받을 수 있습니다.

### ✅ 검증 항목

- **서명 검증** : `.env`에 설정된 `JWT_SECRET` 키를 사용하여 JWT 서명을 확인합니다.
- **만료 여부 확인** : 토큰의 만료 여부를 확인합니다.
- **역할(Role) 확인** *(선택)* : 토큰의 클레임에 포함된 역할에 따라 접근 권한을 제한할 수 있습니다.

# 환경 설정 (`.env`)

`.env` 파일을 루트 디렉토리에 생성하고 다음과 같이 환경변수를 설정합니다:

```env
# JWT 서명용 비밀 키
JWT_SECRET=your_jwt_secret
```
⚠️ .env 파일은 .gitignore에 포함시켜 버전 관리에서 제외하는 것을 권장합니다.
