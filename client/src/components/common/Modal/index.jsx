import React from 'react';
import styles from './modal.module.css';

const Modal = ({ children, openModalHandler }) => {
  return (
    <div className={styles.modalContainer}>
      <div className={styles.modalBackdrop} onClick={openModalHandler}>
        <div className={styles.modalView} onClick={(e) => e.stopPropagation()}>
          {children}
        </div>
      </div>
    </div>
  );
};

export default Modal;
