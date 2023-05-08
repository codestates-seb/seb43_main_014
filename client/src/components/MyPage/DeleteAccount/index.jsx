import React from 'react';
import styles from './deleteAccount.module.css';
import DeleteModal from './DeleteModal';

const DeleteAccount = () => {
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
          <DeleteModal />
        </div>
      </div>
    </>
  );
};

export default DeleteAccount;
