import React from 'react';
import styles from './Alert.module.css';
import CloseIcon from '@mui/icons-material/Close';
import ErrorOutlineIcon from '@mui/icons-material/ErrorOutline';
import CheckCircleIcon from '@mui/icons-material/CheckCircle';

export default function Alert({ isSuccess, children, setShowAlert }) {
  const handleCloseAlert = () => {
    setShowAlert(false);
  };

  const handleClickAlert = (e) => {
    e.stopPropagation();
  };

  return (
    <div className={styles.alert_background} onClick={handleCloseAlert}>
      <div className={styles.alert} onClick={handleClickAlert}>
        <div className={styles.errorIcon}>
          {isSuccess ? (
            <CheckCircleIcon sx={{ fontSize: 150 }} />
          ) : (
            <ErrorOutlineIcon sx={{ fontSize: 150 }} />
          )}
        </div>
        <div className={styles.text}>{children}</div>
        <div className={styles.close} onClick={handleCloseAlert}>
          {!isSuccess && <CloseIcon sx={{ fontSize: 30, color: 'red' }} />}
        </div>
      </div>
    </div>
  );
}
