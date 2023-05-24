import React, { useState } from 'react';
import styles from './deleteAccount.module.css';
import DeleteModal from './DeleteModal';

const DeleteAccount = () => {
  const [isOpen, setIsOpen] = useState(false);
  const openModalHandler = () => {
    setIsOpen(!isOpen);
  };
  return (
    <>
      <div className={styles.container}>
        <div className={styles.date}>
          <div>
            <span className={styles.delete}>
              계정 삭제 시 프로필 및 작성한 이력서 정보가 삭제 됩니다.
            </span>
          </div>
        </div>
        <div>
          <button className={styles.modalBtn} onClick={openModalHandler}>
            삭제하기
          </button>
          {isOpen ? <DeleteModal openModalHandler={openModalHandler} /> : null}
        </div>
      </div>
    </>
  );
};

export default DeleteAccount;
