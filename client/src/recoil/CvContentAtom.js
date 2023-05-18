import { atom } from 'recoil';

export const CvContentAtom = atom({
  key: 'CvContentAtom', //전역적으로 유일한 값이어야 함
  default: [],
});
