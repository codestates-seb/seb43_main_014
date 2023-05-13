import React from 'react';
import styles from './Alert.module.css';
import CloseIcon from '@mui/icons-material/Close';
import ErrorOutlineIcon from '@mui/icons-material/ErrorOutline';

export default function Alert({ children, setShowAlert }) {
  const handleCloseAlert = () => {
    setShowAlert(false);
  };

  const handleBackGround = (e) => {
    e.stopPropagation();
  };

  return (
    <div className={styles.alert_background} onClick={handleCloseAlert}>
      <div className={styles.alert} onClick={handleBackGround}>
        <div className={styles.errorIcon}>
          <ErrorOutlineIcon sx={{ fontSize: 150 }} />
        </div>
        <div className={styles.text}>{children}</div>
        <div className={styles.close} onClick={handleCloseAlert}>
          <CloseIcon sx={{ fontSize: 40, color: 'red' }} />
        </div>
      </div>
    </div>
  );
}
