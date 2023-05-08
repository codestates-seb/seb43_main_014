import { atom, selector } from 'recoil';

// JWT 토큰을 저장하는 역할
export const TokenAtom = atom({
  key: 'TokenAtom', // key는 전역적으로 유일해야한 값이여야 한다.
  default: undefined,
});

export const isLoginSelector = selector({
  key: 'isLoginSelector',
  get: ({ get }) => !!get(TokenAtom),
});
