export function extractAuth(url) {
  const queryString = url.split('?')[1];
  const params = new URLSearchParams(queryString);
  const accessTokenParam = params.get('accessToken');
  const refreshTokenParam = params.get('refreshToken');
  const userIdParam = params.get('userId');
  const userNameParam = params.get('name');

  if (accessTokenParam) {
    // 액세스 토큰에서 'Bearer '를 제거한 값을 반환
    return [
      accessTokenParam.replace('Bearer ', ''),
      refreshTokenParam.replace('Bearer ', ''),
      userIdParam,
      decodeURI(userNameParam),
    ];
  }
}
