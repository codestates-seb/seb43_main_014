import { atom } from 'recoil';

// JWT 토큰을 저장하는 역할
export const tokenState = atom({
  key: 'tokenState', // key는 전역적으로 유일해야한 값이여야 한다.
  default: null,
});

export const isLoginState = atom({
  key: 'isLoginState',
  default: false,
});

export const userState = atom({
  key: 'userState',
  default: null,
});
