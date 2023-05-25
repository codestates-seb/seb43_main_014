import React, { useState } from 'react';
import styles from './pwChange.module.css';
import PwModal from './PwModal/PwModal';

const PwChange = ({ setUserData, modifiedAt }) => {
  const [isOpen, setIsOpen] = useState(false);
  const openModalHandler = () => {
    setIsOpen(!isOpen);
  };
  return (
    <div className={styles.container}>
      <div className={styles.date}>
        <div>
          <span>최근 업데이트 : </span>
          <span>{modifiedAt}</span>
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
            setUserData={setUserData}
          />
        ) : null}
      </div>
    </div>
  );
};

export default PwChange;
