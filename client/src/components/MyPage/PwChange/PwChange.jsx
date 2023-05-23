import React, { useState } from 'react';
import styles from './pwChange.module.css';
import PwModal from './PwModal/PwModal';

const PwChange = () => {
  const date = '2023-05-05';
  const [newDate, setNewDate] = useState(date);
  const [isOpen, setIsOpen] = useState(false);
  const openModalHandler = () => {
    setIsOpen(!isOpen);
  };
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
        <button className={styles.modalBtn} onClick={openModalHandler}>
          비밀번호 변경
        </button>
        {isOpen ? (
          <PwModal
            openModalHandler={openModalHandler}
            setNewDate={setNewDate}
          />
        ) : null}
      </div>
    </div>
  );
};

export default PwChange;
