import { atom } from 'recoil';

export const IsLoginAtom = atom({
  key: 'IsLoginAtom', // 전역적으로 유일해야한 값이여야 한다.
  default: false,
});
