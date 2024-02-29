export function localStorageRemove() {
  localStorage.removeItem('jwt_token');
  localStorage.removeItem('refresh_token');
  localStorage.removeItem('user_info');
  localStorage.removeItem('isLogin');
}

export function localStorageGet() {
  const access_token = localStorage.getItem('jwt_token');
  const refresh_token = localStorage.getItem('refresh_token');
  const user_info = localStorage.getItem('user_info');
  const is_Login = localStorage.getItem('isLogin');

  return [access_token, refresh_token, user_info, is_Login];
}
