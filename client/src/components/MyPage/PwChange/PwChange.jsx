import React, { useState } from 'react';
import styles from './pwChange.module.css';
import Btn from '../Btn/Btn';
import PwModal from './PwModal/PwModal';

const PwChange = () => {
  const date = '2023-05-05';
  const [newDate, setNewDate] = useState(date);

  return (
    <div className={styles.container}>
      <div className={styles.date}>
        <div>
          <span>최근 업데이트 : </span>
          <span>{newDate}</span>
        </div>
        <div>
          <span className={styles.pw}>비밀번호</span>
        </div>
      </div>
      <div>
        {/* <button className={styles.changeBtn}>비밀번호 변경</button> */}
        {/* <Btn btnName="비밀번호 변경"></Btn> */}
        <PwModal />
        {/* <button className={styles.changeBtn}></button> */}
      </div>
    </div>
  );
};

export default PwChange;
