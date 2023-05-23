import React from 'react';
import styles from './cvList.module.css';

const CvList = ({ cv }) => {
  const { title, developmentJob, createdAt } = cv;

  return (
    <div className={styles.cvCard}>
      <div>
        <h4>{title}</h4>
        <div className={styles.cvJob}>{developmentJob}</div>
      </div>
      <div className={styles.cvDate}>{createdAt}</div>
    </div>
  );
};

export default CvList;
