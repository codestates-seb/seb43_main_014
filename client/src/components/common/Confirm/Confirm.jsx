import React from 'react';
import styles from './Confirm.module.css';

export default function Confirm({ children, setIsOpenConfirm, handleLogout }) {
  const handleCloseConfirm = () => {
    setIsOpenConfirm(false);
  };

  const handleClickConfirm = (e) => {
    e.stopPropagation();
  };

  return (
    <div className={styles.modalContainer}>
      <div className={styles.modalBackdrop} onClick={handleCloseConfirm}>
        <div className={styles.modalView} onClick={handleClickConfirm}>
          {children}
          <div className={styles.buttons}>
            <button onClick={handleLogout}>예</button>
            <button onClick={handleCloseConfirm}>아니오</button>
          </div>
        </div>
      </div>
    </div>
  );
}
