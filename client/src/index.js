/* eslint-disable no-undef */
import React from 'react';
import ReactDOM from 'react-dom/client';
import AppRocKetCV from './AppRocketCV';
import { RecoilRoot } from 'recoil';

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
  <RecoilRoot>
    <AppRocKetCV />
  </RecoilRoot>,
);
