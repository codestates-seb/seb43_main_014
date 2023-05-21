export function extractAccessToken(url) {
  const queryString = url.split('?')[1];
  const params = new URLSearchParams(queryString);
  const accessTokenParam = params.get('accessToken');

  if (accessTokenParam) {
    // 액세스 토큰에서 'Bearer '를 제거한 값을 반환
    return accessTokenParam.replace('Bearer ', '');
  }

  return null; // 액세스 토큰이 없는 경우 null을 반환
}

export function extractUserInfo(url) {
  const queryString = url.split('?')[1];
  const params = new URLSearchParams(queryString);
  const accessTokenParam = params.get('accessToken');

  if (accessTokenParam) {
    // 액세스 토큰에서 'Bearer '를 제거한 값을 반환
    return accessTokenParam.replace('Bearer ', '');
  }

  return null; // 액세스 토큰이 없는 경우 null을 반환
}
